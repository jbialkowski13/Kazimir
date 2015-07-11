package com.whiter.kazimir.viewmodel;

import android.databinding.BaseObservable;

import com.whiter.kazimir.model.Place;

/**
 * Created by whiter
 */
public class PlaceViewModel extends BaseObservable {

    private final String name;
    private final String description;


    private PlaceViewModel(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static PlaceViewModel fromPlace(Place place) {
        return new PlaceViewModel(place.getDetails().getName(), place.getDetails().getDescription());
    }
}
