package com.company.ssh.mvc;

import org.springframework.stereotype.Controller;

import com.company.ssh.domain.Department;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IDepartmentService;
import com.company.ssh.util.RequiredPermission;

/**
 * crud
 * 
 * @author yonghui
 *
 */
@Controller
public class DepartmentAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private IDepartmentService departmentService;
	private QueryObject queryObject = new QueryObject();
	private Department department = new Department();
	
	@RequiredPermission("部门列表")
	public String execute() {
		addContext(PAGERESULT, departmentService.query(queryObject));
		return LIST;
	}

	/**
	 * 导入到添加页面
	 */
	@RequiredPermission("新增/修改部门")
	public String input() {
		/**
		 * 判断id是否为null，如果不为null是修改，需要查询出Department对象
		 */
		if (department.getId() != null) {
			department = this.departmentService.get(department.getId());
		}
		return INPUT;
	}
	
	/**
	 * 保存或修改
	 * @return
	 */
	@RequiredPermission("修改部门")
	public String save(){
		/**
		 * 判断id是否为null，如果不为null是修改
		 */
		if (department.getId() != null) {
			this.departmentService.update(department);
		} else {
			this.departmentService.save(department);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequiredPermission("删除部门")
	public String delete(){
		if(department.getId() != null){
			this.departmentService.delete(department);
		}
		return SUCCESS;
	}
	/**
	 * 提供get方法，可以把Department放到值栈的root区域
	 * @return
	 */
	public Department getDepartment() {
		return department;
	}
	
	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}

