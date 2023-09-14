package com.eightbigsticks.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateBookingTests extends BaseTest {

	@Test
	public void UpdateBookingTest() {
		// Get response with booking ids
		
		// Create the booking
		Response response = createBooking();
		
		// Get the booking Id
		int bookingId = response.jsonPath().getInt("bookingid");
		response.print();
		
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Rob");
		body.put("lastname", "Allan");
		body.put("totalprice", 150);
		body.put("depositpaid", false);
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2023-07-02");
		bookingdates.put("checkout", "2023-07-17");
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Healthy Breakfast");
		
		// Add in username and password (would be more secure normally)
		/*
		Response responseUpdate = RestAssured.
				given().auth().preemptive().basic("admin","password123")
				.contentType(ContentType.JSON)
				.body(body.toString())
				.put("https://restful-booker.herokuapp.com/booking/" + bookingId);
		*/
		
		Response responseUpdate = RestAssured.
				given(reqSpec).auth().preemptive().basic("admin","password123")
				.contentType(ContentType.JSON)
				.body(body.toString())
				.put("https://restful-booker.herokuapp.com/booking/" + bookingId);
		
		responseUpdate.print();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 - but instead");
		// The actual data at the far end may change - to find current state use curl -i https://restful-booker.herokuapp.com/booking/5 
		// Now using soft Assertions - as a comparison /  verify all fields
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseUpdate.jsonPath().getString("firstname"),"Rob","Wrong Value for firstName");
		softAssert.assertEquals(responseUpdate.jsonPath().getString("lastname"),"Allan","Wrong Value for lastName");
		softAssert.assertEquals(responseUpdate.jsonPath().getInt("totalprice"),150,"Wrong Value for totalPrice");
		softAssert.assertEquals(responseUpdate.jsonPath().getBoolean("depositpaid"),false,"Wrong Value for depositPaid");
		softAssert.assertEquals(responseUpdate.jsonPath().getString("bookingdates.checkin"),"2023-07-02","Wrong Value for checkin");
		softAssert.assertEquals(responseUpdate.jsonPath().getString("bookingdates.checkout"),"2023-07-17","Wrong Value for checkout");
		softAssert.assertEquals(responseUpdate.jsonPath().getString("additionalneeds"),"Healthy Breakfast","Wrong Value for additional needs");
		softAssert.assertAll("Here are the found values");
	}
}

