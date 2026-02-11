package com.payload;

import java.time.Instant;

import org.json.JSONObject;

public class ClientPayload {

	public static String createClientPayload() {
		
		long timeStamp = Instant.now().toEpochMilli();
		
		String email = "simpleBooks"+timeStamp+"@yopmail.com";
		JSONObject json = new JSONObject();
		json.put("clientName", "Test");
		json.put("clientEmail", email);
		
		return json.toString();
		
	}
}
