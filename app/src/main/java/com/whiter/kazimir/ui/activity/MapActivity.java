package com.whiter.kazimir.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.model.Coordinate;
import com.whiter.kazimir.model.Place;
import com.whiter.kazimir.utils.Intents;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MapActivity extends AppCompatActivity {

    private GoogleMap map;

    @Inject
    Intents intents;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private String coordinatesPathString;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        App.component().inject(this);
        ButterKnife.inject(this);
        coordinatesPathString = intents.getCoordinatesPathString(getIntent());
        place = intents.getPlace(getIntent());
        setupToolbar();
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setupToolbar() {
        toolbar.setTitle(place.getDetails().getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (map != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        List<Coordinate> coordinates = parseCoordinates();
        Coordinate markerCoordinate = coordinates.get(0);
        PolylineOptions polylineOptions = new PolylineOptions();

        for (Coordinate coordinate : coordinates) {
            polylineOptions.add(new LatLng(coordinate.getLat(), coordinate.getLon()));
        }
        map.addPolyline(polylineOptions);
        MarkerOptions marker = new MarkerOptions().position(new LatLng(markerCoordinate.getLat(), markerCoordinate.getLon())).title(place.getDetails().getName());
        map.addMarker(marker);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15);
        map.moveCamera(cameraUpdate);
    }

    private List<Coordinate> parseCoordinates() {

        List<Coordinate> coordinates = new ArrayList<>();
        String[] coordinatesString = coordinatesPathString.split(";");

        for (String coordinateS : coordinatesString) {
            String[] split = coordinateS.split(",");
            Coordinate coordinate = new Coordinate(Double.valueOf(split[0]), Double.valueOf(split[1]));
            coordinates.add(coordinate);
        }

        return coordinates;
    }
}
