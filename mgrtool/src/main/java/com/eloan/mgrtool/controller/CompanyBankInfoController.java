package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.base.query.QueryObject;
import com.eloan.business.domain.CompanyBankInfo;
import com.eloan.business.service.ICompanyBankInfoService;

@Controller
public class CompanyBankInfoController extends BaseController {

	@Autowired
	private ICompanyBankInfoService companyBankInfoService;

	/**
	 * 平台账户管理列表
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("/companyBank_list")
	private String list(@ModelAttribute("qo") QueryObject qo, Model model) {
		model.addAttribute("pageResult", this.companyBankInfoService.query(qo));
		return "companyBankInfo/list";
	}
	
	/**
	 * 平台账户保存
	 * @param info
	 * @return
	 */
	@RequestMapping("/companyBank_update")
	public String save(CompanyBankInfo info){
		this.companyBankInfoService.save(info);
		return "redirect:companyBank_list.do";
	}

}
