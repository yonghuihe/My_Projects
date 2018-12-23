package com.company.crm.web.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Employee;
import com.company.crm.service.IEmployeeService;
import com.company.crm.util.JsonResult;
import com.company.crm.util.SSOUtil;
import com.company.crm.util.UserContext;

@Controller
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	@RequestMapping("/employee")
	public String index() {
		return "employee";
	}

	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(String username, String password, HttpServletRequest request) {

		JsonResult result = new JsonResult();

		Employee employee = service.getEmployeeForlogin(username, password);

		if (employee != null) {
			// 放入session中
			request.getSession().setAttribute(UserContext.USERINSSESION, employee);

			// 创建令牌（使用uuid生成）
			String token = UUID.randomUUID().toString();
			// 把令牌和session放到map集合中，在验证token是否合法时需要使用到session
			SSOUtil.TOKEN_SESSION_MAP.put(token, request.getSession());
			// 把令牌放入session中
			request.getSession().setAttribute("token", token);
			// 创建已经登录的标志
			request.getSession().setAttribute("isLogin", true);

			result.setSuccess(true);
			result.setMsg("登录成功!");
		} else {
			result.setMsg("密码错误,登录失败!");
		}
		return result;
	}

	@RequestMapping("/checkLogin")
	@ResponseBody
	public void checkLogin(HttpServletRequest request, HttpServletResponse response, String redirectURL)
			throws IOException {
		// 1、判断是否有已经登录的标志
		Object isLogin = request.getSession().getAttribute("isLogin");

		// 2、如果有，跳转到client之前的页面
		if (isLogin != null && (Boolean) isLogin) {
			// 获取当前登录的session中的令牌
			Object token = request.getSession().getAttribute("token");
			// 把token放到map中
			String key = UUID.randomUUID().toString();
			SSOUtil.KEY_TOKEN_MAP.put(key, (String) token);
			response.sendRedirect(redirectURL + "?key=" + key);//此时传递的是key，在checkToken时根据这个key找token
		} else {
			// 3、如果没有，重定向到登录页面
			response.sendRedirect("/login.jsp?redirectURL=" + redirectURL);
		}
	}

}
