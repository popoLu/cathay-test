package com.allen.cathayTest.entity.request;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CurrentPrice {

	private TimeZone time;
	private String disclaimer;
	private String chartName;
	private List<Bpi> bpiList;
	
}
