package com.eightbigsticks.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBasicBookingTestWithXML extends BaseTest {

	@Test
	public void getBookingTest() {
		// Create booking
		Response responseCreate = createBooking();
		responseCreate.print();

		// Set path parameter
		reqSpec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));

		// Get response with booking
		Response responseGet = RestAssured.given(reqSpec).header("Accept", "application/xml").get("/booking/{bookingId}");
		responseGet.print();

		// Verify response 200
		Assert.assertEquals(responseGet.getStatusCode(), 200, "Status code should be 200, but it's not");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseGet.xmlPath().getString("booking.firstname"),"Rob","Wrong Value for firstName");
		softAssert.assertEquals(responseGet.xmlPath().getString("booking.lastname"),"Allan","Wrong Value for lastName");
		softAssert.assertEquals(responseGet.xmlPath().getBoolean("booking.depositpaid"),false,"Wrong Value for depositPaid");
		softAssert.assertEquals(responseGet.xmlPath().getString("booking.bookingdates.checkin"),"2023-06-30","Wrong Value for checkin");
		softAssert.assertEquals(responseGet.xmlPath().getString("booking.bookingdates.checkout"),"2023-07-15","Wrong Value for checkout");
		softAssert.assertEquals(responseGet.xmlPath().getString("booking.additionalneeds"),"Healthy Breakfast","Wrong Value for additional needs");
		softAssert.assertAll();
	}
	
}
