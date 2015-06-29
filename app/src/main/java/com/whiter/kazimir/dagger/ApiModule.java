package com.whiter.kazimir.dagger;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by whiter
 */
@Module
public class ApiModule {

    public static final String API_URL = "http://kazimirapp.pl";

    @Provides
    @Singleton
    Client provideClient(OkHttpClient okHttpClient){
        return new OkClient(okHttpClient);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client){
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .build();
    }
}
