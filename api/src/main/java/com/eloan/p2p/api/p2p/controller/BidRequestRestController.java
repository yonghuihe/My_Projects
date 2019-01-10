package com.eloan.p2p.api.p2p.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.util.BidConst;
import com.eloan.p2p.api.p2p.util.CurrentUser;
import com.eloan.p2p.api.p2p.util.RequiredLogin;

/**
 * 借款资源控制器
 * 
 * @author yonghui
 *
 */
@RestController
@RequestMapping(value = "/api/bidRequests", produces = "application/json")
public class BidRequestRestController {

	@Autowired
	private IBidRequestService bidRequestService;

	/**
	 * 借款列表 只查询：招标中、还款中、已完成三种， 只需要查询出借款列表，不需要查询出具体的借款详情
	 */
	@RequestMapping(method = RequestMethod.GET)
	public PageResult list(BidRequestQueryObject qo) {
		// 如果是全部（-1），显示指定的BidRequestState
		if (qo.getStart() == -1) {
			qo.setStates(new int[] { BidConst.BIDREQUEST_STATE_BIDDING, BidConst.BIDREQUEST_STATE_PAYING_BACK,
					BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK });
		}
		// return this.bidRequestService.query(qo);
		return this.bidRequestService.queryForREST(qo);
	}

	/**
	 * 借款详情
	 * 
	 * @param id：借款对象id
	 * @return ResultJSON,将Map<String,Object>装到ResultJSON信封中，返回统一的格式
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResultJSON get(@PathVariable Long id) {
		ResultJSON json = new ResultJSON();
		json.setSuccess(true);
		json.setMsg("审核详情");
		json.setData(this.bidRequestService.getBidRequest(id).getJsonObject());
		return json;
	}

	/**
	 * 投资列表
	 * 
	 * @param id：借款对象id
	 * @return List<bid>，封装到信封ResultJSON中
	 */
	@RequestMapping(value = "/{id}/bids", method = RequestMethod.GET)
	public ResultJSON getBidList(@PathVariable Long id) {
		ResultJSON json = new ResultJSON();
		json.setSuccess(true);
		json.setMsg("投资列表");
		json.setData(this.bidRequestService.getBidRequest(id).getBids());
		return json;
	}

	/**
	 * 投标
	 * 
	 * @param id
	 * @param amount
	 *            方式一：@param request：使用HttpServletRequest传入token获取用户id
	 *            方式二：@param current：在该参数前加注解，在CurrentUserParameterResolver中进行判读、处理
	 * @return
	 */
	@RequiredLogin
	@RequestMapping(value = "/{id}/bids", method = RequestMethod.POST)
	public ResultJSON bid(@PathVariable Long id, BigDecimal amount,@CurrentUser Logininfo current) {
		// 1.获取当前用户
		/*
		 * String token = request.getHeader(ITokenService.TOKEN_IN_HEADER); Long
		 * userId = this.tokenService.findToken(token); Logininfo bidUser = new
		 * Logininfo(); bidUser.setId(userId);
		 */
		// 2.调用投标业务方法
		this.bidRequestService.bid(id, amount, current);
		return new ResultJSON(true, "投标");
	}

}
