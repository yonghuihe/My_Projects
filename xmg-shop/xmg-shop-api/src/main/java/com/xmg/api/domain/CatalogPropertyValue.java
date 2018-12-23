package com.xmg.api.domain;


public class CatalogPropertyValue extends BaseDomain{

    private CatalogProperty catalogProperty;

    private String value;

    private Integer sequence;

   

    public CatalogProperty getCatalogProperty() {
		return catalogProperty;
	}

	public void setCatalogProperty(CatalogProperty catalogProperty) {
		this.catalogProperty = catalogProperty;
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