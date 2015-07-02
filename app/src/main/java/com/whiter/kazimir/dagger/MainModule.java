package com.whiter.kazimir.dagger;

import android.content.Context;
import android.content.res.Resources;

import com.whiter.kazimir.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by whiter
 */
@Module
public class MainModule {

    private final App app;

    public MainModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public App provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Resources provideResources() {
        return app.getResources();
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app.getApplicationContext();
    }
}
