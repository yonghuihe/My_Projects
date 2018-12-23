package com.eloan.business.service;

import com.eloan.business.domain.UserBankinfo;

public interface IUserBankinfoService {

	/**
	 * 银行卡的绑定
	 * 
	 * @param vo
	 */
	void bind(UserBankinfo vo);
	
	/**
	 * 根据用户id获取银行卡信息
	 * @param id
	 * @return
	 */
	UserBankinfo get(Long userInfoId);

}
