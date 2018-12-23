package com.eloan.business.service;

import com.eloan.base.query.PageResult;
import com.eloan.business.query.PaymentScheduleQueryObject;

/**
 * 还款计划明细
 * @author yonghui
 *
 */
public interface IPaymentScheduleService {
	PageResult query(PaymentScheduleQueryObject qo);

	/**
	 * 还款
	 * @param paymentScheduleId
	 */
	void returnMoney(Long paymentScheduleId);
}
