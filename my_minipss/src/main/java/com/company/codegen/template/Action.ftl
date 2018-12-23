package ${basePackage}.mvc;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import ${basePackage}.domain.${className};
import ${basePackage}.service.I${className}Service;
import com.company.pss.query.QueryObject;
import com.company.pss.util.RequiredPermission;

public class ${className}Action extends BaseAction {

	private I${className}Service ${className?uncap_first}Service;
	private QueryObject queryObject = new QueryObject();
	private ${className} ${className?uncap_first} = new ${className}();

	@RequiredPermission("${className}列表")
	public String execute() {
		this.addContext(PAGERESULT, this.${className?uncap_first}Service.query(queryObject));
		return LIST;
	}

	@RequiredPermission("新增/编辑${className}")
	public String input() {
		if (${className?uncap_first}.getId() != null) {
			${className?uncap_first} = this.${className?uncap_first}Service.get(${className?uncap_first}.getId());
		}
		return INPUT;
	}

	@RequiredPermission("修改${className}")
	public String save() {
		if (${className?uncap_first}.getId() != null) {
			this.${className?uncap_first}Service.update(${className?uncap_first});
			this.addActionMessage("修改成功!");
		} else {
			this.${className?uncap_first}Service.save(${className?uncap_first});
			this.addActionMessage("保存成功!");
		}
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(${className?uncap_first}.getId()!=null){
			${className?uncap_first} = this.${className?uncap_first}Service.get(${className?uncap_first}.getId());
		}
	}

	@RequiredPermission("删除${className}")
	public String delete() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (${className?uncap_first}.getId() != null) {
				this.${className?uncap_first}Service.delete(${className?uncap_first});
				response.getWriter().write("删除成功！");
			}
		} catch (Exception e){
			response.getWriter().write("删除失败，请联系管理员！"+e.getMessage());
		}
		return NONE;
	}

	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(QueryObject queryObject) {
		this.queryObject = queryObject;
	}

	public ${className} get${className}() {
		return ${className?uncap_first};
	}

	public void set${className}(${className} ${className?uncap_first}) {
		this.${className?uncap_first} = ${className?uncap_first};
	}

	public void set${className}Service(I${className}Service ${className?uncap_first}Service) {
		this.${className?uncap_first}Service = ${className?uncap_first}Service;
	}

}
