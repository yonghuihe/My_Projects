package com.company.crm.service;
import java.util.List;
import com.company.crm.domain.Customer;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;

public interface ICustomerService {
	int deleteByPrimaryKey(Long id);
    int insert(Customer record);
    Customer selectByPrimaryKey(Long id);
    List<Customer> selectAll();
    int updateByPrimaryKey(Customer record);
	PageResult queryByConditionPage(QueryObject qo);
}
