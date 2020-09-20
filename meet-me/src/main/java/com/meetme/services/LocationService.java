package com.meetme.services;

import com.meetme.models.entities.Location;

public interface LocationService {
    Location findLocation(Location location);
    Location saveLocationInDb(Location location);
}
