package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.StockIncomeBill;
import com.company.pss.query.QueryObject;
import com.company.pss.query.StockIncomeBillQueryObject;
import com.company.pss.service.IDepotService;
import com.company.pss.service.IStockIncomeBillService;
import com.company.pss.util.RequiredPermission;

public class StockIncomeBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IStockIncomeBillService stockIncomeBillService;
	private IDepotService depotService;
	private StockIncomeBillQueryObject queryObject = new StockIncomeBillQueryObject();
	private StockIncomeBill stockIncomeBill = new StockIncomeBill();

	@RequiredPermission("StockIncomeBill列表")
	public String execute() {
		this.addContext("depots", this.depotService.list());
		this.addContext(PAGERESULT, this.stockIncomeBillService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑StockIncomeBill")
	public String input() {
		this.addContext("depots", this.depotService.list());
		if (stockIncomeBill.getId() != null) {
			stockIncomeBill = this.stockIncomeBillService.get(stockIncomeBill.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改StockIncomeBill")
	public String save() {
		if (stockIncomeBill.getId() != null) {
			this.stockIncomeBillService.update(stockIncomeBill);
			this.addActionMessage("修改成功!");
		} else {
			this.stockIncomeBillService.save(stockIncomeBill);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(stockIncomeBill.getId()!=null){
			stockIncomeBill = this.stockIncomeBillService.get(stockIncomeBill.getId());
		}
		//打破关系
		stockIncomeBill.setDepot(null);
		//集合需要清除，否则明细会删不掉
		stockIncomeBill.getStockIncomeBillItem().clear();
	}

	@RequiredPermission("删除StockIncomeBill")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (stockIncomeBill.getId() != null) {
				//级联删除，需要将关联的查询出来
				stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
				this.stockIncomeBillService.delete(stockIncomeBill);
				response.getWriter().write("删除成功！");
			}
		} catch (Exception e){
			response.getWriter().write("删除失败，请联系管理员！"+e.getMessage());
		}
		return NONE;
	}
	
	@RequiredPermission("审核StockIncomeBill")
	public String audit() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (stockIncomeBill.getId() != null) {
				this.stockIncomeBillService.audit(stockIncomeBill);
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
		if (stockIncomeBill.getId() != null) {
			stockIncomeBill = this.stockIncomeBillService.get(stockIncomeBill.getId());
		}
		return "view";
	}

	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(StockIncomeBillQueryObject queryObject) {
		this.queryObject = queryObject;
	}
	
	public StockIncomeBill getStockIncomeBill() {
		return stockIncomeBill;
	}

	public void setStockIncomeBill(StockIncomeBill stockIncomeBill) {
		this.stockIncomeBill = stockIncomeBill;
	}

	public void setStockIncomeBillService(IStockIncomeBillService stockIncomeBillService) {
		this.stockIncomeBillService = stockIncomeBillService;
	}
	public void setDepotService(IDepotService depotService) {
		this.depotService = depotService;
	}

}
