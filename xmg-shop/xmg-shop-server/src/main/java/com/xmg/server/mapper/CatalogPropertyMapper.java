package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.CatalogProperty;

public interface CatalogPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CatalogProperty record);

    CatalogProperty selectByPrimaryKey(Long id);

    List<CatalogProperty> selectAll();

    int updateByPrimaryKey(CatalogProperty record);

	List<CatalogProperty> getCatalogProperty(Long catalogId);
}