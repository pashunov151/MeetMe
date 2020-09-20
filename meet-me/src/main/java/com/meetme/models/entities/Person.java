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
import java.util.Objects;

@Entity
@Table(name = "people")
public class Person extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<Role> roles;
    private Details details;
    private LocalDate born;
    private Location location;
    private List<Person> friends;
    private List<Person> friendOf;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Person() {
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id")
    public List<Role> getRoles() {
        return roles;
    }

    public Person setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    @Column(name = "username", nullable = false, updatable = false)
    @Length(min = 3, message = "Username should be at least 3 symbols")
    public String getUsername() {
        return username;
    }

    public Person setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "first_name", nullable = false)
    @Length(min = 2, message = "First name should be at least 2 chars")
    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "last_name", nullable = false)
    @Length(min = 1, message = "Last name should not be empty")
    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(name = "password", nullable = false)
    @Length(min = 3, message = "Password is too short")
    public String getPassword() {
        return password;
    }

    public Person setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "born", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "You should be at least 18y/o")
    public LocalDate getBorn() {
        return born;
    }

    public Person setBorn(LocalDate born) {
        this.born = born;
        return this;
    }

    @ManyToOne(targetEntity = Details.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    public Details getDetails() {
        return details;
    }

    public Person setDetails(Details details) {
        this.details = details;
        return this;
    }

    @ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    public Location getLocation() {
        return location;
    }

    public Person setLocation(Location location) {
        this.location = location;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    public List<Person> getFriends() {
        return friends;
    }

    public Person setFriends(List<Person> friends) {
        this.friends = friends;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "friend_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    public List<Person> getFriendOf() {
        return friendOf;
    }

    public Person setFriendOf(List<Person> friendOf) {
        this.friendOf = friendOf;
        return this;
    }

    @Column(name = "created", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    public LocalDateTime getCreated() {
        return created;
    }

    public Person setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    @Column(name = "updated")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    public LocalDateTime getUpdated() {
        return updated;
    }

    public Person setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return username.equals(person.username) &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                password.equals(person.password) &&
                roles.equals(person.roles) &&
                details.equals(person.details) &&
                born.equals(person.born) &&
                location.equals(person.location) &&
                friends.equals(person.friends) &&
                friendOf.equals(person.friendOf) &&
                created.equals(person.created) &&
                updated.equals(person.updated);
    }

    @Override
    public int hashCode() {
        return username.hashCode() + firstName.hashCode() + lastName.hashCode() +
                password.hashCode() + List.of(roles).hashCode() + details.hashCode() +
                born.hashCode() + location.hashCode() + List.of(friends).hashCode() +
                List.of(friendOf).hashCode() + created.hashCode() + updated.hashCode();
    }
}
