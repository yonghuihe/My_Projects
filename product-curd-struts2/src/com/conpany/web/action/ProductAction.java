package com.conpany.web.action;

import java.util.List;

import com.conpany.dao.IProductDao;
import com.conpany.dao.impl.ProductDaoImpl;
import com.conpany.domain.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: yonghui
 * @Description:
 * @Date: Created in 20:56 2018/4/14
 */
public class ProductAction extends ActionSupport {

	private Product product;

	private IProductDao productDao;

	public ProductAction() {
		productDao = new ProductDaoImpl();
	}

	public String list() {
		ActionContext.getContext().put("products", productDao.list());
		return "list";
	}

	public String edit() {
		if (product != null && product.getId() != null) {
			product = productDao.get(product.getId());
		}
		return "edit";
	}

	public String saveOrUpdate() {
		if (product != null && product.getId() != null) {
			productDao.update(product);
		} else {
			productDao.save(product);
		}
		return SUCCESS;
	}

	public String delete() {
		if (product != null && product.getId() != null) {
			productDao.delete(product.getId());
		} 
		return SUCCESS;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
