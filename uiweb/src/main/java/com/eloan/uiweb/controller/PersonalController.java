package com.eloan.uiweb.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.DateUtil;
import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.domain.VerifyCode;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IEmailActiveService;
import com.eloan.business.service.ISendVerifyCodeService;
import com.eloan.business.service.IUserService;
import com.eloan.business.service.IVerifyCodeService;
import com.eloan.uiweb.interceptor.RequiredLogin;

@Controller
public class PersonalController extends BaseController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IUserService userinfoService;

	@Autowired
	private ISendVerifyCodeService sendVerifyCodeService;

	@Autowired
	private IEmailActiveService emailService;
	
	@Autowired
	private IVerifyCodeService verifyCodeService;

	@RequiredLogin
	@RequestMapping("/personal")
	public String personal(Model model) {
		Logininfo current = UserContext.getCurrent();
		model.addAttribute("userinfo", userinfoService.get(current.getId()));
		model.addAttribute("account", accountService.get(current.getId()));
		return "personal";
	}

	/**
	 * 发送验证码
	 * 
	 * @param phoneNumber
	 * @param newPhoneNumber
	 * @return
	 */
	@RequiredLogin
	@RequestMapping("/sendVerifyCode")
	@ResponseBody
	public ResultJSON sendVerifyCode(String phoneNumber) {
		ResultJSON json = new ResultJSON();
		try {
			sendVerifyCodeService.sendVerifyCode(phoneNumber);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}

	@RequiredLogin
	@RequestMapping("/bindPhone")
	@ResponseBody
	public ResultJSON bindPhone(String phoneNumber, String newPhoneNumber, String verifyCode) {
		ResultJSON json = new ResultJSON();
		try {
			if(newPhoneNumber == null){
				json.setSuccess(this.userinfoService.bindPhone(phoneNumber, verifyCode));
			}else{
				//修改手机号
				Userinfo userinfo = userinfoService.queryByPhoneNumber(phoneNumber);
				userinfo.setPhoneNumber(newPhoneNumber);
				userService.update(userinfo);
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}

	@RequiredLogin
	@RequestMapping("/bindEmail")
	@ResponseBody
	public ResultJSON bindEmail(String email,String newEmail,String verifyCode) {
		ResultJSON json = new ResultJSON();
		
		try {
			if(StringUtils.isNotBlank(newEmail)&& StringUtils.isNotBlank(verifyCode)){
				Userinfo userinfo = userService.get(UserContext.getCurrent().getId());
				VerifyCode vc = verifyCodeService.queryByPhoneNumber(userinfo.getPhoneNumber());
				if(vc.getRandomCode().equals(verifyCode)&&
						 DateUtil.getSecondsBetweenDates(vc.getLastSendTime(),new Date()) <= 60 * 60 * 24 * 3){
					userinfo.setEmail(newEmail);
					userService.update(userinfo);
				}
			}else{
				emailService.sendActiveEmail(email);
			}
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		return json;
	}

	@RequestMapping("/checkMailActive")
	public String checkMailActive(String code, Model model) {
		try {
			if(UserContext.getCurrent()!=null){
				Userinfo userinfo = userService.get(UserContext.getCurrent().getId());
				if(userinfo.getIsBindEmail()){
					model.addAttribute("userinfo",userinfo);
				}
			}else{
				this.emailService.bindEmail(code);
				model.addAttribute("success", true);
			}
		} catch (Exception e) {
			model.addAttribute("success", false);
			model.addAttribute("msg", e.getMessage());
		}
		return "checkmail_result";
	}

	/**
	 * 查看手机认证信息
	 * 
	 * @return
	 */
	@RequiredLogin
	@RequestMapping("/bindPhone_result")
	public String bindPhoneResult(Model model) {
		Userinfo userinfo = userService.get(UserContext.getCurrent().getId());
		if (userinfo.getIsBindPhone()) {
			model.addAttribute("userinfo", userinfo);
			return "bindPhone_result";
		}
		return "personal";
	}
}
