package com.design.parkinglot.observers;

import java.util.Map;

import com.design.parkinglot.enums.VehicleType;

public interface Observer {

    void update(int floorId, Map<VehicleType, Integer> availability);
}
