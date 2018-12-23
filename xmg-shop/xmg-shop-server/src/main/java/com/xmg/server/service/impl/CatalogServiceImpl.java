package com.xmg.server.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.Catalog;
import com.xmg.api.service.ICatalogService;
import com.xmg.server.mapper.CatalogMapper;

@Service("catalogService")
public class CatalogServiceImpl implements ICatalogService{

	@Autowired
	CatalogMapper catalogDao;
	
	@Override
	public List<Catalog> getChildenCatalogById(Long parentId) {
		// TODO Auto-generated method stub
		return catalogDao.getChildenCatalogById(parentId);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return catalogDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Catalog catalog) {
		// TODO Auto-generated method stub
		catalog.setCreateDate(new Date());
		catalog.setIsParent(Byte.valueOf("0"));
		catalog.setLastModifiedDate(new Date());
		catalog.setVersion(0);
		catalog.setSequence(1);
		Catalog parentCatalog = catalogDao.selectByPrimaryKey(catalog.getParentCatalogId());
		parentCatalog.setIsParent(Byte.valueOf("1"));
		catalogDao.updateByPrimaryKey(parentCatalog);
		return catalogDao.insert(catalog);
	}

	@Override
	public Catalog selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return catalogDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Catalog> selectAll() {
		// TODO Auto-generated method stub
		return catalogDao.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Catalog record) {
		// TODO Auto-generated method stub
		return catalogDao.updateByPrimaryKey(record);
	}

}
