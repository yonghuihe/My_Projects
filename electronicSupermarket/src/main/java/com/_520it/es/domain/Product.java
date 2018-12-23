package com._520it.es.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by dell on 2017/7/15.
 */
@Setter@Getter
public class Product {
    private Long id;
    private Long dept_id;
    private String productName;
    private BigDecimal price;
    private String  depict;
}
