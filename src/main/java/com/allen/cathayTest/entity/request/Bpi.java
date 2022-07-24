package com.allen.cathayTest.entity.request;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Bpi {
	
	private String code;
	private String symbol;
	private String rate;
	private String description;
	private BigDecimal rateFloat;

}
