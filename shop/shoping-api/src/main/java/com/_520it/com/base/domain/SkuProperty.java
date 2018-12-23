package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SkuProperty extends BaseDomain{
    private String name;
    private Long catalogId;
    private Byte type;
    private Integer sequence;
}