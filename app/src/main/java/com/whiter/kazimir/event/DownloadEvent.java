package com.whiter.kazimir.event;

/**
 * Created by whiter
 */
public class DownloadEvent {
    private boolean success;

    public DownloadEvent(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
