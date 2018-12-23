package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.pss.domain.Role;
import com.company.pss.query.QueryObject;
import com.company.pss.query.RoleQueryObject;
import com.company.pss.service.IPermissionService;
import com.company.pss.service.IRoleService;
import com.company.pss.service.ISystemMenuService;
import com.company.pss.util.RequiredPermission;

/**
 * crud
 * 
 * @author yonghui
 *
 */
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private IRoleService roleService;
	private IPermissionService permissionService;
	private ISystemMenuService systemMenuService;
	private QueryObject queryObject = new RoleQueryObject();
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
		addContext("systemMenus", systemMenuService.list());
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
		role.getSystemMenus().clear();
	}
	
	/**
	 * 保存或修改
	 * @return
	 */
	@RequiredPermission("修改角色")
	public String save(){
		try {
			/**
			 * 判断id是否为null，如果不为null是修改
			 */
			if (role.getId() != null) {
				this.roleService.update(role);
				this.addActionMessage("修改成功");
			} else {
				this.roleService.save(role);
				this.addActionMessage("保存成功");
			}
		} catch (Exception e) {
			this.addActionError("修改错误");
			this.addActionError(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws IOException 
	 */
	@RequiredPermission("删除角色")
	public String delete() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if(role.getId() != null){
				this.roleService.delete(role);
				response.getWriter().write("删除成功！");
			}
		} catch (Exception e) {
			this.addActionError("修改错误");
			response.getWriter().write("删除失败，请联系管理员！"+e.getMessage());
		}
		return NONE;
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
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}
	public IPermissionService getPermissionService() {
		return permissionService;
	}
	public void setSystemMenuService(ISystemMenuService systemMenuService) {
		this.systemMenuService = systemMenuService;
	}
	public ISystemMenuService getSystemMenuService() {
		return systemMenuService;
	}
}

