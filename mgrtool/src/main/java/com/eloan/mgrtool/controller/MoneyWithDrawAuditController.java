package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.MoneyWithDrawQueryObject;
import com.eloan.business.service.IMoneyWithDrawService;

/**
 * 提现审核
 * 
 * @author yonghui
 *
 */
@Controller
public class MoneyWithDrawAuditController extends BaseController {
	
	@Autowired
	private IMoneyWithDrawService moneyWithDrawService;

	/**
	 * 提现审核列表
	 * 
	 * @return
	 */
	@RequestMapping("moneyWithdraw")
	public String list(@ModelAttribute("qo") MoneyWithDrawQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.moneyWithDrawService.query(qo));
		return "moneyWithDraw/list";
	}
	
	/**
	 * 提现审核
	 * @param vo
	 * @return
	 */
	@RequestMapping("moneyWithdraw_audit")
	@ResponseBody
	public ResultJSON audit(Long id, String remark, int state){
		ResultJSON json = new ResultJSON();
		try {
			this.moneyWithDrawService.audit(id, remark, state);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("提现审核失败");
			json.setSuccess(false);
		}
		return json;
	}
}
