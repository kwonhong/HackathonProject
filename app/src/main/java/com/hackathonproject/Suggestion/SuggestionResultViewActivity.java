package com.hackathonproject.Suggestion;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackathonproject.R;
import com.hackathonproject.Search.QueryOption;
import com.hackathonproject.Search.SearchCategory;
import com.hackathonproject.Search.SearchResult;
import com.hackathonproject.Search.SearchResultParser;
import com.hackathonproject.Search.SearchService;
import com.hackathonproject.User.Location;
import com.hackathonproject.User.User;
import com.hackathonproject.User.UserService;

import org.json.JSONObject;

public class SuggestionResultViewActivity extends AppCompatActivity {

    private UserService userService = new UserService();
    private SearchService searchService = new SearchService();

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
            String searchEntitiyIDString =extras.getString(SearchCategory.SEARCH_ENTITY_ID);
            searchEntityID = Integer.parseInt(searchEntitiyIDString.trim());
        }

        btnAccept = (TextView) findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Save Routine

            }
        });

        btnCancel = (TextView) findViewById(R.id.btnDecline);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Set the frequency counter back to zero if exist in database
            }
        });

        SearchResult searchResult = searchService.getSearchResult(searchEntityID);
        TextView descriptionTxtView = (TextView) findViewById(R.id.descriptionTxtView);
        descriptionTxtView.setText(searchResult.getName());

        TextView contanctTxtView = (TextView) findViewById(R.id.contanctTxtView);
        contanctTxtView.setText(searchResult.getPhoneNumber());

//        MapFragment googleMap = (MapFragment) findViewById(R.id.map);







    }

    private void setUpLocation() {
        LatLng DAVAO = new LatLng(7.0722, 125.6131);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Marker davao = map.addMarker(new MarkerOptions().position(DAVAO).title("Davao City").snippet("Ateneo de Davao University"));

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
