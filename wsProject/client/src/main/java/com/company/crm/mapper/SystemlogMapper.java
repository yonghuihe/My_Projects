package com.company.crm.mapper;

import java.util.List;

import com.company.crm.domain.Systemlog;

public interface SystemlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Systemlog record);

    Systemlog selectByPrimaryKey(Long id);

    List<Systemlog> selectAll();

    int updateByPrimaryKey(Systemlog record);
}