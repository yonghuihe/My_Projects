package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductImage extends BaseDomain{
    private Long productId;
    private String imagePath;
    private Integer sequence;
    private Long mods;
    private Byte cover;
}