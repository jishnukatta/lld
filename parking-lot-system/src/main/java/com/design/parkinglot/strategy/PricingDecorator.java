package com.design.parkinglot.strategy;

import com.design.parkinglot.models.Ticket;

public abstract class PricingDecorator implements PricingStrategy {

	protected PricingStrategy baseStrategy;
	
	public PricingDecorator(PricingStrategy baseStrategy) {
		this.baseStrategy = baseStrategy;
	}
}
