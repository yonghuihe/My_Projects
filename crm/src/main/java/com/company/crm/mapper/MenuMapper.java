package com.company.crm.mapper;

import com.company.crm.domain.Menu;
import com.company.crm.query.QueryObject;
import java.util.List;

public interface MenuMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Menu record);

	Menu selectByPrimaryKey(Long id);

	List<Menu> selectAll();

	int updateByPrimaryKey(Menu record);

	Long queryByCondictionCount(QueryObject qo);

	List<Menu> queryByConditionResult(QueryObject qo);

	List<Menu> selectRoot();
}