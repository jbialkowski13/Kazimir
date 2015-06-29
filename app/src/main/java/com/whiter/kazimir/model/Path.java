package com.whiter.kazimir.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by whiter
 */
public class Path {

    public enum Type {
        LINESTRING
    }

    @SerializedName("type")
    private Type type;

    public Type getType() {
        return type;
    }
}
