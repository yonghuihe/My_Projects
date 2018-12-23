package com.company.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Weather {
	private Long id;
	private String city;
	private String temparatrue;
	private String condition;
}
