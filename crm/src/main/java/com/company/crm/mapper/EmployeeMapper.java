package com.company.crm.mapper;

import com.company.crm.domain.BatchInsertIds;
import com.company.crm.domain.Employee;
import com.company.crm.query.EmployeeQueryObject;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
    
    Long queryPageResultCount(EmployeeQueryObject qo);
    
    List<Employee> queryPageResult(EmployeeQueryObject qo);

	void updateState(Employee employee);

	void batchInsertRelation(List<BatchInsertIds> list);

	void deleteRelation(Long id);

	List<Long> getRoleIdsByEId(Long id);

	Employee getEmployeeByUsername(String username);

	List<String> getRoleByEId(Long id);

	List<String> getPermissionByEId(Long id);
}