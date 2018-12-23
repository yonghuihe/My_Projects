package com.eloan.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.business.domain.MoneyWithDraw;
import com.eloan.business.domain.SystemAccount;
import com.eloan.business.domain.SystemAccountFlow;
import com.eloan.business.mapper.SystemAccountFlowMapper;
import com.eloan.business.mapper.SystemAccountMapper;
import com.eloan.business.service.ISystemAccountService;
import com.eloan.business.util.BidConst;

@Service
public class SystemAccountServcieImpl implements ISystemAccountService {

	@Autowired
	private SystemAccountMapper systemAccountMapper;

	@Autowired
	private SystemAccountFlowMapper systemAccountFlowMapper;

	@Override
	public void update(SystemAccount systemAccount) {
		int count = this.systemAccountMapper.updateByPrimaryKey(systemAccount);
		if (count == 0) {
			throw new RuntimeException("系统账户乐观锁失败");
		}
	}

	@Override
	public void initSystemAccount() {
		SystemAccount sa = this.systemAccountMapper.selectCurrent();
		if (sa == null) {
			sa = new SystemAccount();
			sa.setFreezedAmount(BidConst.ZERO);
			sa.setUsableAmount(BidConst.ZERO);
			this.systemAccountMapper.insert(sa);
		}
	}

	@Override
	public void chargeManagementFee(BigDecimal manageFee) {
		this.baseChargeFee(manageFee, BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_MANAGE_CHARGE, "平台收取借款管理费,生成系统账户流水");
	}

	@Override
	public void chargeMoneyWithDrawFee(MoneyWithDraw draw) {
		this.baseChargeFee(draw.getChargeFee(), BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_WITHDRAW_MANAGE_CHARGE,
				"借款成功，系统账户收取借款手续费，生成系统账户流水");
	}

	@Override
	public void chargeInterestManageFee(BigDecimal interestManagerCharge) {
		this.baseChargeFee(interestManagerCharge, BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_INTREST_MANAGE_CHARGE,
				"借款成功，系统账户收取利息管理费，生成系统账户流水");
	}

	private void baseChargeFee(BigDecimal amount, int actionType, String note) {
		SystemAccount sa = this.systemAccountMapper.selectCurrent();
		sa.setUsableAmount(sa.getUsableAmount().add(amount));
		this.update(sa);

		// 生成系统账户流水
		SystemAccountFlow flow = new SystemAccountFlow();
		flow.setActionTime(new Date());
		flow.setActionType(actionType);
		flow.setFreezedAmount(sa.getFreezedAmount());
		flow.setUsableAmount(sa.getUsableAmount());
		flow.setAmount(amount);
		flow.setNote(note);
		this.systemAccountFlowMapper.insert(flow);
	}

}
