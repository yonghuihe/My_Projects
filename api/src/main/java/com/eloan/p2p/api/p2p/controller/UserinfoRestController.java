package com.eloan.p2p.api.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eloan.base.service.ILogininfoService;
import com.eloan.base.util.ResultJSON;

/**
 * 用户资源对应的控制器
 * 
 * @author yonghui
 *
 */
@RestController
@RequestMapping(value = "/api/userinfos/", produces = "application/json")
public class UserinfoRestController {

	@Autowired
	private ILogininfoService loginfoService;

	/**
	 * 用户注册接口
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResultJSON register(String username, String password) {
		ResultJSON json = new ResultJSON();
		try {
			this.loginfoService.register(username, password);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			json.setSuccess(false);
		}
		return json;
	}

}
