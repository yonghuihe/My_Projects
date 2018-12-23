package com.company.pss.mvc;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.company.pss.domain.Client;
import com.company.pss.service.IClientService;
import com.company.pss.query.QueryObject;
import com.company.pss.util.RequiredPermission;

public class ClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IClientService clientService;
	private QueryObject queryObject = new QueryObject();
	private Client client = new Client();

	@RequiredPermission("Client列表")
	public String execute() {
		this.addContext(PAGERESULT, this.clientService.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑Client")
	public String input() {
		if (client.getId() != null) {
			client = this.clientService.get(client.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改Client")
	public String save() {
		if (client.getId() != null) {
			this.clientService.update(client);
			this.addActionMessage("修改成功!");
		} else {
			this.clientService.save(client);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(client.getId()!=null){
			client = this.clientService.get(client.getId());
		}
	}

	@RequiredPermission("删除Client")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (client.getId() != null) {
				this.clientService.delete(client);
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

}
