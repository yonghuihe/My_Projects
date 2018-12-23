package com._520it.es.service.impl;

import com._520it.es.domain.MenuManage;
import com._520it.es.domain.clickButton;
import com._520it.es.domain.viewButton;
import com._520it.es.mapper.MenuManageMapper;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;
import com._520it.es.service.IMenuManageService;
import com._520it.es.util.HttpUtil;
import com._520it.es.util.WeiXinUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MenuManageServiceImpl implements IMenuManageService {
    @Autowired
    private MenuManageMapper menuManageMapper;

    public int deleteByPrimaryKey(Long id) {
        return menuManageMapper.deleteByPrimaryKey(id);
    }

    public int insert(MenuManage record) {
        return menuManageMapper.insert(record);
    }

    public MenuManage selectByPrimaryKey(Long id) {
        return menuManageMapper.selectByPrimaryKey(id);
    }

    public List<MenuManage> selectAll() {
        return menuManageMapper.selectAll();
    }

    public int updateByPrimaryKey(MenuManage record) {
        return menuManageMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryByConditionPage(QueryObject qo) {
        Long count = menuManageMapper.queryByCondictionCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<MenuManage> result = menuManageMapper.queryByConditionResult(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public List<MenuManage> listParent() {
        return menuManageMapper.listParent();
    }

    @Override
    public List<MenuManage> listButton() {
        return menuManageMapper.listButton();
    }

    @Override
    public void reloadMenu() {
        List<MenuManage> supmenu = listParent();//获取一级菜单信息
        List json = new ArrayList();//收集封装完成的数据,可以直接转换为JSON的
        for (MenuManage menu : supmenu) {
            Map<String, Object> map = new HashMap<>();//收集一级菜单
            List list = new ArrayList();//收集二级菜单
            List<MenuManage> menuManageList = menuManageMapper.listByParentId(menu.getId());//根据一级菜单id获取其子菜单
            //判断是否存在二级子菜单
            if (menuManageList.size() > 0) {
                for (MenuManage submenu : menuManageList) {
                    if ("view".equals(submenu.getType())) {
                        viewButton vb = new viewButton();
                        vb.setType("view");
                        vb.setName(submenu.getName());
                        vb.setUrl(submenu.getLink());
                        list.add(vb);
                    } else {
                        clickButton cb = new clickButton();
                        cb.setType("click");
                        cb.setName(submenu.getName());
                        cb.setKey(submenu.getLink());
                        list.add(cb);
                    }
                    map.put("name", menu.getName());
                    map.put("sub_button", list);
                }
                json.add(map);
            } else {//不存在二级子菜单
                map.put("type", menu.getType());
                map.put("name", menu.getName());
                if ("view".equals(menu.getType())) {
                    map.put("url", menu.getLink());
                } else {
                    map.put("key", menu.getLink());
                }
                json.add(map);
            }
            System.out.println("阶段:" + map);
        }
        String js = JSON.toJSONString(json);
        System.out.println("最终JSON:" + "{\"button\":" + js + "}");
        String post = HttpUtil.post(WeiXinUtil.CREATE_MENU_URL.replace("ACCESS_TOKEN", WeiXinUtil.getAccessToken()), "{\"button\":" + js + "}");
        System.out.println(post);
    }
}