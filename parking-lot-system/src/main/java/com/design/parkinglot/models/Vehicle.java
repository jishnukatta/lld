package com.design.parkinglot.models;

import com.design.parkinglot.enums.VehicleType;

public class Vehicle {

	String vehicleNumber;
	VehicleType vehicleType;
	
	public Vehicle(String vehicleNumber, VehicleType vehicleType) {
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
	}
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVechicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
}
