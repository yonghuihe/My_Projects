package com.company.pss.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.company.pss.domain.Employee;
import com.company.pss.query.EmployeeQueryObject;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IDepartmentService;
import com.company.pss.service.IEmployeeService;
import com.company.pss.service.IRoleService;
import com.company.pss.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

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
	private List<Long> ids;

	@RequiredPermission("员工列表")
	public String execute() {
		try {
			addContext("depts", departmentService.list());
			addContext(PAGERESULT, employeeService.query(queryObject));
		} catch (Exception e) {
			this.addActionError("查询错误");
			this.addActionError(e.getMessage());
		}
		return LIST;
	}

	/**
	 * 导入到添加页面
	 */
	@RequiredPermission("新增/修改员工")
	public String input() {
		addContext("depts", departmentService.list());
		addContext("roles", roleService.list());
		/**
		 * 判断id是否为null，如果不为null是修改，需要查询出employee对象
		 */
		if (employee.getId() != null) {
			employee = this.employeeService.get(employee.getId());
		}
		return INPUT;
	}

	public void prepareSave() {
		if (employee.getId() != null) {
			employee = employeeService.get(employee.getId());
			/**
			 * 因为设置了open session in view，所以：employee是持久化对象，department也是持久化对象
			 * 如果他们之间的关系还在，直接修改department持久化对象的id，hibernate不允许，会报错
			 * 解决方案：打破employee与department之间的关系，在update的时候，
			 * 再来维护employee与department之间的关系
			 */
			employee.setDept(null);
		}
		employee.getRoles().clear();
	}

	/**
	 * 保存或修改
	 * 
	 * @return
	 */
	@InputConfig(methodName = "input")
	@RequiredPermission("修改员工")
	public String save() {
		/**
		 * 判断id是否为null，如果不为null是修改
		 */
		if (employee.getId() != null) {
			this.employeeService.update(employee);
			this.addActionMessage("修改成功");
		} else {
			this.employeeService.save(employee);
			this.addActionMessage("添加成功");
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws IOException 
	 */
	@RequiredPermission("删除员工")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (employee.getId() != null) {
				//int i = 1/0;
				this.employeeService.delete(employee);
			}
			response.getWriter().write("删除成功！");
		} catch (Exception e) {
			response.getWriter().write("删除失败，请联系管理员！"+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 * @throws IOException
	 */
	public void batchDelete() throws IOException {
		if (ids.size() <= 0) {
			return;
		}
		employeeService.batchDelete(ids);
	}

	/**
	 * 提供get方法，可以把employee放到值栈的root区域
	 * 
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

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}
