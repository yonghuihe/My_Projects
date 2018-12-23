package com.company.pss.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.company.pss.domain.Department;
import com.company.pss.query.DepartmentQueryObject;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IDepartmentService;
import com.company.pss.util.RequiredPermission;

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
	private QueryObject queryObject = new DepartmentQueryObject();
	private Department department = new Department();

	@RequiredPermission("部门列表")
	public String execute() {
		addContext("depts", departmentService.list());
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
	 * 
	 * @return
	 */
	@RequiredPermission("修改部门")
	public String save() {
		/**
		 * 判断id是否为null，如果不为null是修改
		 */
		if (department.getId() != null) {
			this.departmentService.update(department);
			this.addActionMessage("修改成功");
		} else {
			this.departmentService.save(department);
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
	@RequiredPermission("删除部门")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (department.getId() != null) {
				this.departmentService.delete(department);
			}
			response.getWriter().write("删除成功");
		} catch (Exception e) {
			response.getWriter().write("删除失败，请联系管理员："+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 提供get方法，可以把Department放到值栈的root区域
	 * 
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
