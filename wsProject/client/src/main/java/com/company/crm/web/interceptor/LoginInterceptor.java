package com.company.crm.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.company.crm.util.SSOUtil;
import com.company.crm.ws.sso.ISSOService;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private ISSOService ssoService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 0、判断是否有令牌，如果有，则访问server端进行验证，验证令牌合法，则设置已经登录的标志到局部会话中
		String key = request.getParameter("key");
		if (StringUtils.isNotBlank(key)) {
			System.out.println("key:" + key);
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			// 访问server端验证令牌
			String token = ssoService.checkToken(key, url);
			// 如果令牌合法，设置已经登录的标志到局部会话中
			if (StringUtils.isNotBlank(token)) {
				SSOUtil.TOKEN_SESSION_MAP.put(token, request.getSession());
				// 设置登录的标志
				request.getSession().setAttribute("isLogin", true);
			}
		}

		// 1、判断局部会话是否已经有认证标志
		Object isLogin = request.getSession().getAttribute("isLogin");
		System.out.println("isLogin:" + isLogin);
		// 2、如果有并且为true，放行
		if (isLogin != null && (Boolean) isLogin) {
			return true;
		}
		// 例如：http://localhost:8080/index
		String redirectURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + request.getServletPath();
		System.out.println("redirectURL:" + redirectURL);

		// 3、如果没有，重定向到server端的验证是否已经登录的方法上：验证是否已经在认证系统上登录过，带上当前页面的url
		response.sendRedirect("http://www.server.com/checkLogin?redirectURL=" + redirectURL);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
