package com.eightbigsticks.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBasicBookingTest extends BaseTest {

	@Test
	public void getBookingTest() {
		// Create booking
		Response responseCreate = createBooking();
		responseCreate.print();

		// Set path parameter
		reqSpec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));

		// Get response with booking
		Response responseGet = RestAssured.given(reqSpec).get("/booking/{bookingId}");
		responseGet.print();

		// Verify response 200
		Assert.assertEquals(responseGet.getStatusCode(), 200, "Status code should be 200, but it's not");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseGet.jsonPath().getString("firstname"),"Rob","Wrong Value for firstName");
		softAssert.assertEquals(responseGet.jsonPath().getString("lastname"),"Allan","Wrong Value for lastName");
		//softAssert.assertEquals(response.jsonPath().getInt("booking.totalprice"),"Allan","Wrong Value for lastName");
		softAssert.assertEquals(responseGet.jsonPath().getBoolean("depositpaid"),false,"Wrong Value for depositPaid");
		softAssert.assertEquals(responseGet.jsonPath().getString("bookingdates.checkin"),"2023-06-30","Wrong Value for checkin");
		softAssert.assertEquals(responseGet.jsonPath().getString("bookingdates.checkout"),"2023-07-15","Wrong Value for checkout");
		softAssert.assertEquals(responseGet.jsonPath().getString("additionalneeds"),"Healthy Breakfast","Wrong Value for additional needs");
		softAssert.assertAll();
	}

	@Test
	public void getBookingTestWithQueryParameter() {
		// Create booking
		Response responseCreate = createBooking();
		responseCreate.print();

		// Set query parameter to the specification
		reqSpec.queryParam("firstname","Rob");
		reqSpec.queryParam("lastname","Allan");

		// Get response with booking
		Response responseGet = RestAssured.given(reqSpec).get("/booking");
		System.out.println("Here's the get response for the query parameter case");
		responseGet.print();

		int firstFoundBooking = responseGet.jsonPath().getInt("[0].bookingid");
		
		// Verify response 200
		Assert.assertEquals(responseGet.getStatusCode(), 200, "Status code should be 200, but it's not");

		// let now verify the values
		Response responseGetForFirstBooking = RestAssured.given(reqSpec).get("/booking/" + firstFoundBooking);
		responseGetForFirstBooking.print();
		
		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseGetForFirstBooking.jsonPath().getString("firstname"),"Rob","Wrong Value for firstName");
		softAssert.assertEquals(responseGetForFirstBooking.jsonPath().getString("lastname"),"Allan","Wrong Value for lastName");
		softAssert.assertEquals(responseGetForFirstBooking.jsonPath().getBoolean("depositpaid"),false,"Wrong Value for depositPaid");
		softAssert.assertEquals(responseGetForFirstBooking.jsonPath().getString("bookingdates.checkin"),"2023-06-30","Wrong Value for checkin");
		softAssert.assertEquals(responseGetForFirstBooking.jsonPath().getString("bookingdates.checkout"),"2023-07-15","Wrong Value for checkout");
		softAssert.assertEquals(responseGetForFirstBooking.jsonPath().getString("additionalneeds"),"Healthy Breakfast","Wrong Value for additional needs");
		softAssert.assertAll();
	}
	
}
