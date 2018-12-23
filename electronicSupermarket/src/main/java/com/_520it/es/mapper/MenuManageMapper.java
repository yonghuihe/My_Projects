package com._520it.es.mapper;

import com._520it.es.domain.MenuManage;
import com._520it.es.query.QueryObject;

import java.util.List;

public interface MenuManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuManage record);

    MenuManage selectByPrimaryKey(Long id);

    List<MenuManage> selectAll();

    int updateByPrimaryKey(MenuManage record);

    Long queryByCondictionCount(QueryObject qo);

    List<MenuManage> queryByConditionResult(QueryObject qo);

    List<MenuManage> listParent();

    List<MenuManage> listButton();

    List<MenuManage> listByParentId(Long id);

}