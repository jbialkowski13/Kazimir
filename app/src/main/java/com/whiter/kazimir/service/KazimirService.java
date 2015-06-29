package com.whiter.kazimir.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.squareup.otto.Bus;
import com.whiter.kazimir.App;
import com.whiter.kazimir.api.KazimirApi;
import com.whiter.kazimir.event.DownloadEvent;
import com.whiter.kazimir.model.Street;

import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by whiter
 */
public class KazimirService extends Service {

    @Inject
    KazimirApi kazimirApi;

    @Inject
    Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        App.component().inject(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceCaller.Action action = ServiceCaller.getAction(intent);
        if (action != null) {
            onActionCalled(action);
        }
        return START_STICKY_COMPATIBILITY;
    }

    private void onActionCalled(@NonNull ServiceCaller.Action action) {
        switch (action) {
            case DOWNLOAD_STREETS:
                downloadStreets();
                break;
        }

    }

    private void downloadStreets() {
        kazimirApi.getStreets(new Callback<List<Street>>() {
            @Override
            public void success(List<Street> streets, Response response) {
                bus.post(new DownloadEvent(true));
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                bus.post(new DownloadEvent(false));
            }
        });
    }

}
