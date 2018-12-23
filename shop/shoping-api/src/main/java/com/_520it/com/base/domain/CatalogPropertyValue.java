package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogPropertyValue extends BaseDomain {

    private CatalogProperty catalogProperty;

    private String value;

    private Integer sequence;
}