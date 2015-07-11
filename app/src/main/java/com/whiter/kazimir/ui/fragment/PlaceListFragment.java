package com.whiter.kazimir.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.PlaceListRecyclerViewAdapter;
import com.whiter.kazimir.databinding.PlaceListFragmentBinding;
import com.whiter.kazimir.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiter
 */
public class PlaceListFragment extends BaseFragment<PlaceListFragment.Contract> implements PlaceListRecyclerViewAdapter.ItemClickListener {

    private static final String PLACES_TAG = "places";


    private List<Place> places = new ArrayList<>();
    private PlaceListFragmentBinding placeListFragmentBinding;

    public interface Contract {
        void showPlace(Place place);

        void showPlaceOnMap(Place place);
    }

    public static PlaceListFragment newInstance(List<Place> places) {
        Bundle args = new Bundle();
        PlaceListFragment fragment = new PlaceListFragment();
        args.putParcelableArrayList(PLACES_TAG, (ArrayList<? extends Parcelable>) places);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        placeListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.place_list_fragment, container, false);
        return placeListFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        placeListFragmentBinding = DataBindingUtil.getBinding(placeListFragmentBinding.getRoot());
        placeListFragmentBinding.placeListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PlaceListRecyclerViewAdapter placeListRecyclerViewAdapter = new PlaceListRecyclerViewAdapter(places, getActivity());
        placeListRecyclerViewAdapter.setItemClickListener(this);
        placeListFragmentBinding.placeListRecyclerView.setAdapter(placeListRecyclerViewAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        places.clear();
        List<Place> list = getArguments().getParcelableArrayList(PLACES_TAG);
        if (list != null) {
            places.addAll(list);
        }
    }

    @Override
    public void onItemClick(int position) {
        if (contract != null) {
            contract.showPlace(places.get(position));
        }
    }

    @Override
    public void onShowOnMapClick(int position) {
        if (contract != null) {
            contract.showPlaceOnMap(places.get(position));
        }
    }
}
