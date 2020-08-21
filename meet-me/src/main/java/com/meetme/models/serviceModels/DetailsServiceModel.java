package com.meetme.models.serviceModels;

import com.meetme.models.entities.Sex;

import java.util.Base64;

public class DetailsServiceModel {
    private String skinColor;
    private String hairColor;
    private String eyeColor;
    private Double height;
    private Double weight;
    private Sex gender;
    private Sex interestedIn;
    private String picture;
    private String email;

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public Sex getInterestedIn() {
        return interestedIn;
    }

    public void setInterestedIn(Sex interestedIn) {
        this.interestedIn = interestedIn;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
