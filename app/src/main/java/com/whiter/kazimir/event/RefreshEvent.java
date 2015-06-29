package com.whiter.kazimir.event;

import android.support.annotation.Nullable;

import com.whiter.kazimir.model.Street;

import java.util.List;

/**
 * Created by whiter
 */
public class RefreshEvent {
    @Nullable
    private List<Street> streets;

    private boolean success;

    public RefreshEvent(@Nullable List<Street> streets, boolean success) {
        this.streets = streets;
        this.success = success;
    }

    @Nullable
    public List<Street> getStreets() {
        return streets;
    }

    public boolean isSuccess() {
        return success;
    }
}
