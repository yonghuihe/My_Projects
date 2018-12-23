package com.eloan.uiweb.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IMoneyWithDrawService;
import com.eloan.business.service.IUserBankinfoService;
import com.eloan.business.service.IUserService;
import com.eloan.uiweb.interceptor.RequiredLogin;

/**
 * 前台：提现申请
 * 
 * @author yonghui
 *
 */
@Controller
public class MoneyWithDrawController extends BaseController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IUserBankinfoService userBankinfoService;
	
	@Autowired
	private IMoneyWithDrawService moneyWithDrawService;

	/**
	 * 进入提现申请页面
	 * 
	 * @return
	 */
	@RequestMapping("moneyWithDraw")
	@RequiredLogin
	public String list(Model model) {
		Logininfo current = UserContext.getCurrent();
		Userinfo userinfo = this.userService.get(current.getId());
		model.addAttribute("haveProcessing", false);
		if (userinfo.getMoneyWithDraw()) {
			model.addAttribute("haveProcessing", true);
		} else {
			model.addAttribute("bankInfo", this.userBankinfoService.get(userinfo.getId()));
			model.addAttribute("account", this.accountService.get(userinfo.getId()));
		}
		return "moneyWithdraw_apply";
	}

	/**
	 * 提现申请操作
	 * 
	 * @return
	 */
	@RequestMapping("moneyWithdraw_apply")
	@RequiredLogin
	@ResponseBody
	public ResultJSON apply(BigDecimal moneyAmount) {
		ResultJSON json = new ResultJSON();
		try {
			this.moneyWithDrawService.apply(moneyAmount);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("提现申请失败"+e.getMessage());
			json.setSuccess(false);
		}
		return json;
	}
}
