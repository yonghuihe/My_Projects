package com.eloan.p2p.api.p2p.service.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eloan.base.domain.Logininfo;
import com.eloan.p2p.api.p2p.service.ITokenService;

@Service
public class TokenServiceImpl implements ITokenService {

	/**
	 * token缓存 改进：将token保存到redis中
	 */
	private static Map<String, Long> tokens = new ConcurrentHashMap<>();

	@Override
	public String createToken(Logininfo logininfo) {
		String token = UUID.randomUUID().toString();
		tokens.put(token, logininfo.getId());
		return token;
	}

	@Override
	public void destroyToken(String token) {
		tokens.remove(token);
	}

	@Override
	public Long findToken(String token) {
		return tokens.get(token);
	}

	@Override
	public boolean checkToken(String token) {
		return StringUtils.hasLength(token) && tokens.get(token) != null;
	}

}
