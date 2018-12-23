package com.company.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Role;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;
import com.company.crm.service.IRoleService;
import com.company.crm.util.JsonResult;

@Controller
public class RoleController {
	
	@Autowired
	private IRoleService service;
	
	
	@RequestMapping("/role")
	public String index(){
		return "role";
	}
	
	@RequestMapping("/role_list")
	@ResponseBody
	public PageResult list(QueryObject qo){
		return service.queryPageResult(qo);
	}
	
	
	@RequestMapping("/role_selectAll")
	@ResponseBody
	public List<Role> selectAll(){
		return service.selectAll();
	}
	
	
	@RequestMapping("/role_save")
	@ResponseBody
	public JsonResult save(Role role){
		JsonResult result = new JsonResult();
		try{
			service.insert(role);
			result.setSuccess(true);
			result.setMsg("保存成功!");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg("保存失败!");
		}
		return result;
	}
	
	@RequestMapping("/role_update")
	@ResponseBody
	public JsonResult update(Role role){
		JsonResult result = new JsonResult();
		try{
			service.updateByPrimaryKey(role);
			result.setSuccess(true);
			result.setMsg("更新成功!");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg("更新失败!");
		}
		return result;
	}
	
	
}
