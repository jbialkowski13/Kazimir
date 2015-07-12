package com.whiter.kazimir.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.databinding.SplashActivityBinding;
import com.whiter.kazimir.presenter.SplashPresenter;
import com.whiter.kazimir.utils.Intents;
import com.whiter.kazimir.viewmodel.SplashViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by whiter
 */
public class SplashActivity extends AppCompatActivity implements SplashPresenter.Contract {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Inject
    SplashPresenter splashPresenter;
    @Inject
    Intents intents;

    private SplashViewModel splashViewModel = new SplashViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashActivityBinding splashActivityBinding = DataBindingUtil.setContentView(this, R.layout.splash_activity);
        splashViewModel.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashPresenter.downloadItems(true);
                splashViewModel.setSplashError(false);
            }
        });
        splashActivityBinding.setSplashViewModel(splashViewModel);
        App.component().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashPresenter.onResume(this);
        splashPresenter.downloadItems(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.onDestroy();
    }

    @Override
    public void onDownloadedSuccess() {
        intents.startStreetsActivity(this);
    }

    @Override
    public void onDownloadedFailed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                splashViewModel.setSplashError(true);
            }
        });
    }
}
