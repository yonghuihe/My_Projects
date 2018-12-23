package com.company.ssh.dao;

import java.util.List;

import com.company.ssh.domain.BaseDomain;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;

public interface IGenericDao<T extends BaseDomain> {
	void save(T o);

	void update(T o);

	void delete(T o);

	T get(Long id);

	List<T> list();

	PageResult query(QueryObject qo);

}
