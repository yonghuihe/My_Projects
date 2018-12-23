package com.company.pss.mvc;

import org.springframework.stereotype.Controller;

import com.company.pss.domain.Permission;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IPermissionService;
import com.company.pss.util.RequiredPermission;

/**
 * crud
 * 
 * @author yonghui
 *
 */
@Controller
public class PermissionAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private IPermissionService permissionService;
	private QueryObject queryObject = new QueryObject();
	private Permission permission = new Permission();
	
	@RequiredPermission("权限列表")
	public String execute() {
		addContext(PAGERESULT, permissionService.query(queryObject));
		return LIST;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequiredPermission("删除权限")
	public String delete(){
		System.out.println(permission.getId());
		if(permission.getId() != null){
			this.permissionService.delete(permission);
		}
		return SUCCESS;
	}
	
	@RequiredPermission("重新加载权限")
	public String reload(){
		this.permissionService.reload();
		return NONE;
	}
	
	/**
	 * 提供get方法，可以把permission放到值栈的root区域
	 * @return
	 */
	public Permission getPermission() {
		return permission;
	}
	
	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
}

