package com.allen.cathayTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.allen.cathayTest.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String>{
	
	List<Currency> findByCode(String code);
	
	@Query(value = "SELECT * FROM CURRENCY WHERE CODE = :code", nativeQuery = true)
	Currency getCurrency(@Param("code") String code);
	
	@Query(value = "INSERT INTO CURRENCY VALUES (:code, :symbol, :discription, :currencyName)", nativeQuery = true)
	Currency addCurrency(@Param("code") String code, @Param("symbol") String symbol, @Param("discription") String discription, @Param("currencyName") String currencyName);

}
