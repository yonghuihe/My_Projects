package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.StockOutcomeBill;
import com.company.pss.query.QueryObject;
import com.company.pss.query.StockOutcomeBillQueryObject;
import com.company.pss.service.IClientService;
import com.company.pss.service.IDepotService;
import com.company.pss.service.IStockOutcomeBillService;
import com.company.pss.util.RequiredPermission;

public class StockOutcomeBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IStockOutcomeBillService stockOutcomeBillService;
	private IClientService clientService;
	private IDepotService depotService;
	private StockOutcomeBillQueryObject queryObject = new StockOutcomeBillQueryObject();
	private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();

	@RequiredPermission("StockOutcomeBill列表")
	public String execute() {
		this.addContext("clients", this.clientService.list());
		this.addContext("depots", this.depotService.list());
		this.addContext(PAGERESULT, this.stockOutcomeBillService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑StockOutcomeBill")
	public String input() {
		this.addContext("clients", this.clientService.list());
		this.addContext("depots", this.depotService.list());
		if (stockOutcomeBill.getId() != null) {
			stockOutcomeBill = this.stockOutcomeBillService.get(stockOutcomeBill.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改StockOutcomeBill")
	public String save() {
		if (stockOutcomeBill.getId() != null) {
			this.stockOutcomeBillService.update(stockOutcomeBill);
			this.addActionMessage("修改成功!");
		} else {
			this.stockOutcomeBillService.save(stockOutcomeBill);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}

	public void prepareSave() {
		if (stockOutcomeBill.getId() != null) {
			stockOutcomeBill = this.stockOutcomeBillService.get(stockOutcomeBill.getId());
		}
		//打破关联关系
		stockOutcomeBill.setClient(null);
		stockOutcomeBill.setDepot(null);
		//如果关联了集合，将集合清空
		stockOutcomeBill.getStockOutcomeBillItem().clear();
	}

	@RequiredPermission("删除StockOutcomeBill")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (stockOutcomeBill.getId() != null) {
				//级联删除，需要先将对象查询出来
				stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
				this.stockOutcomeBillService.delete(stockOutcomeBill);
				response.getWriter().write("删除成功！");
			}
		} catch (Exception e) {
			response.getWriter().write("删除失败，请联系管理员！" + e.getMessage());
		}
		return NONE;
	}
	
	@RequiredPermission("审核StockOutcomeBill")
	public String audit() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (stockOutcomeBill.getId() != null) {
				this.stockOutcomeBillService.audit(stockOutcomeBill);
				response.getWriter().write("审核成功！");
			}
		} catch (Exception e) {
			response.getWriter().write("审核失败，请联系管理员！" + e.getMessage());
		}
		return NONE;
	}
	
	public String view() {
		// 查询所有仓库，放到context区域
		this.addContext("depots", this.depotService.list());
		this.addContext("clients", this.clientService.list());
		if (stockOutcomeBill.getId() != null) {
			stockOutcomeBill = this.stockOutcomeBillService.get(stockOutcomeBill.getId());
		}
		return "view";
	}

	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(StockOutcomeBillQueryObject queryObject) {
		this.queryObject = queryObject;
	}

	public StockOutcomeBill getStockOutcomeBill() {
		return stockOutcomeBill;
	}

	public void setStockOutcomeBill(StockOutcomeBill stockOutcomeBill) {
		this.stockOutcomeBill = stockOutcomeBill;
	}

	public void setStockOutcomeBillService(IStockOutcomeBillService stockOutcomeBillService) {
		this.stockOutcomeBillService = stockOutcomeBillService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	public void setDepotService(IDepotService depotService) {
		this.depotService = depotService;
	}

}
