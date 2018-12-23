package com.xmg.api.domain;

public class SkuProperty extends BaseDomain{

    private String name;

    private Long catalogId;

    private Byte type;

    private Integer sequence;

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    public String getPropertyType(){
    	String propertyType = "输入框";
    	switch (this.type) {
		case 0:
			propertyType = "输入框";
			break;
		case 1:
			propertyType = "复选框";
			break;
		case 2:
			propertyType = "下拉列表";
			break;

		default:
			propertyType = "输入框";
			break;
		}
    	return propertyType;
    }
}