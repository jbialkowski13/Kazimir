package com.whiter.kazimir.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.whiter.kazimir.BR;

/**
 * Created by whiter
 */
public class SplashViewModel extends BaseObservable {

    private boolean splashError;
    private View.OnClickListener retryListener;

    public SplashViewModel() {
    }

    @Bindable
    public boolean isSplashError() {
        return splashError;
    }

    public void setSplashError(boolean splashError) {
        this.splashError = splashError;
        notifyPropertyChanged(BR.splashError);
    }

    public void setRetryListener(View.OnClickListener retryListener) {
        this.retryListener = retryListener;
    }

    public View.OnClickListener getRetryListener() {
        return retryListener;
    }
}
