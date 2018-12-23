package com.xmg.minipss.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import com.xmg.minipss.util.FileUploadUtil;

@Getter
@Setter
public class Product extends BaseDomain {

	private String name;
	private String sn;
	private String pic;
	private BigDecimal costPrice;
	private BigDecimal salePrice;
	private String intro;
	private Brand brand;

	public String getSmallPic() {
		return pic.substring(0, pic.lastIndexOf(".")) + FileUploadUtil.SUFFIX
				+ pic.substring(pic.lastIndexOf("."));
	}
}
