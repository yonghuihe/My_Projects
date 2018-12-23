package com.company.pss.mvc;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.company.pss.domain.StockIncomeBillItem;
import com.company.pss.service.IStockIncomeBillItemService;
import com.company.pss.query.QueryObject;
import com.company.pss.util.RequiredPermission;

public class StockIncomeBillItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IStockIncomeBillItemService stockIncomeBillItemService;
	private QueryObject queryObject = new QueryObject();
	private StockIncomeBillItem stockIncomeBillItem = new StockIncomeBillItem();

	@RequiredPermission("StockIncomeBillItem列表")
	public String execute() {
		this.addContext(PAGERESULT, this.stockIncomeBillItemService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑StockIncomeBillItem")
	public String input() {
		if (stockIncomeBillItem.getId() != null) {
			stockIncomeBillItem = this.stockIncomeBillItemService.get(stockIncomeBillItem.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改StockIncomeBillItem")
	public String save() {
		if (stockIncomeBillItem.getId() != null) {
			this.stockIncomeBillItemService.update(stockIncomeBillItem);
			this.addActionMessage("修改成功!");
		} else {
			this.stockIncomeBillItemService.save(stockIncomeBillItem);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(stockIncomeBillItem.getId()!=null){
			stockIncomeBillItem = this.stockIncomeBillItemService.get(stockIncomeBillItem.getId());
		}
	}

	@RequiredPermission("删除StockIncomeBillItem")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (stockIncomeBillItem.getId() != null) {
			this.stockIncomeBillItemService.delete(stockIncomeBillItem);
			response.getWriter().write("删除成功！");
		}
		} catch (Exception e){
			response.getWriter().write("删除失败，请联系管理员！"+e.getMessage());
		}
		return NONE;
	}

	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(QueryObject queryObject) {
		this.queryObject = queryObject;
	}

	public StockIncomeBillItem getStockIncomeBillItem() {
		return stockIncomeBillItem;
	}

	public void setStockIncomeBillItem(StockIncomeBillItem stockIncomeBillItem) {
		this.stockIncomeBillItem = stockIncomeBillItem;
	}

	public void setStockIncomeBillItemService(IStockIncomeBillItemService stockIncomeBillItemService) {
		this.stockIncomeBillItemService = stockIncomeBillItemService;
	}

}
