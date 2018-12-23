package com.xmg.api.service;

import java.util.List;

import com.xmg.api.domain.Catalog;


public interface ICatalogService {
	int deleteByPrimaryKey(Long id);

    int insert(Catalog record);

    Catalog selectByPrimaryKey(Long id);

    List<Catalog> selectAll();

    int updateByPrimaryKey(Catalog record);
	List<Catalog> getChildenCatalogById(Long parentId);


}
