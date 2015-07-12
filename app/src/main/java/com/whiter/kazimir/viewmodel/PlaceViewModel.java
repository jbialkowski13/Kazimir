package com.whiter.kazimir.viewmodel;

import android.databinding.BaseObservable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.whiter.kazimir.adapter.PlaceImageViewPagerAdapter;

/**
 * Created by whiter
 */
public class PlaceViewModel extends BaseObservable {

    private final String name;
    private final String description;
    private final PlaceImageViewPagerAdapter adapter;
    private final ViewPager viewPager;
    private final View.OnClickListener toolbarNavigationClickListener;

    private PlaceViewModel(final Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.adapter = builder.adapter;
        this.viewPager = builder.viewPager;
        this.toolbarNavigationClickListener = builder.toolbarNavigationClickListener;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public PlaceImageViewPagerAdapter getAdapter() {
        return adapter;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public View.OnClickListener getToolbarNavigationClickListener() {
        return toolbarNavigationClickListener;
    }

    public static class Builder {

        private String name;
        private String description;

        private PlaceImageViewPagerAdapter adapter;
        private ViewPager viewPager;
        private View.OnClickListener toolbarNavigationClickListener;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withPlaceImageViewAdapter(PlaceImageViewPagerAdapter adapter) {
            this.adapter = adapter;
            return this;
        }

        public Builder withViewPager(ViewPager viewPager) {
            this.viewPager = viewPager;
            return this;
        }

        public Builder withToolbarNavigationClickListener(View.OnClickListener toolbarNavigationClickListener) {
            this.toolbarNavigationClickListener = toolbarNavigationClickListener;
            return this;
        }

        public PlaceViewModel build() {
            return new PlaceViewModel(this);
        }
    }
}
