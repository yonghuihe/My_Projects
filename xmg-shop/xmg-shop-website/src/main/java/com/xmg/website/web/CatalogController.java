package com.xmg.website.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.api.domain.Catalog;
import com.xmg.api.service.ICatalogService;

@Controller
public class CatalogController {

	@Autowired
	ICatalogService catalogService;
	
	@RequestMapping("/category")
	@ResponseBody
	public CatalogBean getCatalog(Long parentId){
		List<Catalog> list = catalogService.getChildenCatalogById(parentId);
		CatalogBean catalogBean = new CatalogBean();
		catalogBean.setResult(list);
		return catalogBean;
	}
}

class CatalogBean{
	boolean success = true;
	String errorMsg = "";
	List<Catalog> result;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public List<Catalog> getResult() {
		return result;
	}
	public void setResult(List<Catalog> result) {
		this.result = result;
	}
	
	
}




















