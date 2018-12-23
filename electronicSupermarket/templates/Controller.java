package ${packageName}.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com._520it.crm.util.JsonResult;

import ${packageName}.domain.${className};
import ${packageName}.util.JsonResult;
import ${packageName}.page.PageResult;
import ${packageName}.query.${className}QueryObject;
import ${packageName}.service.I${className}Service;

@Controller
public class ${className}Controller {
	@Autowired
	I${className}Service ${objectName}Service;
	
	@RequestMapping("/${objectName}")
	public String index(){
		return "${objectName}";
	}
	@RequestMapping("/${objectName}_list")
	@ResponseBody
	public PageResult list(${className}QueryObject qo){
		PageResult pageResult = null;
		pageResult = ${objectName}Service.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/${objectName}_save")
	@ResponseBody
	public JsonResult save(${className} ${objectName}){
		JsonResult result = null;
		try{
			${objectName}Service.insert(${objectName});
			result = new JsonResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/${objectName}_update")
	@ResponseBody
	public JsonResult update(${className} ${objectName}){
		JsonResult result = null;
		try{
			${objectName}Service.updateByPrimaryKey(${objectName});
			result = new JsonResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/${objectName}_delete")
	@ResponseBody
	public JsonResult delete(Long ${objectName}Id){
		JsonResult result = null;
		try{
			${objectName}Service.deleteByPrimaryKey(${objectName}Id);
			result = new JsonResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new JsonResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
