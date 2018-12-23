package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogProperty extends BaseDomain {

    private Long catalogId;

    private String name;

    private Integer sequence;

    private Byte type;

    public String getPropertyType() {
        String propertyType = "输入框";
        switch (this.type) {
            case 0:
                propertyType = "输入框";
                break;
            case 1:
                propertyType = "复选框";
                break;
            case 2:
                propertyType = "下拉列表";
                break;

            default:
                propertyType = "输入框";
                break;
        }
        return propertyType;
    }
}






