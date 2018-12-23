package com._520it.es.web.controller;

import com._520it.es.domain.MessageHandling;
import com._520it.es.page.PageResult;
import com._520it.es.query.MessageHandlingQueryObject;
import com._520it.es.service.IMessageHandlingService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageHandlingController {
	@Autowired
	IMessageHandlingService messageHandlingService;
	
	@RequestMapping("/messageHandling")
	public String index(){
		return "messageHandling";
	}
	@RequestMapping("/messageHandling_list")
	@ResponseBody
	public PageResult list(MessageHandlingQueryObject qo){
		PageResult pageResult = null;
		pageResult = messageHandlingService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/messageHandling_save")
	@ResponseBody
	public JsonResult save(MessageHandling messageHandling){
		JsonResult result = null;
		try{
			messageHandlingService.insert(messageHandling);
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/messageHandling_update")
	@ResponseBody
	public JsonResult update(MessageHandling messageHandling){
		JsonResult result = null;
		try{
			messageHandlingService.updateByPrimaryKey(messageHandling);
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/messageHandling_delete")
	@ResponseBody
	public JsonResult delete(Long messageHandlingId){
		JsonResult result = null;
		try{
			messageHandlingService.deleteByPrimaryKey(messageHandlingId);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
