package com.company.crm.mapper;

import java.util.List;

import com.company.crm.domain.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);
    
    List<Menu> getRootMenu();
    
    Menu getChildrenMenu(Long id);
}