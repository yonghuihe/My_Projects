package com._520it.com.base.mapper;

import com._520it.com.base.domain.SkuProperty;
import java.util.List;

public interface SkuPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuProperty record);

    SkuProperty selectByPrimaryKey(Long id);

    List<SkuProperty> selectAll();

    int updateByPrimaryKey(SkuProperty record);
}