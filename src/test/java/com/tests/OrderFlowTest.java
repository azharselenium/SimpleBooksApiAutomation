package com.tests;

import static org.hamcrest.Matchers.notNullValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.payload.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.base.BaseTest;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import com.utils.*;

public class OrderFlowTest extends BaseTest{
	
	public static final Logger log = LoggerUtil.getLogger(OrderFlowTest.class);
	private ExtentReports extent;
	private ExtentTest test;
	
	@BeforeClass
	public void setupReport() {
		extent = ExtentManager.getReport();
	}
	@Test
	public void createClient() throws FileNotFoundException, IOException {
		test = extent.createTest("Simple Books API End to End Test");
		try {
			log.info("=====Test Started =======");
			test.info("Starting Simple Books API End to End Test");
			
		//Create Client
			
			log.info("Creating API Client");
			test.info("Creating API Client");
			
			Response response = 
			given()
				.body(ClientPayload.createClientPayload())
			.when()
				.post("/api-clients")
			.then()
				.statusCode(201)
				.body("accessToken", notNullValue())
				.extract().response();
			
			String token = response.jsonPath().getString("accessToken");
			log.info("Client successfully created");
			test.pass("Client successfully created");
			
			//Read CSV File
			
			log.info("Reading through CSV File");
			test.info("Reading through CSV File");
			
			List<String[]> data = CSVReaderUtil.readCSV("src\\main\\resources\\testdata.csv");
			String bookId = data.get(1)[0];
			String customerName = data.get(1)[1];
			log.info("book id = "+bookId+" customerName = "+customerName);
			test.pass("book id = "+bookId+" customerName = "+customerName);
			//Create Order
			
			log.info("Creating order");
			test.info("Creating order");
			
			Response orderResponse = 
					given()
						.body(OrderPayload.createOrderPayload(bookId,customerName))
						.header("Authorization","Bearer "+token)
					.when()
						.post("/orders")
					.then()
						.statusCode(201)
						.body("created", notNullValue())
						.body("orderId", notNullValue())
						.extract().response();
					
					String orderId = orderResponse.jsonPath().getString("orderId");
					System.out.println("Order ID:"+orderId);
					log.info("Order created with Order ID"+orderId);
					test.pass("Order created with Order ID"+orderId);
					
					
			//Update Order
					log.info("Updating order");
					test.info("Updating order");
					
					Response updatedOrderResponse = 
							given()
								.body(UpdatePayload.createUpdatePayload())
								.header("Authorization","Bearer "+token)
							.when()
								.patch("/orders/"+orderId)
							.then()
								.statusCode(204)
								.extract().response();
	
					log.info("Patch status code : "+updatedOrderResponse.getStatusCode());
					test.pass("Patch status code : "+updatedOrderResponse.getStatusCode());
					
					//Search Order
					
					log.info("Search order");
					test.info("Search order");
					
					Response searchOrderResponse = 
							given()
								.header("Authorization","Bearer "+token)
							.when()
								.get("/orders/"+orderId)
							.then()
								.statusCode(200)
								.body("id",notNullValue())
								.body("bookId",notNullValue())
								.body("customerName",notNullValue())
								.body("quantity",notNullValue())
								.extract().response();
					
					log.info("Search order path : "+ searchOrderResponse.asPrettyString());
					
					//Delete Order
					Response deleteOrderResponse = 
							given()
								.header("Authorization","Bearer "+token)
							.when()
								.delete("/orders/"+orderId)
							.then()
								.statusCode(204)
								.extract().response();
					log.info("Deleted order with status code : "+deleteOrderResponse.getStatusCode());
					test.pass("Deleted order with status code : "+deleteOrderResponse.getStatusCode());
		}catch(Exception e) {
			e.printStackTrace();
			test.fail("Test Failed"+e.getMessage());
			throw e;
		}
		
	}
	@AfterClass
	public void tearDown() {
		extent.flush();
	}
}
