package com.whiter.kazimir.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.whiter.kazimir.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by whiter
 */
public class ImagePlaceFragment extends Fragment {

    private static final String URL_KEY = "url";
    private String url;

    public static ImagePlaceFragment newInstance(String url) {
        Bundle args = new Bundle();
        ImagePlaceFragment fragment = new ImagePlaceFragment();
        args.putString(URL_KEY, url);
        fragment.setArguments(args);
        return fragment;
    }

    @InjectView(R.id.place_image_item)
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this, getView());
        Glide.with(this).load(url).into(imageView);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(URL_KEY);
    }
}
