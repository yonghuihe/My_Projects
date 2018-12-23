package com.eloan.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.PaymentSchedule;
import com.eloan.business.domain.PaymentScheduleDetail;
import com.eloan.business.mapper.PaymentScheduleDetailMapper;
import com.eloan.business.mapper.PaymentScheduleMapper;
import com.eloan.business.query.PaymentScheduleQueryObject;
import com.eloan.business.service.IAccountFlowService;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.service.IPaymentScheduleService;
import com.eloan.business.service.ISystemAccountService;
import com.eloan.business.util.BidConst;
import com.eloan.business.util.CalculatetUtil;

@Service
public class PaymentScheduleServiceImpl implements IPaymentScheduleService {

	@Autowired
	private PaymentScheduleMapper paymentScheduleMapper;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private PaymentScheduleDetailMapper paymentScheduleDetailMapper;

	@Autowired
	private IAccountFlowService accountFlowService;

	@Autowired
	private ISystemAccountService systemAccountService;
	
	@Autowired
	private IBidRequestService bidRequestService;

	@Override
	public PageResult query(PaymentScheduleQueryObject qo) {
		int count = this.paymentScheduleMapper.queryForCount(qo);
		if (count > 0) {
			List<PaymentSchedule> list = this.paymentScheduleMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(), list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void returnMoney(Long paymentScheduleId) {
		Logininfo current = UserContext.getCurrent();
		// 1、查询还款计划，判断
		PaymentSchedule ps = this.paymentScheduleMapper.selectByPrimaryKey(paymentScheduleId);
		Account account = this.accountService.get(current.getId());// 还款账户/借款账户
		if (ps != null // 还款计划不为空
				&& ps.getState() == BidConst.PAYMENT_STATE_NOMAL// 还款计划处的状态处于待还中
				&& account.getUsableAmount().compareTo(ps.getTotalAmount()) >= 0// 当前可用金额>=当前还款金额
				&& current.getId().equals(ps.getBorrowUser().getId())) {// 当前还款人就是借款人
			// 2、针对还款计划对象：设置还款计划属性，修改时间、状态
			ps.setPayDate(new Date());
			ps.setState(BidConst.PAYMENT_STATE_DONE);
			this.paymentScheduleMapper.updateByPrimaryKey(ps);

			Map<Long, Account> investorAccountMap = new HashMap<>();
			List<PaymentScheduleDetail> details = ps.getDetails();
			for (PaymentScheduleDetail psd : details) {
				// 3、针对回款明细：设置回款明细对象属性，设置回款时间
				psd.setPayDate(ps.getPayDate());
				this.paymentScheduleDetailMapper.updateByPrimaryKey(psd);

				// 4、针对投资人，可用金额增加，生成回款流水，支付平台管理费，生成流水，平台账户收取利息管理费，生成流水，代收本金和代收利息减少
				Account investorAccount = investorAccountMap.get(psd.getInvestorId());// 投资人账户，从缓存中查
				if (investorAccount == null) {
					investorAccount = this.accountService.get(psd.getInvestorId());// 投资人账户，从数据库中查
					investorAccountMap.put(psd.getInvestorId(), investorAccount);
				}
				investorAccount.setUsableAmount(investorAccount.getUsableAmount().add(psd.getTotalAmount()));
				this.accountFlowService.createChargeReturnMoneySuccessFlow(investorAccount, psd);
				BigDecimal interestManagerCharge = CalculatetUtil.calInterestManagerCharge(psd.getInterest());// 利息管理费
				investorAccount.setUsableAmount(investorAccount.getUsableAmount().subtract(interestManagerCharge));// 支付平台管理费
				this.accountFlowService.createChargeInterestManageFee(investorAccount, interestManagerCharge);//
				this.systemAccountService.chargeInterestManageFee(interestManagerCharge);// 平台账户收取利息管理费
				investorAccount
						.setUnReceivePrincipal(investorAccount.getUnReceivePrincipal().subtract(psd.getPrincipal()));// 代收本金
				investorAccount
						.setUnReceiveInterest(investorAccount.getUnReceiveInterest().subtract(psd.getInterest()));// 代收利息
			}
			for (Account investorAccount : investorAccountMap.values()) {
				this.accountService.update(investorAccount);// 更新投资人账户
			}
			// 5、针对还款人，可用金额减少，生成还款成功流水，待还总额减少，剩余授信额度增加
			account.setUsableAmount(account.getUsableAmount().subtract(ps.getTotalAmount()));
			this.accountFlowService.createReturnMoneySuccessFlow(account, ps);
			account.setUnReturnAmount(account.getUnReturnAmount().subtract(ps.getTotalAmount()));
			account.setRemainBorrowLimit(account.getRemainBorrowLimit().add(ps.getPrincipal()));
			this.accountService.update(account);

			// 6、如果是最后一个还款，修改借款状态
			List<PaymentSchedule> list = this.paymentScheduleMapper.selectAll();
			boolean state = true;
			for (PaymentSchedule p : list) {
				if(p.getState() != BidConst.PAYMENT_STATE_DONE){
					state = false;
				}
			}
			if(state){
				BidRequest br = this.bidRequestService.getBidRequest(ps.getBidRequestId());
				br.setBidRequestState(BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK);
				this.bidRequestService.update(br);
			}
		}
	}

}
