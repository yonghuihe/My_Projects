package com.company.crm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.company.crm.domain.Menu;

@Component
public class PermissionUtil {
	
	/*private static IPermissionService permissionService;
	
	@Autowired
	public void setPermissionService(IPermissionService permissionService) {
		PermissionUtil.permissionService = permissionService;
	}*/

	//存储数据库中所有权限的集合(只有存在这个集合中就是需要进行权限验证)
	public static List<String> allPermissions = new ArrayList<>();
	
	/**
	 * 权限判断方法
	 * @param function
	 * @return
	 */
	public static boolean checkPermission(String function){
		/*//管理员直接放行
		Object object = UserContext.getRequest().getSession().getAttribute(UserContext.USERINSSESION);
		if(object!=null){
			Employee e = (Employee)object;
			if(e.getAdmin()){
				return true;
			}
		}
		
		//如果allPermissions集合的长度为0,就去查询数据库
		if(allPermissions.size()==0){
			List<Permission> list = permissionService.selectAll();
			for (Permission permission : list) {
				allPermissions.add(permission.getResource());
			}
		}
		//判断function是否存在数据库中(是否存在集合中),如果是就进行权限验证,否则直接放行
		if(allPermissions.contains(function)){
			List<String> selfPermissions = (List<String>) UserContext.getRequest().getSession().getAttribute(UserContext.PERMISSIONINSSESION);
			//权限验证过程
			//1.完全匹配
			if(!selfPermissions.contains(function)){
				//2.进行All匹配
				//com._520it.crm.web.controller.EmployeeController:ALL
				String allFunction = function.split(":")[0]+":"+"ALL";
				return selfPermissions.contains(allFunction);
			}
		}
		//如果该权限表达式不存在数据库中,则放行
*/		return true;
	}
	
	/**
	 * 菜单过滤
	 * @param menus
	 */
	public static void checkMenuPermission(List<Menu> menus){
		/*//遍历菜单集合,获取到每个菜单对象
		Iterator<Menu> iterator = menus.iterator();
		while(iterator.hasNext()){
			Menu menu = iterator.next();
			//判断该菜单是否关系着权限对象,如果关联着就需要进行权限验证,否则不验证
			if(menu.getPermission()!=null){
				boolean result = checkPermission(menu.getPermission().getResource());
				if(!result){
					//如果没有权限就把菜单从集合中移除掉
					iterator.remove();
					continue;
				}
			}
			//判断是否有子菜单,有则进行递归操作
			if(menu.getChildren().size()>0){
				checkMenuPermission(menu.getChildren());
			}
		}*/
	}
	
}
