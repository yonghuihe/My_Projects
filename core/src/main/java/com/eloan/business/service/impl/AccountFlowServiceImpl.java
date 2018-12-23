package com.eloan.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.business.domain.Account;
import com.eloan.business.domain.AccountFlow;
import com.eloan.business.domain.Bid;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.MoneyWithDraw;
import com.eloan.business.domain.PaymentSchedule;
import com.eloan.business.domain.PaymentScheduleDetail;
import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.mapper.AccountFlowMapper;
import com.eloan.business.service.IAccountFlowService;
import com.eloan.business.util.BidConst;

@Service
public class AccountFlowServiceImpl implements IAccountFlowService {

	@Autowired
	private AccountFlowMapper accountFlowMapper;

	@Override
	public void addRechargeOfflineFlow(RechargeOffline offline, Account account) {
		this.createBaseFlow(account, offline.getAmount(), BidConst.ACCOUNT_ACTIONTYPE_DEPOSIT_OFFLINE_LOCAL, // 线下充值
				"线下充值成功");
	}

	@Override
	public void addBidFlow(Bid bid, Account account) {
		this.createBaseFlow(account, bid.getAvailableAmount(), BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL, // 成功投标
				"成功投标");
	}

	@Override
	public void createBidFailedFlow(Account account, Bid bid) {
		this.createBaseFlow(account, bid.getAvailableAmount(), BidConst.ACCOUNT_ACTIONTYPE_BID_UNFREEZED, // 取消投标冻结金额
				"审核拒绝");
	}

	@Override
	public void createBidSuccessFlow(Account applierAccount, BidRequest br) {
		this.createBaseFlow(applierAccount, br.getBidRequestAmount(), BidConst.ACCOUNT_ACTIONTYPE_BIDREQUEST_SUCCESSFUL, // 成功借款
				"满标二审通过");
	}

	@Override
	public void createPayBorrowManageFeeFlow(Account applierAccount, BigDecimal manageFee) {
		this.createBaseFlow(applierAccount, manageFee, BidConst.ACCOUNT_ACTIONTYPE_CHARGE, // 平台管理费
				"支付平台管理费");
	}

	@Override
	public void createInvestorFlow(Account investorAccount, Bid bid) {
		this.createBaseFlow(investorAccount, bid.getAvailableAmount(), BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL, // 成功投标
				"投标成功，投资人冻结金额减少");
	}

	@Override
	public void creatMoneyWithDrawFlow(MoneyWithDraw draw, Account account) {
		this.createBaseFlow(account, draw.getAmount(), BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW_FREEZED, // 提现申请冻结金额
				"提现申请，可用金额减少，冻结金额增加");
	}

	@Override
	public void createMoneyWithDrawSuccessFlow(Account account, MoneyWithDraw draw) {
		this.createBaseFlow(account, draw.getAmount().subtract(draw.getChargeFee()),
				BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW, // 提现成功
				"提现审核通过，冻结金额减少（不包含手续费）");
	}

	@Override
	public void createChargeFeeFlow(Account account, MoneyWithDraw draw) {
		this.createBaseFlow(account, draw.getChargeFee(), BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW_MANAGE_CHARGE, // 提现手续费
				"提现审核通过，冻结金额减少（手续费）");
	}

	@Override
	public void createMoneyWithDrawFailedFlow(Account account, MoneyWithDraw draw) {
		this.createBaseFlow(account, draw.getAmount(), BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW_UNFREEZED, // 提现申请失败取消冻结金额
				"提现审核拒绝，冻结金额减少，可用金额增加");
	}

	@Override
	public void createReturnMoneySuccessFlow(Account account, PaymentSchedule ps) {
		this.createBaseFlow(account, ps.getTotalAmount(), BidConst.ACCOUNT_ACTIONTYPE_RETURN_MONEY, // 还款成功
				"针对还款人，还款成功，可用金额减少");
	}

	@Override
	public void createChargeReturnMoneySuccessFlow(Account investorAccount, PaymentScheduleDetail psd) {
		this.createBaseFlow(investorAccount, psd.getTotalAmount(), BidConst.ACCOUNT_ACTIONTYPE_CALLBACK_MONEY, // 回款成功
				"针对投资人，回款成功，可用金额增加");
	}

	@Override
	public void createChargeInterestManageFee(Account investorAccount, BigDecimal interestManagerCharge) {
		this.createBaseFlow(investorAccount, interestManagerCharge,
				BidConst.ACCOUNT_ACTIONTYPE_INTEREST_SHARE, // 利息管理费
				"还款成功，投资人支付系统账户利息管理费");
	}

	private void createBaseFlow(Account account, BigDecimal amount, int actionType, String note) {
		AccountFlow flow = new AccountFlow();
		flow.setAccount(account);
		flow.setAccountActionType(actionType);// 提现申请冻结金额
		flow.setActionTime(new Date());
		flow.setAmount(amount);
		flow.setBalance(account.getUsableAmount());
		flow.setFreezed(account.getFreezedAmount());
		flow.setNote(note);
		this.accountFlowMapper.insert(flow);
	}

}
