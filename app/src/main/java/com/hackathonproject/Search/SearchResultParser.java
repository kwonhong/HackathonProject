package com.hackathonproject.Search;

import com.hackathonproject.User.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 15-11-07.
 */
public class SearchResultParser {

    public static List<SearchResult> getSearchResult(JSONObject jsonObject) {
        List<SearchResult> searchResults = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONObject("d").getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject tempJsonObject = jsonArray.getJSONObject(i);
                String name = tempJsonObject.getString("Name");
                String entityID = tempJsonObject.getString("EntityID");
                String entityTypeID = tempJsonObject.getString("EntityTypeID");
                String address = tempJsonObject.getString("AddressLine") + ", "
                        + tempJsonObject.getString("Locality") + ", "
                        + tempJsonObject.getString("AdminDistrict2") + ", "
                        + tempJsonObject.getString("AdminDistrict") + ", "
                        + tempJsonObject.getString("PostalCode");
                double longitude = tempJsonObject.getDouble("Longitude");
                double latitude = tempJsonObject.getDouble("Latitude");
                double distance = tempJsonObject.getDouble("__Distance");
                String phoneNumber = tempJsonObject.getString("Phone");

                SearchResult searchResult =
                        new SearchResult(
                                name,
                                distance,
                                new Location(latitude, longitude),
                                phoneNumber,
                                address,
                                entityTypeID,
                                entityID);

                searchResults.add(searchResult);
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return searchResults;
    }

//    List<SearchResult> getRecommendations(JSONObject obj){
//        List<SearchResult> recomms = new ArrayList<SearchResult>();

//    }
}
