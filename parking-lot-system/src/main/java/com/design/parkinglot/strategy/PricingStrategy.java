package com.design.parkinglot.strategy;

import com.design.parkinglot.models.Ticket;

public interface PricingStrategy {
   
	double calculateFee(Ticket ticket);
}
