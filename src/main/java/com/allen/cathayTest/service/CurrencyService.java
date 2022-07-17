package com.allen.cathayTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.cathayTest.entity.Currency;
import com.allen.cathayTest.repository.CurrencyRepository;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;
	
	public Currency createCurrency(Currency currency) {
		Currency newOne = currencyRepository.save(new Currency(currency.getCode(), currency.getSymbol(), currency.getDescription(), currency.getCurrencyName()));
		return newOne;
	}
	
	public List<Currency> getAllCurrencies() {
		List<Currency> list = currencyRepository.findAll();
		return list;
	}
	
	public Optional<Currency> getCurrencyByCode(String code) {
		Optional<Currency> currency = currencyRepository.findById(code.toUpperCase());
		return currency;
	}
	
	public Currency updateCurrency(Currency currency) {
		Currency updateData = currencyRepository.save(currency);
		return updateData;
	}
	
	public List<Currency> updateCurrencies(List<Currency> list) {
		List<Currency> updateList = currencyRepository.saveAll(list);
		return updateList;
	}
	
	public void deleteCurrency(String code) {
		currencyRepository.deleteById(code.toUpperCase());
	}
	
	public void deleteAll() {
		currencyRepository.deleteAll();
	}
	
	public void addCurrency(Currency currency) {
		currencyRepository.save(new Currency(currency.getCode(), currency.getSymbol(), currency.getDescription(), currency.getCurrencyName()));
	}
}
