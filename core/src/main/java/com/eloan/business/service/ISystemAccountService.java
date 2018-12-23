package com.eloan.business.service;

import java.math.BigDecimal;

import com.eloan.business.domain.MoneyWithDraw;
import com.eloan.business.domain.SystemAccount;

public interface ISystemAccountService {

	/**
	 * 修改-乐观锁
	 * 
	 * @param systemAccount
	 */
	void update(SystemAccount systemAccount);

	/**
	 * 创建系统账户
	 */
	void initSystemAccount();
	
	/**
	 * 平台收取借款管理费,并生成系统账户流水
	 * 
	 * @param manageFee
	 */
	void chargeManagementFee(BigDecimal manageFee);

	/**
	 * 借款成功，系统账户收取手续费，并生成系统账户流水
	 * 
	 * @param draw
	 */
	void chargeMoneyWithDrawFee(MoneyWithDraw draw);

	/**
	 * 还款成功，系统账户收取利息管理费，并生成系统账户流水
	 * 
	 * @param interestManagerCharge
	 */
	void chargeInterestManageFee(BigDecimal interestManagerCharge);
}
