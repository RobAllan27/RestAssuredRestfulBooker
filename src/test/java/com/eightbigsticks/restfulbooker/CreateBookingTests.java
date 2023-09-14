package com.eightbigsticks.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBookingTests extends BaseTest {

	//@Test
	public void CreateBookingTest() {
		// Get response with booking ids
		
		Response response = createBooking();
		
		//response.print();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 - but instead");
		// The actual data at the far end may change - to find current state use curl -i https://restful-booker.herokuapp.com/booking/5 
		// Now using soft Assertions - as a comparison /  verify all fields
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.jsonPath().getString("booking.firstname"),"Rob","Wrong Value for firstName");
		softAssert.assertEquals(response.jsonPath().getString("booking.lastname"),"Allan","Wrong Value for lastName");
		softAssert.assertEquals(response.jsonPath().getInt("booking.totalprice"),150,"Wrong Value for totalPrice");
		softAssert.assertEquals(response.jsonPath().getBoolean("booking.depositpaid"),false,"Wrong Value for depositPaid");
		softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"),"2023-06-30","Wrong Value for checkin");
		softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"),"2023-07-15","Wrong Value for checkout");
		softAssert.assertEquals(response.jsonPath().getString("booking.additionalneeds"),"Healthy Breakfast","Wrong Value for additional needs");
		softAssert.assertAll("Here are the found values");
	}
	
	@Test
	public void CreateBookingTestWithPOJO() {
		// Get response with booking ids
		
		// Create Body using POJO
		BookingDates bookingdates = new BookingDates("2024-01-02", "2024-01-15");
		Booking booking = new Booking("Roberto", "Allano",150, false, "Happy Times Please", bookingdates);
		
		Response response = RestAssured.given(reqSpec)
				.contentType(ContentType.JSON)
				.body(booking)
				.post("/booking");
		
		//response.print();
		
		// let now get the POJO class - we deserialise it into a class.
		BookingId bookingid = response.as(BookingId.class);
		
		SoftAssert softAssertOnDeserializedClass = new SoftAssert();
		softAssertOnDeserializedClass.assertEquals(bookingid.getBooking().getFirstname(),"Roberto","Wrong Value for firstName");
		softAssertOnDeserializedClass.assertEquals(bookingid.getBooking().getLastname(),"Allano","Wrong Value for lastName");
		softAssertOnDeserializedClass.assertEquals(bookingid.getBooking().getTotalprice(),150,"Wrong Value for totalPrice");
		softAssertOnDeserializedClass.assertEquals(bookingid.getBooking().isDepositpaid(),false,"Wrong Value for depositPaid");
		softAssertOnDeserializedClass.assertEquals(bookingid.getBooking().getBookingdates().getCheckin(),"2024-01-02","Wrong Value for checkin");
		softAssertOnDeserializedClass.assertEquals(bookingid.getBooking().getBookingdates().getCheckout(),"2024-01-15","Wrong Value for checkout");
		softAssertOnDeserializedClass.assertEquals(bookingid.getBooking().getAdditionalneeds(),"Happy Times Please","Wrong Value for additional needs");
		softAssertOnDeserializedClass.assertAll("Here are the found values for the Failed POJP Dserlaisze classcase");
		
		
		
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 - but instead");
		// The actual data at the far end may change - to find current state use curl -i https://restful-booker.herokuapp.com/booking/5 
		// Now using soft Assertions - as a comparison /  verify all fields
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.jsonPath().getString("booking.firstname"),"Roberto","Wrong Value for firstName");
		softAssert.assertEquals(response.jsonPath().getString("booking.lastname"),"Allano","Wrong Value for lastName");
		softAssert.assertEquals(response.jsonPath().getInt("booking.totalprice"),150,"Wrong Value for totalPrice");
		softAssert.assertEquals(response.jsonPath().getBoolean("booking.depositpaid"),false,"Wrong Value for depositPaid");
		softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"),"2024-01-02","Wrong Value for checkin");
		softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"),"2024-01-15","Wrong Value for checkout");
		softAssert.assertEquals(response.jsonPath().getString("booking.additionalneeds"),"Happy Times Please","Wrong Value for additional needs");
		softAssert.assertAll("Here are the found values for the Failed case");
	}
}

