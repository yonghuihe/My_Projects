package com.company.crm.util;

import java.util.Iterator;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.company.crm.domain.Menu;

public class MenuUtil {
	public static final String MENU_IN_SESSION = "MENU_IN_SESSION";
	
	public static void checkPermission(List<Menu> menus) {
		Subject subject = SecurityUtils.getSubject();
		//迭代取出每一个菜单，使用Iterator迭代器
		Iterator<Menu> iterator = menus.iterator();
		while(iterator.hasNext()){
			Menu menu = iterator.next();
			// 判断该菜单是否有权限对象，如果有权限对象需要进行验证，父菜单没有关联权限
			if(menu.getPermission() != null){
				//判断该员工是是否有该权限,如果没有移出掉
				if(!subject.isPermitted(menu.getPermission().getResource())){
					iterator.remove();
				}
			}
			//过滤子菜单,如果有子菜单，需要进行过滤
			if(menu.getChildren()!=null){
				checkPermission(menu.getChildren());
			}
		}
		
	}

}
