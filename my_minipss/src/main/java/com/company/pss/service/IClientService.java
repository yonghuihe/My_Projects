package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Client;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IClientService {
	void save(Client o);

	void update(Client o);

	void delete(Client o);

	Client get(Long id);

	List<Client> list();

	PageResult query(QueryObject qo);
}
