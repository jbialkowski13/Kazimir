package com.whiter.kazimir.dagger;

import com.whiter.kazimir.utils.Intents;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by whiter
 */
@Module
public class UtilsModule {

    @Provides
    @Singleton
    Intents provideIntents() {
        return new Intents();
    }
}
