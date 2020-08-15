package com.meetme.services.impls;

import com.meetme.models.bindingModels.UserRegisterBindingModel;
import com.meetme.models.entities.Person;
import com.meetme.models.serviceModels.UserServiceModel;
import com.meetme.repositories.PersonRepository;
import com.meetme.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRegisterBindingModel registerUser(UserRegisterBindingModel user) {
        if (!user.getPassword().equals(user.getConfirmPassword()) ||
                this.personRepository.findByUsername(user.getUsername()) != null) {
            return null;
        }
        try {
            user.getLocation().setCountry("Bulgaria");
            Person map = this.modelMapper.map(user, Person.class);
            map.setPassword(this.passwordEncoder.encode(user.getPassword()));
            map.setCreated(LocalDateTime.now());
            map.setUpdated(LocalDateTime.now());
            this.personRepository.saveAndFlush(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            user = null;
        }
        return user;
    }

    @Override
    public UserServiceModel findExistingUsersByEmail(String email) {
        UserServiceModel map;
        try {
            map = this.modelMapper.map(this.personRepository.findByEmail(email), UserServiceModel.class);
        }catch (Exception e){
            map = null;
        }
            return map;
    }
}
