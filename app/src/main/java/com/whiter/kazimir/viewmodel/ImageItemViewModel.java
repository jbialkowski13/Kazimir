package com.whiter.kazimir.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.whiter.kazimir.BR;

/**
 * Created by whiter
 */
public class ImageItemViewModel extends BaseObservable {

    private String imageUrl;

    public ImageItemViewModel() {
    }

    public void setUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }
}
