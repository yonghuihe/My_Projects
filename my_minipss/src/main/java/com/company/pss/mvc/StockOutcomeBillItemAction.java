package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.StockOutcomeBillItem;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IStockOutcomeBillItemService;
import com.company.pss.util.RequiredPermission;

public class StockOutcomeBillItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IStockOutcomeBillItemService stockOutcomeBillItemService;
	private QueryObject queryObject = new QueryObject();
	private StockOutcomeBillItem stockOutcomeBillItem = new StockOutcomeBillItem();

	@RequiredPermission("StockOutcomeBillItem列表")
	public String execute() {
		this.addContext(PAGERESULT, this.stockOutcomeBillItemService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑StockOutcomeBillItem")
	public String input() {
		if (stockOutcomeBillItem.getId() != null) {
			stockOutcomeBillItem = this.stockOutcomeBillItemService.get(stockOutcomeBillItem.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改StockOutcomeBillItem")
	public String save() {
		if (stockOutcomeBillItem.getId() != null) {
			this.stockOutcomeBillItemService.update(stockOutcomeBillItem);
			this.addActionMessage("修改成功!");
		} else {
			this.stockOutcomeBillItemService.save(stockOutcomeBillItem);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}

	public void prepareSave() {
		if (stockOutcomeBillItem.getId() != null) {
			stockOutcomeBillItem = this.stockOutcomeBillItemService.get(stockOutcomeBillItem.getId());
		}
	}

	@RequiredPermission("删除StockOutcomeBillItem")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (stockOutcomeBillItem.getId() != null) {
				this.stockOutcomeBillItemService.delete(stockOutcomeBillItem);
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

	public void setQueryObject(QueryObject queryObject) {
		this.queryObject = queryObject;
	}

	public StockOutcomeBillItem getStockOutcomeBillItem() {
		return stockOutcomeBillItem;
	}

	public void setStockOutcomeBillItem(StockOutcomeBillItem stockOutcomeBillItem) {
		this.stockOutcomeBillItem = stockOutcomeBillItem;
	}

	public void setStockOutcomeBillItemService(IStockOutcomeBillItemService stockOutcomeBillItemService) {
		this.stockOutcomeBillItemService = stockOutcomeBillItemService;
	}

}
