package com.company.crm.service;

import java.util.List;

import com.company.crm.domain.Role;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;

public interface IRoleService {
	
	int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
    
    PageResult queryPageResult(QueryObject qo);
}
