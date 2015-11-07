package com.hackathonproject.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.hackathonproject.R;
import com.hackathonproject.User.User;
import com.hackathonproject.User.UserService;

public class SelectUserActivity extends AppCompatActivity {

    private SelectUserListAdapter selectUserListAdapter;
    private UserService userService = new UserService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        selectUserListAdapter = new SelectUserListAdapter(this);
        selectUserListAdapter.setUserList(userService.getDefaultUser());
        ListView listView = (ListView) findViewById(R.id.userList);
        listView.setAdapter(selectUserListAdapter);

    }

}
