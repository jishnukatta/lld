package com.design.parkinglot.models;

public class Ticket {
	
	private String TicketId;
	private long entryTime;
	private ParkingSpot parkingSpot;
	private Vehicle vehicle;
	
	
	
	public Ticket(String ticketId, long entryTime, ParkingSpot parkingSpot, Vehicle vehicle) {
		this.TicketId = ticketId;
		this.entryTime = entryTime;
		this.parkingSpot = parkingSpot;
		this.vehicle = vehicle;
	}
	
	
	public String getTicketId() {
		return TicketId;
	}
	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}
	public long getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(long entryTime) {
		this.entryTime = entryTime;
	}
	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}
	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
