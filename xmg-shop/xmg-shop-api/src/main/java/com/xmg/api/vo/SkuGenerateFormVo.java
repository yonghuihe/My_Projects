package com.xmg.api.vo;

import java.io.Serializable;
import java.util.List;

import com.xmg.api.domain.SkuProperty;
import com.xmg.api.domain.SkuPropertyValue;


public class SkuGenerateFormVo  implements Serializable{
	
	private Long catalogId;
    private List<SkuProperty> skuPropertieList;
    private List<SkuPropertyValue> skuPropertyValueList;
	public List<SkuProperty> getSkuPropertieList() {
		return skuPropertieList;
	}
	public void setSkuPropertieList(List<SkuProperty> skuPropertieList) {
		this.skuPropertieList = skuPropertieList;
	}
	public List<SkuPropertyValue> getSkuPropertyValueList() {
		return skuPropertyValueList;
	}
	public void setSkuPropertyValueList(List<SkuPropertyValue> skuPropertyValueList) {
		this.skuPropertyValueList = skuPropertyValueList;
	}
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	
	
    
}
