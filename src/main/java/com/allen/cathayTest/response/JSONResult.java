package com.allen.cathayTest.response;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JSONResult {

	public static String fillResultString(Integer code, String message, Object result) {
		JSONObject jsonObject = new JSONObject() {
			{
				put("Code", code);
				put("Message", message);
				put("Result", result);
			}
		};
		return jsonObject.toString();
	}
}
