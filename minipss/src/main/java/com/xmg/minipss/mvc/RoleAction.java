package com.xmg.minipss.mvc;

import com.xmg.minipss.domain.Role;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.service.IPermissionService;
import com.xmg.minipss.service.IRoleService;
import com.xmg.minipss.service.ISystemMenuService;
import com.xmg.minipss.util.RequiredPermission;

public class RoleAction extends BaseAction {

	private IRoleService roleService;
	private IPermissionService permissionService;
	private ISystemMenuService systemMenuService;
	private QueryObject qo = new QueryObject();
	private Role role = new Role();

	@RequiredPermission("角色列表")
	public String execute() {
		this.addContext(PAGERESULT, this.roleService.query(qo));
		return LIST;
	}

	@RequiredPermission("新增/修改角色")
	public String input() {
		this.addContext("permissions", this.permissionService.list());
		this.addContext("menus", this.systemMenuService.listChildren());
		if (role.getId() != null) {
			role = this.roleService.get(role.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改角色")
	public String save() {
		if (role.getId() != null) {
			this.roleService.update(role);
			this.addActionMessage("修改成功");
		} else {
			this.roleService.save(role);
			this.addActionMessage("保存成功");
		}
		return SUCCESS;
	}

	public void prepareSave() {
		if (role.getId() != null) {
			role = this.roleService.get(role.getId());
		}
		role.getPermissions().clear();
		role.getMenus().clear();
	}

	@RequiredPermission("删除角色")
	public String delete() {
		if (role.getId() != null) {
			this.roleService.delete(role);
			this.addActionMessage("删除成功");
		}
		return SUCCESS;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public QueryObject getQo() {
		return qo;
	}

	public void setQo(QueryObject qo) {
		this.qo = qo;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void setSystemMenuService(ISystemMenuService systemMenuService) {
		this.systemMenuService = systemMenuService;
	}

}
