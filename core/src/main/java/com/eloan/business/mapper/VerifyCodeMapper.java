package com.eloan.business.mapper;

import com.eloan.business.domain.VerifyCode;
import java.util.List;

public interface VerifyCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VerifyCode record);

    VerifyCode selectByPrimaryKey(Long id);

    List<VerifyCode> selectAll();

    int updateByPrimaryKey(VerifyCode record);

	VerifyCode queryByPhoneNumber(String phoneNumber);

	VerifyCode queryByCode(String code);
}