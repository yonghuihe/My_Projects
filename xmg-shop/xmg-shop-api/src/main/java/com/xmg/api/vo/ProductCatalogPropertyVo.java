package com.xmg.api.vo;

import java.io.Serializable;
import java.util.List;

import com.xmg.api.domain.CatalogPropertyValue;


public class ProductCatalogPropertyVo  implements Serializable{

	private String catalogPropertyName;
	private Byte type;
	private List<CatalogPropertyValue> propertyValueList;
	public String getCatalogPropertyName() {
		return catalogPropertyName;
	}
	public void setCatalogPropertyName(String catalogPropertyName) {
		this.catalogPropertyName = catalogPropertyName;
	}
	
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public List<CatalogPropertyValue> getPropertyValueList() {
		return propertyValueList;
	}
	public void setPropertyValueList(List<CatalogPropertyValue> propertyValueList) {
		this.propertyValueList = propertyValueList;
	}
	
	
}
