package com.hackathonproject.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hackathonproject.R;

import java.util.Collections;

public class SearchActivity extends AppCompatActivity {
    private static final String LOGGER = "SearchActivityLog";

    private SearchService searchService = new SearchService();
    private SearchResultListAdapter searchResultListAdapter;
    private EditText searchEditTxtView;
    private TextView searchTxtViewCancelBtn;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        searchResultListAdapter = new SearchResultListAdapter(this);
        searchResultListAdapter.setSearchResultList(Collections.<SearchResult>emptyList());
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(searchResultListAdapter);

        searchEditTxtView = (EditText) findViewById(R.id.search_field);
        searchEditTxtView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TODO Uncomment this when searching implementation is done
//                searchResultListAdapter.setSearchResultList(searchService.getSearchResults(charSequence.toString()));
                searchResultListAdapter.setSearchResultList(searchService.getSampleResults());
                searchResultListAdapter.notifyDataSetChanged();
                Log.i(LOGGER, "Data is being changed");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchTxtViewCancelBtn = (TextView) findViewById(R.id.search_x);
        searchTxtViewCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditTxtView.setText("");
                searchResultListAdapter.setSearchResultList(Collections.<SearchResult>emptyList());
                searchResultListAdapter.notifyDataSetChanged();
            }
        });


    }
}
