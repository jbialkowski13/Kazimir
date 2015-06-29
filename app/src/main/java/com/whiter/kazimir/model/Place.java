package com.whiter.kazimir.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by whiter
 */
public class Place {

    @SerializedName("id")
    private long id;

    @SerializedName("updated_at")
    private String lastUpdated;

    @SerializedName("details")
    private Details details;

    @SerializedName("photos")
    private List<Photos> photos;

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

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }
    }

}
