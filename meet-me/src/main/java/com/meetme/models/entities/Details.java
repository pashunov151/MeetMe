package com.meetme.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "details")
public class Details extends BaseEntity {
    private String skinColor;
    private String hairColor;
    private String eyeColor;
    private Double height;
    private Double weight;
    private Sex gender;
    private Sex interestedIn;
    private byte[] picture;
    private String email;


    public Details() {
    }

    @Column(name = "skin_color", nullable = false)
    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    @Column(name = "hair_color", nullable = false)
    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @Column(name = "eye_color", nullable = false)
    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    @Column(name = "height", nullable = false)
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Column(name = "weight", nullable = false)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Column(name = "sex", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    @Column(name = "interested_in")
    @Enumerated(value = EnumType.STRING)
    public Sex getInterestedIn() {
        return interestedIn;
    }

    public void setInterestedIn(Sex interestedIn) {
        this.interestedIn = interestedIn;
    }

    @Column(name = "image", nullable = false)
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] image) {
        this.picture = image;
    }

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = ".+@\\w+\\.\\w+", message = "enter a valid email address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
