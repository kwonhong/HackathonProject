package com.hackathonproject.Suggestion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackathonproject.R;
import com.hackathonproject.Routine.Routine;
import com.hackathonproject.Routine.RoutineService;
import com.hackathonproject.Search.SearchCategory;
import com.hackathonproject.Search.SearchResult;
import com.hackathonproject.Search.SearchService;
import com.hackathonproject.User.UserService;

import org.joda.time.DateTime;

public class SuggestionResultViewActivity extends AppCompatActivity {

    private UserService userService = new UserService();
    private SearchService searchService = new SearchService();
    private RoutineService routineService = new RoutineService();

    private TextView btnAccept;
    private TextView btnCancel;
    private int searchEntityID;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_result_view);

        // Getting the searchResultEntityID
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            searchEntityID = extras.getInt(SearchCategory.SEARCH_ENTITY_ID);
        }

        final SearchResult searchResult = searchService.getSearchResult(searchEntityID);
        if (searchResult != null) {
            TextView descriptionTxtView = (TextView) findViewById(R.id.descriptionTxtView);
            descriptionTxtView.setText(searchResult.getName());

            TextView contactTxtView = (TextView) findViewById(R.id.contanctTxtView);
            contactTxtView.setText(searchResult.getPhoneNumber());
            setUpLocation(searchResult);
        }

        btnAccept = (TextView) findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Find the routine by entityID
//                Routine routine = routineService.findUserRoutine();

                // Not found, add a new one in
                Routine routine = new Routine(new DateTime().getHourOfDay(), Integer.parseInt(searchResult.getEntTypeID()), Integer.parseInt(searchResult.getEntID()), 0 );
                routine.save();
            }
        });

        btnCancel = (TextView) findViewById(R.id.btnDecline);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Set the frequency counter back to zero if exist in database
                finish();
            }
        });

    }

    private void setUpLocation(SearchResult searchResult) {
        LatLng DAVAO =
                new LatLng(searchResult.getLocation().getLatitude(), searchResult.getLocation().getLongitude());
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Marker davao = map.addMarker(
                new MarkerOptions().position(DAVAO).title(searchResult.getAddress()).snippet("Ateneo de Davao University"));

        // zoom in the camera to Davao city && animate the zoom process
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(DAVAO, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

        // Setting on Map Click Listener && Launch GoogleMap Application for more info.
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Uri gmmIntentUri = Uri.parse("geo:7.0722,125.6131(Google+Sydney)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }

            }
        });
    }

}
