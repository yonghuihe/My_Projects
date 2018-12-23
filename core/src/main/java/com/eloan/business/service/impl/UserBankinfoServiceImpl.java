package com.eloan.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.UserBankinfo;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.UserBankinfoMapper;
import com.eloan.business.service.IUserBankinfoService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BitStatesUtils;

@Service
public class UserBankinfoServiceImpl implements IUserBankinfoService {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserBankinfoMapper userBankinfoMapper;

	@Override
	public void bind(UserBankinfo vo) {
		// 1、查询出当前用户对象，判断是否已经实名认证并绑定银行卡
		Logininfo current = UserContext.getCurrent();
		Userinfo userinfo = this.userService.get(current.getId());
		if (userinfo.getRealAuth() && !userinfo.getBindBankinfo()) {
			// 2、创建一个UserBankinfo对象，设置属性并保存到数据库中
			UserBankinfo bankinfo = new UserBankinfo();
			bankinfo.setBankName(vo.getBankName());
			bankinfo.setBankNumber(vo.getBankNumber());
			bankinfo.setForkName(vo.getForkName());
			bankinfo.setAccountName(userinfo.getRealName());
			bankinfo.setUserinfoId(userinfo.getId());
			this.userBankinfoMapper.insert(bankinfo);
			// 3、设置用户已经绑定银行卡
			userinfo.addState(BitStatesUtils.OP_BIND_BANKINFO);
			this.userService.update(userinfo);
		}
	}

	@Override
	public UserBankinfo get(Long userInfoId) {
		return this.userBankinfoMapper.selectByUserInfoId(userInfoId);
	}
	
}
