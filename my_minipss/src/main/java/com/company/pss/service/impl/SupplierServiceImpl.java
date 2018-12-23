package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.ISupplierDao;
import com.company.pss.domain.Supplier;
import com.company.pss.service.ISupplierService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class SupplierServiceImpl implements ISupplierService {

	private ISupplierDao supplierDao;

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	@Override
	public void save(Supplier o) {
		this.supplierDao.save(o);
	}

	@Override
	public void update(Supplier o) {
		this.supplierDao.update(o);
	}

	@Override
	public void delete(Supplier o) {
		this.supplierDao.delete(o);
	}

	@Override
	public Supplier get(Long id) {
		return this.supplierDao.get(id);
	}

	@Override
	public List<Supplier> list() {
		return this.supplierDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.supplierDao.query(qo);
	}

}
