package com.whiter.kazimir.dagger;

import com.whiter.kazimir.presenter.SplashPresenter;
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
    StreetsPresenter provideStreetsPresenter() {
        return new StreetsPresenter();
    }

    @Provides
    @Singleton
    SplashPresenter provideSplashPresenter() {
        return new SplashPresenter();
    }
}
