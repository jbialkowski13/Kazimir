package com.whiter.kazimir.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

import com.whiter.kazimir.model.MapMode;
import com.whiter.kazimir.model.Place;
import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.ui.activity.MapActivity;
import com.whiter.kazimir.ui.activity.PlaceActivity;
import com.whiter.kazimir.ui.activity.PlaceListActivity;
import com.whiter.kazimir.ui.activity.StreetsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiter
 */
public class Intents {

    private static final String STREET_TAG = "street";
    private static final String PLACE_TAG = "place";
    private static final String COORDINATES_PATH_STRING_TAG = "paths";
    private static final String MAP_MODE = "map_mode";
    private static final String STREETS_TAG = "streets";

    public Intents() {

    }

    public void startPlaceListActivity(Activity activity, Street street) {
        Intent intent = new Intent(activity, PlaceListActivity.class);
        intent.putExtra(STREET_TAG, street);
        activity.startActivity(intent);
    }

    public Street getStreet(Intent intent) {
        return intent.getParcelableExtra(STREET_TAG);
    }

    public void startStreetsActivity(Activity activity) {
        Intent intent = new Intent(activity, StreetsActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public void startPlaceActivity(Activity activity, Place place, String coordinatesPath) {
        Intent intent = new Intent(activity, PlaceActivity.class);
        intent.putExtra(PLACE_TAG, place);
        intent.putExtra(COORDINATES_PATH_STRING_TAG, coordinatesPath);
        activity.startActivity(intent);
    }

    public String getCoordinatesPathString(Intent intent) {
        return intent.getStringExtra(COORDINATES_PATH_STRING_TAG);
    }

    public Place getPlace(Intent intent) {
        return intent.getParcelableExtra(PLACE_TAG);
    }

    public List<Street> getStreets(Intent intent) {
        return intent.getParcelableArrayListExtra(STREETS_TAG);
    }

    public MapMode getMapMode(Intent intent) {
        return MapMode.valueOf(intent.getStringExtra(MAP_MODE));
    }

    public void startMapActivity(Activity activity, Place place, String coordinatesPath) {
        Intent intent = new Intent(activity, MapActivity.class);
        intent.putExtra(MAP_MODE, MapMode.SINGLE_STREET.name());
        intent.putExtra(PLACE_TAG, place);
        intent.putExtra(COORDINATES_PATH_STRING_TAG, coordinatesPath);
        activity.startActivity(intent);
    }

    public void startMapActivity(Activity activity, List<Street> streets) {
        Intent intent = new Intent(activity, MapActivity.class);
        intent.putExtra(MAP_MODE, MapMode.ALL_STREET.name());
        intent.putParcelableArrayListExtra(STREETS_TAG, (ArrayList<? extends Parcelable>) streets);
        activity.startActivity(intent);
    }
}
