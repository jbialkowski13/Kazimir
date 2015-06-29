package com.whiter.kazimir.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by whiter
 */
public class Places {

    @SerializedName("present")
    private List<Place> presentPlaces;

    @SerializedName("past")
    private List<Place> pastPlaces;

    public List<Place> getPresentPlaces() {
        return presentPlaces;
    }

    public List<Place> getPastPlaces() {
        return pastPlaces;
    }
}
