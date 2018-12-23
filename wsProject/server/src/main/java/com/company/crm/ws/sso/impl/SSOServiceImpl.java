package com.company.crm.ws.sso.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.company.crm.util.SSOUtil;
import com.company.crm.ws.sso.ISSOService;

@WebService
public class SSOServiceImpl implements ISSOService {

	@Override
	public String checkToken(String key,String url) {
		
		if(SSOUtil.KEY_TOKEN_MAP.containsKey(key)){
			String token = SSOUtil.KEY_TOKEN_MAP.get(key);
			// 验证token是否合法
			boolean result = SSOUtil.TOKEN_SESSION_MAP.containsKey(token);
			if (result){
				List<String> urls = null;
				// 如果map中已经存在该token，则不创建url集合
				if(SSOUtil.TOKEN_URLS_MAP.containsKey(token)){
					urls = SSOUtil.TOKEN_URLS_MAP.get(token);
				} else {
					urls = new ArrayList<String>();
				}
				urls.add(url);
				// 注册应用系统（保存系统的url，后面注销的时候会用到）
				SSOUtil.TOKEN_URLS_MAP.put(token, urls);
			} 
			// 移除key
			SSOUtil.KEY_TOKEN_MAP.remove(key);
			return token;
		}
		return null;
	}

}
