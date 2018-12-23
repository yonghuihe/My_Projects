package com._520it.es.web.controller;

import com._520it.es.domain.Product;
import com._520it.es.page.PageResult;
import com._520it.es.query.ProductQueryObject;
import com._520it.es.service.IProductService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
	@Autowired
	IProductService productService;
	
	@RequestMapping("/product")
	public String index(){
		return "product";
	}
	@RequestMapping("/product_list")
	@ResponseBody
	public PageResult list(ProductQueryObject qo){
		PageResult pageResult = null;
		pageResult = productService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/product_save")
	@ResponseBody
	public JsonResult save(Product product){
		JsonResult result = null;
		try{
			productService.insert(product);
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/product_update")
	@ResponseBody
	public JsonResult update(Product product){
		JsonResult result = null;
		try{
			productService.updateByPrimaryKey(product);
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/product_delete")
	@ResponseBody
	public JsonResult delete(Long productId){
		JsonResult result = null;
		try{
			productService.deleteByPrimaryKey(productId);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
