package com.whiter.kazimir.ui.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by whiter
 */
public abstract class BaseFragment<T> extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();

    protected T contract;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            contract = (T) activity;
        } catch (ClassCastException e){
            Log.w(TAG, "Activity " + activity.getClass().getSimpleName() + " cannot be cast to " + contract.getClass().getSimpleName());
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        contract = null;
    }
}
