package com.hackathonproject.NewsFeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.hackathonproject.Login.SelectUserListAdapter;
import com.hackathonproject.R;
import com.hackathonproject.Routine.RoutineService;
import com.hackathonproject.User.UserService;

public class NewsFeedActivity extends AppCompatActivity {

    private RoutineService routineService = new RoutineService();
    private GoogleCardsSocialAdapter googleCardsSocialAdapter;
    private SelectUserListAdapter selectUserListAdapter;
    private UserService userService = new UserService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        ListView listView = (ListView) findViewById(R.id.listView);
        googleCardsSocialAdapter = new GoogleCardsSocialAdapter(this);
        googleCardsSocialAdapter.setRoutineList(routineService.getDefaultRoutine());

        selectUserListAdapter = new SelectUserListAdapter(this);
        selectUserListAdapter.setUserList(userService.getDefaultUser());
        listView.setAdapter(googleCardsSocialAdapter);
    }
}
