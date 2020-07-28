package com.meetme.models.serviceModels;

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
    private Details details;
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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

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

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

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
