package com.company.pss.dao;

import java.util.List;

import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IGenericDao<T> {
	void save(T o);

	void update(T o);

	void delete(T o);

	T get(Long id);

	List<T> list();

	PageResult query(QueryObject qo);

}
