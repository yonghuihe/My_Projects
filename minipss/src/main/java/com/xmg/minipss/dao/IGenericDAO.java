package com.xmg.minipss.dao;

import java.util.List;

import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface IGenericDAO<T> {
	void save(T o);

	void update(T o);

	void delete(T o);

	T get(Long id);

	List<T> list();

	PageResult query(QueryObject qo);
}
