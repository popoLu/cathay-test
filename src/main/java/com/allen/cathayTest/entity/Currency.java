package com.allen.cathayTest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CURRENCY")
public class Currency {

	@Id
	@Column(name = "CODE")
	private String code;
	@Column(name = "SYMBOL")
	private String symbol;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "CURRENCY_NAME")
	private String currencyName;
	
}
