package com.xmg.api.service;

import com.xmg.api.domain.LoginInfo;


public interface ILoginInfoService {

	LoginInfo selectLoginInfo(String userName,String password);
}
