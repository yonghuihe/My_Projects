package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.OrderBill;
import com.company.pss.query.OrderBillQueryObject;
import com.company.pss.service.IOrderBillService;
import com.company.pss.service.ISupplierService;
import com.company.pss.util.RequiredPermission;

public class OrderBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IOrderBillService orderBillService;
	private ISupplierService supplierService;
	private OrderBillQueryObject queryObject = new OrderBillQueryObject();
	private OrderBill orderBill = new OrderBill();

	@RequiredPermission("OrderBill列表")
	public String execute() {
		// 查询所有的供应商，放到context区域
		this.addContext("suppliers", supplierService.list());
		this.addContext(PAGERESULT, this.orderBillService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑OrderBill")
	public String input() {
		// 查询所有的供应商，放到context区域
		this.addContext("suppliers", supplierService.list());
		if (orderBill.getId() != null) {
			orderBill = this.orderBillService.get(orderBill.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改OrderBill")
	public String save() {
		if (orderBill.getId() != null) {
			this.orderBillService.update(orderBill);
			this.addActionMessage("修改成功!");
		} else {
			this.orderBillService.save(orderBill);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}

	public void prepareSave() {
		if (orderBill.getId() != null) {
			orderBill = this.orderBillService.get(orderBill.getId());
			// 打破关系
			orderBill.setSupplier(null);
			orderBill.getOrderBillItem().clear();
		}
	}

	@RequiredPermission("删除OrderBill")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (orderBill.getId() != null) {
				// 级联删除的时候需要先将对应的关联关系查询出来-->删除关联的对象，再删除本对象
				orderBill = orderBillService.get(orderBill.getId());
				this.orderBillService.delete(orderBill);
				response.getWriter().write("删除成功！");
			}
		} catch (Exception e) {
			response.getWriter().write("删除失败，请联系管理员！" + e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("审核OrderBill")
	public String audit() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (orderBill.getId() != null) {
				this.orderBillService.audit(orderBill);
				response.getWriter().write("审核成功！");
			}
		} catch (Exception e) {
			response.getWriter().write("审核失败，请联系管理员！" + e.getMessage());
		}
		return NONE;
	}

	public String view() {
		// 查询所有的供应商，放到context区域
		this.addContext("suppliers", supplierService.list());
		if (orderBill.getId() != null) {
			orderBill = this.orderBillService.get(orderBill.getId());
		}
		return "view";
	}

	public OrderBillQueryObject getQueryObject() {
		return queryObject;
	}

	public OrderBill getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(OrderBill orderBill) {
		this.orderBill = orderBill;
	}

	public void setOrderBillService(IOrderBillService orderBillService) {
		this.orderBillService = orderBillService;
	}

	public void setSupplierService(ISupplierService supplierService) {
		this.supplierService = supplierService;
	}

}
