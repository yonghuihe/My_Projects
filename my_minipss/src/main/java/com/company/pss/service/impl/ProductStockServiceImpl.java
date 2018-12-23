package com.company.pss.service.impl;

import java.math.RoundingMode;
import java.util.List;

import com.company.pss.dao.IProductStockDao;
import com.company.pss.domain.ProductStock;
import com.company.pss.domain.StockIncomeBill;
import com.company.pss.domain.StockIncomeBillItem;
import com.company.pss.domain.StockOutcomeBill;
import com.company.pss.domain.StockOutcomeBillItem;
import com.company.pss.service.IProductStockService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class ProductStockServiceImpl implements IProductStockService {

	private IProductStockDao productStockDao;

	public void setProductStockDao(IProductStockDao productStockDao) {
		this.productStockDao = productStockDao;
	}

	@Override
	public void save(ProductStock o) {
		this.productStockDao.save(o);
	}

	@Override
	public void update(ProductStock o) {
		this.productStockDao.update(o);
	}

	@Override
	public void delete(ProductStock o) {
		this.productStockDao.delete(o);
	}

	@Override
	public ProductStock get(Long id) {
		return this.productStockDao.get(id);
	}

	@Override
	public List<ProductStock> list() {
		return this.productStockDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.productStockDao.query(qo);
	}
	
	public void auditStockIncomeBill(StockIncomeBill bill) {
		ProductStock productStock;
		//遍历明细，维护库存对象
		for(StockIncomeBillItem item : bill.getStockIncomeBillItem()){
			//根据仓库和商品获取库存对象，如果没有创建库存对象，维护关系
			productStock = productStockDao.queryByDepotAndProduct(bill.getDepot().getId(),item.getProduct().getId());
			if(productStock == null){
				productStock = new ProductStock();
				productStock.setDepot(bill.getDepot());
				productStock.setProduct(item.getProduct());
				productStock.setStockNumber(item.getNumber());
				productStock.setStockPrice(item.getCostPrice());
				productStock.setStockAmount(item.getAmount());
				//保存库存对象
				this.productStockDao.save(productStock);
			} else {
				//库存数量 = 原库存数量 + 入库数量
				productStock.setStockNumber(productStock.getStockNumber().add(item.getNumber()));
				//库存总价值 = 原库存价格 + 入库金额
				productStock.setStockAmount(productStock.getStockAmount().add(item.getAmount()));
				//库存价格 = 库存总价值 /库存数量
				productStock.setStockPrice(productStock.getStockAmount().divide(productStock.getStockNumber(),2, RoundingMode.HALF_UP));
				//更新库存对象
				this.productStockDao.update(productStock);
			}
		}
	}

	public ProductStock auditStockOutcomeBill(StockOutcomeBill bill, StockOutcomeBillItem item) {
		ProductStock productStock = productStockDao.queryByDepotAndProduct(bill.getDepot().getId(),
				item.getProduct().getId());
		if (productStock == null) {
			throw new RuntimeException(bill.getDepot().getName() + "没有" + item.getProduct().getName() + "的库存");
		}
		// 检查出库的数量 是否大于 库存数量
		if (item.getNumber().compareTo(productStock.getStockNumber()) > 0) {
			throw new RuntimeException(bill.getDepot().getName() + "中" + item.getProduct().getName()
					+ "的库存不足，剩余：" + productStock.getStockNumber() + "个");
		} else {
			// 库存数量 = 原库存数量 - 出库数量
			productStock.setStockNumber(productStock.getStockNumber().subtract(item.getNumber()));
			// 库存总金额 = 库存价格 * 库存总数量【库存价格是不变的】
			productStock.setStockAmount(productStock.getStockPrice().multiply(productStock.getStockNumber()));
			// 保存库存对象
			this.productStockDao.save(productStock);
		}
		return productStock;
	}
}
