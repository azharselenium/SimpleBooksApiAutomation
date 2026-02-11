package com.payload;

import org.json.JSONObject;

public class OrderPayload {
public static String createOrderPayload(String bookId,String customerName) {
		
	
		JSONObject json = new JSONObject();
		json.put("bookId", Integer.parseInt(bookId));
		json.put("customerName", customerName);
		
		return json.toString();
		
	}
}
