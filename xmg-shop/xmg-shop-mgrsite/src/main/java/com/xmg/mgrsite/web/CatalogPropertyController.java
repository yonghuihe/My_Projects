package com.xmg.mgrsite.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.api.domain.CatalogProperty;
import com.xmg.api.domain.CatalogPropertyValue;
import com.xmg.api.service.ICatalogPropertyService;
import com.xmg.api.service.ICatalogService;
import com.xmg.api.vo.JSONResult;

@Controller
public class CatalogPropertyController {

	@Autowired
	ICatalogService catalogService;
	
	@Autowired
	ICatalogPropertyService catalogPropertyService;
	
	/**
	 * 进入分类属性管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/catalogProperty.do")
	public String catalogProperty(Model model){
		
		return "property/property";
	}
	
	
	/**
	 * 通过分类ID获取分类属性,返回分类属性列表
	 * @param catalogId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getCatalogProperty.do")
	public String getCatalogProperty(Long catalogId,Model model){
		List<CatalogProperty> list = catalogPropertyService.getCatalogProperty(catalogId);
		model.addAttribute("list", list);
		return "property/property_list";
	}
	
	/**
	 * 通过分类属性ID获取分类属性值
	 * @param catalogPropertyId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getCatalogPropertyValue.do")
	public String getCatalogPropertyValue(Long catalogPropertyId,Model model){
		
		try {
			CatalogProperty catalogProperty = catalogPropertyService.selectByPrimaryKey(catalogPropertyId);
			List<CatalogPropertyValue> catalogPropertyValueList = catalogPropertyService.getCatalogPropertyValue(catalogPropertyId);
			model.addAttribute("list", catalogPropertyValueList);
			model.addAttribute("catalogProperty", catalogProperty);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "property/property_value_list";
	}
	
	/**
	 * 新增分类属性值
	 */
	@RequestMapping("/addCatalogPropertyValue.do")
	@ResponseBody
	public JSONResult addCatalogPropertyValue(CatalogPropertyValue catalogPropertyValue){
		JSONResult jSONResult = new JSONResult();
		try {
			
			catalogPropertyValue.setSequence(0);
			catalogPropertyService.insertPropertyValue(catalogPropertyValue);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jSONResult.setErrorMsg(e.getMessage());
		}
		return jSONResult;
		
	}
	
	/**
	 * 新增分类属性
	 * @param catalogProperty
	 * @return
	 */
	@RequestMapping("/addCatalogProperty.do")
	@ResponseBody
	public JSONResult addCatalogProperty(CatalogProperty catalogProperty){
		JSONResult jSONResult = new JSONResult();
		try {
			if(catalogProperty.getId() == null || catalogProperty.getId() == -1){
				
				catalogProperty.setSequence(0);
				catalogPropertyService.insert(catalogProperty);
			}else{
				CatalogProperty catalogPropertyDb = catalogPropertyService.selectByPrimaryKey(catalogProperty.getId());
				catalogPropertyDb.setName(catalogProperty.getName());
				catalogPropertyDb.setType(catalogProperty.getType());
				catalogPropertyService.updateByPrimaryKey(catalogPropertyDb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jSONResult.setErrorMsg(e.getMessage());
		}
		return jSONResult;
		
	}
	
	@RequestMapping("/toPropertySave.do")
	public String toPropertySave(Model model,CatalogProperty catalogProperty){
		if(catalogProperty.getId() != null && catalogProperty.getId() != -1){
			
			catalogProperty = catalogPropertyService.selectByPrimaryKey(catalogProperty.getId());
		}
		model.addAttribute("catalogProperty", catalogProperty);
		return "property/property_save";
	}
	
	@RequestMapping("/deleteCatalogProperty.do")
	public String deleteCatalogProperty(CatalogProperty catalogProperty){
		catalogPropertyService.deleteByPrimaryKey(catalogProperty.getId());
		
		return "redirect:/getCatalogProperty.do?catalogId="+catalogProperty.getCatalogId();
	}
}






