package com.xmg.server.mapper;

import org.apache.ibatis.annotations.Param;

import com.xmg.api.domain.LoginInfo;

public interface LoginInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoginInfo record);

    LoginInfo selectByPrimaryKey(Long id);
    
    int updateByPrimaryKey(LoginInfo record);

	LoginInfo selectLoginInfo(@Param("userName")String userName, @Param("password")String password);
}