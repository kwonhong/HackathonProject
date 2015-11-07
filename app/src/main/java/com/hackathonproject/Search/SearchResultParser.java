package com.hackathonproject.Search;

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
        for(int i = 0; i < 10; i++){
//            try {
//                String name = jsonObject.get("d").get("results")[i].get("Name"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            searchResult.entID = obj.get("d").get("results")[i].get("EntityID");
//            searchResult.entTypeID = obj.get("d").get("results")[i].get("EntityTypeID");
//            searchResult.address = obj.get("d").get("results")[i].get("AddressLine") + ", " + obj.get("d").get("results")[i].get("Locality") + ", " + obj.get("d").get("results")[i].get("AdminDistrict2") + ", " + obj.get("d").get("results")[i].get("AdminDistrict") + ", " + obj.get("d").get("results")[i].get("PostalCode");
//            searchResult.latitude = obj.get("d").get("results")[i].get("EntityTypeID");
//            searchResult.longitude = obj.get("d").get("results")[i].get("EntityTypeID");
//            searchResult.phonenumber = obj.get("d").get("results")[i].get("EntityTypeID");
//
//            searchResults.add(searchResult);
        }
        return searchResults;

    }

//    List<SearchResult> getRecommendations(JSONObject obj){
//        List<SearchResult> recomms = new ArrayList<SearchResult>();

//    }
}
