package com.hackathonproject.Suggestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.hackathonproject.Login.SelectUserListAdapter;
import com.hackathonproject.R;
import com.hackathonproject.Routine.RoutineService;

public class MyRoutineActivity extends AppCompatActivity {

    private RoutineService routineService = new RoutineService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routine);

        RoutineAdapter routineAdapter = new RoutineAdapter(this);
        routineAdapter.setRoutineList(routineService.getAllUserRoutine());
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(routineAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_routine, menu);
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
