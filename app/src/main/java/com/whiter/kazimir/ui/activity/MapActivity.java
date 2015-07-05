package com.whiter.kazimir.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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

public class MapActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Inject
    Intents intents;
    private String coordinatesPathString;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        App.component().inject(this);
        coordinatesPathString = intents.getCoordinatesPathString(getIntent());
        place = intents.getPlace(getIntent());
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        List<Coordinate> coordinates = parseCoordinates();
        Coordinate markerCoordinate = coordinates.get(0);
        PolylineOptions polylineOptions = new PolylineOptions();

        for (Coordinate coordinate : coordinates) {
            polylineOptions.add(new LatLng(coordinate.getLat(), coordinate.getLon()));
        }
        mMap.addPolyline(polylineOptions);
        MarkerOptions marker = new MarkerOptions().position(new LatLng(markerCoordinate.getLat(), markerCoordinate.getLon())).title(place.getDetails().getName());
        mMap.addMarker(marker);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15);
        mMap.moveCamera(cameraUpdate);
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
