package com.xmg.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.LoginInfo;
import com.xmg.api.service.ILoginInfoService;
import com.xmg.server.mapper.LoginInfoMapper;
import com.xmg.server.utils.MD5;

@Service("loginInfoService")
public class LoginInfoServiceImpl implements ILoginInfoService{

	@Autowired
	LoginInfoMapper loginInfoDao;
	@Override
	public LoginInfo selectLoginInfo(String userName, String password) {
		// TODO Auto-generated method stub
		return loginInfoDao.selectLoginInfo(userName,MD5.encode(password));
	}

}
