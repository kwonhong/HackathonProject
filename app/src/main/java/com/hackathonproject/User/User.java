package com.hackathonproject.User;


import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import lombok.Data;

@Data
public class User extends SugarRecord<User>{
    @Ignore
    private LocationService locationService = new LocationService();

    public User() {
        // Default Constructor for sugarRecord.
    }

    private String userName;
    private Location location;
    private String userKey;

    public boolean hasLocationChanged() {
        // TODO getUser location information from database

        // TODO get user currentLocation with API

        // TODO Compare those two location
        return false;
    }
}
