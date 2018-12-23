package com.xmg.mgrsite.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.api.domain.SkuProperty;
import com.xmg.api.domain.SkuPropertyValue;
import com.xmg.api.service.ICatalogService;
import com.xmg.api.service.ISkuPropertyService;
import com.xmg.api.vo.JSONResult;

@Controller
public class SkuPropertyController {

	@Autowired
	ICatalogService catalogService;
	
	@Autowired
	ISkuPropertyService skuPropertyService;
	
	/**
	 * 进入分类属性管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/skuProperty.do")
	public String skuProperty(Model model){
		
		return "sku/property";
	}
	
	
	/**
	 * 通过分类ID获取分类属性,返回分类属性列表
	 * @param catalogId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getSkuProperty.do")
	public String getSkuProperty(Long catalogId,Model model){
		List<SkuProperty> list = skuPropertyService.getSkuProperty(catalogId);
		model.addAttribute("list", list);
		return "sku/property_list";
	}
	
	/**
	 * 通过分类属性ID获取分类属性值
	 * @param skuPropertyId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getSkuPropertyValue.do")
	public String getSkuPropertyValue(Long skuPropertyId,Model model){
		SkuProperty skuProperty = skuPropertyService.selectByPrimaryKey(skuPropertyId);
		List<SkuPropertyValue> skuPropertyValueList = skuPropertyService.getSkuPropertyValue(skuPropertyId);
		model.addAttribute("list", skuPropertyValueList);
		model.addAttribute("skuProperty", skuProperty);
		return "sku/property_value_list";
	}
	
	/**
	 * 新增分类属性值
	 */
	@RequestMapping("/addSkuPropertyValue.do")
	@ResponseBody
	public JSONResult addSkuPropertyValue(SkuPropertyValue skuPropertyValue){
		JSONResult jSONResult = new JSONResult();
		try {
			
			skuPropertyValue.setSequence(0);
			skuPropertyService.insertPropertyValue(skuPropertyValue);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jSONResult.setErrorMsg(e.getMessage());
		}
		return jSONResult;
		
	}
	
	/**
	 * 新增分类属性
	 * @param skuProperty
	 * @return
	 */
	@RequestMapping("/addSkuProperty.do")
	@ResponseBody
	public JSONResult addSkuProperty(SkuProperty skuProperty){
		JSONResult jSONResult = new JSONResult();
		try {
			if(skuProperty.getId() == null || skuProperty.getId() == -1){
				
				skuProperty.setSequence(0);
				skuPropertyService.insert(skuProperty);
			}else{
				SkuProperty skuPropertyDb = skuPropertyService.selectByPrimaryKey(skuProperty.getId());
				skuPropertyDb.setName(skuProperty.getName());
				skuPropertyDb.setType(skuProperty.getType());
				skuPropertyService.updateByPrimaryKey(skuPropertyDb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jSONResult.setErrorMsg(e.getMessage());
		}
		return jSONResult;
		
	}
	
	@RequestMapping("/toSkuPropertySave.do")
	public String toPropertySave(Model model,SkuProperty skuProperty){
		if(skuProperty.getId() != -1){
			
			skuProperty = skuPropertyService.selectByPrimaryKey(skuProperty.getId());
		}
		model.addAttribute("skuProperty", skuProperty);
		return "sku/property_save";
	}
	
	@RequestMapping("/deleteSkuProperty.do")
	public String deleteSkuProperty(SkuProperty skuProperty){
		skuPropertyService.deleteByPrimaryKey(skuProperty.getId());
		
		return "redirect:/getSkuProperty.do?catalogId="+skuProperty.getCatalogId();
	}
}
