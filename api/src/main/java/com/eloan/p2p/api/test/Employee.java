package com.eloan.p2p.api.test;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Employee {

	private Long id;
	private String name;

	public Employee(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Employee() {
		super();
	}

}
