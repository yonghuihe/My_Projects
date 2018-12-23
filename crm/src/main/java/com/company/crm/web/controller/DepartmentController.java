package com.company.crm.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Department;
import com.company.crm.service.IDepartmentService;
import com.company.crm.util.AjaxResult;
import com.company.crm.util.PermissionName;

@Controller
public class DepartmentController extends BaseController {
	
	@Autowired
	private IDepartmentService departmentService;
	
	@RequestMapping("/department")
	@RequiresPermissions("department:execute")
	@PermissionName("部门主页")
	public String execute(){
		return "/department/department";
	}

	@RequestMapping("/department_list")
	@ResponseBody
	@RequiresPermissions("department:list")
	@PermissionName("部门列表")
	public List<Department> list(){
		List<Department> list = departmentService.selectAll();
		return list;
	}
	
	@RequestMapping("/department_save")
	@ResponseBody
	@RequiresPermissions("department:save")
	@PermissionName("新增部门")
	public AjaxResult save(Department department) {
		AjaxResult result = null;
		try {
			departmentService.insert(department);
			result = new AjaxResult("保存成功", true);
		} catch (Exception e) {
			result = new AjaxResult("保存失败", true);

		}
		return result;
	}

	@RequestMapping("/department_update")
	@ResponseBody
	@RequiresPermissions("department:update")
	@PermissionName("更新员工")
	public AjaxResult update(Department department) {
		AjaxResult result = null;
		try {
			departmentService.updateByPrimaryKey(department);
			result = new AjaxResult("修改成功", true);
		} catch (Exception e) {
			result = new AjaxResult("修改失败", true);

		}
		return result;
	}
	
	@RequestMapping("/department_remove")
	@ResponseBody
	@RequiresPermissions("department:remove")
	@PermissionName("删除部门")
	public AjaxResult remove(Long id) {
		AjaxResult result = null;
		try {
			departmentService.updateState(id);
			result = new AjaxResult("修改成功", true);
		} catch (Exception e) {
			result = new AjaxResult("修改失败", true);
		}
		return result;
	}
}
