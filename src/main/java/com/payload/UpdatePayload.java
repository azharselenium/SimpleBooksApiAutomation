package com.payload;

import org.json.JSONObject;

public class UpdatePayload {
	public static String createUpdatePayload() {
		
		
		JSONObject json = new JSONObject();
		json.put("customerName", "TestQA");
		
		return json.toString();
		
	}

}
