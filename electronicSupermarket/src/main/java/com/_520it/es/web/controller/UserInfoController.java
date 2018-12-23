package com._520it.es.web.controller;

import com._520it.es.domain.UserInfo;
import com._520it.es.page.PageResult;
import com._520it.es.query.UserInfoQueryObject;
import com._520it.es.service.IUserInfoService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserInfoController {
	@Autowired
	IUserInfoService userInfoService;
	
	@RequestMapping("/userInfo")
	public String index(){
		return "userInfo";
	}
	@RequestMapping("/userInfo_list")
	@ResponseBody
	public PageResult list(UserInfoQueryObject qo){
		PageResult pageResult = null;
		pageResult = userInfoService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/userInfo_save")
	@ResponseBody
	public JsonResult save(UserInfo userInfo){
		JsonResult result = null;
		try{
			userInfoService.insert(userInfo);
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/userInfo_update")
	@ResponseBody
	public JsonResult update(UserInfo userInfo){
		JsonResult result = null;
		try{
			userInfoService.updateByPrimaryKey(userInfo);
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/userInfo_delete")
	@ResponseBody
	public JsonResult delete(Long userInfoId){
		JsonResult result = null;
		try{
			userInfoService.deleteByPrimaryKey(userInfoId);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
