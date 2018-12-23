package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.CatalogPropertyValue;

public interface CatalogPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CatalogPropertyValue record);

    CatalogPropertyValue selectByPrimaryKey(Long id);

    List<CatalogPropertyValue> selectAll();

    int updateByPrimaryKey(CatalogPropertyValue record);

	List<CatalogPropertyValue> getCatalogPropertyValue(Long catalogPropertyId);

	void deleteByPropertyId(Long propertyId);
}