package com.allen.cathayTest.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allen.cathayTest.entity.BitCoinRate;
import com.allen.cathayTest.response.JSONResult;
import com.allen.cathayTest.service.BitCoinRateService;
import com.allen.cathayTest.util.JsonUtil;

@RestController
@RequestMapping("/cathay/api")
public class BitCoinRateController {
	
	@Autowired
	private BitCoinRateService bitCoinRateService;

	@GetMapping("/getRateFromUrl")
	public ResponseEntity<String> getRateFromUrl() {
		try {
			JSONObject jsonObject = JsonUtil.getJsonFromUrl("https://api.coindesk.com/v1/bpi/currentprice.json");
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), jsonObject), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateBitCoinRate")
	public ResponseEntity<String> updateBitCoinRate() {
		try {
			JSONObject jsonObject = JsonUtil.getJsonFromUrl("https://api.coindesk.com/v1/bpi/currentprice.json");
			ArrayList<BitCoinRate> list = bitCoinRateService.updateCurrentPrice(jsonObject);
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getCurrencyInfoList")
	public ResponseEntity<String> getCurrencyInfoList() {
		try {
			List<?> list = bitCoinRateService.getCurrencyInfoList();
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);		}
	}
}
