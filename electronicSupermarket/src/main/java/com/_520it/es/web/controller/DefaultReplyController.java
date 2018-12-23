package com._520it.es.web.controller;

import com._520it.es.domain.DefaultReply;
import com._520it.es.page.PageResult;
import com._520it.es.query.DefaultReplyQueryObject;
import com._520it.es.service.IDefaultReplyService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultReplyController {
	@Autowired
	IDefaultReplyService defaultReplyService;
	
	@RequestMapping("/defaultReply")
	public String index(){
		return "defaultReply";
	}
	@RequestMapping("/defaultReply_list")
	@ResponseBody
	public PageResult list(DefaultReplyQueryObject qo){
		PageResult pageResult = null;
		pageResult = defaultReplyService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/defaultReply_save")
	@ResponseBody
	public JsonResult save(DefaultReply defaultReply){
		JsonResult result = null;
		try{
			defaultReplyService.insert(defaultReply);
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/defaultReply_update")
	@ResponseBody
	public JsonResult update(DefaultReply defaultReply){
		JsonResult result = null;
		try{
			defaultReplyService.updateByPrimaryKey(defaultReply);
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/defaultReply_delete")
	@ResponseBody
	public JsonResult delete(Long defaultReplyId){
		JsonResult result = null;
		try{
			defaultReplyService.deleteByPrimaryKey(defaultReplyId);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
