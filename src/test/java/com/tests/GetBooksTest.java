package com.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class GetBooksTest {
	@Test
	public void getBookStatus() {
		
		given().baseUri("https://simple-books-api.click")
		//https://simple-books-api.click - Uniform resoucrce Locator
		//https = protocol
		//simple-books-api.click = host
		// /books = URI
		.when()
			.get("/books")
		.then()
			.statusCode(200)
			.body("[0].id", notNullValue())
			.log().all();
			
	}
}
