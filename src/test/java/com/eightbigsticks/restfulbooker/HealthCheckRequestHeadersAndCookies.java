package com.eightbigsticks.restfulbooker;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HealthCheckRequestHeadersAndCookies extends BaseTest{
	
	@Test
	public void healthCheckTest() {
		
		// Inserting a header and cookies in the request
		
		Header myheader = new Header("some Name","Some Value");
		reqSpec.header(myheader);
		Cookie cookie = new Cookie.Builder("some Name","Some Value").build();
		reqSpec.cookie(cookie);
		
		Response response = RestAssured.given(reqSpec).
				cookie("Test cookie name", "Test cookie value")
				.header("Test Header Name", "Test Header value").log().all().get("/ping");
		
		
		// get the headers and cookies
		Headers headers = response.getHeaders();
		System.out.println("Headers in response: " + headers);
		
		Header serverHeader  = headers.get("Server");
		
		System.out.println("Server Headers via Headers: " + serverHeader.getName() + "---" + serverHeader.getValue());
		System.out.println("Server Headers via response: " + response.getHeader("Server"));
		
		Cookies cookies = response.getDetailedCookies();
		System.out.println("Cookies in response: " + cookies);
		
	}
}
