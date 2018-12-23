package com.eloan.business.service;

import com.eloan.base.query.PageResult;
import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.query.RechargeOfflineQueryObject;

public interface IRechargeOfflineService {
	void apply(RechargeOffline recharge);
	
	PageResult query(RechargeOfflineQueryObject qo);

	void audit(Long id, String remark, int state);
}
