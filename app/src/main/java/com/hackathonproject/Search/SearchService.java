package com.hackathonproject.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by james on 15-11-07.
 */
public class SearchService {

    public List<SearchResult> getSearchResults(String query) {

        List<SearchResult> searchResults = new ArrayList<>();
        // TODO Get Search Result & Populate


        return searchResults;

    }

    public List<SearchResult> getSampleResults() {
        return Arrays.asList(
                new SearchResult("Result1", 1.0),
                new SearchResult("Result2", 1.0),
                new SearchResult("Result3", 1.0)
        );
    }
}
