package com.eightbigsticks.restfulbooker;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class HealthCheckRequestSpecificationTest extends BaseTest{
	
	@Test
	public void healthCheckTest() {
		given().spec(reqSpec).
		when().
			get("/ping").
		then().
			assertThat().
			statusCode(201);
	}
}
