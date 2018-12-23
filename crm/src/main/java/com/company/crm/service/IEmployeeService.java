package com.company.crm.service;

import java.util.List;

import com.company.crm.domain.Employee;
import com.company.crm.page.PageResult;
import com.company.crm.query.EmployeeQueryObject;

public interface IEmployeeService {
	
	int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
    
    PageResult queryPageResult(EmployeeQueryObject qo);

    /**
     * 将员工状态设置为离职
     * @param id
     */
	void updateState(Long id);

	List<Long> getRoleIdsByEId(Long id);

	Employee getEmployeeByUsername(String username);

	List<String> getRoleByEId(Long id);

	List<String> getPermissionByEId(Long id);
}
