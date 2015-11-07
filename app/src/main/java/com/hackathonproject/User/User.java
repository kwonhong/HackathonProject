package com.hackathonproject.User;


import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class User extends SugarRecord<User>{
    @Ignore
    private LocationService locationService = new LocationService();

    public User(String userName, Location location, String userKey, int loggedInType) {
        this.userKey = userKey;
        this.userName = userName;
        this.location = location;
        this.loggedInType = loggedInType;
    }

    public User() {
        // Default Constructor for sugarRecord.
    }

    private String userName;
    private Location location;
    private String userKey;
    private int loggedInType;

    @Ignore
    public enum LoginType {
        USER(0), FOLLOWING(1);

        @Getter private int typeKey;
        LoginType(int typeKey) {
            this.typeKey = typeKey;
        }

        public static LoginType getLoginType(int key) {
           return values()[key];
        }
    }

    public boolean hasLocationChanged() {
        // TODO getUser location information from database

        // TODO get user currentLocation with API

        // TODO Compare those two location
        return false;
    }
}
