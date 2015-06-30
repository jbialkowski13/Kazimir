package com.whiter.kazimir.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by whiter
 */
public class Photos implements Parcelable{


    @SerializedName("id")
    private long id;

    @SerializedName("details")
    private Details details;

    @SerializedName("images")
    private Images images;

    @SerializedName("updated_at")
    private String lastUpdate;

    protected Photos(Parcel in) {
        id = in.readLong();
        details = in.readParcelable(Details.class.getClassLoader());
        images = in.readParcelable(Images.class.getClassLoader());
        lastUpdate = in.readString();
    }

    public static final Creator<Photos> CREATOR = new Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };

    public long getId() {
        return id;
    }

    public Details getDetails() {
        return details;
    }

    public Images getImages() {
        return images;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(details, flags);
        dest.writeParcelable(images, flags);
        dest.writeString(lastUpdate);
    }


    public static class Details implements Parcelable{

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


    public static class Detail implements Parcelable{
        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        protected Detail(Parcel in) {
            title = in.readString();
            description = in.readString();
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

        public String getDescription() {
            return description;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(description);
        }
    }

    public static class Images implements Parcelable{
        private String thumb;
        private String tiny;
        private String small;
        private String medium;
        private String large;


        protected Images(Parcel in) {
            thumb = in.readString();
            tiny = in.readString();
            small = in.readString();
            medium = in.readString();
            large = in.readString();
        }

        public static final Creator<Images> CREATOR = new Creator<Images>() {
            @Override
            public Images createFromParcel(Parcel in) {
                return new Images(in);
            }

            @Override
            public Images[] newArray(int size) {
                return new Images[size];
            }
        };

        public String getThumb() {
            return thumb;
        }

        public String getTiny() {
            return tiny;
        }

        public String getSmall() {
            return small;
        }

        public String getMedium() {
            return medium;
        }

        public String getLarge() {
            return large;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(thumb);
            dest.writeString(tiny);
            dest.writeString(small);
            dest.writeString(medium);
            dest.writeString(large);
        }
    }
}
