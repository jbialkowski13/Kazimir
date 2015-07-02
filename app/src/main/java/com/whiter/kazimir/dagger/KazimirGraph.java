package com.whiter.kazimir.dagger;

import com.whiter.kazimir.presenter.StreetsPresenter;
import com.whiter.kazimir.service.KazimirService;
import com.whiter.kazimir.ui.activity.StreetsActivity;
import com.whiter.kazimir.ui.activity.SplashActivity;

/**
 * Created by whiter
 */
public interface KazimirGraph {
    void inject(KazimirService kazimirService);
    void inject(SplashActivity splashActivity);
    void inject(StreetsActivity streetsActivity);
    void inject(StreetsPresenter streetsPresenter);
}
