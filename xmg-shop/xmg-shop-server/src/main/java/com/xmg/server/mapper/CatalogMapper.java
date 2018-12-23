package com.xmg.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmg.api.domain.Catalog;

public interface CatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Catalog record);

    Catalog selectByPrimaryKey(Long id);

    List<Catalog> selectAll();

    int updateByPrimaryKey(Catalog record);

	List<Catalog> getChildenCatalogById(@Param("parentId")Long parentId);

}