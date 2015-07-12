package com.whiter.kazimir.viewmodel;

import android.databinding.BaseObservable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.whiter.kazimir.adapter.PlaceListFragmentPagerAdapter;

/**
 * Created by whiter
 */
public class PlaceListViewModel extends BaseObservable {

    private final String title;
    private final PlaceListFragmentPagerAdapter adapter;
    private final ViewPager viewPager;
    private final View.OnClickListener toolbarNavigationOnClickListener;

    private PlaceListViewModel(final Builder builder) {
        this.title = builder.title;
        this.adapter = builder.adapter;
        this.viewPager = builder.viewPager;
        this.toolbarNavigationOnClickListener = builder.toolbarNavigationOnClickListener;
    }

    public String getTitle() {
        return title;
    }

    public PlaceListFragmentPagerAdapter getAdapter() {
        return adapter;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public View.OnClickListener getToolbarNavigationOnClickListener() {
        return toolbarNavigationOnClickListener;
    }

    public static class Builder {
        private String title;
        private PlaceListFragmentPagerAdapter adapter;
        private ViewPager viewPager;
        private View.OnClickListener toolbarNavigationOnClickListener;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAdapter(PlaceListFragmentPagerAdapter adapter) {
            this.adapter = adapter;
            return this;
        }

        public Builder withViewPager(ViewPager viewPager){
            this.viewPager = viewPager;
            return this;
        }

        public Builder withToolbarNavigationOnClickListener(View.OnClickListener onClickListener){
            this.toolbarNavigationOnClickListener = onClickListener;
            return this;
        }

        public PlaceListViewModel build() {
            return new PlaceListViewModel(this);
        }
    }
}
