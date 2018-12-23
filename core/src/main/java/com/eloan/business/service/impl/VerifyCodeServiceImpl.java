package com.eloan.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.business.domain.VerifyCode;
import com.eloan.business.mapper.VerifyCodeMapper;
import com.eloan.business.service.IVerifyCodeService;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {
	
	@Autowired
	private VerifyCodeMapper verifyCodeMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return verifyCodeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(VerifyCode record) {
		return verifyCodeMapper.insert(record);
	}

	@Override
	public VerifyCode selectByPrimaryKey(Long id) {
		return verifyCodeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<VerifyCode> selectAll() {
		return verifyCodeMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(VerifyCode record) {
		return verifyCodeMapper.updateByPrimaryKey(record);
	}

	@Override
	public VerifyCode queryByPhoneNumber(String phoneNumber) {
		return verifyCodeMapper.queryByPhoneNumber(phoneNumber);
	}

	@Override
	public VerifyCode queryByCode(String code) {
		return verifyCodeMapper.queryByCode(code);
	}

}
