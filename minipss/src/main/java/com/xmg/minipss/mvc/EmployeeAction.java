package com.xmg.minipss.mvc;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xmg.minipss.domain.Employee;
import com.xmg.minipss.query.EmployeeQueryObject;
import com.xmg.minipss.service.IDepartmentService;
import com.xmg.minipss.service.IEmployeeService;
import com.xmg.minipss.service.IRoleService;
import com.xmg.minipss.util.RequiredPermission;

public class EmployeeAction extends BaseAction {

	private IEmployeeService employeeService;
	private IDepartmentService departmentService;
	private IRoleService roleService;
	private EmployeeQueryObject qo = new EmployeeQueryObject();
	private Employee employee = new Employee();
	private List<Long> ids = new ArrayList<Long>();

	@RequiredPermission("员工列表")
	@InputConfig(methodName = "input")
	public String execute() {
		this.addContext(PAGERESULT, this.employeeService.query(qo));
		this.addContext("depts", this.departmentService.list());
		return LIST;
	}

	@RequiredPermission("新增/修改员工")
	public String input() {
		this.addContext("depts", this.departmentService.list());
		this.addContext("roles", this.roleService.list());
		if (employee.getId() != null) {
			employee = this.employeeService.get(employee.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改员工")
	@InputConfig(methodName = "input")
	public String save() {
		if (employee.getId() != null) {
			this.employeeService.update(employee);
			this.addActionMessage("修改成功!");
		} else {
			this.employeeService.save(employee);
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}

	public void prepareSave() {
		if (employee.getId() != null) {
			employee = this.employeeService.get(employee.getId());
			employee.setDept(null);
		}
		employee.getRoles().clear();
	}

	@RequiredPermission("删除员工")
	public String delete() {
		if (employee.getId() != null) {
			this.employeeService.delete(employee);
		}
		return SUCCESS;
	}

	@RequiredPermission("批量删除员工")
	public String batchDelete() {
		if (ids.size() > 0) {
			this.employeeService.deleteBatch(ids);
		}
		return NONE;
	}

	public EmployeeQueryObject getQo() {
		return qo;
	}

	public void setQo(EmployeeQueryObject qo) {
		this.qo = qo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}
