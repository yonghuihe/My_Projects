package com._520it.es.mapper;

import com._520it.es.domain.UserInfo;
import com._520it.es.query.QueryObject;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);
    int insert(UserInfo record);
    UserInfo selectByPrimaryKey(Long id);
    List<UserInfo> selectAll();
    int updateByPrimaryKey(UserInfo record);
	Long queryByCondictionCount(QueryObject qo);
	List<UserInfo> queryByConditionResult(QueryObject qo);

}