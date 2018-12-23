package com.company.crm.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.util.HttpUtil;
import com.company.crm.util.SSOUtil;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("name", "lucy");
		return "index";
	}

	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		// 获取当前session中的token，根据token获取对应的urls，逐个清除
		Object token = session.getAttribute("token");
		// 1、通知登录过的系统清除会话（1、client端存储登录的url，使用WebService或者HTTP，2、根据session中的token获取urls，并逐个清除）
		List<String> urls = SSOUtil.TOKEN_URLS_MAP.get(token);
		for (String url : urls) {
			HttpUtil.get(url + "/logout?token=" + token);
		}
		// 2、清除全局会话
		session.invalidate();
		// 3、重定向到登录页面
		response.sendRedirect("/login.jsp");
	}
}
