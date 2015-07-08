package com.whiter.kazimir.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.PlaceListFragmentPagerAdapter;
import com.whiter.kazimir.model.Place;
import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.ui.fragment.PlaceListFragment;
import com.whiter.kazimir.utils.Intents;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by whiter
 */
public class PlaceListActivity extends AppCompatActivity implements PlaceListFragment.Contract {

    @Inject
    Intents intents;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.tabs)
    TabLayout tabLayout;

    @InjectView(R.id.place_list_view_pager)
    ViewPager placesViewPager;
    private Street street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list_activity);
        ButterKnife.inject(this);
        App.component().inject(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        street = intents.getStreet(getIntent());
        toolbar.setTitle(street.getName());
        setupList(street);
    }

    private void setupList(Street street) {
        List<Place> presentPlaces = street.getPlaces().getPresentPlaces();
        List<Place> pastPlaces = street.getPlaces().getPastPlaces();

        PlaceListFragmentPagerAdapter placeListFragmentPagerAdapter = new PlaceListFragmentPagerAdapter(getSupportFragmentManager());
        placeListFragmentPagerAdapter.addPlaceListFragment(PlaceListFragment.newInstance(pastPlaces), getString(R.string.past));
        placeListFragmentPagerAdapter.addPlaceListFragment(PlaceListFragment.newInstance(presentPlaces), getString(R.string.present));

        placesViewPager.setAdapter(placeListFragmentPagerAdapter);
        tabLayout.setupWithViewPager(placesViewPager);
    }

    @Override
    public void showPlace(Place place) {
        intents.startPlaceActivity(this, place, street.getPathString());
    }

    @Override
    public void showPlaceOnMap(Place place) {
        intents.startMapActivity(this, place, street.getPathString());
    }
}
