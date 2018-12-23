package com.company.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Department;
import com.company.crm.service.IDepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private IDepartmentService service;
	

	@RequestMapping("/getSimpleDept")
	@ResponseBody
	public List<Department> getSimpleDept(){
		//System.out.println(soap);
		return service.getSimpleDept();
	}
	
}
