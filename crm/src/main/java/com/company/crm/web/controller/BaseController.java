package com.company.crm.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.company.crm.util.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseController {

	/**
	 * 处理没有权限的异常
	 * 页面包认证异常后，执行这个方法
	 * @throws IOException 
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public void handleShiroExcepiton(HandlerMethod method,HttpServletResponse response) throws IOException{
		ResponseBody annotation = method.getMethodAnnotation(ResponseBody.class);
		//如果是ajax请求，返回AjaxResult给前台
		if(annotation != null){
			//这句话的意思，是让浏览器用utf8来解析返回的数据  
			response.setHeader("Content-type", "text/html;charset=UTF-8");  
			//设置编码
			response.setCharacterEncoding("utf-8");
			//返回AjaxResult数据
			response.getWriter().print(new ObjectMapper().writeValueAsString(new AjaxResult("您没有改操作的权限", false)));
		}else{
			//跳转到没有权限异常页面
			response.sendRedirect("/noPermission.jsp");
		}
	}
}
