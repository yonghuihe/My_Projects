package com.eloan.business.service;

import java.math.BigDecimal;
import java.util.List;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.query.BidQueryObject;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.service.impl.BidRequestServiceImpl.EntryValue;

public interface IBidRequestService {

	void update(BidRequest br);

	/**
	 * 判定一个用户是否能够借款
	 * @param current
	 * @return
	 */
	boolean canBorrow(Logininfo current);

	/**
	 * 申请借款
	 * @param bidRequest
	 */
	void apply(BidRequest bidRequest);

	PageResult query(BidRequestQueryObject qo);

	/**
	 * 发标前审核
	 * @param id
	 * @param remark
	 * @param state
	 */
	void publishAudit(Long id, String remark, int state);

	/**
	 * @param i
	 * @return
	 */
	List<BidRequest> listIndexBidRequests(int size);

	/**
	 * 根据id,查询BidRequest对象
	 * @param id
	 * @return
	 */
	BidRequest getBidRequest(Long id);

	/**
	 * 投标逻辑
	 * @param bidRequestId
	 * @param amount
	 */
	void bid(Long bidRequestId, BigDecimal amount);
	
	/**
	 * 
	 * @return
	 */
	List<EntryValue<Integer,String>> listBidRequestStates();
	
	PageResult queryBids(BidQueryObject qo);

	/**
	 * 满标一审逻辑
	 * @param bidRequestId
	 * @param remark
	 * @param state
	 */
	void audit1(Long bidRequestId, String remark, int state);

	/**
	 * 满标二审逻辑
	 * @param bidRequestId
	 * @param remark
	 * @param state
	 */
	void audit2(Long bidRequestId, String remark, int state);
}
