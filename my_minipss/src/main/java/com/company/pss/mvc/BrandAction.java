package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.Brand;
import com.company.pss.service.IBrandService;
import com.company.pss.query.QueryObject;
import com.company.pss.util.RequiredPermission;

public class BrandAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IBrandService brandService;
	private QueryObject queryObject = new QueryObject();
	private Brand brand = new Brand();

	@RequiredPermission("Brand列表")
	public String execute() {
		this.addContext(PAGERESULT, this.brandService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑Brand")
	public String input() {
		if (brand.getId() != null) {
			brand = this.brandService.get(brand.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改Brand")
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

	public void prepareSave() {
		if (brand.getId() != null) {
			brand = this.brandService.get(brand.getId());
		}
	}

	@RequiredPermission("删除Brand")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (brand.getId() != null) {
				this.brandService.delete(brand);
			}
			response.getWriter().write("删除成功！");
		} catch (Exception e) {
			response.getWriter().write("删除失败，请联系管理员！"+e.getMessage());
		}
		
		return NONE;
	}

	public void setQueryObject(QueryObject queryObject) {
		this.queryObject = queryObject;
	}

	public QueryObject getQueryObject() {
		return queryObject;
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
