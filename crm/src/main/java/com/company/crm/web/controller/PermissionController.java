package com.company.crm.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Permission;
import com.company.crm.page.PageResult;
import com.company.crm.query.PermissionQueryObject;
import com.company.crm.service.IPermissionService;
import com.company.crm.util.AjaxResult;
import com.company.crm.util.PermissionName;

@Controller
public class PermissionController extends BaseController {

	@Autowired
	private IPermissionService permissionService;

	@RequestMapping("/permission")
	@RequiresPermissions("permission:execute")
	@PermissionName("权限主页")
	public String execute() {
		return "/permission/permission";
	}

	@RequestMapping("/permission_list")
	@ResponseBody
	@RequiresPermissions("permission:list")
	@PermissionName("权限列表")
	public PageResult list(PermissionQueryObject qo) {
		PageResult result = permissionService.queryPageResult(qo);
		return result;
	}
	
	@RequestMapping("/permission_getPermissionsByRId")
	@ResponseBody
	public List<Permission> getPermissionsByRId(Long rId) {
		List<Permission> permissions = permissionService.getPermissionsByRId(rId);
		return permissions;
	}
	
	@RequestMapping("/permission_load")
	@ResponseBody
	@RequiresPermissions("permission:load")
	@PermissionName("重新加载权限")
	public AjaxResult load() {
		AjaxResult result = null;
		try {
			permissionService.load();
			result = new AjaxResult("权限加载成功！", true);
		} catch (Exception e) {
			result = new AjaxResult("权限加载失败，请重试或联系管理员！", true);
		}
		return result;
	}

}
