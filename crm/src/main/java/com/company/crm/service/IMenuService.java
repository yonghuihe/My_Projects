package com.company.crm.service;
import java.util.List;
import com.company.crm.domain.Menu;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;

public interface IMenuService {
	int deleteByPrimaryKey(Long id);
    int insert(Menu record);
    Menu selectByPrimaryKey(Long id);
    List<Menu> selectAll();
    int updateByPrimaryKey(Menu record);
	PageResult queryByConditionPage(QueryObject qo);
	List<Menu> selectRoot();
}
