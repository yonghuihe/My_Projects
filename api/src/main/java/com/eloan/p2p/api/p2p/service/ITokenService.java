package com.eloan.p2p.api.p2p.service;

import com.eloan.base.domain.Logininfo;

/**
 * 令牌服务接口
 * 
 * @author yonghui
 *
 */
public interface ITokenService {
	
	public static final String TOKEN_IN_HEADER = "token_in_header";

	/**
	 * 登录成功，创建token
	 * @param logininfo
	 * @return
	 */
	String createToken(Logininfo logininfo);

	/**
	 * 登录成功，销毁token（注销）
	 * @param token
	 */
	void destroyToken(String token);

	/**
	 * 获取当前用户
	 * @param token
	 * @return
	 */
	Long findToken(String token);

	/**
	 * 根据token检查当前用户是否登录
	 * @param token
	 * @return
	 */
	boolean checkToken(String token);

}
