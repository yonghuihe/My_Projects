package com._520it.es.service;

import com._520it.es.domain.MenuManage;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

import java.util.List;

public interface IMenuManageService {
    int deleteByPrimaryKey(Long id);

    int insert(MenuManage record);

    MenuManage selectByPrimaryKey(Long id);

    List<MenuManage> selectAll();

    int updateByPrimaryKey(MenuManage record);

    PageResult queryByConditionPage(QueryObject qo);

    List<MenuManage> listParent();

    List<MenuManage> listButton();

    void reloadMenu();
}
