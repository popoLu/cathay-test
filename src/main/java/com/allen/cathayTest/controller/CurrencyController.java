package com.allen.cathayTest.controller;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allen.cathayTest.entity.Currency;
import com.allen.cathayTest.response.JSONResult;
import com.allen.cathayTest.service.CurrencyService;

@RestController
@RequestMapping("/cathay/api")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;
	
	@Transactional
	@PostMapping("/addCurrency")
	public ResponseEntity<String> createCurrency(@RequestBody Currency currency) {
		
		try {
			Currency createCurrency = currencyService.createCurrency(currency);
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), createCurrency), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAllCurrencies")
	public ResponseEntity<String> findAllCurrency() {
		try {
			List<Currency> list = currencyService.getAllCurrencies();
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("getCurrency/{code}")
	public ResponseEntity<String> findByCode(@PathVariable("code") String code) {
		try {
			Optional<Currency> data = currencyService.getCurrencyByCode(code);
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data.get()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@PutMapping("/updateCurrency")
	public ResponseEntity<String> updateCurrency(@RequestBody Currency currency) {
		try {
			Optional<Currency> data = currencyService.getCurrencyByCode(currency.getCode());
			if (data.isPresent()) {
				Currency updateCurrency = currencyService.updateCurrency(currency);
				return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), updateCurrency), HttpStatus.OK);
			} else {
				ResponseEntity<String> addCurrency = createCurrency(currency);
				return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), addCurrency), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@PutMapping("updateCurrencies")
	public ResponseEntity<String> updateCurrencies(@RequestBody List<Currency> list) {
		try {
			for (Currency currency : list) {
				Optional<Currency> data = currencyService.getCurrencyByCode(currency.getCode());
				if (data.isPresent()) {
					currencyService.updateCurrency(currency);
				} else {
					createCurrency(currency);
				}
			}
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@DeleteMapping("/deleteCurrency/{code}")
	public ResponseEntity<String> deleteCurrency(@PathVariable("code") String code) {
		try {
			Optional<Currency> data = currencyService.getCurrencyByCode(code);
			if (data.isPresent()) {
				currencyService.deleteCurrency(code);
			}
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		try {
			currencyService.deleteAll();
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(JSONResult.fillResultString(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
