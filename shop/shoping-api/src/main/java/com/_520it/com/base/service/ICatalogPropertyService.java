package com._520it.com.base.service;

import com._520it.com.base.domain.CatalogProperty;
import com._520it.com.base.domain.CatalogPropertyValue;

import java.util.List;




public interface ICatalogPropertyService {

	int deleteByPrimaryKey(Long id);

    int insert(CatalogProperty record);

    CatalogProperty selectByPrimaryKey(Long id);

    List<CatalogProperty> selectAll();

    int updateByPrimaryKey(CatalogProperty record);

	List<CatalogProperty> getCatalogProperty(Long catalogId);


	List<CatalogPropertyValue> getCatalogPropertyValue(Long catalogPropertyId);

	void insertPropertyValue(CatalogPropertyValue catalogPropertyValue);


}





