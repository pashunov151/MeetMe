package com.meetme.services;

import com.meetme.models.bindingModels.UserRegisterBindingModel;
import com.meetme.models.entities.Person;
import com.meetme.models.serviceModels.UserServiceModel;
import org.springframework.web.multipart.MultipartFile;

public interface PersonService {
    UserRegisterBindingModel registerUser(UserRegisterBindingModel user, MultipartFile file);

    UserServiceModel findExistingUsersByEmail(String email);

    public Person getOrCreateUser(String email);
}
