package com.company.ssh.mvc;

import org.springframework.stereotype.Controller;

import com.company.ssh.domain.Employee;
import com.company.ssh.query.EmployeeQueryObject;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IDepartmentService;
import com.company.ssh.service.IEmployeeService;
import com.company.ssh.service.IRoleService;
import com.company.ssh.util.RequiredPermission;

/**
 * crud
 * 
 * @author yonghui
 *
 */
@Controller
public class EmployeeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private IEmployeeService employeeService;
	private IDepartmentService departmentService;
	private IRoleService roleService;
	private QueryObject queryObject = new EmployeeQueryObject();
	private Employee employee = new Employee();
	
	@RequiredPermission("员工列表")
	public String execute() {
		addContext("depts",departmentService.list());
		addContext(PAGERESULT, employeeService.query(queryObject));
		return LIST;
	}

	/**
	 * 导入到添加页面
	 */
	@RequiredPermission("新增/修改员工")
	public String input() {
		addContext("depts",departmentService.list());
		addContext("roles",roleService.list());
		/**
		 * 判断id是否为null，如果不为null是修改，需要查询出employee对象
		 */
		if (employee.getId() != null) {
			employee = this.employeeService.get(employee.getId());
		}
		return INPUT;
	}
	
	
	public void prepareSave(){
		if(employee.getId() != null){
			employee = employeeService.get(employee.getId());
			/**
			 * 因为设置了open session in view，所以：employee是持久化对象，department也是持久化对象
			 * 如果他们之间的关系还在，直接修改department持久化对象的id，hibernate不允许，会报错
			 * 解决方案：打破employee与department之间的关系，在update的时候，再来维护employee与department之间的关系
			 */
			employee.setDept(null);
		}
		employee.getRoles().clear();
	}
	
	/**
	 * 保存或修改
	 * @return
	 */
	@RequiredPermission("修改员工")
	public String save(){
		/**
		 * 判断id是否为null，如果不为null是修改
		 */
		if (employee.getId() != null) {
			this.employeeService.update(employee);
		} else {
			this.employeeService.save(employee);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequiredPermission("删除员工")
	public String delete(){
		if(employee.getId() != null){
			this.employeeService.delete(employee);
		}
		return SUCCESS;
	}
	
	/**
	 * 提供get方法，可以把employee放到值栈的root区域
	 * @return
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	public QueryObject getQueryObject() {
		return queryObject;
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
}

