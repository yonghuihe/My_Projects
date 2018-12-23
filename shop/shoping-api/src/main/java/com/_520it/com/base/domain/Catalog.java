package com._520it.com.base.domain;

import java.util.Date;

public class Catalog extends BaseDomain{

    private Date createDate;

    private Date lastModifiedDate;

    private Integer version;

    private Integer level;

    private String name;

    private String code;

    private Integer sequence;

    private Integer childrenCatalogs;

    private Integer products;

    private Long parentCatalogId;
    
    private Byte isParent;


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getChildrenCatalogs() {
        return childrenCatalogs;
    }

    public void setChildrenCatalogs(Integer childrenCatalogs) {
        this.childrenCatalogs = childrenCatalogs;
    }

    public Integer getProducts() {
        return products;
    }

    public void setProducts(Integer products) {
        this.products = products;
    }

    public Long getParentCatalogId() {
        return parentCatalogId;
    }

    public void setParentCatalogId(Long parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
    }

	public Byte getIsParent() {
		return isParent;
	}

	public void setIsParent(Byte isParent) {
		this.isParent = isParent;
	}
    
    
}




