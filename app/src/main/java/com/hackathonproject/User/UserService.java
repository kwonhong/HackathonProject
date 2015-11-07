package com.hackathonproject.User;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/**
 * Created by james on 15-11-07.
 */
public class UserService {

    public static User selectedUser;

    // TODO get User information
    public User getCurrentUserInformation() {
        User currentUser = new User();

        // TODO Pull location information from user.

        return currentUser;
    }

    public List<User> getDefaultUser() {
        return Arrays.asList(
                new User("TestingUser", new Location(), "aksjdhf", User.LoginType.FOLLOWING.getTypeKey()),
                new User("TestingUser2", new Location(), "aksjdhf", User.LoginType.USER.getTypeKey()));
    }
}
