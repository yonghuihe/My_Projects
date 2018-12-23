package com.company.crm.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Menu;
import com.company.crm.util.UserContext;

@Controller
@SuppressWarnings("all")
public class MenuController {
	
	/*@Autowired
	private IMenuService service;*/
	
	@RequestMapping("/getRootMenu")
	@ResponseBody
	public List<Menu> getRootMenu(HttpSession session){
		return (List<Menu>) session.getAttribute(UserContext.MENUINSSESION);
	}
	
}
