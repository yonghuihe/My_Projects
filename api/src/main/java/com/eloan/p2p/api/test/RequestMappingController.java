package com.eloan.p2p.api.test;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用于测试RequestMapping属性
 * 
 * @author yonghui
 *
 */
//@RestController
public class RequestMappingController {

	/**
	 * 根据请求格式返回对应的格式，例如：xml请求返回xml格式，json请求返回json格式
	 * 
	 * produces = headers="Accept=xxx"
	 */
	// @RequestMapping(value="emps",headers="Accept=application/xml")
	@RequestMapping(value = "emps", method = RequestMethod.GET, produces = "application/xml")
	public void getXml() {
		System.out.println("xml");
	}

	// @RequestMapping(value="emps",headers="Accept=application/json")
	@RequestMapping(value = "emps", method = RequestMethod.GET, produces = "application/json")
	public void getJson() {
		System.out.println("json");
	}

	/**
	 * 创建一个Employee对象
	 * 
	 * consumes = headers="Content-Type=xxx"
	 */
	@RequestMapping(value = "emps", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
	public Employee createEmployeeByForm(Employee e) {
		System.out.println("使用表单内容创建Employee对象");
		return e;
	}

	@RequestMapping(value = "emps", method = RequestMethod.POST, consumes = "application/json")
	public Employee createEmployeeByJson(@RequestBody Employee e) {
		System.out.println("使用json创建Employee对象");
		return e;
	}

}
