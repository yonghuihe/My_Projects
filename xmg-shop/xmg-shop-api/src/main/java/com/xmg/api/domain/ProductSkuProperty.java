package com.xmg.api.domain;

public class ProductSkuProperty extends BaseDomain{

    private Long productSkuId;

    private SkuProperty skuProperty;

    private String value;

    private String image;

   

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

   

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}