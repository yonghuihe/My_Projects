package com._520it.es.domain;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Article {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;
}
