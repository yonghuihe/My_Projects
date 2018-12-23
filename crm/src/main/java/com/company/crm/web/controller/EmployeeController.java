package com.company.crm.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.crm.domain.Department;
import com.company.crm.domain.Employee;
import com.company.crm.page.PageResult;
import com.company.crm.query.EmployeeQueryObject;
import com.company.crm.service.IDepartmentService;
import com.company.crm.service.IEmployeeService;
import com.company.crm.util.AjaxResult;
import com.company.crm.util.PermissionName;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Boolean;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
public class EmployeeController extends BaseController {

	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDepartmentService departmentService;

	@RequestMapping("/employee")
	@RequiresPermissions("employee:execute")
	@PermissionName("员工主页")
	public String execute() {
		return "/employee/employee";
	}

	@RequestMapping("/employee_list")
	@ResponseBody
	@RequiresPermissions("employee:list")
	@PermissionName("员工列表")
	public PageResult list(EmployeeQueryObject qo) {
		// 1、获取主体对象，判断当前用户是否拥有admin角色，如果没有只显示自己的数据
		Subject subject = SecurityUtils.getSubject();
		if (!subject.hasRole("admin")) {
			// 获取主体信息
			Employee employee = (Employee) subject.getPrincipal();
			qo.setId(employee.getId());
		}

		PageResult result = employeeService.queryPageResult(qo);
		return result;
	}
	
	@RequestMapping("/employee_selectAll")
	@ResponseBody
	@RequiresPermissions("employee_selectAll")
	@PermissionName("员工列表")
	public List<Employee> list(){
		List<Employee> list = employeeService.selectAll();
		return list;
	}

	@RequestMapping("/employee_save")
	@ResponseBody
	@RequiresPermissions("employee:save")
	@PermissionName("新增员工")
	public AjaxResult save(Employee employee) {
		AjaxResult result = null;
		try {
			employeeService.insert(employee);
			result = new AjaxResult("保存成功", true);
		} catch (Exception e) {
			result = new AjaxResult("保存失败", true);

		}
		return result;
	}

	@RequestMapping("/employee_update")
	@ResponseBody
	@RequiresPermissions("employee:update")
	@PermissionName("更新员工")
	public AjaxResult update(Employee employee) {
		AjaxResult result = null;
		try {
			employeeService.updateByPrimaryKey(employee);
			result = new AjaxResult("修改成功", true);
		} catch (Exception e) {
			result = new AjaxResult("修改失败", true);

		}
		return result;
	}

	@RequestMapping("/employee_remove")
	@ResponseBody
	@RequiresPermissions("employee:remove")
	@PermissionName("删除员工")
	public AjaxResult remove(Long id) {
		AjaxResult result = null;
		try {
			employeeService.updateState(id);
			result = new AjaxResult("修改成功", true);
		} catch (Exception e) {
			result = new AjaxResult("修改失败", true);
		}
		return result;
	}

	@RequestMapping("/employee_getRoleIdsByEId")
	@ResponseBody
	public List<Long> getRoleIdsByEId(Long id) {
		List<Long> rIds = employeeService.getRoleIdsByEId(id);
		System.out.println(rIds);
		return rIds;
	}

	/**
	 * 导出
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/employee_export")
	public void doExport(HttpServletResponse response) throws Exception {
		// 0 、使用浏览器完成导出
		response.setHeader("Content-Disposition", "attachement;filename=a.xls");
		// 1、创建xls文件
		// WritableWorkbook workbook = Workbook.createWorkbook(new
		// File("d:/a.xls"));
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
		// 2、创建工作簿
		WritableSheet sheet = workbook.createSheet("day01", 0);

		// 设置单元格的头
		// 3、创建单元格
		Label label = new Label(0, 0, "用户名");
		// 4、添加到工作簿中
		sheet.addCell(label);

		label = new Label(1, 0, "真实名字");
		sheet.addCell(label);

		label = new Label(2, 0, "电话号码");
		sheet.addCell(label);

		label = new Label(3, 0, "邮箱");
		sheet.addCell(label);

		label = new Label(4, 0, "入职时间");
		sheet.addCell(label);

		label = new Label(5, 0, "部门");
		sheet.addCell(label);

		label = new Label(6, 0, "状态");
		sheet.addCell(label);

		label = new Label(7, 0, "是否是管理员");
		sheet.addCell(label);

		// ----------------从数据库中获取数据并封装----------------------
		List<Employee> list = employeeService.selectAll();
		Employee employee = null;
		DateTime time = null;
		Boolean state = null;
		for (int i = 0; i < list.size(); i++) {
			employee = list.get(i);
			System.out.println(employee);
			label = new Label(0, i + 1, employee.getUsername());
			sheet.addCell(label);
			label = new Label(1, i + 1, employee.getRealName());
			sheet.addCell(label);
			label = new Label(2, i + 1, employee.getTel());
			sheet.addCell(label);
			label = new Label(3, i + 1, employee.getEmail());
			sheet.addCell(label);
			time = new DateTime(4, i + 1, employee.getInputTime());
			sheet.addCell(time);
			label = new Label(5, i + 1, employee.getDept().getName());
			sheet.addCell(label);
			state = new Boolean(6, i + 1, employee.getState());
			sheet.addCell(state);
			state = new Boolean(7, i + 1, employee.getAdmin());
			sheet.addCell(state);
		}
		// ----------------------------------------------------

		// 5、写入数据
		workbook.write();
		// 6、关闭资源
		workbook.close();

	}

	/**
	 * 导入
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/employee_import")
	@ResponseBody
	public AjaxResult doImport(MultipartFile file) throws Exception {
		AjaxResult result = null;
		try {
			// 获取上传的xls文件
			Workbook workbook = Workbook.getWorkbook(file.getInputStream());
			// 获取工作簿
			Sheet sheet = workbook.getSheet(0);
			// 获取行数
			int rows = sheet.getRows();
			// 获取列数
			// int columns = sheet.getColumns();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Department department = new Department();
			Cell cell = null;
			DateCell dc = null;
			java.lang.String contents = null;

			for (int i = 1; i < rows; i++) {
				Employee employee = new Employee();
				employee.setUsername(sheet.getCell(0, i).getContents());
				employee.setRealName(sheet.getCell(1, i).getContents());
				employee.setTel(sheet.getCell(2, i).getContents());
				employee.setEmail(sheet.getCell(3, i).getContents());

				// 处理日期
				cell = sheet.getCell(4, i);
				dc = (DateCell) cell;
				Date date = dc.getDate();
				String String = format.format(date);
				Date parse = format.parse(String);
				employee.setInputTime(parse);

				// 处理部门
				contents = sheet.getCell(5, i).getContents();
				department = departmentService.selectByName(contents);

				employee.setDept(department);
				employee.setState(java.lang.Boolean.valueOf(sheet.getCell(6, i).getContents()));
				employee.setAdmin(java.lang.Boolean.valueOf(sheet.getCell(7, i).getContents()));
				employee.setPassword("111");
				employeeService.insert(employee);
			}
			workbook.close();

			result = new AjaxResult("导入成功", true);
		} catch (Exception e) {
			result = new AjaxResult("导入失败", true);
		}
		return result;
	}

}
