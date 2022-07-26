package com.allen.cathayTest.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.cathayTest.entity.BitCoinRate;
import com.allen.cathayTest.repository.BitCoinRateRepository;


@Service
public class BitCoinRateService {

	@Autowired
	private BitCoinRateRepository bitCoinRateRepository;
	
	public ArrayList<BitCoinRate> updateCurrentPrice(JSONObject jsonObject) {
//		JSONArray jsonArray = new JSONArray();
		ArrayList<BitCoinRate> list = new ArrayList<BitCoinRate>();
		try {
			JSONObject timeObj = jsonObject.getJSONObject("time");
			// 取出即期匯率的時間
			String updated = timeObj.getString("updated");
			// 將時區轉換為台灣時區
		    SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss zzz", Locale.ENGLISH);
		    Date date = format.parse(updated);
		    // 轉換時間格式
		    SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
		    String formatDate = dateFormat.format(date);
		    
		    // 獲取全部的即期匯率
		    JSONObject bpiObj = jsonObject.getJSONObject("bpi");
		    // 獲取每一種幣別的匯率與名稱
			Iterator<String> keys = bpiObj.keys();
		    while (keys.hasNext()) {
		    	String key = keys.next();
		    	JSONObject innerObj = (JSONObject) bpiObj.get(key);
		    	BitCoinRate bitCoinRate = new BitCoinRate();
		    	bitCoinRate.setCode(innerObj.getString("code"));
		    	bitCoinRate.setRate(BigDecimal.valueOf(innerObj.getDouble("rate_float")));
		    	bitCoinRate.setUpdateTime(date);
		    	bitCoinRate.setFormatDate(formatDate);
		    	bitCoinRateRepository.save(bitCoinRate);
		    	list.add(bitCoinRate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public List<?> getCurrencyInfoList() {
		List<?> list = bitCoinRateRepository.getCurrencyInfo();
		return list;
	}
}
