package com.eloan.uiweb.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userfile;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.query.BidQueryObject;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.query.UserFileQueryObject;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserFileService;
import com.eloan.business.service.IUserService;
import com.eloan.business.service.impl.BidRequestServiceImpl.EntryValue;
import com.eloan.business.util.BidConst;
import com.eloan.uiweb.interceptor.RequiredLogin;

/**
 * 借款模块
 * 
 * @author Administrator
 *
 */
@Controller
public class BorrowController extends BaseController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IBidRequestService bidRequestService;

	@Autowired
	private IRealAuthService realAuthService;

	@Autowired
	private IUserFileService userFileService;

	/**
	 * 我要借款
	 * 
	 * @return
	 */
	@RequiredLogin
	@RequestMapping("/borrow")
	public String borrowIndex(Model model) {
		Logininfo current = UserContext.getCurrent();
		if (current == null) {
			return "redirect:borrow.html";
		}
		model.addAttribute("account", this.accountService.get(current.getId()));
		model.addAttribute("userinfo", this.userService.get(current.getId()));
		model.addAttribute("creditBorrowScore", BidConst.CREDIT_BORROW_SCORE);
		return "borrow";
	}

	/**
	 * 申请贷款，跳转到申请贷款页面或申请贷款结果页面
	 * 
	 * @return
	 */
	@RequiredLogin
	@RequestMapping("/borrowInfo")
	public String apply(Model model) {
		Logininfo current = UserContext.getCurrent();
		Userinfo userinfo = userService.get(current.getId());
		// 如果当前用户已经有一个借款申请，跳转到借款结果页面
		if (userinfo.getHasBidRequest()) {
			return "borrow_apply_result";
		}
		Account account = accountService.get(userinfo.getId());
		model.addAttribute("account", account);
		model.addAttribute("minBidRequestAmount", BidConst.SMALLEST_BIDREQUEST_AMOUNT);
		model.addAttribute("minBidAmount", BidConst.SMALLEST_BID_AMOUNT);
		return "borrow_apply";
	}

	/**
	 * 提交借款申请
	 * 
	 * @return
	 */
	@RequiredLogin
	@RequestMapping("/borrow_apply")
	public String borrowApply(BidRequest bidRequest) {
		// 再次检查该用户是否有发标的权限
		Logininfo current = UserContext.getCurrent();
		boolean canBorrow = bidRequestService.canBorrow(current);
		if (!canBorrow) {
			return "redirect:borrow.do";
		}
		// 提交申请
		this.bidRequestService.apply(bidRequest);
		return "redirect:borrowInfo.do";
	}

	/**
	 * 查看标的明细
	 * 
	 * @return
	 */
	@RequestMapping("/borrow_info")
	public String borrowInfo(Long id, Model model) {
		BidRequest bidRequest = this.bidRequestService.getBidRequest(id);
		Userinfo userinfo = userService.get(bidRequest.getCreateUser().getId());
		Realauth realauth = this.realAuthService.get(userinfo.getRealauthId());
		// 查询文件
		UserFileQueryObject userFileQo = new UserFileQueryObject();
		userFileQo.setPageSize(-1);
		userFileQo.setApplierId(userinfo.getId());
		userFileQo.setState(Userfile.STATE_PASS);
		List<Userfile> list = this.userFileService.queryForList(userFileQo);
		
		if (UserContext.getCurrent() != null) {
			Logininfo current = UserContext.getCurrent();
			//如果已经登录，并且当前用户是这个标的借款人，只显示借款状态
			if(bidRequest.getCreateUser().getId().equals(current.getId())){
				model.addAttribute("self", true);
			}else{
				Account account = this.accountService.get(current.getId());
				model.addAttribute("account", account);
				model.addAttribute("self", false);
			}
		}else{
			//如果当前是非登录用户，显示登陆投标即可，
			model.addAttribute("self", false);
		}

		model.addAttribute("bidRequest", bidRequest);
		model.addAttribute("userInfo", userinfo);
		model.addAttribute("realauth", realauth);
		model.addAttribute("userFiles", list);

		return "borrow_info";
	}
	
	/**
	 * 投标
	 * @param bidRequestId
	 * @param amount
	 * @return
	 */
	@RequestMapping("borrow_bid")
	@ResponseBody
	public ResultJSON borrowBid(Long bidRequestId,BigDecimal amount){
		ResultJSON json = new ResultJSON();
		try {
			this.bidRequestService.bid(bidRequestId,amount);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("投标失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 个人中心-借款明细
	 * @param model
	 * @return
	 */
	@RequestMapping("myborrow_list")
	@RequiredLogin
	public String myborrowList(@ModelAttribute("qo") BidRequestQueryObject qo,Model model){
		List<EntryValue<Integer,String>> bidRequestStates = this.bidRequestService.listBidRequestStates();
		model.addAttribute("bidRequestStates", bidRequestStates);
		qo.setCreateUserId(UserContext.getCurrent().getId());
		PageResult pageResult = this.bidRequestService.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "bidRequest_result";
	}
	
	/**
	 * 投标明细
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("bid_list")
	@RequiredLogin
	public String bidList(@ModelAttribute("qo") BidQueryObject qo,Model model){
		qo.setBidUserId(UserContext.getCurrent().getId());
		PageResult pageResult = this.bidRequestService.queryBids(qo);
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("bidRequestStates", this.bidRequestService.listBidRequestStates());
		return "bid_list";
	}

}
