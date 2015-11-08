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
        SearchResult searchResult = null;
        try {
            JSONArray jsonArray = jsonObject.getJSONObject("d").getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                searchResult = new SearchResult();
                JSONObject tempJsonObject = jsonArray.getJSONObject(i);
                searchResult.setName(tempJsonObject.getString("Name"));
                searchResult.setEntID(tempJsonObject.getString("EntityID"));
                searchResult.setEntTypeID(tempJsonObject.getString("EntityTypeID"));
                String address = tempJsonObject.getString("AddressLine") + ", "
                        + tempJsonObject.getString("Locality") + ", "
                        + tempJsonObject.getString("AdminDistrict2") + ", "
                        + tempJsonObject.getString("AdminDistrict") + ", "
                        + tempJsonObject.getString("PostalCode");
                searchResult.setAddress(address);
                double longitude = tempJsonObject.getDouble("Longitude");
                double latitude = tempJsonObject.getDouble("Latitude");
                searchResult.setLocation(new Location(latitude, longitude));
                String phoneNumber = tempJsonObject.getString("Phone");
                searchResult.setPhoneNumber(phoneNumber);
                double distance = tempJsonObject.getDouble("__Distance");
                searchResult.setDistance(distance);

                searchResults.add(searchResult);
            }

        } catch (JSONException e1) {
            searchResults.add(searchResult);
        }

        return searchResults;
    }

//    List<SearchResult> getRecommendations(JSONObject obj){
//        List<SearchResult> recomms = new ArrayList<SearchResult>();

//    }
}
