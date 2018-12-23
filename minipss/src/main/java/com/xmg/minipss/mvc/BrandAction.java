package com.xmg.minipss.mvc;

import com.xmg.minipss.domain.Brand;
import com.xmg.minipss.service.IBrandService;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.util.RequiredPermission;

public class BrandAction extends BaseAction {

	private IBrandService brandService;
	private QueryObject qo = new QueryObject();
	private Brand brand = new Brand();

	@RequiredPermission("品牌列表")
	public String execute() {
		PageResult pr = this.brandService.query(qo);
		this.addContext(PAGERESULT, this.brandService.query(qo));
		return LIST;
	}

	@RequiredPermission("新增/编辑品牌")
	public String input() {
		if (brand.getId() != null) {
			brand = this.brandService.get(brand.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改品牌")
	public String save() {
		if (brand.getId() != null) {
			this.brandService.update(brand);
			this.addActionMessage("修改成功");
		} else {
			this.brandService.save(brand);
			this.addActionMessage("保存成功");
		}
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(brand.getId()!=null){
			brand = this.brandService.get(brand.getId());
		}
	}

	@RequiredPermission("删除品牌")
	public String delete() {
		if (brand.getId() != null) {
			this.brandService.delete(brand);
			this.addActionMessage("删除成功");
		}
		return SUCCESS;
	}

	public QueryObject getQo() {
		return qo;
	}

	public void setQo(QueryObject qo) {
		this.qo = qo;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setBrandService(IBrandService brandService) {
		this.brandService = brandService;
	}

}
