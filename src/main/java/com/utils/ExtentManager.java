package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;
public class ExtentManager {
	private static ExtentReports extent;
	public static ExtentReports getReport() {
		if(extent == null) {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String reportPath = "reports/API_ExtentReport"+timeStamp+".html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			extent = new ExtentReports();
			extent.attachReporter(spark);
		}
		return extent;
		
	}
}
