package com.whiter.kazimir.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.whiter.kazimir.R;
import com.whiter.kazimir.databinding.ImageItemBinding;

/**
 * Created by whiter
 */
public class ImagePlaceFragment extends Fragment {

    private static final String URL_KEY = "url";
    private String url;
    private ImageItemBinding imageItemBinding;

    public static ImagePlaceFragment newInstance(String url) {
        Bundle args = new Bundle();
        ImagePlaceFragment fragment = new ImagePlaceFragment();
        args.putString(URL_KEY, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageItemBinding = DataBindingUtil.inflate(inflater, R.layout.image_item, container, false);
        return imageItemBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageItemBinding = DataBindingUtil.getBinding(getView());
        Glide.with(this).load(url).into(imageItemBinding.placeImageItem);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(URL_KEY);
    }
}
