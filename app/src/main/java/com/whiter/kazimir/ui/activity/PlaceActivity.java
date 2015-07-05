package com.whiter.kazimir.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.PlaceImageViewPagerAdapter;
import com.whiter.kazimir.model.Place;
import com.whiter.kazimir.utils.Intents;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by whiter
 */
public class PlaceActivity extends AppCompatActivity {

    @Inject
    Intents intents;

    @InjectView(R.id.tabs)
    TabLayout tabLayout;

    @InjectView(R.id.backdrop)
    ViewPager backdrop;

    @InjectView(R.id.backdrop_indicator)
    CircleIndicator circleIndicator;

    @InjectView(R.id.place_name)
    TextView placeName;

    @InjectView(R.id.place_description)
    TextView placeDescription;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_activity);
        App.component().inject(this);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        place = intents.getPlace(getIntent());
        loadImages();
        loadDetails();
    }

    private void loadImages() {
        PlaceImageViewPagerAdapter placeImageViewPagerAdapter = new PlaceImageViewPagerAdapter(getSupportFragmentManager(), place.getPhotos());
        backdrop.setAdapter(placeImageViewPagerAdapter);
        circleIndicator.setViewPager(backdrop);
    }

    private void loadDetails() {
        collapsingToolbarLayout.setTitle(place.getDetails().getDetails().getName());
        placeName.setText(place.getDetails().getDetails().getName());
        placeDescription.setText(place.getDetails().getDetails().getDescription());
    }

    @OnClick(R.id.show_on_map)
    public void showMap() {
        intents.startMapActivity(this, place);
    }

}
