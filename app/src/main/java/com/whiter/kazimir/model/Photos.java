package com.whiter.kazimir.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by whiter
 */
public class Photos {


    public static class Photo {

        @SerializedName("id")
        private long id;

        @SerializedName("details")
        private Details details;

        @SerializedName("images")
        private Images images;

        @SerializedName("updated_at")
        private String lastUpdate;

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
    }

    public static class Details {

        @SerializedName("en")
        private Detail detailEn;

        @SerializedName("pl")
        private Detail detailPl;

        public Detail getDetailEn() {
            return detailEn;
        }

        public Detail getDetailPl() {
            return detailPl;
        }
    }


    public static class Detail {
        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        public String getDescription() {
            return description;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class Images {
        private String thumb;
        private String tiny;
        private String small;
        private String medium;
        private String large;


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
    }
}
