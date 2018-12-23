package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Supplier;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface ISupplierService {
	void save(Supplier o);

	void update(Supplier o);

	void delete(Supplier o);

	Supplier get(Long id);

	List<Supplier> list();

	PageResult query(QueryObject qo);
}
