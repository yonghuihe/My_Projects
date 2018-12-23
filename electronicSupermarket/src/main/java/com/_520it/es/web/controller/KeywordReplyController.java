package com._520it.es.web.controller;

import com._520it.es.domain.KeywordReply;
import com._520it.es.page.PageResult;
import com._520it.es.query.KeywordReplyQueryObject;
import com._520it.es.service.IKeywordReplyService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KeywordReplyController {
	@Autowired
	IKeywordReplyService keywordReplyService;
	
	@RequestMapping("/keywordReply")
	public String index(){
		return "keywordReply";
	}
	@RequestMapping("/keywordReply_list")
	@ResponseBody
	public PageResult list(KeywordReplyQueryObject qo){
		PageResult pageResult = null;
		pageResult = keywordReplyService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/keywordReply_save")
	@ResponseBody
	public JsonResult save(KeywordReply keywordReply){
		JsonResult result = null;
		try{
			keywordReplyService.insert(keywordReply);
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/keywordReply_update")
	@ResponseBody
	public JsonResult update(KeywordReply keywordReply){
		JsonResult result = null;
		try{
			keywordReplyService.updateByPrimaryKey(keywordReply);
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/keywordReply_delete")
	@ResponseBody
	public JsonResult delete(Long keywordReplyId){
		JsonResult result = null;
		try{
			keywordReplyService.deleteByPrimaryKey(keywordReplyId);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
