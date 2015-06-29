package com.whiter.kazimir.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by whiter
 */
public class Street {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("path_string")
    private String pathString;

    @SerializedName("updated_at")
    private String lastUpdate;

    @SerializedName("places")
    private Places places;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPathString() {
        return pathString;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public Places getPlaces() {
        return places;
    }
}
