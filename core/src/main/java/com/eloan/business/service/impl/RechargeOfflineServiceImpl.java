package com.eloan.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.mapper.AccountMapper;
import com.eloan.business.mapper.RechargeOfflineMapper;
import com.eloan.business.query.RechargeOfflineQueryObject;
import com.eloan.business.service.IAccountFlowService;
import com.eloan.business.service.IRechargeOfflineService;

@Service
public class RechargeOfflineServiceImpl implements IRechargeOfflineService {

	@Autowired
	private RechargeOfflineMapper rechargeOfflineMapper;

	@Autowired
	private AccountMapper accountMappr;
	
	@Autowired
	private IAccountFlowService accountFlowService;

	@Override
	public void apply(RechargeOffline recharge) {
		recharge.setApplier(UserContext.getCurrent());
		recharge.setApplyTime(new Date());
		recharge.setState(RechargeOffline.STATE_APPLY);
		this.rechargeOfflineMapper.insert(recharge);
	}

	@Override
	public PageResult query(RechargeOfflineQueryObject qo) {
		int count = this.rechargeOfflineMapper.queryForCount(qo);
		if (count > 0) {
			List<RechargeOffline> list = this.rechargeOfflineMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(), list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit(Long id, String remark, int state) {
		// 设置相关状态
		RechargeOffline offline = this.rechargeOfflineMapper.selectByPrimaryKey(id);
		if (offline != null && offline.getState() == RechargeOffline.STATE_APPLY) {
			offline.setState(state);
			offline.setRemark(remark);
			offline.setAuditor(UserContext.getCurrent());
			offline.setAuditTime(new Date());
			this.rechargeOfflineMapper.updateByPrimaryKey(offline);
			if (state == RechargeOffline.STATE_PASS) {
				// 如果审核通过，改变账户余额（余额增加）
				Account account = this.accountMappr.selectByPrimaryKey(offline.getApplier().getId());
				account.setUsableAmount(account.getUsableAmount().add(offline.getAmount()));
				this.accountMappr.updateByPrimaryKey(account);
				// 创建线下充值流水
				this.accountFlowService.addRechargeOfflineFlow(offline, account);
			}
		}
	}

}
