package com.whiter.kazimir.dagger;

import com.whiter.kazimir.presenter.SplashPresenter;
import com.whiter.kazimir.presenter.StreetsPresenter;
import com.whiter.kazimir.service.KazimirService;
import com.whiter.kazimir.ui.activity.MapActivity;
import com.whiter.kazimir.ui.activity.PlaceActivity;
import com.whiter.kazimir.ui.activity.PlaceListActivity;
import com.whiter.kazimir.ui.activity.SplashActivity;
import com.whiter.kazimir.ui.activity.StreetsActivity;

/**
 * Created by whiter
 */
public interface KazimirGraph {
    void inject(KazimirService kazimirService);

    void inject(SplashActivity splashActivity);

    void inject(StreetsActivity streetsActivity);

    void inject(PlaceActivity placeActivity);

    void inject(StreetsPresenter streetsPresenter);

    void inject(SplashPresenter splashPresenter);

    void inject(PlaceListActivity placeListActivity);

    void inject(MapActivity mapActivity);
}
