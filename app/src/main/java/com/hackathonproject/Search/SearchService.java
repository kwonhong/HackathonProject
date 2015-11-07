package com.hackathonproject.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by james on 15-11-07.
 */
public class SearchService {

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
            // Convert queryResult to JsonObject
//            SearchResultParser.getSearchResult()
//            System.out.println(query(qo));

        } catch (Exception je) {
            System.out.println(je.toString());
        }

        List<String> categoryTexts = SearchCategory.categoryList;
        List<SearchResult> searchResults = new ArrayList<>();
        for (String string : categoryTexts) {
            if (string.contains(query)) {
//                searchResults.add(new SE)

            }
        }
        // TODO Get Search Result & Populate


        return searchResults;

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

    public SearchResult getSearchResult(String entityID) {


        return null;
    }

    public List<SearchResult> getSampleResults() {
//        return Arrays.asList(
//                new SearchResult("Result1", 1.0, new Loca),
//                new SearchResult("Result2", 1.0),
//                new SearchResult("Result3", 1.0)
//        );
        return Collections.emptyList();
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
