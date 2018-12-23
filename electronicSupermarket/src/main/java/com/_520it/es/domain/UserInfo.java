package com._520it.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by dell on 2017/7/14.
 */
@Setter@Getter@ToString
public class UserInfo {
    private Long id;
    private Long openid;
    private String nickname;
    private Integer sex;
    private String province;
    private String country;
    private String headimgurl;
    private String privilege;
    private String unionid;
}
