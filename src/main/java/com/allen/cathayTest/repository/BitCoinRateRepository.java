package com.allen.cathayTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allen.cathayTest.entity.BitCoinRate;

@Repository
public interface BitCoinRateRepository extends JpaRepository<BitCoinRate, String> {

	@Query(value = "SELECT c.code, c.symbol, c.description, c.currency_name, b.rate, b.format_date FROM CURRENCY c JOIN BITCOIN_RATE b on c.code = b.code", nativeQuery = true)
	List<?> getCurrencyInfo();
}
