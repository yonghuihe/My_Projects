package com._520it.es.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.es.domain.Dept;
import com._520it.es.mapper.DeptMapper;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;
import com._520it.es.service.IDeptService;
@Service
public class DeptServiceImpl implements IDeptService {
	@Autowired
	private DeptMapper deptMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return deptMapper.deleteByPrimaryKey(id);
	}

	public int insert(Dept record) {
		return deptMapper.insert(record);
	}

	public Dept selectByPrimaryKey(Long id) {
		return deptMapper.selectByPrimaryKey(id);
	}

	public List<Dept> selectAll() {
		return deptMapper.selectAll();
	}

	public int updateByPrimaryKey(Dept record) {
		return deptMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = deptMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Dept> result = deptMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
