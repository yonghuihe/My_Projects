package com.eloan.uiweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.UserBankinfo;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IUserBankinfoService;
import com.eloan.business.service.IUserService;
import com.eloan.uiweb.interceptor.RequiredLogin;

/**
 * 用户绑定银行卡
 * 
 * @author yonghui
 *
 */
@Controller
public class UserBankinfoController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserBankinfoService userBankinfoService;

	/**
	 * 进入银行卡绑定页面/绑定结果页面
	 * 
	 * @return
	 */
	@RequestMapping("bankInfo")
	@RequiredLogin
	public String list(Model model) {
		Logininfo current = UserContext.getCurrent();
		Userinfo userinfo = this.userService.get(current.getId());
		model.addAttribute("userinfo", this.userService.get(current.getId()));
		if(userinfo.getBindBankinfo()){
			UserBankinfo bankinfo = this.userBankinfoService.get(userinfo.getId());
			model.addAttribute("bankInfo", bankinfo);
			return "bankInfo_result";
		}
		return "bankInfo";
	}

	/**
	 * 银行卡的绑定
	 * 
	 * @return
	 */
	@RequestMapping("bankInfo_save")
	@RequiredLogin
	public String bind(UserBankinfo vo) {
		this.userBankinfoService.bind(vo);
		return "redirect:/bankInfo.do";
	}
}
