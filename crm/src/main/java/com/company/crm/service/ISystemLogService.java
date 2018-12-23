package com.company.crm.service;
import java.util.List;
import com.company.crm.domain.SystemLog;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;

public interface ISystemLogService {
	int deleteByPrimaryKey(Long id);
    int insert(SystemLog record);
    SystemLog selectByPrimaryKey(Long id);
    List<SystemLog> selectAll();
    int updateByPrimaryKey(SystemLog record);
	PageResult queryByConditionPage(QueryObject qo);
}
