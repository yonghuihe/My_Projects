package com.xmg.minipss.mvc;

import com.xmg.minipss.domain.Department;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.service.IDepartmentService;
import com.xmg.minipss.util.RequiredPermission;

public class DepartmentAction extends BaseAction {

	private IDepartmentService departmentService;
	private QueryObject qo = new QueryObject();
	private Department department = new Department();

	@RequiredPermission("部门列表")
	public String execute() {
		PageResult pr = this.departmentService.query(qo);
		this.addContext(PAGERESULT, this.departmentService.query(qo));
		return LIST;
	}

	@RequiredPermission("新增/编辑部门")
	public String input() {
		if (department.getId() != null) {
			department = this.departmentService.get(department.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改部门")
	public String save() {
		if (department.getId() != null) {
			this.departmentService.update(department);
			this.addActionMessage("修改成功");
		} else {
			this.departmentService.save(department);
			this.addActionMessage("保存成功");
		}
		return SUCCESS;
	}

	@RequiredPermission("删除部门")
	public String delete() {
		if (department.getId() != null) {
			this.departmentService.delete(department);
			this.addActionMessage("删除成功");
		}
		return SUCCESS;
	}

	public QueryObject getQo() {
		return qo;
	}

	public void setQo(QueryObject qo) {
		this.qo = qo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}
