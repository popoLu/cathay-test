package com.allen.cathayTest.entity.request;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class TimeZone {

	private String updated;
	private String updatedISO;
	private String updateduk;
	
}
