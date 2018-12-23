package com.company.crm.mapper;

import java.util.List;

import com.company.crm.domain.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);
    
    List<Permission> getPermissionByRid(Long id);
    
    List<String> getPermissionByEid(Long id);
}