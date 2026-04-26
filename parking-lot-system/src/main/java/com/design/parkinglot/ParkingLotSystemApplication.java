package com.design.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.design.parkinglot.enums.VehicleType;
import com.design.parkinglot.gate.EntryGate;
import com.design.parkinglot.models.ParkingFloor;
import com.design.parkinglot.models.ParkingSpot;
import com.design.parkinglot.models.Ticket;
import com.design.parkinglot.models.Vehicle;
import com.design.parkinglot.service.ParkingLot;

@SpringBootApplication
public class ParkingLotSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingLotSystemApplication.class, args);
	}

	
	@Bean
    public CommandLineRunner run() {
        return args -> {
        	
            EntryGate gate = new EntryGate(0, 0);

            // Create Parking Spots
            ParkingSpot s1 = new ParkingSpot("S1", VehicleType.CAR, 1, 2, 5);
            ParkingSpot s2 = new ParkingSpot("S2", VehicleType.CAR, 1, 5, 10);
            ParkingSpot s3 = new ParkingSpot("S3", VehicleType.CAR, 2, 10, 15);

            // Floor 1 setup
            Map<VehicleType, List<ParkingSpot>> floor1Spots = new HashMap<>();
            floor1Spots.put(VehicleType.CAR, new ArrayList<>());
            ParkingFloor floor1 = new ParkingFloor(1, floor1Spots);
            floor1.addSpot(s1);
            floor1.addSpot(s2);

            // Floor 2 setup
            Map<VehicleType, List<ParkingSpot>> floor2Spots = new HashMap<>();
            floor2Spots.put(VehicleType.CAR, new ArrayList<>());
            ParkingFloor floor2 = new ParkingFloor(2, floor2Spots);
            floor2.addSpot(s3);

            
            // ParkingLot setup
            Map<Integer, ParkingFloor> floorMap = new HashMap<>();
            floorMap.put(1, floor1);
            floorMap.put(2, floor2);
            ParkingLot parkingLot = new ParkingLot(floorMap);

            
            
            
            
            Vehicle vehicle = new Vehicle("HYD01", VehicleType.CAR);

            Ticket ticket = gate.park(vehicle, parkingLot);

            if (ticket == null) {
                System.out.println("No parking spot available");
                return;
            }

            System.out.println("Vehicle Parked. Ticket ID: " + ticket.getTicketId());

            ticket.setEntryTime(System.currentTimeMillis() - (2 * 60 * 60 * 1000));

            double fee = parkingLot.exitVehicle(ticket.getTicketId());
            System.out.println("Parking Fee: " + fee);
        };
    }
	
}
