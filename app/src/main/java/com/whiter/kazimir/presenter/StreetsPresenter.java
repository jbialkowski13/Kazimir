package com.whiter.kazimir.presenter;

import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.whiter.kazimir.event.RefreshEvent;
import com.whiter.kazimir.event.StreetsEvent;
import com.whiter.kazimir.model.Street;
import com.whiter.kazimir.service.ServiceCaller;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by whiter
 */
public class StreetsPresenter extends BasePresenter<StreetsPresenter.Contract> {

    public interface Contract {
        void setStreets(List<Street> streets);
        void setRefreshing(boolean refreshing);
    }

    private List<Street> streets;

    private Bus bus;
    private Context context;


    public StreetsPresenter(Bus bus, Context context) {
        this.bus = bus;
        this.context = context;
    }

    @Override
    public void onResume(Contract contract) {
        super.onResume(contract);
        bus.register(this);
        if (streets == null) {
            ServiceCaller.getStreets(context);
        } else {
            setStreets(streets);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    public void refresh() {
        ServiceCaller.refreshStreets(context);
    }

    @Subscribe
    public void onStreetsEvent(StreetsEvent streetsEvent) {
        setStreets(streetsEvent.getStreets());
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent refreshEvent) {
        boolean success = refreshEvent.isSuccess();
        if (success) {
            setStreets(refreshEvent.getStreets());
        }
        Contract contract = getContract();
        contract.setRefreshing(false);
    }

    private void setStreets(List<Street> streets) {
        if (streets == null) {
            return;
        }
        this.streets = streets;
        Contract contract = getContract();
        if (contract != null) {
            contract.setStreets(this.streets);
        }
    }

}
