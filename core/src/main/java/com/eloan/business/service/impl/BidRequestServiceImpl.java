package com.eloan.business.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.Bid;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.BidRequestAuditHistory;
import com.eloan.business.domain.PaymentSchedule;
import com.eloan.business.domain.PaymentScheduleDetail;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.BidMapper;
import com.eloan.business.mapper.BidRequestAuditHistoryMapper;
import com.eloan.business.mapper.BidRequestMapper;
import com.eloan.business.mapper.PaymentScheduleDetailMapper;
import com.eloan.business.mapper.PaymentScheduleMapper;
import com.eloan.business.query.BidQueryObject;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.service.IAccountFlowService;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.service.ISystemAccountService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BidConst;
import com.eloan.business.util.BitStatesUtils;
import com.eloan.business.util.CalculatetUtil;

import lombok.Getter;
import lombok.Setter;

@Service
public class BidRequestServiceImpl implements IBidRequestService {

	@Autowired
	private BidRequestMapper bidRequestMapper;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;

	@Autowired
	private BidMapper bidMapper;

	@Autowired
	private IAccountFlowService accountFlowService;

	@Autowired
	private ISystemAccountService systemAccountService;

	@Autowired
	private PaymentScheduleMapper paymentScheduleMapper;

	@Autowired
	private PaymentScheduleDetailMapper paymentScheduleDetailMapper;

	@Override
	public List<BidRequest> listIndexBidRequests(int size) {
		BidRequestQueryObject qo = new BidRequestQueryObject();
		qo.setStates(new int[] { BidConst.BIDREQUEST_STATE_BIDDING, BidConst.BIDREQUEST_STATE_PAYING_BACK,
				BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK });
		qo.setPageSize(size);
		qo.setOrderBy("bidRequestState");
		qo.setOrderType("ASC");
		return this.bidRequestMapper.query(qo);
	}

	@Override
	public void update(BidRequest br) {
		int ret = bidRequestMapper.updateByPrimaryKey(br);
		if (ret <= 0) {
			throw new RuntimeException("借款对象乐观锁失败:" + br.getId());
		}
	}

	@Override
	public boolean canBorrow(Logininfo li) {
		Userinfo current = this.userService.get(li.getId());
		Account account = this.accountService.get(li.getId());

		return !current.getHasBidRequest()// 没有标在过程当中
				&& current.getRealAuth()// 通过实名认证
				&& current.getBaseInfo()// 填写基本资料
				&& current.getVedioAuth()// 通过视频认证
				&& current.getAuthScore() >= BidConst.CREDIT_BORROW_SCORE// 用户风控分数大于要求分数
				&& account.getRemainBorrowLimit().compareTo(BidConst.SMALLEST_BIDREQUEST_AMOUNT) >= 0;// 用户剩余额度>系统最小发标金额
	}

	@Override
	public void apply(BidRequest bidRequest) {
		Logininfo current = UserContext.getCurrent();
		Account account = this.accountService.get(current.getId());
		// 再次检查当前用户是否能够发标
		if (canBorrow(current) && bidRequest.getBidRequestAmount().compareTo(account.getRemainBorrowLimit()) <= 0) {
			bidRequest.setBidRequestType(BidConst.BIDREQUEST_TYPE_NORMAL);
			bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
			bidRequest.setTotalRewardAmount(CalculatetUtil.calTotalInterest(bidRequest.getReturnType(),
					bidRequest.getBidRequestAmount(), bidRequest.getCurrentRate(), bidRequest.getMonthes2Return()));
			bidRequest.setCreateUser(current);
			bidRequest.setApplyTime(new Date());

			this.bidRequestMapper.insert(bidRequest);
			Userinfo userinfo = this.userService.get(current.getId());
			userinfo.addState(BitStatesUtils.OP_HAS_BIDRQUEST);
			this.userService.update(userinfo);
		}
	}

