package com.company.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Customer;
import com.company.crm.page.PageResult;
import com.company.crm.query.CustomerQueryObject;
import com.company.crm.service.ICustomerService;
import com.company.crm.util.AjaxResult;

@Controller
public class CustomerController {
	@Autowired
	ICustomerService customerService;
	
	@RequestMapping("/customer")
	public String index(){
		return "customer/customer";
	}
	@RequestMapping("/customer_list")
	@ResponseBody
	public PageResult list(CustomerQueryObject qo){
		PageResult pageResult = null;
		pageResult = customerService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/customer_save")
	@ResponseBody
	public AjaxResult save(Customer customer){
		AjaxResult result = null;
		try{
			customerService.insert(customer);
			result = new AjaxResult("保存成功",true);
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！",false);
		}
		return result;
	}
	@RequestMapping("/customer_update")
	@ResponseBody
	public AjaxResult update(Customer customer){
		AjaxResult result = null;
		try{
			customerService.updateByPrimaryKey(customer);
			result = new AjaxResult("更新成功",true);
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！",false);
		}
		return result;
	}
	@RequestMapping("/customer_delete")
	@ResponseBody
	public AjaxResult delete(Long customerId){
		AjaxResult result = null;
		try{
			customerService.deleteByPrimaryKey(customerId);
			result = new AjaxResult("删除成功",true);
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！",false);
		}
		return result;
	}
}
