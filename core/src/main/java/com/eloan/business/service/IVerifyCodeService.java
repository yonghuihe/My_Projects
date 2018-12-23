package com.eloan.business.service;

import java.util.List;

import com.eloan.business.domain.VerifyCode;

public interface IVerifyCodeService {
	int deleteByPrimaryKey(Long id);

    int insert(VerifyCode record);

    VerifyCode selectByPrimaryKey(Long id);

    List<VerifyCode> selectAll();

    int updateByPrimaryKey(VerifyCode record);

	VerifyCode queryByPhoneNumber(String phoneNumber);

	VerifyCode queryByCode(String code);
}
