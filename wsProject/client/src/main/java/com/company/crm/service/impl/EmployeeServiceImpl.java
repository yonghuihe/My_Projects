package com.company.crm.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Employee;
import com.company.crm.domain.Role;
import com.company.crm.mapper.EmployeeMapper;
import com.company.crm.page.PageResult;
import com.company.crm.query.EmployeeQuery;
import com.company.crm.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Employee record) {
		int count = mapper.insert(record);
		
		//关联关系
		List<Role> roles = record.getRoles();
		for (Role role : roles) {
			mapper.insertRelation(role.getId(), record.getId());
		}
		return count;
	}

	@Override
	public Employee selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Employee> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Employee record) {
		//打破关系
		mapper.deleteRelation(record.getId());
		
		//关联关系
		List<Role> roles = record.getRoles();
		for (Role role : roles) {
			mapper.insertRelation(role.getId(), record.getId());
		}
		
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public Employee getEmployeeForlogin(String username, String password) {
		return mapper.getEmployeeForlogin(username, password);
	}

	@Override
	public void updateState(Long id) {
		mapper.updateState(id);
	}

	@Override
	public PageResult queryPageResult(EmployeeQuery qo) {
		Long count = mapper.queryPageResultCount(qo);
		//如果有数据就去查询结果集
		if(count>0){
			return new PageResult(count, mapper.queryPageResult(qo));
		}
		
		return new PageResult(count,Collections.EMPTY_LIST);
	}

	@Override
	public List<Long> getRidByEid(Long id) {
		return mapper.getRidByEid(id);
	}
	
}
