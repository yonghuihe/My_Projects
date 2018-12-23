package com.company.pss.dao;

import com.company.pss.domain.ProductStock;

public interface IProductStockDao extends IGenericDao<ProductStock> {

	/**
	 * 
	 * @param depotId：仓库id
	 * @param productId：商品id
	 * @return 库存对象
	 */
	ProductStock queryByDepotAndProduct(Long depotId, Long productId);

}
