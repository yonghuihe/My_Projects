package ${basePackage}.mvc;

import ${basePackage}.domain.${className};
import ${basePackage}.service.I${className}Service;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.util.RequiredPermission;

public class ${className}Action extends BaseAction {

	private I${className}Service ${className?uncap_first}Service;
	private QueryObject qo = new QueryObject();
	private ${className} ${className?uncap_first} = new ${className}();

	@RequiredPermission("${className}列表")
	public String execute() {
		PageResult pr = this.${className?uncap_first}Service.query(qo);
		this.addContext(PAGERESULT, this.${className?uncap_first}Service.query(qo));
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
			this.addActionMessage("修改成功");
		} else {
			this.${className?uncap_first}Service.save(${className?uncap_first});
			this.addActionMessage("保存成功");
		}
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(${className?uncap_first}.getId()!=null){
			${className?uncap_first} = this.${className?uncap_first}Service.get(${className?uncap_first}.getId());
		}
	}

	@RequiredPermission("删除${className}")
	public String delete() {
		if (${className?uncap_first}.getId() != null) {
			this.${className?uncap_first}Service.delete(${className?uncap_first});
			this.addActionMessage("删除成功");
		}
		return SUCCESS;
	}

	public QueryObject getQo() {
		return qo;
	}

	public void setQo(QueryObject qo) {
		this.qo = qo;
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
