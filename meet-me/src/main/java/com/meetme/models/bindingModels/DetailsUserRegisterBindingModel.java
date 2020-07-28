package com.meetme.models.bindingModels;

import com.meetme.models.entities.Sex;

public class DetailsUserRegisterBindingModel {
    private String skinColor;
    private String hairColor;
    private String eyeColor;
    private Double height;
    private Double weight;
    private Sex gender;
    private Sex interestedIn;
    private byte[] picture;
    private String email;

    public DetailsUserRegisterBindingModel() {
    }

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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
