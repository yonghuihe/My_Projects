package com.company.crm.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.crm.domain.Employee;
import com.company.crm.page.PageResult;
import com.company.crm.query.EmployeeQuery;
import com.company.crm.service.IEmployeeService;
import com.company.crm.util.JsonResult;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
public class EmployeeController {
	
	@Autowired
	private IEmployeeService service;
	/*@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IPermissionService permissionService;*/
	
	@RequestMapping("/employee")
	public String index(){
		return "employee";
	}
	
	@RequestMapping("/employee_list")
	@ResponseBody
	public PageResult list(EmployeeQuery qo){
		return service.queryPageResult(qo);
	}
	
	@RequestMapping("/employee_save")
	@ResponseBody
	public JsonResult save(Employee employee){
		JsonResult result = new JsonResult();
		try{
			//设置默认状态
			employee.setState(true);
			service.insert(employee);
			result.setSuccess(true);
			result.setMsg("保存成功!");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg("保存失败!");
		}
		return result;
	}
	
	@RequestMapping("/employee_update")
	@ResponseBody
	public JsonResult update(Employee employee){
		JsonResult result = new JsonResult();
		try{
			service.updateByPrimaryKey(employee);
			result.setSuccess(true);
			result.setMsg("更新成功!");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg("更新失败!");
		}
		return result;
	}
	
	@RequestMapping("/employee_updateState")
	@ResponseBody
	public JsonResult update(Long id){
		JsonResult result = new JsonResult();
		try{
			service.updateState(id);
			result.setSuccess(true);
			result.setMsg("更新成功!");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg("更新失败!");
		}
		return result;
	}
	
	@RequestMapping("/getRidByEid")
	@ResponseBody
	public List<Long> getRidByEid(Long id){
		return service.getRidByEid(id);
	}
	
	@RequestMapping("/employee_export")
	public void employee_export(HttpServletResponse response) throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=a.xls");
		//创建xls文件
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
		//创建工作簿
		WritableSheet sheet = workbook.createSheet("day01", 0);
		/*//创建单元格
		Label label = new Label(0, 0, "lucy");
		//把单元格添加到工作簿上
		sheet.addCell(label);
		//创建日期单元格
		DateTime date = new DateTime(1,0,new Date());
		//把单元格添加到工作簿上
		sheet.addCell(date);*/
		
		//创建标题
		Label title = new Label(0, 0, "用户名");
		sheet.addCell(title);
		title = new Label(1, 0, "真实姓名");
		sheet.addCell(title);
		title = new Label(2, 0, "手机号码");
		sheet.addCell(title);
		title = new Label(3, 0, "状态");
		sheet.addCell(title);
		
		
		//把学生的信息导出
		List<Employee> list = service.selectAll();
		for (int i = 1; i < list.size(); i++) {
			Employee employee = list.get(i);
			Label label = new Label(0, i,employee.getUsername() );
			sheet.addCell(label);
			label = new Label(1, i,employee.getRealname() );
			sheet.addCell(label);
			label = new Label(2, i,employee.getTel() );
			sheet.addCell(label);
			label = new Label(3, i,employee.getState()?"在职":"离职");
			sheet.addCell(label);
		}
		
		//写入内容
		workbook.write();
		//关闭
		workbook.close();
	}
	
	@RequestMapping("/employee_import")
	public void employee_import(MultipartFile file) throws Exception{
		Workbook workbook = Workbook.getWorkbook(file.getInputStream());
		//获取工作簿
		Sheet sheet = workbook.getSheet(0);
		//获取总行数
		int rows = sheet.getRows();
		for (int i = 1; i < rows; i++) {
			Employee e = new Employee();
			e.setUsername(sheet.getCell(0, i).getContents());
			e.setRealname(sheet.getCell(1, i).getContents());
			e.setTel(sheet.getCell(2, i).getContents());
			e.setState(true);
			service.insert(e);
		}
		
		workbook.close();
	}
	
	
	
}
