package com.eloan.business.mapper;

import com.eloan.business.domain.SystemAccountFlow;
import java.util.List;

public interface SystemAccountFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemAccountFlow record);

    SystemAccountFlow selectByPrimaryKey(Long id);

    List<SystemAccountFlow> selectAll();

    int updateByPrimaryKey(SystemAccountFlow record);
}