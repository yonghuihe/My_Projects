package com._520it.es.web.controller;

import com._520it.es.domain.OrderBill;
import com._520it.es.service.IOrderBillService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderBillController {
	@Autowired
	IOrderBillService orderBillService;
	
	@RequestMapping("/orderBill")
	public String index(){
		return "orderBill";
	}
	@RequestMapping("/orderBill_list")
	@ResponseBody
	public Map list(){
		//PageResult pageResult = null;
		//pageResult = orderBillService.queryByConditionPage(qo);
		Map<String,Object> map = new HashMap();
		List<OrderBill> orderBills = orderBillService.selectAll();
		for (OrderBill orderBill : orderBills) {
			System.out.println(orderBill);
		}

		map.put("total",orderBills.size());
		map.put("rows",orderBills);
		return map;
	}
	@RequestMapping("/orderBill_save")
	@ResponseBody
	public JsonResult save(OrderBill orderBill){
		JsonResult result = null;
		try{
			orderBillService.insert(orderBill);
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/orderBill_update")
	@ResponseBody
	public JsonResult update(OrderBill orderBill){
		JsonResult result = null;
		try{
			orderBillService.updateByPrimaryKey(orderBill);
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/orderBill_delete")
	@ResponseBody
	public JsonResult delete(Long orderBillId){
		JsonResult result = null;
		try{
			orderBillService.deleteByPrimaryKey(orderBillId);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
