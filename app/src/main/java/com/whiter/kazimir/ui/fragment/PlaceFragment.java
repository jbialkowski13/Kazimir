package com.whiter.kazimir.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiter.kazimir.R;

/**
 * Created by whiter
 */
public class PlaceFragment extends Fragment {

    public static PlaceFragment newInstance() {
        Bundle args = new Bundle();
        PlaceFragment fragment = new PlaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.place_fragment, container, false);
    }
}
