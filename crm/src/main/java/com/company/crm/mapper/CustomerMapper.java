package com.company.crm.mapper;

import java.util.List;

import com.company.crm.domain.Customer;
import com.company.crm.query.QueryObject;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Customer record);
    Customer selectByPrimaryKey(Long id);
    List<Customer> selectAll();
    int updateByPrimaryKey(Customer record);
	Long queryByCondictionCount(QueryObject qo);
	List<Customer> queryByConditionResult(QueryObject qo);
}