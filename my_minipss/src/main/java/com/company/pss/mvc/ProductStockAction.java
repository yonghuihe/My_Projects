package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.ProductStock;
import com.company.pss.query.ProductStockQueryObject;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IBrandService;
import com.company.pss.service.IDepotService;
import com.company.pss.service.IProductStockService;
import com.company.pss.util.RequiredPermission;

public class ProductStockAction extends BaseAction {

	private static final long serialVersionUID = -3665885276609140001L;

	private IProductStockService productStockService;
	private IDepotService depotService;
	private IBrandService brandService;
	private ProductStockQueryObject queryObject = new ProductStockQueryObject();
	private ProductStock productStock = new ProductStock();

	@RequiredPermission("ProductStock列表")
	public String execute() {
		this.addContext("depots", depotService.list());
		this.addContext("brands", brandService.list());
		this.addContext(PAGERESULT, this.productStockService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑ProductStock")
	public String input() {
		if (productStock.getId() != null) {
			productStock = this.productStockService.get(productStock.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改ProductStock")
	public String save() {
		if (productStock.getId() != null) {
			this.productStockService.update(productStock);
			this.addActionMessage("修改成功!");
		} else {
			this.productStockService.save(productStock);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}

	public void prepareSave() {
		if (productStock.getId() != null) {
			productStock = this.productStockService.get(productStock.getId());
		}
	}

	@RequiredPermission("删除ProductStock")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (productStock.getId() != null) {
				this.productStockService.delete(productStock);
				response.getWriter().write("删除成功！");
			}
		} catch (Exception e) {
			response.getWriter().write("删除失败，请联系管理员！" + e.getMessage());
		}
		return NONE;
	}

	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(ProductStockQueryObject queryObject) {
		this.queryObject = queryObject;
	}

	public ProductStock getProductStock() {
		return productStock;
	}

	public void setProductStock(ProductStock productStock) {
		this.productStock = productStock;
	}

	public void setProductStockService(IProductStockService productStockService) {
		this.productStockService = productStockService;
	}

	public void setDepotService(IDepotService depotService) {
		this.depotService = depotService;
	}

	public void setBrandService(IBrandService brandService) {
		this.brandService = brandService;
	}

}
