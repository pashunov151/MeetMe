package com.meetme.services.impls;

import com.meetme.models.entities.Location;
import com.meetme.repositories.LocationRepository;
import com.meetme.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location findLocation(Location location) {
        return this.locationRepository.findByCityAndAndCountry(location.getCity(), location.getCountry())
                .orElse(null);
    }

    @Override
    public Location saveLocationInDb(Location location) {
        return this.locationRepository.saveAndFlush(location);
    }
}
