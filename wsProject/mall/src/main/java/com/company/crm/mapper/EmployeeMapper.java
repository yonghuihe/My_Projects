package com.company.crm.mapper;

import org.apache.ibatis.annotations.Param;

import com.company.crm.domain.Employee;

public interface EmployeeMapper {
  
    Employee selectByPrimaryKey(Long id);
    
    Employee getEmployeeForlogin(@Param("username")String username,@Param("password")String password);

}