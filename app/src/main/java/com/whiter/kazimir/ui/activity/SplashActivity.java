package com.whiter.kazimir.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.event.DownloadEvent;
import com.whiter.kazimir.service.ServiceCaller;

import javax.inject.Inject;

/**
 * Created by whiter
 */
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Inject
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        App.component().inject(this);
        bus.register(this);
        ServiceCaller.downloadStreets(this);
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onDownloadEvent(DownloadEvent downloadEvent) {
        if (downloadEvent.isSuccess()) {
            startMainActivity();
        } else {
            //todo: show warning (maybe internet is disabled or sm went wrong)
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(this, StreetsActivity.class));
        finish();
    }
}
