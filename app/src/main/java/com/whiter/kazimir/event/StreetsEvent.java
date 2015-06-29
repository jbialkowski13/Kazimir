package com.whiter.kazimir.event;

import com.whiter.kazimir.model.Street;

import java.util.List;

/**
 * Created by whiter
 */
public class StreetsEvent {
    private List<Street> streets;

    public StreetsEvent(List<Street> streets) {
        this.streets = streets;
    }

    public List<Street> getStreets() {
        return streets;
    }
}
