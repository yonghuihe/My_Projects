package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IDepotDao;
import com.company.pss.domain.Depot;
import com.company.pss.service.IDepotService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class DepotServiceImpl implements IDepotService {

	private IDepotDao depotDao;

	public void setDepotDao(IDepotDao depotDao) {
		this.depotDao = depotDao;
	}

	public void save(Depot o) {
		this.depotDao.save(o);
	}

	public void update(Depot o) {
		this.depotDao.update(o);
	}

	public void delete(Depot o) {
		this.depotDao.delete(o);
	}

	public Depot get(Long id) {
		return this.depotDao.get(id);
	}

	public List<Depot> list() {
		return this.depotDao.list();
	}

	public PageResult query(QueryObject qo) {
		return this.depotDao.query(qo);
	}

}
