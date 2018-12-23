package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.Supplier;
import com.company.pss.service.ISupplierService;
import com.company.pss.query.QueryObject;
import com.company.pss.util.RequiredPermission;

public class SupplierAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ISupplierService supplierService;
	private QueryObject queryObject = new QueryObject();
	private Supplier supplier = new Supplier();

	@RequiredPermission("Supplier列表")
	public String execute() {
		this.addContext(PAGERESULT, this.supplierService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑Supplier")
	public String input() {
		if (supplier.getId() != null) {
			supplier = this.supplierService.get(supplier.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改Supplier")
	public String save() {
		if (supplier.getId() != null) {
			this.supplierService.update(supplier);
			this.addActionMessage("修改成功!");
		} else {
			this.supplierService.save(supplier);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}

	public void prepareSave() {
		if (supplier.getId() != null) {
			supplier = this.supplierService.get(supplier.getId());
		}
	}

	@RequiredPermission("删除Supplier")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (supplier.getId() != null) {
				this.supplierService.delete(supplier);
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setSupplierService(ISupplierService supplierService) {
		this.supplierService = supplierService;
	}

}
