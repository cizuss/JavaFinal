package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
public interface LocationService {
    List<Location> findAllLocations();
    Location findOneLocation(Long id);
    void deleteOneLocation(Long id);
    void saveOneLocation(String streetAddress, String postalCode, String city, String stateProvince);
}
