package com._520it.es.web.controller;

import com._520it.es.domain.OrderBillItem;
import com._520it.es.page.PageResult;
import com._520it.es.query.OrderBillItemQueryObject;
import com._520it.es.service.IOrderBillItemService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderBillItemController {
	@Autowired
	IOrderBillItemService orderBillItemService;
	
	@RequestMapping("/orderBillItem")
	public String index(){
		return "orderBillItem";
	}
	@RequestMapping("/orderBillItem_list")
	@ResponseBody
	public PageResult list(OrderBillItemQueryObject qo){
		PageResult pageResult = null;
		pageResult = orderBillItemService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/orderBillItem_save")
	@ResponseBody
	public JsonResult save(OrderBillItem orderBillItem){
		JsonResult result = null;
		try{
			orderBillItemService.insert(orderBillItem);
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/orderBillItem_update")
	@ResponseBody
	public JsonResult update(OrderBillItem orderBillItem){
		JsonResult result = null;
		try{
			orderBillItemService.updateByPrimaryKey(orderBillItem);
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/orderBillItem_delete")
	@ResponseBody
	public JsonResult delete(Long orderBillItemId){
		JsonResult result = null;
		try{
			orderBillItemService.deleteByPrimaryKey(orderBillItemId);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
