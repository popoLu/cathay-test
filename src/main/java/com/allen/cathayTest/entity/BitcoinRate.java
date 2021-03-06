package com.allen.cathayTest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "BITCOIN_RATE")
public class BitCoinRate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CODE")
	private String code;
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	@Column(name = "FORMAT_DATE")
	private String formatDate;
	@Column(name = "RATE")
	private BigDecimal rate;
}
