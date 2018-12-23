package com.eloan.mgrtool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.BidRequestAuditHistory;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userfile;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.query.BidRequestAuditHistoryQueryObject;
import com.eloan.business.query.UserFileQueryObject;
import com.eloan.business.service.IBidRequestAuditHistoryService;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserFileService;
import com.eloan.business.service.IUserService;

/**
 * 查看列表中的标
 * 
 * @author yonghui
 *
 */
@SuppressWarnings("all")
@Controller
public class BorrowController extends BaseController {
	
	@Autowired
	private IBidRequestService bidRequestService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBidRequestAuditHistoryService bidRequestAuditHistoryService;
	
	@Autowired
	private IUserFileService userFileService;
	
	@Autowired
	private IRealAuthService realAuthService;
	
	@RequestMapping("/borrow_info")
	public String borrowInfo(Long id,Model model){
		//标的信息
		BidRequest bidRequest = this.bidRequestService.getBidRequest(id);
		//借款人的userinfo信息
		Userinfo userinfo = userService.get(bidRequest.getCreateUser().getId());
		//借款人的realAuth信息
		Realauth realAuth = this.realAuthService.get(userinfo.getRealauthId());
		//借款人的所有审核信息
		BidRequestAuditHistoryQueryObject auditQo = new BidRequestAuditHistoryQueryObject();
		auditQo.setPageSize(-1);
		auditQo.setBidRequestId(id);
		List<BidRequestAuditHistory> history = this.bidRequestAuditHistoryService.queryForList(auditQo);
		//借款人的所有审核通过的风控材料
		UserFileQueryObject userFileQo = new UserFileQueryObject();
		userFileQo.setPageSize(-1);
		userFileQo.setApplierId(userinfo.getId());
		userFileQo.setState(Userfile.STATE_PASS);
		List<Userfile> file = userFileService.queryForList(userFileQo);
		
		model.addAttribute("bidRequest", bidRequest);
		model.addAttribute("userInfo", userinfo);
		model.addAttribute("realAuth", realAuth);
		model.addAttribute("audits", history);
		model.addAttribute("userFiles", file);
		
		return "bidRequest/borrow_info";
	}

}
