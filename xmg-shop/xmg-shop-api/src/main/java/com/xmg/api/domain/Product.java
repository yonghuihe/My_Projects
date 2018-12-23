package com.xmg.api.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Product extends BaseDomain{

    private Date createdDate = new Date();

    private Date lastModifiedDate = new Date();

    private Integer version = 0;

    private Catalog catalog;

    private Brand brand;

    private String name;

    private String keyword;

    private String code;

    private String image;

    private BigDecimal marketPrice;

    private BigDecimal basePrice;

    private Long mods;

    private String impressions;
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Long getMods() {
        return mods;
    }

    public void setMods(Long mods) {
        this.mods = mods;
    }
    
    
    
    public String getImpressions() {
		return impressions;
	}

	public void setImpressions(String impressions) {
		this.impressions = impressions;
	}

	public String getCatalogName(){
		if(this.catalog == null){
			return "";
		}
    	return this.catalog.getName();
    }
    public String getBrandName(){
    	if(this.brand == null){
    		return "";
    	}
    	return this.brand.getChineseName();
    }
}








