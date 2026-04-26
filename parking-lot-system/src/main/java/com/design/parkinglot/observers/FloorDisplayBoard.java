package com.design.parkinglot.observers;

import java.util.Map;

import com.design.parkinglot.enums.VehicleType;

public class FloorDisplayBoard implements Observer {

	@Override
	public void update(int floorId, Map<VehicleType, Integer> availability) {
        System.out.println("Floor " + floorId + " -> " + availability);
	}

}
