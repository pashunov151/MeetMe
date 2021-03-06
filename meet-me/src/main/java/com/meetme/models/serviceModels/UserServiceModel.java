package com.meetme.models.serviceModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetme.models.entities.Details;
import com.meetme.models.entities.Location;
import com.meetme.models.entities.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserServiceModel {
    private String username;
    private String firstName;
    private String lastName;
    private DetailsServiceModel details;
    private LocalDate born;
    private Location location;
    private List<Person> friends;
    private List<Person> friendOf;
    private LocalDateTime created;
    private LocalDateTime updated;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public DetailsServiceModel getDetails() {
        return details;
    }

    public void setDetails(DetailsServiceModel details) {
        this.details = details;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    @JsonIgnore
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonIgnore
    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    @JsonIgnore
    public List<Person> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(List<Person> friendOf) {
        this.friendOf = friendOf;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
