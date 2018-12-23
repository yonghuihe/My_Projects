package com.company.crm.web.filter;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.crm.domain.Employee;
import com.company.crm.domain.Menu;
import com.company.crm.service.IMenuService;
import com.company.crm.util.AjaxResult;
import com.company.crm.util.MenuUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyFormFilter extends FormAuthenticationFilter {
	
	@Autowired
	private IMenuService menuService;
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		System.out.println("登录成功");
		
		//查询用户拥有的菜单，将菜单放到session中
		List<Menu> list = menuService.selectRoot();
		// 如果是管理员就不需要过滤
		Employee employee = (Employee) subject.getPrincipal();
		if(!employee.getAdmin()){
			MenuUtil.checkPermission(list);
		}
		// 过滤用户的菜单（检查用户是否拥有菜单的权限，如果没有就移除）
		
		subject.getSession().setAttribute(MenuUtil.MENU_IN_SESSION, list);
		
		//返回json数据给页面
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(new ObjectMapper().writeValueAsString(new AjaxResult("登录成功",true)));
		// 返回false表示不需要执行下一个过滤器
		return false;
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		System.out.println("登录失败");
		
		AjaxResult result = null;
		//获取shiro输出的错误信息
		e.printStackTrace();
		String exceptionName = e.getClass().getName();
		if(exceptionName != null){
			if(exceptionName.equals(UnknownAccountException.class.getName())){
				result =  new AjaxResult("账号不存在",false);
			} else if(exceptionName.equals(IncorrectCredentialsException.class.getName())) {
				result =  new AjaxResult("密码错误",false);
			} else {
				result =  new AjaxResult("未知错误",false);
			}
		}
		
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().println(new ObjectMapper().writeValueAsString(result));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}
}
