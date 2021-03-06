package com.hackathonproject.Search;

import com.beust.jcommander.JCommander;
import com.hackathonproject.User.Location;
import com.hackathonproject.YelpApi.YelpAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by james on 15-11-07.
 */
public class SearchService {

    private String firstLocation = "yale University";

    public List<SearchResult> getSearchResults(String query) {

        if (!SearchCategory.categoryToEntityMap.containsKey(query)) {
            throw new IllegalArgumentException();
        }


        Integer entityTypeID = SearchCategory.categoryToEntityMap.get(query);
        try {
            QueryOption qo = new QueryOption();
            qo.setAtitude(40.83274904439099);
            qo.setLongitude(-74.3163299560546935);
            qo.setDistance(5.0);
            qo.setEntityTypeId(entityTypeID);
            qo.setTop(3);

            // Parse Data Received
            String queryResult = query(qo);
            JSONObject jsonObject = new JSONObject(queryResult);
            return SearchResultParser.getSearchResult(jsonObject);

        } catch (Exception je) {
            System.out.println(je.toString());
        }

        // TODO Get Search Result & Populate
        return Collections.emptyList();
    }

    public List<SearchResult> getSearchResultWithTypeId(int entityTypeID) {
        try {
            QueryOption qo = new QueryOption();
            qo.setAtitude(40.83274904439099);
            qo.setLongitude(-74.3163299560546935);
            qo.setDistance(5.0);
            qo.setEntityTypeId(entityTypeID);
            qo.setTop(3);

            // Parse Data Received
            String queryResult = query(qo);
            JSONObject jsonObject = new JSONObject(queryResult);
            return SearchResultParser.getSearchResult(jsonObject);

        } catch (Exception je) {
            System.out.println(je.toString());
        }

        // TODO Get Search Result & Populate
        return Collections.emptyList();

    }


    public SearchResult getSearchResult(int entityID) {
        try {
            QueryOption qo = new QueryOption();
            qo.setEntityId(entityID);

            // Parse Data Received
            String queryResult = query(qo);
            JSONObject jsonObject = new JSONObject(queryResult);
            List<SearchResult> list = SearchResultParser.getSearchResult(jsonObject);
            if (!list.isEmpty()) {
                return list.get(0);
            }

        } catch (Exception je) {
            System.out.println(je.toString());
        }

        return null;
    }

    public void populateAutoCompleteStrings(List<String> list, String word) {

        list.clear();
        List<String> stringList = SearchCategory.categoryList;
        for (String string : stringList) {
            if (string.toLowerCase().contains(word.toLowerCase())) {
                list.add(string);
            }
        }
    }

    public void SearchWithYelpCall(final String searchKeyWord) {

        final YelpAPI.YelpAPICLI yelpApiCli = new YelpAPI.YelpAPICLI();
        new JCommander(yelpApiCli, new String[0]);

        final YelpAPI yelpApi = new YelpAPI(YelpAPI.CONSUMER_KEY, YelpAPI.CONSUMER_SECRET, YelpAPI.TOKEN, YelpAPI.TOKEN_SECRET);

        ExecutorService service =  Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return yelpApi.queryAPI(yelpApi, yelpApiCli, searchKeyWord, firstLocation);
            }
        });

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public List<SearchResult> getSampleResults() {
        return Arrays.asList(
                new SearchResult("Result1", 1.0, new Location(0.0, 0.0),"", "", "", ""),
                new SearchResult("Result1", 1.0, new Location(0.0, 0.0),"", "", "", "")
        );
//        return Collections.emptyList();
    }

    public static String query(final QueryOption qp) {
        String qResult = null;
        try {

            ExecutorService service =  Executors.newSingleThreadExecutor();
            Future<String> future = service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return httpGet(qp.toString().replaceAll("\\s+", "%20"));
                }
            });
            qResult = future.get();
            System.out.println(qResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return qResult;
    }

    private static String httpGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();
        return sb.toString();
    }

}
