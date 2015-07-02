package com.whiter.kazimir.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.ui.activity.PlaceActivity;

/**
 * Created by whiter
 */
public class Intents {


    public Intents() {

    }

    public void startPlaceActivity(Activity activity, Street street) {
        Intent intent = new Intent(activity, PlaceActivity.class);
        intent.putExtra(PlaceActivity.STREET_TAG, street);
        activity.startActivity(intent);
    }
}
