package com.eloan.p2p.api.p2p.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eloan.p2p.api.p2p.service.ITokenService;
import com.eloan.p2p.api.p2p.util.RequiredLogin;

/**
 * 登录检查-api
 * 
 * @author yonghui
 *
 */
@Component("tokenCheckInterceptor")
public class TokenCheckInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ITokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod hm = (HandlerMethod) handler;
			// 1、方法上有@RequiredLogin标签，并且用户没有登录（token为null），设置状态码401
			if (hm.getMethodAnnotation(RequiredLogin.class) != null
					&& !this.tokenService.checkToken(request.getHeader(ITokenService.TOKEN_IN_HEADER))) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		}
		// 2、放行
		return super.preHandle(request, response, handler);
	}

}
