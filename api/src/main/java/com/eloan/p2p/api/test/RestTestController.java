package com.eloan.p2p.api.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringMVC rest开发测试控制器 完成一个员工的api
 * 
 * 
 * @author yonghui
 *
 */
@RestController // @RestController=@Controller+@ResponseBody注解
@RequestMapping("emps") // RequestMapping的继承
public class RestTestController {

	/**
	 * 获取员工列表
	 * 
	 * GET emps 返回员工列表的json数据 限定只能使用GET的方式来请求该方法
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> listEmps() {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		emps.add(new Employee(1L, "emp1"));
		emps.add(new Employee(2L, "emp2"));
		return emps;
	}

	/**
	 * 得到某一个指定员工的信息
	 * 
	 * GET emps/1 返回一个json对象
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Employee getEmp(@PathVariable("id") Long id) {
		return new Employee(id, "emp");
	}

	/**
	 * 得到某个员工的某个月的工资
	 * 
	 * GET emps/1/salary/2019-1 返回一个工资对象
	 */
	@RequestMapping(value = "/{empid}/salary/{month}", method = RequestMethod.GET)
	public Salary getSalary(@PathVariable("empid") Long empId,
			@PathVariable("month") @DateTimeFormat(pattern = "yyyy-MM") Date month) {
		return new Salary(month, empId, BigDecimal.TEN);
	}

	/**
	 * 给一个员工添加一条薪资信息
	 * 
	 * POST emps/1/salaries 返回新生成的薪资记录
	 */
	@RequestMapping(value = "/{empid}/salaries", method = RequestMethod.POST)
	public Salary saveSalary(@PathVariable("empid") Long empId, Salary salary) {
		salary.setEmpid(empId);
		return salary;
	}

	/**
	 * 删除一个员工
	 * 
	 * DELETE emps/1 返回一个空文档
	 * 
	 * 设置响应的状态码为204
	 */
	@RequestMapping(value = "/{empid}", method = RequestMethod.DELETE)
	public void deleteEmp(@PathVariable("empid") Long empId, HttpServletResponse response) {
		System.out.println("删除员工" + empId);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);// 204

	}

}
