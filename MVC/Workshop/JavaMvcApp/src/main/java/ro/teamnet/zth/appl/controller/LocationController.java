package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Location;
import ro.teamnet.zth.appl.service.impl.LocationServiceImpl;

import java.util.List;

/**
 * Created by cizuss94 on 7/15/2016.
=======

/**
 * Created by user on 7/14/2016.
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
 */
@MyController(urlPath = "/locations")
public class LocationController {
    @MyRequestMethod(urlPath = "/all")
    public List<Location> getAllLocations() {
        return new LocationServiceImpl().findAllLocations();
    }
    @MyRequestMethod(urlPath = "/one")
    public Location getOneLocation(@MyRequestParam(name = "id") Long locationId) {
        return new LocationServiceImpl().findOneLocation(locationId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "DELETE")
    public void deleteOneLocation(@MyRequestParam(name = "id") Long locationId) {
        new LocationServiceImpl().deleteOneLocation(locationId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "POST")
    public void saveOneLocation(@MyRequestParam(name = "streetaddress") String streetAddress,
                                @MyRequestParam(name = "postalcode") String postalCode,
                                @MyRequestParam(name = "city") String city,
                                @MyRequestParam(name = "stateprovince") String stateProvince)
    {
            new LocationServiceImpl().saveOneLocation(streetAddress, postalCode, city, stateProvince);
    }
}
