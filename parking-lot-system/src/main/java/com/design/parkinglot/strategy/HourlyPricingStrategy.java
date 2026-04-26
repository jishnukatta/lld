package com.design.parkinglot.strategy;

import com.design.parkinglot.models.Ticket;

public class HourlyPricingStrategy implements PricingStrategy {

    private static final double RATE_PER_HOUR = 10.0;

    
	@Override
	public double calculateFee(Ticket ticket) {
       
        long durationMillis = System.currentTimeMillis() - ticket.getEntryTime();
        long hours = durationMillis / (1000 * 60 * 60);
        if (hours == 0) {
            hours = 1;
        }

        return hours * RATE_PER_HOUR;
	}

}
