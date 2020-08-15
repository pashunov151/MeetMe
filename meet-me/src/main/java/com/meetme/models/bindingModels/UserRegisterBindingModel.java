package com.meetme.models.bindingModels;

import com.meetme.models.entities.Details;
import com.meetme.models.entities.Location;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.time.LocalDate;

public class UserRegisterBindingModel {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private LocalDate born;
    private Location location;
    private Details details;

    public UserRegisterBindingModel() {
    }

    @Min(value = 3, message = "Username should be at least 3 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Min(value = 2, message = "First name should be at least 2 chars")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Min(value = 1, message = "Last name should not be empty")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Min(value = 3, message = "password is too short")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Min(value = 3, message = "password is too short")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
