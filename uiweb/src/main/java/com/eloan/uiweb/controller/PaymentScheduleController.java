package com.eloan.uiweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.query.PaymentScheduleQueryObject;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IPaymentScheduleService;
import com.eloan.uiweb.interceptor.RequiredLogin;

/**
 * 还款计划
 * @author yonghui
 *
 */
@Controller
public class PaymentScheduleController extends BaseController {
	
	@Autowired
	private IPaymentScheduleService paymentScheduleService;
	
	@Autowired
	private IAccountService accountService;
	
	/**
	 * 还款计划列表
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("borrowBidReturn_list")
	@RequiredLogin
	public String list(@ModelAttribute("qo")PaymentScheduleQueryObject qo,Model model){
		qo.setUserinfoId(UserContext.getCurrent().getId());
		model.addAttribute("pageResult", this.paymentScheduleService.query(qo));
		model.addAttribute("account", this.accountService.get(UserContext.getCurrent().getId()));
		return "returnMoney_list";
	}
	
	@RequestMapping("returnMoney")
	@ResponseBody
	@RequiredLogin
	public ResultJSON returnMoney(Long id){
		ResultJSON json = new ResultJSON();
		try {
			this.paymentScheduleService.returnMoney(id);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("还款失败"+e.getMessage());
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
}
