package com.whiter.kazimir.api;

import com.whiter.kazimir.model.Street;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by whiter
 */
public interface KazimirApi {

    @GET("/streets.json")
    void getStreets(Callback<List<Street>> streetListCallback);
}
