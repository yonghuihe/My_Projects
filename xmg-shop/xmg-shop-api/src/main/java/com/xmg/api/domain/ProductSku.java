package com.xmg.api.domain;

import java.math.BigDecimal;
import java.util.List;

public class ProductSku extends BaseDomain{

    private Product product;

    private String code;

    private BigDecimal price;

    private Long mods;
    
    private List<ProductSkuProperty> productSkuPropertyList;

   
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ProductSkuProperty> getProductSkuPropertyList() {
		return productSkuPropertyList;
	}

	public void setProductSkuPropertyList(
			List<ProductSkuProperty> productSkuPropertyList) {
		this.productSkuPropertyList = productSkuPropertyList;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getMods() {
        return mods;
    }

    public void setMods(Long mods) {
        this.mods = mods;
    }
}