package com.design.parkinglot.gate;

import com.design.parkinglot.models.Ticket;
import com.design.parkinglot.models.Vehicle;
import com.design.parkinglot.service.ParkingLot;

public class EntryGate {
	
	int x;
	int y;
	
	public EntryGate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Ticket park(Vehicle vehicle, ParkingLot lot) {
		return lot.parkVehicle(vehicle, this);
	}
}
