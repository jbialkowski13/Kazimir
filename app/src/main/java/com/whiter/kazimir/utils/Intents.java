package com.whiter.kazimir.utils;

import android.app.Activity;
import android.content.Intent;

import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.ui.activity.PlaceActivity;
import com.whiter.kazimir.ui.activity.PlaceListActivity;
import com.whiter.kazimir.ui.activity.StreetsActivity;

/**
 * Created by whiter
 */
public class Intents {

    private static final String STREET_TAG = "street";

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

    public void startStreetsActivity(Activity activity){
        Intent intent = new Intent(activity, StreetsActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
