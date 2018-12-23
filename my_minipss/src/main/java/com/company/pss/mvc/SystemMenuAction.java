package com.company.pss.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.company.pss.domain.SystemMenu;
import com.company.pss.query.QueryObject;
import com.company.pss.query.SystemMenuQueryObject;
import com.company.pss.service.ISystemMenuService;
import com.company.pss.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

public class SystemMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISystemMenuService systemMenuService;
	private SystemMenuQueryObject queryObject = new SystemMenuQueryObject();
	private SystemMenu systemMenu = new SystemMenu();
	private String parentSn;

	@RequiredPermission("SystemMenu列表")
	public String execute() {
		this.addContext(PAGERESULT, this.systemMenuService.query(queryObject));
		this.addContext("parentPath", this.systemMenuService.viewParentPath(queryObject.getParentId()));
		// 当前是在查询某一个级别，那么需要把当前查询的SystemMenu对象也传到前台
		return LIST;
	}

	@RequiredPermission("新增/编辑SystemMenu")
	public String input() {
		/*
		 * if (systemMenu.getParent() != null && systemMenu.getParent().getId()
		 * != null) {
		 * systemMenu.setParent(this.systemMenuService.get(systemMenu.getParent(
		 * ).getId())); }
		 */
		if(queryObject.getParentId() == null){
			ActionContext.getContext().put("parentName", "父菜单");
		} else{
			ActionContext.getContext().put("parentName", systemMenuService.get(queryObject.getParentId()).getName());
		}
		if (systemMenu.getId() != null) {
			systemMenu = systemMenuService.get(systemMenu.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改SystemMenu")
	public String save() {
		// 保存的时候判断父id是否为空
		if (systemMenu.getParent().getId() == null) {
			systemMenu.setParent(null);
		} else {
			systemMenu.setParent(systemMenuService.get(systemMenu.getParent().getId()));
		}
		if (systemMenu.getId() != null) {
			this.systemMenuService.update(systemMenu);
			this.addActionMessage("修改成功");
		} else {
			this.systemMenuService.save(systemMenu);
			this.addActionMessage("保存成功");
		}
		
		return SUCCESS;
	}

	public void prepareSave() {
		if (systemMenu.getId() != null) {
			systemMenu = this.systemMenuService.get(systemMenu.getId());
		}
	}

	@RequiredPermission("删除SystemMenu")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (systemMenu.getId() != null) {
				systemMenu = systemMenuService.get(systemMenu.getId());
				this.systemMenuService.delete(systemMenu);
				response.getWriter().write("删除成功！");
			}
		} catch (Exception e){
			response.getWriter().write("删除失败，请联系管理员！"+e.getMessage());
		}
		return NONE;
	}

	public String currentUserMenu() {
		if (StringUtils.hasLength(this.parentSn)) {
			List<SystemMenu> menus = this.systemMenuService.getCurrentUserMenuByParent(parentSn);
			addContext("rootMenu", menus);
		}
		return null;
	}
	
	public String queryMenuChildren() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<SystemMenu> menus = systemMenuService.queryMenuChildren(queryObject.getSn());
		for (SystemMenu systemMenu : menus) {
			list.add(systemMenu.toMap());
		}
		
		String str = JSON.toJSONString(list);
		
		response.getWriter().write(str);
		return NONE;
	}

	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(SystemMenuQueryObject queryObject) {
		this.queryObject = queryObject;
	}

	public SystemMenu getSystemMenu() {
		return systemMenu;
	}

	public void setSystemMenu(SystemMenu systemMenu) {
		this.systemMenu = systemMenu;
	}

	public void setSystemMenuService(ISystemMenuService systemMenuService) {
		this.systemMenuService = systemMenuService;
	}

	public ISystemMenuService getSystemMenuService() {
		return systemMenuService;
	}

	public void setParentSn(String parentSn) {
		this.parentSn = parentSn;
	}

	public String getParentSn() {
		return parentSn;
	}

}
