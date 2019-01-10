package com.eloan.p2p.api.p2p.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.service.ILogininfoService;
import com.eloan.base.util.ResultJSON;
import com.eloan.p2p.api.p2p.service.ITokenService;

/**
 * 令牌资源相关控制器
 * 
 * @author yonghui
 *
 */
@RestController
@RequestMapping(value = "api/tokens", produces = "application/json")
public class TokenRestController {

	@Autowired
	private ILogininfoService logininfoService;
	@Autowired
	private ITokenService tokenService;

	/**
	 * 登录
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResultJSON login(String username, String password, HttpServletRequest request) {
		ResultJSON json = new ResultJSON();
		Logininfo logininfo = this.logininfoService.login(username, password, Logininfo.USERTYPE_NORMAL,
				request.getRemoteAddr());
		//登录成功
		if (logininfo != null) {
			// 创建token
			String token = this.tokenService.createToken(logininfo);
			json.setSuccess(true);
			json.setMsg("登录成功");
			json.setData(token);
		} else {
			json.setMsg("用户名或密码错误");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 注销
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	public ResultJSON logout(HttpServletRequest request){
		String token = request.getHeader(ITokenService.TOKEN_IN_HEADER);
		System.out.println(token);
		this.tokenService.destroyToken(token);
		return new ResultJSON(true,null);
	}
}
