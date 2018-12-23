package com._520it.es.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ydm on 2017/7/14.
 */
@Controller
public class backStageManagement {

    @RequestMapping("/back")
    public String back(){

       return "back";
    }
}
