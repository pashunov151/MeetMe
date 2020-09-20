package com.meetme.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.Objects;

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

    @Lob
    @Column(name = "image", nullable = false, length = 100000)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;

        return skinColor.equals(details.skinColor) &&
                hairColor.equals(details.hairColor) &&
                eyeColor.equals(details.eyeColor) &&
                height.equals(details.height) &&
                weight.equals(details.weight) &&
                gender == details.gender &&
                interestedIn == details.interestedIn &&
                Arrays.equals(picture, details.picture) &&
                email.equals(details.email);
    }

    @Override
    public int hashCode() {
        int result = skinColor.hashCode() + hairColor.hashCode() + eyeColor.hashCode() +
                Double.hashCode(height) + Double.hashCode(weight) + gender.hashCode() + interestedIn.hashCode()
                + email.hashCode();
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
