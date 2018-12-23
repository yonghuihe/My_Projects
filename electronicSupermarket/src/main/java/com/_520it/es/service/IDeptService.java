package com._520it.es.service;
import java.util.List;
import com._520it.es.domain.Dept;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

public interface IDeptService {
	int deleteByPrimaryKey(Long id);
    int insert(Dept record);
    Dept selectByPrimaryKey(Long id);
    List<Dept> selectAll();
    int updateByPrimaryKey(Dept record);
	PageResult queryByConditionPage(QueryObject qo);
}
