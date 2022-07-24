package com.allen.cathayTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.allen.cathayTest.util.JsonUtil;


@SpringBootTest
class CathayTestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void test() {
		try {
			// 獲取url的json資訊
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = JsonUtil.getJsonFromUrl("https://api.coindesk.com/v1/bpi/currentprice.json");
			JSONObject timeObj = jsonObject.getJSONObject("time");
			// 取出即期匯率的時間
			String updated = timeObj.getString("updated");
			System.out.println(updated);
			// 將時區轉換為台灣時區
		    SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss zzz", Locale.ENGLISH);
		    Date date = format.parse(updated);
		    System.out.println(date);
		    // 獲取全部的即期匯率
		    JSONObject bpiObj = jsonObject.getJSONObject("bpi");
		    System.out.println(bpiObj);
		    // 獲取每一種幣別的匯率與名稱
		    @SuppressWarnings("unchecked")
			Iterator<String> keys = bpiObj.keys();
		    while (keys.hasNext()) {
		    	String key = keys.next();
		    	JSONObject innerObj = (JSONObject) bpiObj.get(key);
		    	System.out.println(innerObj);
		    	JSONObject currentObj = new JSONObject();
		    	currentObj.put("code", innerObj.getString("code"));
		    	currentObj.put("rate_float", innerObj.get("rate_float"));
		    	currentObj.put("updateTime", date);
		    	jsonArray.put(currentObj);
			}
		    // 將要更新的資料整理成list
		    System.out.println(jsonArray);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
