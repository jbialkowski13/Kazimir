package com.whiter.kazimir.dagger;

import android.content.Context;

import com.squareup.otto.Bus;
import com.whiter.kazimir.presenter.StreetsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by whiter
 */
@Module
public class PresentersModule {

    @Provides
    @Singleton
    StreetsPresenter provideStreetsPresenter(Bus bus, Context context) {
        return new StreetsPresenter(bus, context);
    }
}