	@Override
	public PageResult query(BidRequestQueryObject qo) {
		int count = this.bidRequestMapper.queryForCount(qo);
		if (count > 0) {
			List<BidRequest> list = this.bidRequestMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(), list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void publishAudit(Long bidRequestId, String remark, int state) {
		BidRequest br = this.bidRequestMapper.selectByPrimaryKey(bidRequestId);
		if (br != null && br.getBidRequestState() == BidConst.BIDREQUEST_STATE_PUBLISH_PENDING) {
			Date now = new Date();
			// 创建审核历史对象，设置属性，并保存
			createHistory(bidRequestId, remark, state, br, BidRequestAuditHistory.AUDITTYPE_PUBLISH_BIDREQUEST);

			// 处理标相关信息
			if (state == BidRequestAuditHistory.STATE_REJECT) {
				br.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_REFUSE);
				Userinfo createUser = this.userService.get(br.getCreateUser().getId());
				createUser.removeState(BitStatesUtils.OP_HAS_BIDRQUEST);
				this.userService.update(createUser);
			} else if (state == BidRequestAuditHistory.STATE_PASS) {
				br.setBidRequestState(BidConst.BIDREQUEST_STATE_BIDDING);
				br.setNote(remark);
				br.setDisableDate(DateUtils.addDays(now, br.getDisableDays()));
				br.setPublishTime(now);
			}
			this.update(br);
		}
	}

	@Override
	public BidRequest getBidRequest(Long id) {
		return this.bidRequestMapper.selectByPrimaryKey(id);
	}

	@Override
	public void bid(Long bidRequestId, BigDecimal amount) {
		// 1、判断标的状态和用户是否有投标资格
		BidRequest bidRequest = this.bidRequestMapper.selectByPrimaryKey(bidRequestId);
		if (bidRequest != null && bidRequest.getBidRequestState() == BidConst.BIDREQUEST_STATE_BIDDING) {
			Logininfo current = UserContext.getCurrent();
			Account account = this.accountService.get(current.getId());
			if (bidRequest.getBidRequestAmount().compareTo(bidRequest.getCurrentSum()) > 0
					&& amount.compareTo(account.getUsableAmount()) <= 0// 投标金额小于等于用户余额
					&& amount.compareTo(bidRequest.getMinBidAmount()) >= 0// 投标金额大于等于最小投标金额
					&& amount.compareTo(bidRequest.getRemainAmount()) <= 0// 投标金额小于等于可投金额
					&& !current.getId().equals(bidRequest.getCreateUser().getId())) {// 当前用户不是借款人
				// 创建投标对象
				Bid bid = new Bid();
				bid.setActualRate(bidRequest.getCurrentRate());
				bid.setAvailableAmount(amount);
				bid.setBidRequestId(bidRequestId);
				bid.setBidUser(current);
				bid.setBidTime(new Date());
				bid.setBidRequestTitle(bidRequest.getTitle());
				this.bidMapper.insert(bid);
				// 修改标和投标人账户信息
				bidRequest.setBidCount(bidRequest.getBidCount() + 1);
				bidRequest.setCurrentSum(bidRequest.getCurrentSum().add(amount));
				account.setUsableAmount(account.getUsableAmount().subtract(amount));
				account.setFreezedAmount(account.getFreezedAmount().add(amount));
				this.accountService.update(account);
				// 生成投标的资金流水
				this.accountFlowService.addBidFlow(bid, account);
				// 标投满之后，设置标为一审状态
				if (bidRequest.getCurrentSum().equals(bidRequest.getBidRequestAmount())) {
					bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
				}
				this.update(bidRequest);
			}
		}
	}

	@Setter
	@Getter
	public class EntryValue<K, T> {
		private K key;
		private T value;

		public EntryValue(K key, T value) {
			this.key = key;
			this.value = value;
		}
	}

	@Override
	public List<EntryValue<Integer, String>> listBidRequestStates() {
		ArrayList<EntryValue<Integer, String>> states = new ArrayList<>();
		states.add(new EntryValue<>(0, "待发布"));
		states.add(new EntryValue<>(1, "招标中"));
		states.add(new EntryValue<>(2, "已撤销"));
		states.add(new EntryValue<>(3, "流标"));
		states.add(new EntryValue<>(4, "满标1审"));
		states.add(new EntryValue<>(5, "满标2审"));
		states.add(new EntryValue<>(6, "满标审核被拒绝"));
		states.add(new EntryValue<>(7, "还款中"));
		states.add(new EntryValue<>(8, "已还清"));
		states.add(new EntryValue<>(9, "逾期"));
		states.add(new EntryValue<>(10, "发标审核拒绝状态"));
		return states;
	}

	@Override
	public PageResult queryBids(BidQueryObject qo) {
		int count = this.bidMapper.queryForCount(qo);
		if (count > 0) {
			List<Bid> list = this.bidMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(), list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit1(Long bidRequestId, String remark, int state) {
		// 满标一审：
		// 查询出借款对象，判断是否处于一审状态
		BidRequest br = this.bidRequestMapper.selectByPrimaryKey(bidRequestId);
		if (br.getBidRequestState() == BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1) {
			// 创建审核历史对象，设置属性，并保存
			createHistory(bidRequestId, remark, state, br, BidRequestAuditHistory.AUDITTYPE_FULL_AUDIT1);
			if (state == BidRequestAuditHistory.STATE_PASS) {
				// 审核成功，修改借款和投标对象的状态，进入满标二审
				br.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);
			} else if (state == BidRequestAuditHistory.STATE_REJECT) {
				// 审核失败，退钱（投资人冻结金额减少，可用金额增加）借款人处于借款中的状态取消
				auditReject(br);
			}
			this.update(br);
		}
	}

	@Override
	public void audit2(Long bidRequestId, String remark, int state) {
		// 查询出借款对象，并判断
		BidRequest br = this.bidRequestMapper.selectByPrimaryKey(bidRequestId);
		if (br.getBidRequestState() == BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2) {
			// 创建借款历史对象，设置属性，
			this.createHistory(bidRequestId, remark, state, br, BidRequestAuditHistory.AUDITTYPE_FULL_AUDIT2);
			if (state == BidRequestAuditHistory.STATE_PASS) {
				// 审核通过
				br.setBidRequestState(BidConst.BIDREQUEST_STATE_PAYING_BACK);
				// 1、借款人
				Account applierAccount = this.accountService.get(br.getCreateUser().getId());
				// 借款人可用金额增加
				applierAccount.setUsableAmount(applierAccount.getUsableAmount().add(br.getBidRequestAmount()));
				// 添加二审通过流水
				this.accountFlowService.createBidSuccessFlow(applierAccount, br);
				// 剩余授信额度减少
				applierAccount
						.setRemainBorrowLimit(applierAccount.getRemainBorrowLimit().subtract(br.getBidRequestAmount()));
				// 未还金额增加
				applierAccount.setUnReturnAmount(br.getBidRequestAmount().add(br.getTotalRewardAmount()));
				// 移除借款中的状态
				Userinfo applier = this.userService.get(br.getCreateUser().getId());
				applier.removeState(BitStatesUtils.OP_HAS_BIDRQUEST);
				this.userService.update(applier);
				// 支付平台管理费，生成流水
				BigDecimal manageFee = CalculatetUtil.calAccountManagementCharge(br.getBidRequestAmount());
				applierAccount.setUsableAmount(applierAccount.getUsableAmount().subtract(manageFee));
				this.accountFlowService.createPayBorrowManageFeeFlow(applierAccount, manageFee);
				// 更新借款账户
				this.accountService.update(applierAccount);

				// 2、投资人
				Map<Long, Account> bidAccountMap = new HashMap<>();// 将投资人放到缓存中，减少update语句的执行
				for (Bid bid : br.getBids()) {
					Long bidUserId = bid.getBidUser().getId();
					Account investorAccount = bidAccountMap.get(bidUserId);// 从缓存中查找投资人，如果没找到去数据库中找
					if (investorAccount == null) {
						// 投资人账户-冻结金额减少，生成流水
						investorAccount = this.accountService.get(bidUserId);
						bidAccountMap.put(bidUserId, investorAccount);
					}
					investorAccount
							.setFreezedAmount(investorAccount.getFreezedAmount().subtract(bid.getAvailableAmount()));
					this.accountFlowService.createInvestorFlow(investorAccount, bid);
				}
				// 3、平台
				// 平台账户收取管理费，生成系统账户流水
				this.systemAccountService.chargeManagementFee(manageFee);

				// 4、后续还款
				// 生成还款计划对象和还款计划明细对象
				List<PaymentSchedule> psList = this.createPaymentSchedule(br);
				for (PaymentSchedule ps : psList) {
					List<PaymentScheduleDetail> details = ps.getDetails();
					for (PaymentScheduleDetail psd : details) {
						Account investorAccount = bidAccountMap.get(psd.getInvestorId());// 投资人账户
						// 计算代收本金和代收利息
						investorAccount
								.setUnReceivePrincipal(investorAccount.getUnReceivePrincipal().add(psd.getPrincipal()));
						investorAccount
								.setUnReceiveInterest(investorAccount.getUnReceiveInterest().add(psd.getInterest()));
					}
				}
				// 更新投资人
				for (Account bidAccount : bidAccountMap.values()) {
					this.accountService.update(bidAccount);
				}
			} else if (state == BidRequestAuditHistory.STATE_REJECT) {
				// 审核拒绝
				auditReject(br);
			}
			this.update(br);
		}
	}

	/**
	 * 生成还款计划
	 * 
	 * @param br
	 *            借款对象
	 * @return
	 */
	private List<PaymentSchedule> createPaymentSchedule(BidRequest br) {
		List<PaymentSchedule> pss = new ArrayList<>();
		BigDecimal totalPrincipal = BidConst.ZERO;// 已还的总本金
		BigDecimal totalInterest = BidConst.ZERO;// 已还的总利息
		for (int i = 0; i < br.getMonthes2Return(); i++) {
			PaymentSchedule ps = new PaymentSchedule();

			ps.setDeadLine(DateUtils.addMonths(br.getPublishTime(), i + 1));
			ps.setMonthIndex(i + 1);
			ps.setBidRequestType(br.getBidRequestType());
			ps.setReturnType(br.getReturnType());
			ps.setBidRequestId(br.getId());
			ps.setBorrowUser(br.getCreateUser());
			ps.setBidRequestTitle(br.getTitle());

			// -------------计算总额、本金、利息--------------
			if (i != br.getMonthes2Return() - 1) {
				ps.setTotalAmount(CalculatetUtil.calMonthToReturnMoney(// 本期还款金额
						br.getReturnType(), // 还款方式
						br.getBidRequestAmount(), // 借款金额
						br.getCurrentRate(), // 年化利率
						i + 1, // 第几期
						br.getMonthes2Return()));// 还款期限
				ps.setInterest(CalculatetUtil.calMonthlyInterest(// 本期还款利息
						br.getReturnType(), // 还款方式
						br.getBidRequestAmount(), // 借款金额
						br.getCurrentRate(), // 年化利率
						i + 1, // 第几期
						br.getMonthes2Return()));// 还款期限
				ps.setPrincipal(ps.getTotalAmount().subtract(ps.getInterest()));// 本期还款本金

				totalPrincipal = totalPrincipal.add(ps.getPrincipal());
				totalInterest = totalInterest.add(ps.getInterest());
			} else {
				// 最后一期通过总额前N期得到
				ps.setPrincipal(br.getBidRequestAmount().subtract(totalPrincipal));
				ps.setInterest(br.getTotalRewardAmount().subtract(totalInterest));
				ps.setTotalAmount(ps.getPrincipal().add(ps.getInterest()));
			}
			pss.add(ps);
			this.paymentScheduleMapper.insert(ps);
			// 生成还款计划明细
			createPaymentScheduleDetails(ps, br);
		}
		return pss;
	}

	/**
	 * 生成还款计划明细对象
	 * 
	 * @param ps
	 * @param br
	 */
	private void createPaymentScheduleDetails(PaymentSchedule ps, BidRequest br) {
		BigDecimal totalPrincipal = BidConst.ZERO;// 已还的总本金
		BigDecimal totalInterest = BidConst.ZERO;// 已还的总利息
		for (int i = 0; i < br.getBidCount(); i++) {// 当前借款对象中的投标次数
			Bid bid = br.getBids().get(i);
			PaymentScheduleDetail psd = new PaymentScheduleDetail();// 创建还款计划明细对象
			// 设置属性，并保存到数据库中
			psd.setBidAmount(bid.getAvailableAmount());
			psd.setBidId(bid.getId());
			psd.setPaymentScheduleId(ps.getId());
			psd.setBorrowUser(ps.getBorrowUser());
			psd.setInvestorId(bid.getBidUser().getId());
			psd.setBidRequestId(ps.getBidRequestId());
			psd.setReturnType(ps.getReturnType());
			psd.setPayDate(psd.getPayDate());
			psd.setDeadLine(ps.getDeadLine());
			psd.setMonthIndex(ps.getMonthIndex());
			// -------------计算总额、本金、利息--------------
			// 计算比率
			BigDecimal rate = bid.getAvailableAmount().divide(br.getBidRequestAmount(), BidConst.CAL_SCALE,
					RoundingMode.HALF_UP);
			if (i != br.getBidCount() - 1) {
				psd.setPrincipal(rate.multiply(ps.getPrincipal().setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP)));
				psd.setInterest(rate.multiply(ps.getInterest().setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP)));

				totalInterest = totalInterest.add(psd.getInterest());
				totalPrincipal = totalPrincipal.add(psd.getPrincipal());
			} else {
				psd.setInterest(ps.getInterest().subtract(totalInterest));
				psd.setPrincipal(ps.getPrincipal().subtract(totalPrincipal));
			}
			psd.setTotalAmount(psd.getPrincipal().add(psd.getInterest()));
			ps.getDetails().add(psd);
			this.paymentScheduleDetailMapper.insert(psd);
		}
	}

