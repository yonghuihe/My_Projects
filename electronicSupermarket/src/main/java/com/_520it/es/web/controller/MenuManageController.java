package com._520it.es.web.controller;

import com._520it.es.domain.MenuManage;
import com._520it.es.page.PageResult;
import com._520it.es.query.MenuManageQueryObject;
import com._520it.es.service.IMenuManageService;
import com._520it.es.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuManageController {
    @Autowired
    IMenuManageService menuManageService;

    @RequestMapping("/menuManage")
    public String index() {
        return "menuManage";
    }

    @RequestMapping("/menuManage_list")
    @ResponseBody
    public PageResult list(MenuManageQueryObject qo) {
        PageResult pageResult = null;
        pageResult = menuManageService.queryByConditionPage(qo);
        return pageResult;
    }

    @RequestMapping("/menuManage_listParent")
    @ResponseBody
    public List<MenuManage> listParent() {
        List<MenuManage> list = menuManageService.listParent();
        return list;
    }
    @RequestMapping("/menuManage_listButton")
    @ResponseBody
    public List<MenuManage> listButton() {
        List<MenuManage> list = menuManageService.listButton();
        return list;
    }

    @RequestMapping("/menuManage_save")
    @ResponseBody
    public JsonResult save(MenuManage menuManage) {
        JsonResult result = null;
        try {
            menuManageService.insert(menuManage);
            result = new JsonResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/menuManage_update")
    @ResponseBody
    public JsonResult update(MenuManage menuManage) {
        JsonResult result = null;
        try {
            menuManageService.updateByPrimaryKey(menuManage);
            result = new JsonResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/menuManage_delete")
    @ResponseBody
    public JsonResult delete(Long menuManageId) {
        JsonResult result = null;
        try {
            menuManageService.deleteByPrimaryKey(menuManageId);
            result = new JsonResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("删除失败,请联系管理员！");
        }
        return result;
    }
    @RequestMapping("/menuManage_reloadMenu")
    @ResponseBody
    public JsonResult reloadMenu() {
        JsonResult result = null;
        try {
            menuManageService.reloadMenu();
            result = new JsonResult(true, "加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("加载失败,请联系管理员！");
        }
        return result;
    }
}
