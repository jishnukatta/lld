package com.design.parkinglot.models;

import com.design.parkinglot.enums.VehicleType;
import com.design.parkinglot.gate.EntryGate;

public class ParkingSpot {
	
	private String spotId;
	private VehicleType vehicleType;
	private boolean isOccupied;
	private Vehicle vehicle;
	private Integer floorId;
	
	private int x;
	private int y;
	
	
	public ParkingSpot(String spotId, VehicleType vehicleType, int floorId, int x , int y) {
		this.spotId = spotId;
		this.vehicleType = vehicleType;
		this.setFloorId(floorId);
		this.x = x;
		this.y = y;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	

	public boolean isAvailable() {
		return !isOccupied;
	}
	
	public void park(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.isOccupied = true;
	}
	
	public void remove() {
		this.vehicle = null;
		this.isOccupied = false;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public long calculateDistanceFromGate(EntryGate gate) {
		
		return Math.abs(gate.getX() - x) + Math.abs(gate.getY() - y);
	}
	    
}
