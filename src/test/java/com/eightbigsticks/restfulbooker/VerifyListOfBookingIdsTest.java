package com.eightbigsticks.restfulbooker;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class VerifyListOfBookingIdsTest {

	@Test
	public void verifyBookingIdsNoFilterTest() {
		// Get response with booking ids
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
		System.out.println("Here is the message body");
		response.print();

		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

		// Verify at least 1 booking id in response
		List<Integer> bookingIdsList = response.jsonPath().getList("bookingid");
		Assert.assertTrue(bookingIdsList.size() > 5);
		Assert.assertFalse(bookingIdsList.isEmpty(), "we expected that the list of booking ids is not empty ");
	}
	
	@Test
	public void verifyBookingNamesForID() {
		// Get response with booking ids
		
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/2");

		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 - but instead");

		// Verify at least 1 booking id in response
		String firstName = response.jsonPath().getString("firstname");
		String lastName = response.jsonPath().getString("lastname");
		Assert.assertTrue(firstName.equals("Mary"),"we did not get the right first name");
		Assert.assertTrue(lastName.equals("Ericsson"),"we did not get the right last name");
		
	}
	
	@Test
	public void verifyBookingDetailsForID() {
		// Get response with booking ids
		
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/5");

		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 - but instead");
		// The actual data at the far end may change - to find current state use curl -i https://restful-booker.herokuapp.com/booking/5 
		// Now using soft Assertions - as a comparison /  verify all fields
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.jsonPath().getString("firstname"),"Susan","Wrong Value for firstName");
		softAssert.assertEquals(response.jsonPath().getString("lastname"),"Brown","Wrong Value for lastName");
		softAssert.assertEquals(response.jsonPath().getInt("totalprice"),506,"Wrong Value for totalPrice");
		softAssert.assertEquals(response.jsonPath().getBoolean("depositpaid"),true,"Wrong Value for depositPaid");
		softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"),"2019-07-18","Wrong Value for checkin");
		softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"),"2023-03-19","Wrong Value for checkout");
		softAssert.assertAll("Here are the found values");
	}
	
	//Use rest assured JSONPath
}
