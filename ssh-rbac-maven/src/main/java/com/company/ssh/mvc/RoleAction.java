package com.company.ssh.mvc;

import org.springframework.stereotype.Controller;

import com.company.ssh.domain.Role;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IPermissionService;
import com.company.ssh.service.IRoleService;
import com.company.ssh.util.RequiredPermission;

/**
 * crud
 * 
 * @author yonghui
 *
 */
@Controller
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private IRoleService roleService;
	private IPermissionService permissionService;
	private QueryObject queryObject = new QueryObject();
	private Role role = new Role();
	
	@RequiredPermission("角色列表")
	public String execute() {
		addContext(PAGERESULT, roleService.query(queryObject));
		return LIST;
	}

	/**
	 * 导入到添加页面
	 */
	@RequiredPermission("新增/修改角色")
	public String input() {
		addContext("permissions", permissionService.list());
		/**
		 * 判断id是否为null，如果不为null是修改，需要查询出role对象
		 */
		if (role.getId() != null) {
			role = this.roleService.get(role.getId());
		}
		return INPUT;
	}
	
	
	public void prepareSave(){
		if(role.getId() != null){
			role = roleService.get(role.getId());
		}
		role.getPermissions().clear();
	}
	
	/**
	 * 保存或修改
	 * @return
	 */
	@RequiredPermission("修改角色")
	public String save(){
		/**
		 * 判断id是否为null，如果不为null是修改
		 */
		if (role.getId() != null) {
			this.roleService.update(role);
		} else {
			this.roleService.save(role);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequiredPermission("删除角色")
	public String delete(){
		if(role.getId() != null){
			this.roleService.delete(role);
		}
		return SUCCESS;
	}
	
	/**
	 * 提供get方法，可以把role放到值栈的root区域
	 * @return
	 */
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
}

