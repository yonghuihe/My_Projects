package com.xmg.minipss.mvc;

import com.xmg.minipss.domain.Permission;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.service.IPermissionService;
import com.xmg.minipss.util.RequiredPermission;

public class PermissionAction extends BaseAction {
	private IPermissionService permissionService;
	private QueryObject qo = new QueryObject();
	private Permission permission = new Permission();

	@RequiredPermission("权限列表")
	public String execute() {
		this.addContext(PAGERESULT, this.permissionService.query(qo));
		return LIST;
	}

	@RequiredPermission("删除权限")
	public String delete() {
		if (permission.getId() != null) {
			this.permissionService.delete(permission);
			this.addActionMessage("删除成功!");
		}
		return SUCCESS;
	}

	@RequiredPermission("重新加载系统权限")
	public String reload() {
		this.permissionService.reload();
		return NONE;
	}

	public QueryObject getQo() {
		return qo;
	}

	public void setQo(QueryObject qo) {
		this.qo = qo;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

}
