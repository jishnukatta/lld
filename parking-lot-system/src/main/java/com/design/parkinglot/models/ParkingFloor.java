package com.design.parkinglot.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.design.parkinglot.enums.VehicleType;
import com.design.parkinglot.gate.EntryGate;
import com.design.parkinglot.observers.Observer;

public class ParkingFloor {

	private int floorId;
	private Map<VehicleType, List<ParkingSpot>> parkingSpots;
	private Map<VehicleType, Integer> availableCount = new HashMap<>();
	private Map<VehicleType, Object> locks = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();
    
    
    public ParkingFloor(int floorId, Map<VehicleType, List<ParkingSpot>> parkingSpots) {
        this.floorId = floorId;
        this.parkingSpots = parkingSpots;
        
        for (VehicleType type : VehicleType.values()) {
            availableCount.put(type, 0);
            locks.put(type, new Object());
        }
    }
	
	
	public ParkingSpot findSpot(Vehicle vehicle, EntryGate gate) {
		ParkingSpot best = null;
		long mini = Long.MAX_VALUE;
        for(ParkingSpot spot : parkingSpots.get(vehicle.getVehicleType())) {
        	
        	if(!spot.isAvailable()) continue;
        	
        	long dist = spot.calculateDistanceFromGate(gate);
        	if(dist < mini)
        	{
        		best = spot;
        		mini = dist;
        	}
        }
        
        return best;
	}
	
	public ParkingSpot allocateSpot(Vehicle vehicle, EntryGate gate) {
 
		ParkingSpot spot = null;
		
		synchronized (locks.get(vehicle.getVehicleType())) {
			spot = findSpot(vehicle, gate);
			if (spot != null) {
				spot.park(vehicle);
				updateCount(vehicle.getVehicleType(), -1);
				notifyObservers();
			}
		}

		return spot;
	}
	
	public void freeSpot(ParkingSpot spot) {

		synchronized (locks.get(spot.getVehicleType())) {
			spot.remove();
			updateCount(spot.getVehicleType(), 1);
			notifyObservers();
		}
	}
	

	private void updateCount(VehicleType type, int delta) {
        availableCount.put(type,
            availableCount.getOrDefault(type, 0) + delta);
    }
	
    public Map<VehicleType, Integer> getAvailability() {
        return availableCount;
    }

    public void registerObserver(Observer o) {
    	observers.add(o);
    }

    private void notifyObservers() {
       for(Observer o : observers) {
    	   o.update(floorId,new HashMap<>(availableCount));
       }	
	}
    
    public void addSpot(ParkingSpot spot) {
    	parkingSpots
        .computeIfAbsent(spot.getVehicleType(), k -> new ArrayList<>())
        .add(spot);
        updateCount(spot.getVehicleType(), 1);
    }

}
