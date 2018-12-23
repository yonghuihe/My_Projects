package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.SkuProperty;

public interface SkuPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuProperty record);

    SkuProperty selectByPrimaryKey(Long id);

    List<SkuProperty> selectAll();

    int updateByPrimaryKey(SkuProperty record);

	List<SkuProperty> getSkuProperty(Long catalogId);
}