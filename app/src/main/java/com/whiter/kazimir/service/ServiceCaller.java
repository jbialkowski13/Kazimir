package com.whiter.kazimir.service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by whiter
 */
public final class ServiceCaller {

    private static final String ACTION_EXTRA_KEY = "action";

    enum Action {
        DOWNLOAD_STREETS, GET_STREETS, REFRESH_STREETS
    }

    public static void downloadStreets(@NonNull Context context) {
        Intent serviceIntent = getServiceIntent(context);
        serviceIntent.putExtra(ACTION_EXTRA_KEY, Action.DOWNLOAD_STREETS.name());
        context.startService(serviceIntent);
    }

    public static void getStreets(@NonNull Context context) {
        Intent serviceIntent = getServiceIntent(context);
        serviceIntent.putExtra(ACTION_EXTRA_KEY, Action.GET_STREETS.name());
        context.startService(serviceIntent);
    }

    public static void refreshStreets(@NonNull Context context) {
        Intent serviceIntent = getServiceIntent(context);
        serviceIntent.putExtra(ACTION_EXTRA_KEY,Action.REFRESH_STREETS.name());
        context.startService(serviceIntent);
    }

    @Nullable
    static Action getAction(Intent intent) {
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra(ACTION_EXTRA_KEY);
        return Action.valueOf(stringExtra);
    }

    @NonNull
    private static Intent getServiceIntent(@NonNull Context context) {
        return new Intent(context, KazimirService.class);
    }
}
