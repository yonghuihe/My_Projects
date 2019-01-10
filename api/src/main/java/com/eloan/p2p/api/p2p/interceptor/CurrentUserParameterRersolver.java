package com.eloan.p2p.api.p2p.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.eloan.base.domain.Logininfo;
import com.eloan.p2p.api.p2p.service.ITokenService;
import com.eloan.p2p.api.p2p.util.CurrentUser;

/**
 * 专门用于controller类中方法参数上贴@CurrentUser标签
 * 
 * @author yonghui
 *
 */
@Component("currentUserParameterRersolver")
public class CurrentUserParameterRersolver extends HandlerMethodArgumentResolverComposite {

	@Autowired
	private ITokenService tokenService;
	
	/**
	 * 判断当前处理的参数是不是目标参数
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(CurrentUser.class) != null;
	}

	/**
	 * 创建并返回设置好的参数对象
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String token = webRequest.getHeader(ITokenService.TOKEN_IN_HEADER);
		Long currentId = this.tokenService.findToken(token);
		Logininfo current = new Logininfo();
		current.setId(currentId);
		return current;
	}

}
