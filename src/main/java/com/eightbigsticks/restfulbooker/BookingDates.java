package com.eightbigsticks.restfulbooker;

public class BookingDates {

	private String checkin;
	private String checkout;
	
	public BookingDates(String checkin, String checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	public BookingDates() {

	}
	
	
	

	@Override
	public String toString() {
		return "BookingDates [checkin=" + checkin + ", checkout=" + checkout + "]";
	}

	/**
	 * @return the checkin
	 */
	public String getCheckin() {
		return checkin;
	}

	/**
	 * @param checkin the checkin to set
	 */
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	/**
	 * @return the checkout
	 */
	public String getCheckout() {
		return checkout;
	}

	/**
	 * @param checkout the checkout to set
	 */
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
}
