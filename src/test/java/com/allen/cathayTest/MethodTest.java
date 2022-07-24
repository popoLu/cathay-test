package com.allen.cathayTest;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.allen.cathayTest.controller.BitCoinRateController;
import com.allen.cathayTest.controller.CurrencyController;
import com.allen.cathayTest.entity.Currency;

@SpringBootTest
public class MethodTest {

	@Autowired
	private CurrencyController currencyController;
	
	@Autowired
	private BitCoinRateController bitCoinRateController;
	
	
	@Test
	void findAllCurrencies() {
		ResponseEntity<String> list = currencyController.findAllCurrency();
		System.out.println(list);
	}
	
	@Test
	void findByCode() {
		ResponseEntity<String> data = currencyController.findByCode("USD");
		System.out.println(data);
	}
	
	@Test
	void createCurrency() {
		Currency currency = new Currency("TWD", "&twd;", "Taiwan Dollar", "新台幣");
		ResponseEntity<String> addCur = currencyController.createCurrency(currency);
		System.out.println(addCur);
	}
	
	@Test
	void updateCurrencies() {
		ArrayList<Currency> list = new ArrayList<Currency>();
		Currency currency1 = new Currency("USD", "美金", "US Dollar", "&#36;");
		list.add(currency1);
		ResponseEntity<String> updateCurrencies = currencyController.updateCurrencies(list);
		System.out.println(updateCurrencies);
	}
	
	@Test
	void deleteCurrency() {
		ResponseEntity<String> deleteCurrency = currencyController.deleteCurrency("TWD");
		System.out.println(deleteCurrency);
	}
	
	@Test
	void getRateFromUrl() {
		ResponseEntity<String> data = bitCoinRateController.getRateFromUrl();
		System.out.println(data);
	}
	
	@Test
	void updateBitCoinRate() {
		ResponseEntity<String> updateList = bitCoinRateController.updateBitCoinRate();
		System.out.println(updateList);
	}
	
}
