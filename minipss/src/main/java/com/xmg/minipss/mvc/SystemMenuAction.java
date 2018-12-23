package com.xmg.minipss.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.xmg.minipss.domain.SystemMenu;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.SystemMenuQueryObject;
import com.xmg.minipss.service.ISystemMenuService;
import com.xmg.minipss.util.RequiredPermission;

public class SystemMenuAction extends BaseAction {

	private ISystemMenuService systemMenuService;
	private SystemMenuQueryObject qo = new SystemMenuQueryObject();
	private SystemMenu systemMenu = new SystemMenu();

	@RequiredPermission("SystemMenu列表")
	public String execute() {
		PageResult pr = this.systemMenuService.query(qo);
		this.addContext(PAGERESULT, this.systemMenuService.query(qo));
		if (qo.getParentId() > 0) {
			systemMenu = this.systemMenuService.get(qo.getParentId());
		}
		return LIST;
	}

	@RequiredPermission("新增/编辑SystemMenu")
	public String input() {
		if (systemMenu.getParent() != null
				&& systemMenu.getParent().getId() != null) {
			systemMenu.setParent(this.systemMenuService.get(systemMenu
					.getParent().getId()));
		}
		if (systemMenu.getId() != null) {
			systemMenu = this.systemMenuService.get(systemMenu.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改SystemMenu")
	public String save() {
		if (systemMenu.getParent().getId() == null) {
			systemMenu.setParent(null);
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
		systemMenu.setParent(null);
	}

	@RequiredPermission("删除SystemMenu")
	public String delete() {
		if (systemMenu.getId() != null) {
			this.systemMenuService.delete(systemMenu);
			this.addActionMessage("删除成功");
		}
		return SUCCESS;
	}

	public String loadCurrentEmployeeMenus() {
		if (StringUtils.hasLength(qo.getParentSn())) {
			List<SystemMenu> menus = this.systemMenuService
					.loadCurrentEmployeeMenus(qo.getParentSn());
			List<Map<String, Object>> menuJsons = new ArrayList<Map<String, Object>>();
			for (SystemMenu menu : menus) {
				menuJsons.add(menu.toJson());
			}
			this.addContext("rootResult", menuJsons);
		}
		return JSON;
	}

	public SystemMenuQueryObject getQo() {
		return qo;
	}

	public void setQo(SystemMenuQueryObject qo) {
		this.qo = qo;
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

}
