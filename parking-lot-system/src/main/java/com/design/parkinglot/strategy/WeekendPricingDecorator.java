package com.design.parkinglot.strategy;

import java.util.Calendar;

import com.design.parkinglot.models.Ticket;

public class WeekendPricingDecorator extends PricingDecorator {
	
	public WeekendPricingDecorator(PricingStrategy baseStrategy) {
		super(baseStrategy);
	}

	@Override
	public double calculateFee(Ticket ticket) {
		
		double fee = baseStrategy.calculateFee(ticket);
			
		return fee + 20;
	}

}
