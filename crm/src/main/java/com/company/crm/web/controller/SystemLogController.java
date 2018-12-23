package com.company.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.page.PageResult;
import com.company.crm.query.SystemLogQueryObject;
import com.company.crm.service.ISystemLogService;

@Controller
public class SystemLogController {
	@Autowired
	ISystemLogService systemLogService;
	
	@RequestMapping("/systemLog")
	public String index(){
		return "systemLog";
	}
	@RequestMapping("/systemLog_list")
	@ResponseBody
	public PageResult list(SystemLogQueryObject qo){
		PageResult pageResult = null;
		pageResult = systemLogService.queryByConditionPage(qo);
		return pageResult;
	}
	
}
