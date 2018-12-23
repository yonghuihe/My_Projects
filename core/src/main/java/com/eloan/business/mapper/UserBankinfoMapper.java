package com.eloan.business.mapper;

import com.eloan.business.domain.UserBankinfo;
import java.util.List;

public interface UserBankinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBankinfo record);

    UserBankinfo selectByUserInfoId(Long userInfoId);

    List<UserBankinfo> selectAll();

    int updateByPrimaryKey(UserBankinfo record);
}