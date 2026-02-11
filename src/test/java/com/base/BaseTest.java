package com.base;

import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.utils.*;
import io.restassured.RestAssured;

public class BaseTest {
	protected static ExtentReports extent;
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = ConfigReader.getProperty("baseURI");
		System.out.println(RestAssured.baseURI);
		extent = ExtentManager.getReport();
	}
}
