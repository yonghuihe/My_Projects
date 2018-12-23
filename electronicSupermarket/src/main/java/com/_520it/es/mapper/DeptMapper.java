package com._520it.es.mapper;

import com._520it.es.domain.Dept;
import com._520it.es.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Dept record);
    Dept selectByPrimaryKey(Long id);
    List<Dept> selectAll();
    int updateByPrimaryKey(Dept record);
	Long queryByCondictionCount(QueryObject qo);
	List<Dept> queryByConditionResult(QueryObject qo);
}