package com.eloan.business.service;

import java.math.BigDecimal;

import com.eloan.business.domain.Account;
import com.eloan.business.domain.Bid;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.MoneyWithDraw;
import com.eloan.business.domain.PaymentSchedule;
import com.eloan.business.domain.PaymentScheduleDetail;
import com.eloan.business.domain.RechargeOffline;

public interface IAccountFlowService {
	/**
	 * 添加线下充值流水
	 * 
	 * @param offline
	 * @param account
	 */
	void addRechargeOfflineFlow(RechargeOffline offline, Account account);

	/**
	 * 添加投标流水
	 * 
	 * @param bid
	 * @param account
	 */
	void addBidFlow(Bid bid, Account account);

	/**
	 * 满标一审拒绝流水
	 * 
	 * @param account
	 * @param bid
	 */
	void createBidFailedFlow(Account account, Bid bid);

	/**
	 * 满标二审通过流水
	 * 
	 * @param applierAccount
	 * @param br
	 */
	void createBidSuccessFlow(Account applierAccount, BidRequest br);

	/**
	 * 满标二审通过-支付平台管理费
	 * 
	 * @param applierAccount
	 * @param manageFee
	 */
	void createPayBorrowManageFeeFlow(Account applierAccount, BigDecimal manageFee);

	/**
	 * 二审通过-生成投资人流水
	 * 
	 * @param investorAccount
	 * @param bid
	 */
	void createInvestorFlow(Account investorAccount, Bid bid);

	/**
	 * 提现申请流水
	 * 
	 * @param draw
	 * @param account
	 */
	void creatMoneyWithDrawFlow(MoneyWithDraw draw, Account account);

	/**
	 * 提现审核通过流水（不包含手续费）
	 * 
	 * @param account
	 * @param draw
	 */
	void createMoneyWithDrawSuccessFlow(Account account, MoneyWithDraw draw);

	/**
	 * 提现审核通过流水（手续费）
	 * 
	 * @param account
	 * @param draw
	 */
	void createChargeFeeFlow(Account account, MoneyWithDraw draw);

	/**
	 * 提现审核拒绝流水
	 * 
	 * @param account
	 * @param draw
	 */
	void createMoneyWithDrawFailedFlow(Account account, MoneyWithDraw draw);

	/**
	 * 还款成功流水
	 * 
	 * @param account
	 * @param ps
	 */
	void createReturnMoneySuccessFlow(Account account, PaymentSchedule ps);

	/**
	 * 回款成功，投资人人可用金额增加
	 * 
	 * @param investorAccount
	 * @param psd
	 */
	void createChargeReturnMoneySuccessFlow(Account investorAccount, PaymentScheduleDetail psd);

	/**
	 * 还款成功，投资人支付系统账户利息管理费
	 * 
	 * @param investorAccount
	 * @param interestManagerCharge
	 */
	void createChargeInterestManageFee(Account investorAccount, BigDecimal interestManagerCharge);
}
