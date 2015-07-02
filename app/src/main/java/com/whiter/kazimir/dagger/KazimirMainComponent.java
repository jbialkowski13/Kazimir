package com.whiter.kazimir.dagger;

import com.squareup.otto.Bus;
import com.whiter.kazimir.App;

import javax.inject.Singleton;

import dagger.Component;

import static com.squareup.otto.ThreadEnforcer.ANY;

/**
 * Created by whiter
 */
@Singleton
@Component(modules = {MainModule.class, ReleaseApiModule.class, EventBusModule.class, PresentersModule.class, UtilsModule.class})
public interface KazimirMainComponent extends KazimirGraph {

    final class Initializer {
        private Initializer() {

        }

        public static KazimirMainComponent init(App app) {
            return DaggerKazimirMainComponent.builder()
                    .mainModule(new MainModule(app))
                    .eventBusModule(new EventBusModule(new Bus(ANY)))
                    .build();
        }
    }
}
