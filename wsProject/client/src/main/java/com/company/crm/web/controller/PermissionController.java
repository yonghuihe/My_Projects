package com.company.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Permission;
import com.company.crm.service.IPermissionService;

@Controller
public class PermissionController {
	
	@Autowired
	private IPermissionService service;
	
	
	@RequestMapping("/permission_list")
	@ResponseBody
	public List<Permission> list(){
		return service.selectAll();
	}
	
	@RequestMapping("/permission_getPermissionByRid")
	@ResponseBody
	public List<Permission> getPermissionByRid(Long id){
		return service.getPermissionByRid(id);
	}

}
