package com.design.parkinglot.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.design.parkinglot.gate.EntryGate;
import com.design.parkinglot.models.ParkingFloor;
import com.design.parkinglot.models.ParkingSpot;
import com.design.parkinglot.models.Ticket;
import com.design.parkinglot.models.Vehicle;
import com.design.parkinglot.strategy.PricingStrategy;
import com.design.parkinglot.strategy.PricingStrategyFactory;

public class ParkingLot {

	private Map<Integer, ParkingFloor> parkingFloors;
    private Map<String, Ticket> activeTickets = new HashMap<>();
    private PricingStrategyFactory factory = new PricingStrategyFactory();
    
    public ParkingLot(Map<Integer, ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }
	
	public Ticket parkVehicle(Vehicle vehicle, EntryGate gate) {
		for(ParkingFloor floor : parkingFloors.values()) {
			ParkingSpot spot = floor.allocateSpot(vehicle, gate);
			if(spot!=null) {
				Ticket ticket =  createTicket(spot, vehicle);
				activeTickets.put(ticket.getTicketId(), ticket);
				return ticket;
			}
		}
		return null;
	}
	
	public double exitVehicle(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        
        //free spot
        ParkingSpot spot  = ticket.getParkingSpot();
        ParkingFloor floor = parkingFloors.get(spot.getFloorId());
        floor.freeSpot(spot);
        activeTickets.remove(ticketId);
        
        //calculate fee
        PricingStrategy strategy = factory.getStrategy(ticket);
        double fee = strategy.calculateFee(ticket);

        
        return fee;
	}

	private Ticket createTicket(ParkingSpot spot, Vehicle vehicle) {
       
	    String ticketId = UUID.randomUUID().toString();
	    
	    Ticket ticket = new Ticket(
	            ticketId,
	            System.currentTimeMillis(),
	            spot,
	            vehicle
	        );
        return ticket;
	}
}
