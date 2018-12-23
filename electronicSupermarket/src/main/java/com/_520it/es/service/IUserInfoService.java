package com._520it.es.service;
import com._520it.es.domain.UserInfo;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

import java.util.List;

public interface IUserInfoService {
	int deleteByPrimaryKey(Long id);
    int insert(UserInfo record);
    UserInfo selectByPrimaryKey(Long id);
    List<UserInfo> selectAll();
    int updateByPrimaryKey(UserInfo record);
	PageResult queryByConditionPage(QueryObject qo);

}
