package com.eloan.business.service;

import java.math.BigDecimal;

import com.eloan.base.query.PageResult;
import com.eloan.business.query.MoneyWithDrawQueryObject;

public interface IMoneyWithDrawService {

	/**
	 * 前台：提现申请操作
	 * @param moneyAmount
	 */
	void apply(BigDecimal moneyAmount);
	
	/**
	 * 后台：提现申请列表的查询
	 * @param qo
	 * @return
	 */
	PageResult query(MoneyWithDrawQueryObject qo);

	/**
	 * 后台：提现审核
	 * @param id
	 * @param remark
	 * @param state
	 */
	void audit(Long id, String remark, int state);

}
