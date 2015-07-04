package com.whiter.kazimir.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.whiter.kazimir.App;
import com.whiter.kazimir.R;
import com.whiter.kazimir.presenter.SplashPresenter;
import com.whiter.kazimir.utils.Intents;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
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

    @InjectView(R.id.splash_progress)
    CircleProgressBar circleProgressBar;
    @InjectView(R.id.retry_section)
    LinearLayout retrySection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.inject(this);
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
    public void onDownloadedSuccess() {
        intents.startStreetsActivity(this);
    }

    @Override
    public void onDownloadedFailed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                circleProgressBar.setVisibility(View.INVISIBLE);
                retrySection.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick(R.id.retry_button)
    public void onRetryClicked() {
        splashPresenter.downloadItems(true);
        circleProgressBar.setVisibility(View.VISIBLE);
        retrySection.setVisibility(View.INVISIBLE);
    }
}
