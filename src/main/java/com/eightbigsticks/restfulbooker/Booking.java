package com.eightbigsticks.restfulbooker;

public class Booking {

	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private String additionalneeds;
	private BookingDates bookingdates;
	
	public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds,
			BookingDates bookingdates) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.additionalneeds = additionalneeds;
		this.bookingdates = bookingdates;
	}
	
	public Booking() {
	}	
	
	@Override
	public String toString() {
		return "Booking [firstname=" + firstname + ", lastname=" + lastname + ", totalprice=" + totalprice
				+ ", depositpaid=" + depositpaid + ", additionalneeds=" + additionalneeds + ", bookingdates="
				+ bookingdates + "]";
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the totalprice
	 */
	public int getTotalprice() {
		return totalprice;
	}

	/**
	 * @param totalprice the totalprice to set
	 */
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	/**
	 * @return the depositpaid
	 */
	public boolean isDepositpaid() {
		return depositpaid;
	}

	/**
	 * @param depositpaid the depositpaid to set
	 */
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	/**
	 * @return the additionalneeds
	 */
	public String getAdditionalneeds() {
		return additionalneeds;
	}

	/**
	 * @param additionalneeds the additionalneeds to set
	 */
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	/**
	 * @return the bookingdates
	 */
	public BookingDates getBookingdates() {
		return bookingdates;
	}

	/**
	 * @param bookingdates the bookingdates to set
	 */
	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}

}

