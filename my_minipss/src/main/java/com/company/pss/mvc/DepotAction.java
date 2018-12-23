package com.company.pss.mvc;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.company.pss.domain.Depot;
import com.company.pss.service.IDepotService;
import com.company.pss.query.QueryObject;
import com.company.pss.util.RequiredPermission;

public class DepotAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IDepotService depotService;
	private QueryObject queryObject = new QueryObject();
	private Depot depot = new Depot();

	@RequiredPermission("Depot列表")
	public String execute() {
		this.addContext(PAGERESULT, this.depotService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑Depot")
	public String input() {
		if (depot.getId() != null) {
			depot = this.depotService.get(depot.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改Depot")
	public String save() {
		if (depot.getId() != null) {
			this.depotService.update(depot);
			this.addActionMessage("修改成功!");
		} else {
			this.depotService.save(depot);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(depot.getId()!=null){
			depot = this.depotService.get(depot.getId());
		}
	}

	@RequiredPermission("删除Depot")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (depot.getId() != null) {
			this.depotService.delete(depot);
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

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public void setDepotService(IDepotService depotService) {
		this.depotService = depotService;
	}

}
