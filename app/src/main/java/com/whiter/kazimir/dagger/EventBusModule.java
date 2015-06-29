package com.whiter.kazimir.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by whiter
 */
@Module
public class EventBusModule {

    private final Bus bus;

    public EventBusModule(Bus bus) {
        this.bus = bus;
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return bus;
    }
}
