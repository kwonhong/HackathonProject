package com.hackathonproject.Suggestion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hackathonproject.R;
import com.hackathonproject.Routine.Routine;
import com.hackathonproject.Routine.RoutineService;
import com.hackathonproject.Search.SearchActivity;
import com.hackathonproject.Search.SearchCategory;
import com.hackathonproject.Search.SearchResult;
import com.hackathonproject.Search.SearchService;
import com.hackathonproject.Suggestion.SuggestionResultViewActivity;
import com.hackathonproject.User.User;
import com.hackathonproject.User.UserService;

import java.util.List;
import java.util.Random;

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

        Routine routine = routineService.getUserRoutine();
        if (routine == null) {
            // Suggest to search
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return;
        } else {
            searchEntityID = routine.getEntityID();
            // Suggest something
        }


        // Random Image Setting
        ImageView imageView = (ImageView) findViewById(R.id.imgView);
        imageView.setImageDrawable(getResources().getDrawable(SearchCategory.sampleDrawbles.get(new Random().nextInt(SearchCategory.sampleDrawbles.size()))));

        // Description Setting
        SearchResult searchResult = searchService.getSearchResult(searchEntityID);
        TextView descriptionTxtView  = (TextView) findViewById(R.id.descriptionTxtView);
        String string = searchResult.getName() + ":\nBetween " + routine.getHour() +
                " - " + (routine.getHour() + 1) +
                        " o'clock, you might prefer to do following activity.";
        descriptionTxtView.setText(string);

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
                Routine existingRoutine = routineService.findUserRoutine(searchEntityID);
                if (existingRoutine != null) {
                    existingRoutine.setFrequency(-1);
                    existingRoutine.save();
                }

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.list);
        SuggestionListAdapter suggestionListAdapter = new SuggestionListAdapter(this);
        final List<SearchResult> searchResultList = searchService.getSearchResultWithTypeId(routine.getEntitypTypeID());
        // Exclude the suggestion at the top.

        suggestionListAdapter.setSearchResultList(searchResultList);
        listView.setAdapter(suggestionListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), SuggestionResultViewActivity.class);
                intent.putExtra(SearchCategory.SEARCH_ENTITY_ID, Integer.parseInt(searchResultList.get(i).getEntID()));
                startActivity(intent);
            }
        });

        TextView myRoutineBtn = (TextView) findViewById(R.id.myRoutineBtn);
        myRoutineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRoutineActivity.class);
                startActivity(intent);
            }
        });
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
