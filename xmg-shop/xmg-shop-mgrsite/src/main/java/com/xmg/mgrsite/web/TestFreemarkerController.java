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
public class TestFreemarkerController {

	
	@RequestMapping("/testFreemarker.do")
	public String deleteSkuProperty(SkuProperty skuProperty){
		
		return "test_freemarker";
	}
}
