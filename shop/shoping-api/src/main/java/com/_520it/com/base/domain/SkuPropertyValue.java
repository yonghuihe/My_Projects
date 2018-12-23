package com._520it.com.base.domain;

public class SkuPropertyValue extends BaseDomain{
    private SkuProperty skuProperty;
    private String value;
    private Integer sequence;
    public SkuProperty getSkuProperty() {
		return skuProperty;
	}

	public void setSkuProperty(SkuProperty skuProperty) {
		this.skuProperty = skuProperty;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}