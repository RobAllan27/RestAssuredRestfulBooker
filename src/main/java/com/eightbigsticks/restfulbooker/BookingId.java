package com.eightbigsticks.restfulbooker;

public class BookingId {

	private int bookingid;
	private Booking booking;
	
	@Override
	public String toString() {
		return "BookingId [bookingid=" + bookingid + ", booking=" + booking + "]";
	}
	/**
	 * @return the bookingid
	 */
	public int getBookingid() {
		return bookingid;
	}
	/**
	 * @param bookingid the bookingid to set
	 */
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	/**
	 * @return the booking
	 */
	public Booking getBooking() {
		return booking;
	}
	/**
	 * @param booking the booking to set
	 */
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public BookingId() {
	}
	
	
}
