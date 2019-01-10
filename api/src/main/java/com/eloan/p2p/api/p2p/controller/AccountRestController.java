package com.eloan.p2p.api.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eloan.base.domain.Logininfo;
import com.eloan.business.domain.Account;
import com.eloan.business.service.IAccountService;
import com.eloan.p2p.api.p2p.util.CurrentUser;
import com.eloan.p2p.api.p2p.util.RequiredLogin;

/**
 * 个人账户资源控制器
 * 
 * @author yonghui
 *
 */
@RestController
@RequestMapping(value = "/api/accounts", produces = "application/json")
public class AccountRestController {

	@Autowired
	private IAccountService accountService;

	/**
	 * 获取个人账户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequiredLogin
	@RequestMapping(method = RequestMethod.GET)
	public Account get(@CurrentUser Logininfo current) {
		/*String token = request.getHeader(ITokenService.TOKEN_IN_HEADER);
		Long currentId = this.tokenService.findToken(token);*/
		return this.accountService.get(current.getId());
	}
}
