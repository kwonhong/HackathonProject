package com.hackathonproject.Search;

import com.hackathonproject.User.Location;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class SearchResult {
    public SearchResult() {
    }

    public SearchResult(String name, double distance, Location location, String phoneNumber, String address, String entTypeID, String entID) {
        this.name = name;
        this.distance = distance;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.entTypeID = entTypeID;
        this.entID = entID;
    }

    private String name;
    private double distance;
    private Location location;
    private String phoneNumber;
    private String address;
    private String entTypeID;
    private String entID;
    private String imgUrl;
    private boolean isClosed;
    private String rating;
    private String contactInfo;

}

