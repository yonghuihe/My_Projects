package com.company.pss.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.company.pss.util.FileUploadUtil;

public class Product {

	private Long id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品编码
	 */
	private String sn;
	/**
	 * 成本价
	 */
	private BigDecimal costPrice;
	/**
	 * 销售价
	 */
	private BigDecimal salePrice;
	/**
	 * 图片地址
	 */
	private String imagePath;
	/**
	 * 商品介绍
	 */
	private String intro;
	/**
	 * 品牌
	 */
	private Brand brand;

	public String getSmallImagePath() {
		String smallImagePath = imagePath.substring(0, imagePath.lastIndexOf(".")) + FileUploadUtil.suffix
				+ imagePath.substring(imagePath.lastIndexOf("."));
		return smallImagePath;
	}

	public String getProductJSONStr() {
		// 将数据封装成map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("name", this.name);
		map.put("brandName", brand.getName());
		map.put("costPrice", this.costPrice);
		map.put("salePrice", this.salePrice);

		String jsonString = JSON.toJSONString(map);
		return jsonString;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}
