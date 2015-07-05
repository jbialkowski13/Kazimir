package com.whiter.kazimir.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiter.kazimir.R;
import com.whiter.kazimir.adapter.PlaceListRecyclerViewAdapter;
import com.whiter.kazimir.model.Place;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by whiter
 */
public class PlaceListFragment extends BaseFragment<PlaceListFragment.Contract> implements PlaceListRecyclerViewAdapter.ItemClickListener {

    private static final String PLACES_TAG = "places";

    @InjectView(R.id.place_list_recycler_view)
    RecyclerView placeListFragmentRecyclerView;

    private List<Place> places = new ArrayList<>();

    public interface Contract {
        void showPlace(Place place);
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
        return inflater.inflate(R.layout.place_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this, getView());
        placeListFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PlaceListRecyclerViewAdapter placeListRecyclerViewAdapter = new PlaceListRecyclerViewAdapter(places, getActivity());
        placeListRecyclerViewAdapter.setItemClickListener(this);
        placeListFragmentRecyclerView.setAdapter(placeListRecyclerViewAdapter);
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
}
