package com.company.crm.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.SystemLog;
import com.company.crm.mapper.SystemLogMapper;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;
import com.company.crm.service.ISystemLogService;
@Service
public class SystemLogServiceImpl implements ISystemLogService {
	@Autowired
	private SystemLogMapper systemLogMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return systemLogMapper.deleteByPrimaryKey(id);
	}

	public int insert(SystemLog record) {
		return systemLogMapper.insert(record);
	}

	public SystemLog selectByPrimaryKey(Long id) {
		return systemLogMapper.selectByPrimaryKey(id);
	}

	public List<SystemLog> selectAll() {
		return systemLogMapper.selectAll();
	}

	public int updateByPrimaryKey(SystemLog record) {
		return systemLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = systemLogMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<SystemLog> result = systemLogMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
