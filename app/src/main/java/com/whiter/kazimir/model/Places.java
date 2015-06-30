package com.whiter.kazimir.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by whiter
 */
public class Places implements Parcelable{

    @SerializedName("present")
    private List<Place> presentPlaces;

    @SerializedName("past")
    private List<Place> pastPlaces;

    protected Places(Parcel in) {
        presentPlaces = in.createTypedArrayList(Place.CREATOR);
        pastPlaces = in.createTypedArrayList(Place.CREATOR);
    }

    public static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };

    public List<Place> getPresentPlaces() {
        return presentPlaces;
    }

    public List<Place> getPastPlaces() {
        return pastPlaces;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(presentPlaces);
        dest.writeTypedList(pastPlaces);
    }
}
