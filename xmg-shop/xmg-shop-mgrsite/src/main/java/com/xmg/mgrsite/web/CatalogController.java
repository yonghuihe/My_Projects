package com.xmg.mgrsite.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.api.domain.Catalog;
import com.xmg.api.service.ICatalogPropertyService;
import com.xmg.api.service.ICatalogService;
import com.xmg.api.vo.JSONResult;

@Controller
public class CatalogController {

	@Autowired
	ICatalogService catalogService;
	
	@Autowired
	ICatalogPropertyService catalogPropertyService;
	
	/**
	 * 进入分类管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/catalog.do")
	public String catalog(Model model){
		
		return "catalog/catalog";
	}
	
	/**
	 * 通过分类ID获取子分类（用于分类树显示）
	 * @param id
	 * @return
	 */
	@RequestMapping("/getCatalog.do")
	@ResponseBody
	public List<Catalog> getCatalog(Long id){
		List<Catalog> list = catalogService.getChildenCatalogById(id);
		return list;
	}
	
	/**
	 * 保存分类
	 * @param catalog
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/catalogSave.do")
	public JSONResult catalogSave(Catalog catalog){
		JSONResult jsonResult = new JSONResult();
		try {
			if(catalog.getId() == null){
				
				catalogService.insert(catalog);
			}
			else{
				Catalog catalogDb = catalogService.selectByPrimaryKey(catalog.getId());
				catalogDb.setCode(catalog.getCode());
				catalogDb.setName(catalog.getName());
				catalogDb.setLastModifiedDate(new Date());
				catalogService.updateByPrimaryKey(catalogDb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonResult.setErrorMsg(e.getMessage());
		}
		return jsonResult;
		
	}
	
	/**
	 * 返回分类编辑面板
	 * @param catalogId
	 * @param model
	 * @return
	 */
	@RequestMapping("/catalogInput.do")
	public String catalogInput(Long catalogId,Model model){
		Catalog catalog = catalogService.selectByPrimaryKey(catalogId);
		model.addAttribute("catalog", catalog);
		return "catalog/catalog_input";
	}
	
	@RequestMapping("/deleteCatalog.do")
	@ResponseBody
	public JSONResult deleteCatalog(Long catalogId){
		JSONResult jsonResult = new JSONResult();
		try {
			catalogService.deleteByPrimaryKey(catalogId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonResult.setErrorMsg(e.getMessage());
		}
		return jsonResult;
		
	}
}






