package com.meetme.services;

import com.meetme.models.bindingModels.UserRegisterBindingModel;
import com.meetme.models.entities.Person;
import com.meetme.models.serviceModels.UserServiceModel;

public interface PersonService {
    UserRegisterBindingModel registerUser(UserRegisterBindingModel user);

    UserServiceModel findExistingUsersByEmail(String email);

    public Person getOrCreateUser(String email);
}
