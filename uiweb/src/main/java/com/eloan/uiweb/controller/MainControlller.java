package com.eloan.uiweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.base.query.PageResult;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.util.BidConst;

@Controller
public class MainControlller extends BaseController {
	
	@Autowired
	private IBidRequestService bidRequestService;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String main(Model model){
		//查询出正在招标的标的信息
		List<BidRequest> list = this.bidRequestService.listIndexBidRequests(5);
		model.addAttribute("bidRequests", list);
		return "main";
	}
	
	/**
	 * 投资列表
	 * @return
	 */
	@RequestMapping("/invest")
	public String invest(){
		return "invest";
	}
	
	/**
	 * 投资列表Ajax分页
	 * @return
	 */
	@RequestMapping("/invest_list")
	public String inverstList(int bidRequestState,int currentPage, Model model){
		BidRequestQueryObject qo = new BidRequestQueryObject();
		if(bidRequestState == -1){
			qo.setStates(new int[]{BidConst.BIDREQUEST_STATE_BIDDING,
					BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1,
					BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2,
					BidConst.BIDREQUEST_STATE_PAYING_BACK,
					BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK});
		}else{
			qo.setState(bidRequestState);
		}
		PageResult result = this.bidRequestService.query(qo);
		model.addAttribute("pageResult", result);
		return "invest_list";
	}
	
	
}