	/**
	 * 审核拒绝逻辑
	 * 
	 * @param br
	 *            借款对象
	 */
	private void auditReject(BidRequest br) {
		br.setBidRequestState(BidConst.BIDREQUEST_STATE_REJECTED);
		List<Bid> bids = br.getBids();
		for (Bid bid : bids) {
			// 获取投资人账户
			Account account = this.accountService.get(bid.getBidUser().getId());
			account.setUsableAmount(account.getUsableAmount().add(bid.getAvailableAmount()));
			account.setFreezedAmount(account.getFreezedAmount().subtract(bid.getAvailableAmount()));
			this.accountService.update(account);
			// 生成流水
			this.accountFlowService.createBidFailedFlow(account, bid);
		}
		Userinfo applier = this.userService.get(br.getCreateUser().getId());
		applier.removeState(BitStatesUtils.OP_HAS_BIDRQUEST);
		this.userService.update(applier);
	}

	/**
	 * 创建审核历史对象
	 * 
	 * @param bidRequestId
	 * @param remark
	 * @param state
	 * @param br
	 * @param auditType
	 */
	private void createHistory(Long bidRequestId, String remark, int state, BidRequest br, int auditType) {
		BidRequestAuditHistory history = new BidRequestAuditHistory();
		history.setApplier(br.getCreateUser());
		history.setApplyTime(br.getApplyTime());
		history.setAuditor(UserContext.getCurrent());
		history.setAuditTime(new Date());
		history.setAuditType(auditType);
		history.setBidRequestId(bidRequestId);
		history.setRemark(remark);
		history.setState(state);
		this.bidRequestAuditHistoryMapper.insert(history);
	}

}
