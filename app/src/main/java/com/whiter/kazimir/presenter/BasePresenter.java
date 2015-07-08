package com.whiter.kazimir.presenter;

/**
 * Created by whiter
 */
public abstract class BasePresenter<T> {

    protected T contract;

    public void onResume(T contract) {
        this.contract = contract;
    }


    public void onPause() {
        contract = null;
    }


    public T getContract() {
        return contract;
    }

    public void onDestroy() {
        releaseResources();
    }

    abstract void releaseResources();
}
