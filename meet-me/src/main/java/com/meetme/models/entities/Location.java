package com.meetme.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {
    private String country = "Bulgaria";
    private String city;

    public Location() {
    }

    public Location(String country, String city) {
        this.city = city;
        this.country = country;
    }


    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Location location = (Location) o;
        return country.equals(location.country) &&
                city.equals(location.city);
    }

    @Override
    public int hashCode() {
        return country.hashCode() * 31 + city.hashCode() * 31;
    }
}
