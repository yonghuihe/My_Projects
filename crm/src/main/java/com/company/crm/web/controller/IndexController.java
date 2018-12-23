package com.company.crm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.crm.util.PermissionName;

@Controller
public class IndexController {

	@RequestMapping("/index")
	@RequiresPermissions("index")
	@PermissionName("主页")
	public String index(){
		return "index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		// 直接跳转到页面
		System.out.println("是否认证："+SecurityUtils.getSubject().isAuthenticated());
		return "redirect:login.jsp";
	}

}
