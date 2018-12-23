package com.company.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.crm.domain.Employee;
import com.company.crm.domain.Role;
import com.company.crm.query.QueryObject;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
    
    List<Employee> queryPageResult(QueryObject qo);
    
    Long queryPageResultCount(QueryObject qo);
    
    void insertRelation(@Param("rId")Long rid,@Param("pId")Long pid);
    
    void deleteRelation(Long id);
}