package com.whiter.kazimir.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.PlaceImageViewPagerAdapter;
import com.whiter.kazimir.databinding.PlaceActivityBinding;
import com.whiter.kazimir.model.Place;
import com.whiter.kazimir.utils.Intents;
import com.whiter.kazimir.viewmodel.PlaceViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by whiter
 */
public class PlaceActivity extends AppCompatActivity {

    @Inject
    Intents intents;

    private Place place;
    private String coordinatesPathString;
    private PlaceActivityBinding placeActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placeActivityBinding = DataBindingUtil.setContentView(this, R.layout.place_activity);
        App.component().inject(this);
        setSupportActionBar(placeActivityBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        place = intents.getPlace(getIntent());
        coordinatesPathString = intents.getCoordinatesPathString(getIntent());
        setupViewBinding();
    }

    private void setupViewBinding() {
        PlaceImageViewPagerAdapter placeImageViewPagerAdapter = new PlaceImageViewPagerAdapter(getSupportFragmentManager(), place.getPhotos());

        PlaceViewModel placeViewModel = new PlaceViewModel.Builder()
                .withName(place.getDetails().getName())
                .withDescription(place.getDetails().getDescription())
                .withPlaceImageViewAdapter(placeImageViewPagerAdapter)
                .withViewPager(placeActivityBinding.backdrop)
                .withToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                })
                .build();

        placeActivityBinding.setPlaceViewModel(placeViewModel);
    }

    @OnClick(R.id.show_on_map)
    public void showMap() {
        intents.startMapActivity(this, place, coordinatesPathString);
    }

}
