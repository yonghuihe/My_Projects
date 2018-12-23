package com.company.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.crm.domain.Employee;
import com.company.crm.query.EmployeeQuery;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);
    
    Employee getEmployeeForlogin(@Param("username")String username,@Param("password")String password);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
    
    void updateState(Long id);
    
    List<Employee> queryPageResult(EmployeeQuery qo);
    
    Long queryPageResultCount(EmployeeQuery qo);
    
    void insertRelation(@Param("rId")Long rid,@Param("eId")Long eid);
    
    List<Long> getRidByEid(Long id);
    
    void deleteRelation(Long id);
}