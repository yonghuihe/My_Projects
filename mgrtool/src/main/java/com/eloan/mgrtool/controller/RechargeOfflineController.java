package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.RechargeOfflineQueryObject;
import com.eloan.business.service.ICompanyBankInfoService;
import com.eloan.business.service.IRechargeOfflineService;

/**
 * 线下充值审核
 * 
 * @author yonghui
 *
 */
@Controller
public class RechargeOfflineController extends BaseController {

	@Autowired
	private IRechargeOfflineService rechargeOfflineService;

	@Autowired
	private ICompanyBankInfoService companyBankInfoService;

	/**
	 * 线下充值审核列表
	 * 
	 * @return
	 */
	@RequestMapping("rechargeOffline")
	public String rechargeOfflineList(@ModelAttribute("qo") RechargeOfflineQueryObject qo, Model model) {
		System.out.println(qo.toString());
		model.addAttribute("pageResult", this.rechargeOfflineService.query(qo));
		System.out.println(this.rechargeOfflineService.query(qo).getResult());
		model.addAttribute("banks", this.companyBankInfoService.list());
		return "rechargeOffline/list";
	}

	/**
	 * 线下充值审核
	 * 
	 * @return
	 */
	@RequestMapping("rechargeOffline_audit")
	@ResponseBody
	public ResultJSON audit(Long id,String remark,int state) {
		ResultJSON json = new ResultJSON();
		try {
			this.rechargeOfflineService.audit(id,remark,state);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("审核失败");
			json.setSuccess(false);
		}
		return json;
	}

}
