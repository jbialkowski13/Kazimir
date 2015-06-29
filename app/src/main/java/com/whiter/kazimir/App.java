package com.whiter.kazimir;

import android.app.Application;

import com.whiter.kazimir.dagger.DaggerKazimirMainComponent;
import com.whiter.kazimir.dagger.KazimirMainComponent;

/**
 * Created by whiter
 */
public class App extends Application {

    private static App instance;
    private static KazimirMainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildComponent();
    }

    public static KazimirMainComponent component(){
        return component;
    }

    public static void buildComponent() {
        component = DaggerKazimirMainComponent.Initializer.init(instance);
    }
}
