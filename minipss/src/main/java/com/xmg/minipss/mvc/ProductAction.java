package com.xmg.minipss.mvc;

import java.io.File;

import com.xmg.minipss.domain.Product;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.ProductQueryObject;
import com.xmg.minipss.service.IBrandService;
import com.xmg.minipss.service.IProductService;
import com.xmg.minipss.util.FileUploadUtil;
import com.xmg.minipss.util.RequiredPermission;

public class ProductAction extends BaseAction {

	private IProductService productService;
	private IBrandService brandService;
	private ProductQueryObject qo = new ProductQueryObject();
	private Product product = new Product();

	private File pic;
	private String picFileName;

	@RequiredPermission("货品列表")
	public String execute() {
		PageResult pr = this.productService.query(qo);
		this.addContext("brands", this.brandService.list());
		this.addContext(PAGERESULT, this.productService.query(qo));
		return LIST;
	}

	@RequiredPermission("新增/编辑货品")
	public String input() {
		if (product.getId() != null) {
			product = this.productService.get(product.getId());
		}
		this.addContext("brands", this.brandService.list());
		return INPUT;
	}

	@RequiredPermission("修改货品")
	public String save() {
		if (product.getId() != null && pic != null) {
			FileUploadUtil.deleteFile(product.getPic());
		}
		if (pic != null) {
			String picPath;
			try {
				picPath = FileUploadUtil.uploadFile(pic, picFileName);
				product.setPic(picPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (product.getId() != null) {
			this.productService.update(product);
			this.addActionMessage("修改成功");
		} else {
			this.productService.save(product);
			this.addActionMessage("保存成功");
		}
		return SUCCESS;
	}

	public void prepareSave() {
		if (product.getId() != null) {
			product = this.productService.get(product.getId());
		}
		product.setBrand(null);
	}

	@RequiredPermission("删除货品")
	public String delete() {
		if (product.getId() != null) {
			FileUploadUtil.deleteFile(product.getPic());
			this.productService.delete(product);
			this.addActionMessage("删除成功");
		}
		return SUCCESS;
	}

	public ProductQueryObject getQo() {
		return qo;
	}

	public void setQo(ProductQueryObject qo) {
		this.qo = qo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public void setBrandService(IBrandService brandService) {
		this.brandService = brandService;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

}
