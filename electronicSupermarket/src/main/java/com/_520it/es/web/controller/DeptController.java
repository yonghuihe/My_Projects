package com._520it.es.web.controller;

import com._520it.es.domain.Dept;
import com._520it.es.page.PageResult;
import com._520it.es.query.DeptQueryObject;
import com._520it.es.service.IDeptService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {
	@Autowired
	IDeptService deptService;
	
	@RequestMapping("/dept")
	public String index(){
		return "dept";
	}
	@RequestMapping("/dept_list")
	@ResponseBody
	public PageResult list(DeptQueryObject qo){
		PageResult pageResult = null;
		pageResult = deptService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/dept_save")
	@ResponseBody
	public JsonResult save(Dept dept){
		JsonResult result = null;
		try{
			deptService.insert(dept);
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/dept_update")
	@ResponseBody
	public JsonResult update(Dept dept){
		JsonResult result = null;
		try{
			deptService.updateByPrimaryKey(dept);
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/dept_delete")
	@ResponseBody
	public JsonResult delete(Long deptId){
		JsonResult result = null;
		try{
			deptService.deleteByPrimaryKey(deptId);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
