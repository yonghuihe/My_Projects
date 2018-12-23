package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.query.PageResult;
import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.util.BidConst;

/**
 * 发标审核
 * 
 * @author yonghui
 *
 */
@Controller
public class BidRequestAuditController extends BaseController {
	
	@Autowired
	private IBidRequestService bidRequestService;

	/**
	 * 添加发标前审核列表查询方法
	 * 
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("/bidrequest_publishaudit_list")
	public String publishAuditList(@ModelAttribute("qo") BidRequestQueryObject qo, Model model) {
		qo.setState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
		PageResult pageResult = bidRequestService.query(qo);
		model.addAttribute("pageResult",pageResult);
		return "bidRequest/publish_audit";
	}
	
	/**
	 * 发标前审核
	 * @return
	 */
	@RequestMapping("/bidrequest_publishaudit")
	@ResponseBody
	public ResultJSON publishAudit(Long id,String remark,int state){
		ResultJSON json = new ResultJSON();
		try {
			bidRequestService.publishAudit(id, remark, state);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("发标前审核失败!");
		}
		return json;
	}
	
	/**
	 * 满标一审列表
	 * @return
	 */
	@RequestMapping("bidrequest_audit1_list")
	public String audit1List(@ModelAttribute("qo")BidRequestQueryObject qo,Model model){
		qo.setState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
		model.addAttribute("pageResult", this.bidRequestService.query(qo));
		return "bidRequest/audit1";
	}
	
	/**
	 * 满标一审逻辑
	 * @param id
	 * @param remark
	 * @param state
	 * @return
	 */
	@RequestMapping("bidrequest_audit1")
	@ResponseBody
	public ResultJSON audit1(Long id,String remark,int state){
		ResultJSON json = new ResultJSON();
		try {
			this.bidRequestService.audit1(id,remark,state);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("满标一审失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 满标二审列表
	 * @return
	 */
	@RequestMapping("bidrequest_audit2_list")
	public String audit2List(@ModelAttribute("qo")BidRequestQueryObject qo,Model model){
		qo.setState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);
		model.addAttribute("pageResult", this.bidRequestService.query(qo));
		return "bidRequest/audit2";
	}
	
	/**
	 * 满标二审逻辑
	 * @param id
	 * @param remark
	 * @param state
	 * @return
	 */
	@RequestMapping("bidrequest_audit2")
	@ResponseBody
	public ResultJSON audit2(Long id,String remark,int state){
		ResultJSON json = new ResultJSON();
		try {
			this.bidRequestService.audit2(id,remark,state);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("满标二审失败");
			json.setSuccess(false);
		}
		return json;
	}
}
