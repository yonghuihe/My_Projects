package com._520it.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by dell on 2017/7/15.
 */
@Setter@Getter@ToString
public class KeywordReply {
    private Long id;
    private String keywords;//关键字
    private String type;//类型
    private String responseContent;//回复内容
}
