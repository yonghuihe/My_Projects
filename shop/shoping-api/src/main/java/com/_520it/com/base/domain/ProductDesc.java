package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by dell on 2017/8/10.
 */
@Setter@Getter@ToString
public class ProductDesc extends BaseDomain {
    private String details;
    private Long productId;
}
