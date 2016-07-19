package ro.teamnet.zth.appl.service.impl;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;
import ro.teamnet.zth.appl.service.LocationService;

import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
@MyService
public class LocationServiceImpl implements LocationService {
    @Override
    public List<Location> findAllLocations() {
        return new LocationDao().getAllLocations();
    }

    @Override
    public Location findOneLocation(Long id) {
        return new LocationDao().getLocationById(id);
    }

    @Override
    public void deleteOneLocation(Long id) {
        new LocationDao().deleteLocation(new LocationDao().getLocationById(id));
    }

    @Override
    public void saveOneLocation(String streetAddress, String postalCode, String city, String stateProvince) {
        Location newLocation = new Location();
        newLocation.setStreetAddress(streetAddress);
        newLocation.setPostalCode(postalCode);
        newLocation.setCity(city);
        newLocation.setStateProvince(stateProvince);
        new LocationDao().insertLocation(newLocation);
    }
}
