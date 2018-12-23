package com.company.pss.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.company.pss.dao.IProductStockDao;
import com.company.pss.domain.ProductStock;

public class ProductStockDaoImpl extends GenericDaoImpl<ProductStock> implements
		IProductStockDao {

	public ProductStock queryByDepotAndProduct(Long depotId, Long productId) {
		//获取session
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ps from ProductStock ps where ps.depot.id = ? and ps.product.id = ?");
		query.setParameter(0, depotId);
		query.setParameter(1, productId);
		return (ProductStock) query.uniqueResult();
	}
}
