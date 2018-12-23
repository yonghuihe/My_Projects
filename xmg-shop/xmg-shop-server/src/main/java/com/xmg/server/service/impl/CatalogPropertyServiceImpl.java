package com.xmg.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.CatalogProperty;
import com.xmg.api.domain.CatalogPropertyValue;
import com.xmg.api.service.ICatalogPropertyService;
import com.xmg.server.mapper.CatalogPropertyMapper;
import com.xmg.server.mapper.CatalogPropertyValueMapper;

@Service("catalogPropertyService")
public class CatalogPropertyServiceImpl implements ICatalogPropertyService{

	@Autowired
	CatalogPropertyMapper catalogPropertyDao;
	@Autowired
	CatalogPropertyValueMapper catalogPropertyValueDao;
	
	@Override
	public int deleteByPrimaryKey(Long propertyId) {
		// TODO Auto-generated method stub
		catalogPropertyValueDao.deleteByPropertyId(propertyId);
		return catalogPropertyDao.deleteByPrimaryKey(propertyId);
	}

	@Override
	public int insert(CatalogProperty catalogProperty) {
		// TODO Auto-generated method stub
		return catalogPropertyDao.insert(catalogProperty);
	}

	@Override
	public CatalogProperty selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return catalogPropertyDao.selectByPrimaryKey(id);
	}

	@Override
	public List<CatalogProperty> selectAll() {
		// TODO Auto-generated method stub
		return catalogPropertyDao.selectAll();
	}

	@Override
	public int updateByPrimaryKey(CatalogProperty record) {
		// TODO Auto-generated method stub
		return catalogPropertyDao.updateByPrimaryKey(record);
	}

	@Override
	public List<CatalogProperty> getCatalogProperty(Long catalogId) {
		// TODO Auto-generated method stub
		return catalogPropertyDao.getCatalogProperty(catalogId);
	}

	@Override
	public List<CatalogPropertyValue> getCatalogPropertyValue(
			Long catalogPropertyId) {
		// TODO Auto-generated method stub
		List<CatalogPropertyValue> catalogPropertyValue = catalogPropertyValueDao.getCatalogPropertyValue(catalogPropertyId); 
		return catalogPropertyValue;
	}

	@Override
	public void insertPropertyValue(CatalogPropertyValue catalogPropertyValue) {
		// TODO Auto-generated method stub
		catalogPropertyValueDao.insert(catalogPropertyValue);
	}



}
