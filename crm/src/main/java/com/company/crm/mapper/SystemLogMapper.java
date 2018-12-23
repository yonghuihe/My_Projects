package com.company.crm.mapper;

import java.util.List;

import com.company.crm.domain.SystemLog;
import com.company.crm.query.QueryObject;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);
    int insert(SystemLog record);
    SystemLog selectByPrimaryKey(Long id);
    List<SystemLog> selectAll();
    int updateByPrimaryKey(SystemLog record);
	Long queryByCondictionCount(QueryObject qo);
	List<SystemLog> queryByConditionResult(QueryObject qo);
}