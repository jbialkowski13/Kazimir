package com.whiter.kazimir.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.whiter.kazimir.ui.fragment.PlaceFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiter
 */
public class PlaceFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> placeFragments = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    public PlaceFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addPlaceFragment(Fragment placeFragment, String title) {
        placeFragments.add(placeFragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return placeFragments.get(position);
    }

    @Override
    public int getCount() {
        return placeFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
