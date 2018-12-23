package com.company.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public void clearSession(String token) {
		// 清除局部会话
		if (SSOUtil.TOKEN_SESSION_MAP.containsKey(token)) {
			HttpSession session = SSOUtil.TOKEN_SESSION_MAP.get(token);
			session.invalidate();
			// 从集合中移除映射
			SSOUtil.TOKEN_SESSION_MAP.remove(token);
			System.out.println("清空client的session");
		}
	}
}
