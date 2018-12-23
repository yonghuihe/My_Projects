package com._520it.com.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SkuPropertyController {
    /**
     * 进入分类属性管理界面
     * @param model
     * @return
     */
    @RequestMapping("skuProperty.do")
    public String skuProperty(Model model){

        return "sku/property";
    }
}
