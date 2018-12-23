package com.eloan.uiweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.query.PageResult;
import com.eloan.base.util.ResultJSON;
import com.eloan.business.domain.CompanyBankInfo;
import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.query.RechargeOfflineQueryObject;
import com.eloan.business.service.ICompanyBankInfoService;
import com.eloan.business.service.IRechargeOfflineService;

@Controller
public class RechargeOfflineController extends BaseController {

	@Autowired
	private ICompanyBankInfoService companyBankInfoService;

	@Autowired
	private IRechargeOfflineService rechargeOfflineService;

	/**
	 * 线下充值页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("rechargeOffline")
	public String list(Model model) {
		List<CompanyBankInfo> list = this.companyBankInfoService.list();
		model.addAttribute("banks", list);
		return "recharge";
	}

	/**
	 * 线下充值提交申请
	 * 
	 * @return
	 */
	@RequestMapping("recharge_apply")
	@ResponseBody
	public ResultJSON apply(RechargeOffline recharge) {
		ResultJSON json = new ResultJSON();
		try {
			this.rechargeOfflineService.apply(recharge);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("提交失败");
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 线下充值明细
	 * 
	 * @return
	 */
	@RequestMapping("/recharge_list")
	public String list(@ModelAttribute("qo") RechargeOfflineQueryObject qo, Model model) {
		PageResult pageResult = this.rechargeOfflineService.query(qo);
		model.addAttribute("pageResult", pageResult);

		return "recharge_list";
	}

}
