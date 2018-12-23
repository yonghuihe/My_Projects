package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Depot;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IDepotService {
	void save(Depot o);

	void update(Depot o);

	void delete(Depot o);

	Depot get(Long id);

	List<Depot> list();

	PageResult query(QueryObject qo);
}
