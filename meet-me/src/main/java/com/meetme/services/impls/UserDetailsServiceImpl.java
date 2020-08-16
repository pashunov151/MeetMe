package com.meetme.services.impls;

import com.meetme.models.entities.Person;
import com.meetme.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> byEmail = this.userRepository.findOneByEmail(username);
        return byEmail.
                map(this::map).
                orElseThrow(() -> new IllegalArgumentException("No such user " + username));
    }

    private User map(Person user) {
        List<GrantedAuthority> authorities = user.getRoles().
                stream().
                map(r -> new SimpleGrantedAuthority(r.getRole())).
                collect(Collectors.toList());
        String email = user.getDetails().getEmail();
        String password = user.getPassword();
        User result = new User(email, password, authorities);
        if (user.getPassword() == null) {
            result.eraseCredentials();
        }
        return result;
    }
}
