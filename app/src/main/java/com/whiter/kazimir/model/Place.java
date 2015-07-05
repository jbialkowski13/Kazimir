package com.whiter.kazimir.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

/**
 * Created by whiter
 */
public class Place implements Parcelable {

    @SerializedName("id")
    private long id;

    @SerializedName("updated_at")
    private String lastUpdated;

    @SerializedName("details")
    private Details details;

    @SerializedName("photos")
    private List<Photos> photos;

    protected Place(Parcel in) {
        id = in.readLong();
        lastUpdated = in.readString();
        details = in.readParcelable(Details.class.getClassLoader());
        photos = in.createTypedArrayList(Photos.CREATOR);
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public Detail getDetails() {
        return details.getDetails();
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(lastUpdated);
        dest.writeParcelable(details, flags);
        dest.writeTypedList(photos);
    }

    public static class Details implements Parcelable {
        @SerializedName("en")
        private Detail detailEn;

        @SerializedName("pl")
        private Detail detailPl;

        protected Details(Parcel in) {
            detailEn = in.readParcelable(Detail.class.getClassLoader());
            detailPl = in.readParcelable(Detail.class.getClassLoader());
        }

        public static final Creator<Details> CREATOR = new Creator<Details>() {
            @Override
            public Details createFromParcel(Parcel in) {
                return new Details(in);
            }

            @Override
            public Details[] newArray(int size) {
                return new Details[size];
            }
        };

        public Detail getDetailEn() {
            return detailEn;
        }

        public Detail getDetailPl() {
            return detailPl;
        }

        public Detail getDetails() {
            String language = Locale.getDefault().getLanguage();
            switch (language) {
                case "pl":
                    return detailPl;
                default:
                    return detailEn;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(detailEn, flags);
            dest.writeParcelable(detailPl, flags);
        }
    }


    public static class Detail implements Parcelable {

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        protected Detail(Parcel in) {
            name = in.readString();
            description = in.readString();
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(description);
        }

        public static final Creator<Detail> CREATOR = new Creator<Detail>() {
            @Override
            public Detail createFromParcel(Parcel in) {
                return new Detail(in);
            }

            @Override
            public Detail[] newArray(int size) {
                return new Detail[size];
            }
        };
    }

}
