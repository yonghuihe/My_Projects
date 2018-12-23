package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IClientDao;
import com.company.pss.domain.Client;
import com.company.pss.service.IClientService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class ClientServiceImpl implements IClientService {

	private IClientDao clientDao;

	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public void save(Client o) {
		this.clientDao.save(o);
	}

	public void update(Client o) {
		this.clientDao.update(o);
	}

	public void delete(Client o) {
		this.clientDao.delete(o);
	}

	public Client get(Long id) {
		return this.clientDao.get(id);
	}

	public List<Client> list() {
		return this.clientDao.list();
	}

	public PageResult query(QueryObject qo) {
		return this.clientDao.query(qo);
	}

}
