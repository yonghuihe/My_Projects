package com.company.crm.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Customer;
import com.company.crm.mapper.CustomerMapper;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;
import com.company.crm.service.ICustomerService;
@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return customerMapper.deleteByPrimaryKey(id);
	}

	public int insert(Customer record) {
		return customerMapper.insert(record);
	}

	public Customer selectByPrimaryKey(Long id) {
		return customerMapper.selectByPrimaryKey(id);
	}

	public List<Customer> selectAll() {
		return customerMapper.selectAll();
	}

	public int updateByPrimaryKey(Customer record) {
		return customerMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = customerMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Customer> result = customerMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
