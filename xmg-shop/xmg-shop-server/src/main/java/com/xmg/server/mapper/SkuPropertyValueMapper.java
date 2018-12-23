package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.SkuPropertyValue;

public interface SkuPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuPropertyValue record);

    SkuPropertyValue selectByPrimaryKey(Long id);

    List<SkuPropertyValue> selectAll();

    int updateByPrimaryKey(SkuPropertyValue record);
    
    void deleteByPropertyId(Long propertyId);

	List<SkuPropertyValue> getSkuPropertyValue(Long skuPropertyId);

}