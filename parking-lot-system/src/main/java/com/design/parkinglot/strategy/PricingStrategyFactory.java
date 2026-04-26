package com.design.parkinglot.strategy;

import java.util.Calendar;

import com.design.parkinglot.models.Ticket;

public class PricingStrategyFactory {

	public PricingStrategy getStrategy(Ticket ticket) {
		
		if(isWeekend(ticket))
			return new WeekendPricingDecorator(new HourlyPricingStrategy());
		
		return new HourlyPricingStrategy();
	}

	private boolean isWeekend(Ticket ticket) {
       
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(ticket.getEntryTime());
        int day = cal.get(Calendar.DAY_OF_WEEK);
		
		return day == Calendar.SATURDAY || day == Calendar.SUNDAY;		
	}

}
