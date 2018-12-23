package com.company.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {

	/*@Autowired
	private IEmployeeService service;*/

	@RequestMapping("/employee")
	public String index() {
		return "employee";
	}

}
