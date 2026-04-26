package com.design.parkinglot.gate;

import com.design.parkinglot.service.ParkingLot;

public class ExitGate {

	public double exit(String ticketId, ParkingLot lot) {
		
		return lot.exitVehicle(ticketId);
	}
}
