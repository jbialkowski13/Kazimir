package com.whiter.kazimir.utils;

import android.app.Activity;
import android.content.Intent;

import com.whiter.kazimir.model.Place;
import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.ui.activity.MapActivity;
import com.whiter.kazimir.ui.activity.PlaceActivity;
import com.whiter.kazimir.ui.activity.PlaceListActivity;
import com.whiter.kazimir.ui.activity.StreetsActivity;

/**
 * Created by whiter
 */
public class Intents {

    private static final String STREET_TAG = "street";
    private static final String PLACE_TAG = "place";

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

    public void startPlaceActivity(Activity activity, Place place) {
        Intent intent = new Intent(activity, PlaceActivity.class);
        intent.putExtra(PLACE_TAG, place);
        activity.startActivity(intent);
    }

    public Place getPlace(Intent intent) {
        return intent.getParcelableExtra(PLACE_TAG);
    }

    public void startMapActivity(Activity activity, Place place) {
        Intent intent = new Intent(activity, MapActivity.class);
        intent.putExtra(PLACE_TAG, place);
        activity.startActivity(intent);
    }
}
