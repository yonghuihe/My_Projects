package com.eloan.p2p.api.test;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Salary {
	
	@JsonFormat(pattern="yyyy-MM",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM")
	private Date month;
	private Long empid;
	private BigDecimal salary;

	public Salary(Date month, Long empid, BigDecimal salary) {
		super();
		this.month = month;
		this.empid = empid;
		this.salary = salary;
	}

	public Salary() {
		super();
	}

}
