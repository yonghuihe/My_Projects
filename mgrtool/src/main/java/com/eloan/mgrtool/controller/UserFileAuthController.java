package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.query.PageResult;
import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.UserFileQueryObject;
import com.eloan.business.service.IUserFileService;

/**
 * 后台：风控材料审核
 * 
 * @author yonghui
 *
 */
@Controller
public class UserFileAuthController extends BaseController {

	@Autowired
	private IUserFileService userFileService;

	// 风控资料审核列表
	@RequestMapping("/userFileAuth")
	public String list(@ModelAttribute("qo") UserFileQueryObject qo, Model model) {
		PageResult pageResult = userFileService.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "/userFileAuth/list";
	}

	// 风控资料审核
	@RequestMapping("/userFile_audit")
	@ResponseBody
	public ResultJSON audit(Long id, String remark, int score, int state) {
		ResultJSON json = new ResultJSON();
		try {
			this.userFileService.audit(id, remark, score, state);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
