package com.whiter.kazimir.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.whiter.kazimir.model.Photos;
import com.whiter.kazimir.ui.fragment.ImagePlaceFragment;

import java.util.List;

/**
 * Created by whiter
 */
public class PlaceImageViewPagerAdapter extends FragmentPagerAdapter {

    private List<Photos> photos;

    public PlaceImageViewPagerAdapter(FragmentManager fragmentManager, List<Photos> photos) {
        super(fragmentManager);
        this.photos = photos;
    }

    @Override
    public Fragment getItem(int position) {
        return ImagePlaceFragment.newInstance(photos.get(position).getImages().getMedium());
    }

    @Override
    public int getCount() {
        return photos.size();
    }

}
