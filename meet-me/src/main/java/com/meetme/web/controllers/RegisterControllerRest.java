package com.meetme.web.controllers;

import com.meetme.models.serviceModels.UserServiceModel;
import com.meetme.services.PersonService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class RegisterControllerRest {
    private final PersonService personService;

    public RegisterControllerRest(PersonService personService) {
        this.personService = personService;
    }

    //    @GetMapping("/users/{email}")
//    public UserServiceModel getUser(@PathVariable(name = "email") String email) {
//        UserServiceModel existingUsersByEmail = this.personService.findExistingUsersByEmail(email);
//        if (existingUsersByEmail != null) {
//            return existingUsersByEmail;
//        }
//        return new UserServiceModel();
//    }

    @Cacheable(value = "cachedUsers")
    @GetMapping("/users/{email}")
    public ResponseEntity<UserServiceModel> getUser(@PathVariable(name = "email") String email) {
        UserServiceModel existingUsersByEmail = this.personService.findExistingUsersByEmail(email);
        if (existingUsersByEmail != null) {
            return ResponseEntity.ok().body(existingUsersByEmail);
        } else {
            return ResponseEntity.status(204).build();
        }
    }

}
