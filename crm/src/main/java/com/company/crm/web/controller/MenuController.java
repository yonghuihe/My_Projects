package com.company.crm.web.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.crm.domain.Menu;
import com.company.crm.page.PageResult;
import com.company.crm.query.MenuQueryObject;
import com.company.crm.service.IMenuService;
import com.company.crm.util.AjaxResult;
import com.company.crm.util.MenuUtil;

@SuppressWarnings("all")
@Controller
public class MenuController {
	@Autowired
	IMenuService menuService;
	
	@RequestMapping("/menu")
	public String index(){
		return "menu/menu";
	}
	@RequestMapping("/menu_list")
	@ResponseBody
	public PageResult list(MenuQueryObject qo){
		PageResult pageResult = null;
		pageResult = menuService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/menu_listAll")
	@ResponseBody
	public List<Menu> listAll(){
		List<Menu> list = menuService.selectAll();
		return list; 
	}
	@RequestMapping("/menu_loadTree")
	@ResponseBody
	public List<Menu> loadTree(){
		//从缓存中查询菜单
		List<Menu> menus = (List<Menu>) SecurityUtils.getSubject().getSession().getAttribute(MenuUtil.MENU_IN_SESSION);
//		List<Menu> menus = menuService.selectRoot();
		return menus; 
	}
	@RequestMapping("/menu_save")
	@ResponseBody
	public AjaxResult save(Menu menu){
		AjaxResult result = null;
		try{
			menuService.insert(menu);
			result = new AjaxResult("保存成功",true);
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！",false);
		}
		return result;
	}
	@RequestMapping("/menu_update")
	@ResponseBody
	public AjaxResult update(Menu menu){
		AjaxResult result = null;
		try{
			menuService.updateByPrimaryKey(menu);
			result = new AjaxResult("更新成功",true);
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！",false);
		}
		return result;
	}
	@RequestMapping("/menu_delete")
	@ResponseBody
	public AjaxResult delete(Long menuId){
		AjaxResult result = null;
		try{
			menuService.deleteByPrimaryKey(menuId);
			result = new AjaxResult("删除成功",true);
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！",false);
		}
		return result;
	}
}
