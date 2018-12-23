package com.xmg.api.service;

import java.util.List;

import com.xmg.api.domain.SkuProperty;
import com.xmg.api.domain.SkuPropertyValue;



public interface ISkuPropertyService {

	int deleteByPrimaryKey(Long id);

    int insert(SkuProperty record);

    SkuProperty selectByPrimaryKey(Long id);

    List<SkuProperty> selectAll();

    int updateByPrimaryKey(SkuProperty record);

	List<SkuProperty> getSkuProperty(Long catalogId);


	List<SkuPropertyValue> getSkuPropertyValue(Long skuPropertyId);

	void insertPropertyValue(SkuPropertyValue skuPropertyValue);



}





