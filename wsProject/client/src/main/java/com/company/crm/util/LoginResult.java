package com.company.crm.util;

import com.company.crm.domain.Employee;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResult {
	private boolean success;
	private String msg;
	private String token;
	private Employee loginUser;

}
