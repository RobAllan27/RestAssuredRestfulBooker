package com.eightbigsticks.restfulbooker;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	protected RequestSpecification reqSpec;
	
	
	@BeforeMethod
	public void setUp() {
		reqSpec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
	}
	
	
	protected Response createBooking() {
		JSONObject body = new JSONObject();
		body.put("firstname", "Rob");
		body.put("lastname", "Allan");
		body.put("totalprice", 150);
		body.put("depositpaid", false);
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2023-06-30");
		bookingdates.put("checkout", "2023-07-15");
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Healthy Breakfast");
	
		return RestAssured.given(reqSpec)
				.contentType(ContentType.JSON)
				.body(body.toString())
				.post("/booking");
	}

}
