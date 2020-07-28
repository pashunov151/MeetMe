package com.meetme.models.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "people")
public class Person extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Details details;
    private LocalDate born;
    private Location location;
    private List<Person> friends;
    private List<Person> friendOf;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Person() {
    }

    @Column(name = "username", nullable = false, updatable = false)
    @Length(min = 3, message = "Username should be at least 3 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "first_name", nullable = false)
    @Length(min = 2, message = "First name should be at least 2 chars")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @Length(min = 1, message = "Last name should not be empty")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "password", nullable = false)
    @Length(min = 3, message = "Password is too short")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "born", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "You should be at least 18y/o")
    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    @ManyToOne(targetEntity = Details.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    @ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "friend_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    public List<Person> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(List<Person> friendOf) {
        this.friendOf = friendOf;
    }

    @Column(name = "created", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "updated")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
