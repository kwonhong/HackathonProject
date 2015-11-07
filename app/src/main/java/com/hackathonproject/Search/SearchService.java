package com.hackathonproject.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by james on 15-11-07.
 */
public class SearchService {

    public List<SearchResult> getSearchResults(String query) {

        List<String> categoryTexts = SearchCategory.categoryList;
        List<SearchResult> searchResults = new ArrayList<>();
        for (String string: categoryTexts) {
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
        for (String string: stringList) {
            if (string.toLowerCase().contains(word.toLowerCase())) {
                list.add(string);
            }
        }
    }

    public List<SearchResult> getSampleResults() {
        return Arrays.asList(
                new SearchResult("Result1", 1.0),
                new SearchResult("Result2", 1.0),
                new SearchResult("Result3", 1.0)
        );
    }
}
