package com.company.crm.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Role;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;
import com.company.crm.service.IRoleService;
import com.company.crm.util.AjaxResult;
import com.company.crm.util.PermissionName;

@Controller
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping("/role")
	@RequiresPermissions("role:execute")
	@PermissionName("角色主页")
	public String execute() {
		return "/role/role";
	}

	@RequestMapping("/role_list")
	@ResponseBody
	@RequiresPermissions("role:list")
	@PermissionName("角色列表")
	public PageResult list(QueryObject qo) {
		PageResult result = roleService.queryPageResult(qo);
		return result;
	}

	@RequestMapping("/role_save")
	@ResponseBody
	@RequiresPermissions("role:save")
	@PermissionName("新增角色")
	public AjaxResult save(Role role) {
		AjaxResult result;
		try {
			roleService.insert(role);
			result = new AjaxResult("保存角色成功", true);
		} catch (Exception e) {
			result = new AjaxResult("保存角色失败", false);
		}
		return result;
	}

	@RequestMapping("/role_update")
	@ResponseBody
	@RequiresPermissions("role:update")
	@PermissionName("更新角色")
	public AjaxResult update(Role role) {
		AjaxResult result;
		try {
			roleService.updateByPrimaryKey(role);
			result = new AjaxResult("修改角色成功", true);
		} catch (Exception e) {
			result = new AjaxResult("修改角色失败", false);
		}
		return result;
	}

	@RequestMapping("/role_remove")
	@ResponseBody
	@RequiresPermissions("role:remove")
	@PermissionName("删除角色")
	public AjaxResult remove(Long id) {
		AjaxResult result;
		try {
			roleService.deleteByPrimaryKey(id);
			result = new AjaxResult("删除角色成功", true);
		} catch (Exception e) {
			result = new AjaxResult("删除角色失败", false);
		}
		return result;
	}

	/**
	 * 查询所有角色
	 * @return
	 */
	@RequestMapping("/role_listAll")
	@ResponseBody
	public List<Role> listAll() {
		List<Role> list = roleService.selectAll();
		return list;
	}
}
