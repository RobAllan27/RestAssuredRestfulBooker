package com.eightbigsticks.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteBookingTests extends BaseTest {

	@Test
	public void deleteBookingTest() {
		// Get response with booking ids
		
		// Create the booking
		Response response = createBooking();
		
		// Get the booking Id
		int bookingId = response.jsonPath().getInt("bookingid");
		response.print();
		
		/*
		Response response = 
				RestAssured.given(reqSpec)
				.auth().preemptive().basic("admin","password123")
				.contentType(ContentType.JSON)
				.delete("https://restful-booker.herokuapp.com/booking/" + bookingId);
*/
		
		
		Response responseDelete = RestAssured.
				given(reqSpec).auth().preemptive().basic("admin","password123")
				.contentType(ContentType.JSON)
				.delete("/booking/" + bookingId);
		
		responseDelete.print();
		
		Assert.assertEquals(responseDelete.getStatusCode(), 201, "Status code should be 201 - but instead");

		// lts chek it no longer exists
		Response responseGET = RestAssured.
				given()
				.get("https://restful-booker.herokuapp.com/booking/" + bookingId);
		
		responseGET.print();
		
		// Now using soft Assertions - as a comparison /  verify all fields
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseGET.getStatusCode(), 404, "Status code should be 404 - but instead");
		softAssert.assertEquals(responseGET.getStatusLine(), "HTTP/1.1 404 Not Found", "Status code should be Not Found - but instead");
		softAssert.assertAll("Here are the found values");
		
	}
}

