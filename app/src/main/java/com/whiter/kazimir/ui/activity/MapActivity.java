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
import com.whiter.kazimir.model.MapMode;
import com.whiter.kazimir.model.Place;
import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.utils.Intents;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MapActivity extends AppCompatActivity implements GoogleMap.OnMapClickListener {

    private GoogleMap map;

    @Inject
    Intents intents;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private MapMode mapMode;
    private List<LatLng> latLngs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        App.component().inject(this);
        ButterKnife.inject(this);
        mapMode = intents.getMapMode(getIntent());
        setupToolbar();
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (mapMode == MapMode.SINGLE_STREET) {
            return;
        }

        if (latLngs == null) {
            return;
        }
    }

    private void findPoint(LatLng latLng) {
        //todo
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void enterMode() {
        switch (mapMode) {
            case SINGLE_STREET:
                enterSingleStreetMode();
                break;
            case ALL_STREET:
                enterAllStreetsMode();
                break;
        }
    }

    private void enterSingleStreetMode() {
        String coordinatesPathString = intents.getCoordinatesPathString(getIntent());
        Place place = intents.getPlace(getIntent());
        setUpSingleStreetMap(coordinatesPathString, place);
    }

    private void enterAllStreetsMode() {
        List<Street> streets = intents.getStreets(getIntent());
        setToolbarTitle(getString(R.string.map));
        latLngs = null;
        for (Street street : streets) {
            latLngs = drawStreet(street.getPathString());
        }
        if (latLngs != null) {
            MarkerOptions markerOptions = drawMarker(latLngs.get(0), null);
            moveMapToMarker(markerOptions);
        }
    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (map != null) {
                map.setOnMapClickListener(this);
                enterMode();
            }
        }
    }

    private void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void setUpSingleStreetMap(String coordinatesPathString, Place place) {
        setToolbarTitle(place.getDetails().getName());
        latLngs = drawStreet(coordinatesPathString);
        MarkerOptions marker = drawMarker(latLngs.get(0), place.getDetails().getName());
        moveMapToMarker(marker);
    }

    private void moveMapToMarker(MarkerOptions markerOptions) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 15);
        map.moveCamera(cameraUpdate);
    }

    private MarkerOptions drawMarker(LatLng latLng, String title) {
        MarkerOptions marker = new MarkerOptions().position(latLng).title(title);
        map.addMarker(marker);
        return marker;
    }

    private List<LatLng> drawStreet(String coordinatesPathString) {
        List<Coordinate> coordinates = parseCoordinates(coordinatesPathString);
        PolylineOptions polylineOptions = new PolylineOptions();
        for (Coordinate coordinate : coordinates) {
            polylineOptions.add(new LatLng(coordinate.getLat(), coordinate.getLon()));
        }
        map.addPolyline(polylineOptions);
        return polylineOptions.getPoints();
    }

    private List<Coordinate> parseCoordinates(String coordinatesPathString) {
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
