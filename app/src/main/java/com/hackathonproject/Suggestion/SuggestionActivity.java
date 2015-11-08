package com.hackathonproject.Suggestion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hackathonproject.R;
import com.hackathonproject.Routine.RoutineService;
import com.hackathonproject.Search.SearchActivity;
import com.hackathonproject.Search.SearchCategory;
import com.hackathonproject.Search.SearchService;
import com.hackathonproject.Suggestion.SuggestionResultViewActivity;
import com.hackathonproject.User.User;
import com.hackathonproject.User.UserService;

public class SuggestionActivity extends AppCompatActivity {

    private RoutineService routineService = new RoutineService();
    private SearchService searchService = new SearchService();
    private int searchEntityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView btnLike = (TextView) findViewById(R.id.btnLike);
        TextView btnUnLike = (TextView) findViewById(R.id.btnUnLike);

        searchEntityID = 996564435;
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SuggestionResultViewActivity.class);
                intent.putExtra(SearchCategory.SEARCH_ENTITY_ID, searchEntityID);
                startActivity(intent);
            }
        });

        btnUnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.list);
        SuggestionListAdapter suggestionListAdapter = new SuggestionListAdapter(this);
        suggestionListAdapter.setSearchResultList(searchService.getSampleResults());
        listView.setAdapter(suggestionListAdapter);

        TextView myRoutineBtn = (TextView) findViewById(R.id.myRoutineBtn);
        myRoutineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRoutineActivity.class);
                startActivity(intent);
            }
        });

//        if (User.LoginType.getLoginType(UserService.selectedUser.getLoggedInType()) == User.LoginType.FOLLOWING) {
//            Toast.makeText(this, "Following", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
