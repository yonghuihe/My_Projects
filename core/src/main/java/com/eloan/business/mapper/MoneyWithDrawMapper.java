package com.eloan.business.mapper;

import com.eloan.business.domain.MoneyWithDraw;
import com.eloan.business.query.MoneyWithDrawQueryObject;

import java.util.List;

public interface MoneyWithDrawMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyWithDraw record);

    MoneyWithDraw selectByPrimaryKey(Long id);

    List<MoneyWithDraw> selectAll();

    int updateByPrimaryKey(MoneyWithDraw record);

	int queryForCount(MoneyWithDrawQueryObject qo);

	List<MoneyWithDraw> query(MoneyWithDrawQueryObject qo);
}