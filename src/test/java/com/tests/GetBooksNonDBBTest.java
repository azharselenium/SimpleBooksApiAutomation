package com.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class GetBooksNonDBBTest {
	
	@Test
	public void getBooksNBDDTest() {
		Response respone = get("https://simple-books-api.click/books");
		respone.then().statusCode(200);
		respone.then().body("[0].id", notNullValue());
		System.out.println(respone.asPrettyString());
		
	}

}
