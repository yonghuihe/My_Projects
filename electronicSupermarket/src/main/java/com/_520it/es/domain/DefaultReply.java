package com._520it.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by dell on 2017/7/15.
 */
@Setter@Getter@ToString
public class DefaultReply {
    private Long id;
    private String defaultReply;//默认回复
    private String attentionReply;//关注回复
}
