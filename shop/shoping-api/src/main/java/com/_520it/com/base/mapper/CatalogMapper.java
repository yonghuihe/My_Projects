package com._520it.com.base.mapper;

import com._520it.com.base.domain.Catalog;
import java.util.List;

public interface CatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Catalog record);

    Catalog selectByPrimaryKey(Long id);

    List<Catalog> selectAll();

    int updateByPrimaryKey(Catalog record);

    List<Catalog> getChildenCatalogById(Long parentId);
}