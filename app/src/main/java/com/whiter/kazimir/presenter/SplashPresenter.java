package com.whiter.kazimir.presenter;

import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.whiter.kazimir.App;
import com.whiter.kazimir.event.DownloadEvent;
import com.whiter.kazimir.service.ServiceCaller;

import javax.inject.Inject;

/**
 * Created by whiter
 */
public class SplashPresenter extends BasePresenter<SplashPresenter.Contract> {

    public interface Contract {
        void onDownloadedSuccess();

        void onDownloadedFailed();
    }

    @Inject
    Bus bus;

    @Inject
    Context context;

    private DownloadEvent downloadEvent;

    public SplashPresenter() {
        App.component().inject(this);
    }

    public void downloadItems(boolean force) {
        if (downloadEvent == null || force) {
            ServiceCaller.downloadStreets(context);
        } else {
            onDownloaded();
        }
    }

    @Override
    public void onResume(Contract contract) {
        super.onResume(contract);
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    void releaseResources() {
        this.downloadEvent = null;
    }

    @Subscribe
    public void onDownloadEvent(DownloadEvent downloadEvent) {
        this.downloadEvent = downloadEvent;
        onDownloaded();
    }

    private void onDownloaded() {
        if (contract != null) {
            if (downloadEvent.isSuccess()) {
                contract.onDownloadedSuccess();
            } else {
                contract.onDownloadedFailed();
            }
        }
    }
}
