package com.hackathonproject.Search;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hackathonproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private static final String LOGGER = "SearchActivityLog";

    private SearchService searchService = new SearchService();
    private SearchResultListAdapter searchResultListAdapter;
    private AutoCompleteTextView autoCompleteTextView;
    private TextView searchTxtViewCancelBtn, saerchTxtBtn;
    private ListView listView;

    String[] language ={"C","C++","Java",".NET","iPhone","Android","ASP.NET","PHP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //Creating the instance of ArrayAdapter containing list of language names
        List<String> list = new ArrayList<>();
        searchService.populateAutoCompleteStrings(list, "a");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,list);
        //Getting the instance of AutoCompleteTextView
        autoCompleteTextView= (AutoCompleteTextView)findViewById(R.id.search_field);
        autoCompleteTextView.setThreshold(1);//will start working from first character
        autoCompleteTextView.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

        searchResultListAdapter = new SearchResultListAdapter(this);
        searchResultListAdapter.setSearchResultList(Collections.<SearchResult>emptyList());
        listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(searchResultListAdapter);

        searchTxtViewCancelBtn = (TextView) findViewById(R.id.search_x);
        searchTxtViewCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCompleteTextView.setText("");
                searchResultListAdapter.setSearchResultList(Collections.<SearchResult>emptyList());
                searchResultListAdapter.notifyDataSetChanged();
            }
        });

        //
        saerchTxtBtn = (TextView) findViewById(R.id.search);
        saerchTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<SearchResult> searchResults = searchService.getSearchResults(autoCompleteTextView.getText().toString());
                searchResultListAdapter.setSearchResultList(searchResults);
                searchResultListAdapter.notifyDataSetChanged();
            }
        });



    }
}
