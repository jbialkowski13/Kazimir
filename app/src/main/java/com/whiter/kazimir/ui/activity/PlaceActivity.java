package com.whiter.kazimir.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.whiter.kazimir.model.Street;

/**
 * Created by whiter
 */
public class PlaceActivity extends AppCompatActivity {

    public static final String STREET_TAG = "street";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Street street = getIntent().getParcelableExtra(STREET_TAG);
    }
}
