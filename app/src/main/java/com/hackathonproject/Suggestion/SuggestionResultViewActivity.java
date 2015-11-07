package com.hackathonproject.Suggestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hackathonproject.R;
import com.hackathonproject.Search.SearchCategory;
import com.hackathonproject.User.Location;
import com.hackathonproject.User.User;
import com.hackathonproject.User.UserService;

public class SuggestionResultViewActivity extends AppCompatActivity {

    private UserService userService = new UserService();

    private TextView btnAccept;
    private TextView btnCancel;
    private int searchEntityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_result_view);

        // Getting the searchResultEntityID
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            searchEntityID = extras.getInt(SearchCategory.SEARCH_ENTITY_ID);
        }
        



        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Save Routine

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Set the frequency counter back to zero if exist in database
            }
        });

    }

}
