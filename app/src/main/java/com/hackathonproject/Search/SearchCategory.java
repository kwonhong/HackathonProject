package com.hackathonproject.Search;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by james on 15-11-07.
 */
public class SearchCategory {
    public static final List<String> categoryList = Arrays.asList(
            "Winery", "ATM", "Train Station", "Commuter Rail Station", "Bus Station", "Named Place", "Ferry Terminal", "Marina", "Public Sports Airport", "Airport", "Business Facility", "Grocery Store", "Auto Dealerships", "Auto Dealership-Used Cars", "Petrol/Gasoline Station", "Motorcycle Dealership", "Restaurant", "Nightlife", "Historical Monument", "Bank", "Shopping", "Hotel", "Ski Resort", "Other Accommodation", "Ski Lift", "Tourist Information", "Rental Car Agency", "Parking Lot", "Parking Garage/House", "Park & Ride", "Auto Service & Maintenance", "Cinema", "Rest Area", "Performing Arts", "Bowling Centre", "Sports Complex", "Park/Recreation Area", "Casino", "Convention/Exhibition Centre", "Golf Course", "Civic/Community Centre", "Amusement Park", "Sports Centre", "Ice Skating Rink", "Tourist Attraction", "Hospital", "Higher Education", "School", "Library", "Museum", "Automobile Club", "City Hall", "Court House", "Police Station", "Campground", "Truck Stop/Plaza", "Government Office", "Post Office", "Convenience Store", "Clothing Store", "Department Store", "Home Specialty Store", "Pharmacy", "Specialty Store", "Sporting Goods Store", "Medical Service", "Residential Area/Building", "Cemetery", "Highway Exit", "Transportation Service", "Weigh Station", "Cargo Centre", "Military Base", "Animal Park", "Truck Dealership", "Home Improvement & Hardware Store", "Consumer Electronics Store", "Office Supply & Services Store", "Industrial Zone", "Place of Worship", "Embassy", "County Council", "Bookstore", "Coffee Shop", "Hamlet", "Border Crossing"
    );

    public static final ImmutableMap<String, Integer> categoryToEntityMap =
            new ImmutableMap.Builder<String, Integer>()
                    .put("Winery", 2084)
                    .put("ATM", 3578)
                    .put("Train Station", 4013)
                    .put("Commuter Rail Station", 4100)
                    .put("Bus Station", 4170)
                    .put("Named Place", 4444)
                    .put("Ferry Terminal", 4482)
                    .put("Marina", 4493)
                    .put("Public Sports Airport", 4580)
                    .put("Airport", 4581)
                    .put("Business Facility", 5000)
                    .put("Grocery Store", 5400)
                    .put("Auto Dealerships", 5511)
                    .put("Auto Dealership-Used Cars", 5512)
                    .put("Petrol/Gasoline Station", 5540)
                    .put("Motorcycle Dealership", 5571)
                    .put("Restaurant", 5800)
                    .put("Nightlife", 5813)
                    .put("Historical Monument", 5999)
                    .put("Bank", 6000)
                    .put("Shopping", 6512)
                    .put("Hotel", 7011)
                    .put("Ski Resort", 7012)
                    .put("Other Accommodation", 7013)
                    .put("Ski Lift", 7014)
                    .put("Tourist Information", 7389)
                    .put("Rental Car Agency", 7510)
                    .put("Parking Lot", 7520)
                    .put("Parking Garage/House", 7521)
                    .put("Park & Ride", 7522)
                    .put("Auto Service & Maintenance", 7538)
                    .put("Cinema", 7832)
                    .put("Rest Area", 7897)
                    .put("Performing Arts", 7929)
                    .put("Bowling Centre", 7933)
                    .put("Sports Complex", 7940)
                    .put("Park/Recreation Area", 7947)
                    .put("Casino", 7985)
                    .put("Convention/Exhibition Centre", 7990)
                    .put("Golf Course", 7992)
                    .put("Civic/Community Centre", 7994)
                    .put("Amusement Park", 7996)
                    .put("Sports Centre", 7997)
                    .put("Ice Skating Rink", 7998)
                    .put("Tourist Attraction", 7999)
                    .put("Hospital", 8060)
                    .put("Higher Education", 8200)
                    .put("School", 8211)
                    .put("Library", 8231)
                    .put("Museum", 8410)
                    .put("Automobile Club", 8699)
                    .put("City Hall", 9121)
                    .put("Court House", 9211)
                    .put("Police Station", 9221)
                    .put("Campground", 9517)
                    .put("Truck Stop/Plaza", 9522)
                    .put("Government Office", 9525)
                    .put("Post Office", 9530)
                    .put("Convenience Store", 9535)
                    .put("Clothing Store", 9537)
                    .put("Department Store", 9545)
                    .put("Home Specialty Store", 9560)
                    .put("Pharmacy", 9565)
                    .put("Specialty Store", 9567)
                    .put("Sporting Goods Store", 9568)
                    .put("Medical Service", 9583)
                    .put("Residential Area/Building", 9590)
                    .put("Cemetery", 9591)
                    .put("Highway Exit", 9592)
                    .put("Transportation Service", 9593)
                    .put("Weigh Station", 9710)
                    .put("Cargo Centre", 9714)
                    .put("Military Base", 9715)
                    .put("Animal Park", 9718)
                    .put("Truck Dealership", 9719)
                    .put("Home Improvement & Hardware Store", 9986)
                    .put("Consumer Electronics Store", 9987)
                    .put("Office Supply & Services Store", 9988)
                    .put("Industrial Zone", 9991)
                    .put("Place of Worship", 9992)
                    .put("Embassy", 9993)
                    .put("County Council", 9994)
                    .put("Bookstore", 9995)
                    .put("Coffee Shop", 9996)
                    .put("Hamlet", 9998)
                    .put("Border Crossing", 9999)
                    .build();


}
