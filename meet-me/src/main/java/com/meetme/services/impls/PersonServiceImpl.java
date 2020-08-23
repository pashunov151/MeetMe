package com.meetme.services.impls;

import com.meetme.models.bindingModels.UserRegisterBindingModel;
import com.meetme.models.entities.Details;
import com.meetme.models.entities.Person;
import com.meetme.models.entities.Role;
import com.meetme.models.serviceModels.DetailsServiceModel;
import com.meetme.models.serviceModels.UserServiceModel;
import com.meetme.repositories.UserRepository;
import com.meetme.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @CacheEvict(value = "cachedUsers", allEntries = true)
    @Override
    public UserRegisterBindingModel registerUser(UserRegisterBindingModel user, MultipartFile file) {
        if (!user.getPassword().equals(user.getConfirmPassword()) ||
                this.userRepository.findByUsername(user.getUsername()) != null) {
            return null;
        }
        try {
            user.getLocation().setCountry("Bulgaria");
            Person map = this.modelMapper.map(user, Person.class);
            map.setPassword(this.passwordEncoder.encode(user.getPassword()));
            map.setCreated(LocalDateTime.now());
            map.setUpdated(LocalDateTime.now());
            List<Role> roles = new ArrayList<>();
            if (this.userRepository.count() == 0) {
                roles.add(new Role().setRole("ROLE_ADMIN"));
            }
            roles.add(new Role().setRole("ROLE_USER"));
            map.setRoles(roles);
            map.getDetails().setPicture(file.getBytes());
            this.userRepository.saveAndFlush(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            user = null;
        }
        return user;
    }

    @Cacheable(value = "cachedUsers")
    @Override
    public UserServiceModel findExistingUsersByEmail(String email) {
        System.out.println("cached");
        UserServiceModel map;
        try {
            Person byEmail = this.userRepository.findByEmail(email);
            map = this.modelMapper.map(byEmail, UserServiceModel.class);
            DetailsServiceModel detailsServiceModel = this.modelMapper.map(byEmail.getDetails(), DetailsServiceModel.class);
            byte[] encode = Base64.getEncoder().encode(byEmail.getDetails().getPicture());
            String base64Encoded = new String(encode, "UTF-8");
            detailsServiceModel.setPicture(base64Encoded);
            map.setDetails(detailsServiceModel);
        } catch (Exception e) {
            map = null;
        }
        return map;
    }

    @Override
    public Person getOrCreateUser(String email) {
        Objects.requireNonNull(email);
        Optional<Person> userEntityOpt =
                userRepository.findOneByEmail(email);
        return userEntityOpt.
                orElseGet(() -> createUser(email));
    }

    private Person createUser(String email) {
        return createUser(email, null);
    }

    private Person createUser(String email, String password) {
        Person userEntity = new Person();
        Details details = new Details();
        userEntity.setDetails(details);
        userEntity.getDetails().setEmail(email);
        if (password != null) {
            userEntity.setPassword(passwordEncoder.encode(password));
        }

        Role userRole = new Role();
        userRole.setRole("ROLE_USER");

        userEntity.setRoles(List.of(userRole));

        return userRepository.save(userEntity);
    }
}