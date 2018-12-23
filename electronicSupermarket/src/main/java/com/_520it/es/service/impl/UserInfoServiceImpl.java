package com._520it.es.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.es.domain.UserInfo;
import com._520it.es.mapper.UserInfoMapper;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;
import com._520it.es.service.IUserInfoService;
@Service
public class UserInfoServiceImpl implements IUserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return userInfoMapper.deleteByPrimaryKey(id);
	}

	public int insert(UserInfo record) {
		return userInfoMapper.insert(record);
	}

	public UserInfo selectByPrimaryKey(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	public List<UserInfo> selectAll() {
		return userInfoMapper.selectAll();
	}

	public int updateByPrimaryKey(UserInfo record) {
		return userInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = userInfoMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<UserInfo> result = userInfoMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
