package com.whiter.kazimir.dagger;

import com.whiter.kazimir.api.KazimirApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;

/**
 * Created by whiter
 */
@Module(includes = ApiModule.class)
public class ReleaseApiModule {

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(ApiModule.API_URL);
    }

    @Provides
    @Singleton
    KazimirApi provideKazimirApi(RestAdapter restAdapter){
        return restAdapter.create(KazimirApi.class);
    }
}
