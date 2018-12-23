package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by dell on 2017/8/7.
 */
@Setter@Getter
public class Brand extends BaseDomain {
    private Date createdDate;
    private Date lastModifiedDate;
    private Integer version;
    private Date foundDate;
    private String logo;
    private String chineseName;
    private String englishName;
    private String description;
    private String url;
    private Integer sequence;
    private Long mods;
}
