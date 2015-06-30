package com.whiter.kazimir.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by whiter
 */
public class Street implements Parcelable{

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

    protected Street(Parcel in) {
        id = in.readLong();
        name = in.readString();
        pathString = in.readString();
        lastUpdate = in.readString();
        places = in.readParcelable(Places.class.getClassLoader());
    }

    public static final Creator<Street> CREATOR = new Creator<Street>() {
        @Override
        public Street createFromParcel(Parcel in) {
            return new Street(in);
        }

        @Override
        public Street[] newArray(int size) {
            return new Street[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(pathString);
        dest.writeString(lastUpdate);
        dest.writeParcelable(places, flags);
    }
}
