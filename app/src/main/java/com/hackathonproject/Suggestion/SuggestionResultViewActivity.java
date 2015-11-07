package com.hackathonproject.Suggestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hackathonproject.R;
import com.hackathonproject.User.Location;
import com.hackathonproject.User.User;
import com.hackathonproject.User.UserService;

public class SuggestionResultViewActivity extends AppCompatActivity {

    private UserService userService = new UserService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_result_view);


    }

}
