package com.whiter.kazimir.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.PlaceFragmentPagerAdapter;
import com.whiter.kazimir.model.Places;
import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.ui.fragment.PlaceFragment;
import com.whiter.kazimir.utils.Intents;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by whiter
 */
public class PlaceActivity extends AppCompatActivity {


    @Inject
    Intents intents;

    private Street street;

    @InjectView(R.id.tabs)
    TabLayout tabLayout;

    @InjectView(R.id.backdrop)
    ImageView backdrop;

    @InjectView(R.id.place_list_view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_activity);
        App.component().inject(this);
        ButterKnife.inject(this);
        street = intents.getStreet(getIntent());
        loadBackdrop(0);
        setupViewPager();
    }

    private void loadBackdrop(int position) {
        String url;
        Places places = street.getPlaces();
        if (position == 0) {
            url = places.getPresentPlaces().get(0).getPhotos().get(0).getImages().getLarge();
        } else {
            url = places.getPastPlaces().get(0).getPhotos().get(0).getImages().getLarge();
        }
        Glide.with(this).load(url).centerCrop().into(backdrop);
    }

    private void setupViewPager() {
        PlaceFragmentPagerAdapter placeFragmentPagerAdapter = new PlaceFragmentPagerAdapter(getSupportFragmentManager());
        placeFragmentPagerAdapter.addPlaceFragment(PlaceFragment.newInstance(), "present");
        placeFragmentPagerAdapter.addPlaceFragment(PlaceFragment.newInstance(), "past");
        viewPager.setAdapter(placeFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                loadBackdrop(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}
