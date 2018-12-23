package com._520it.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Rory Leo on 2017/7/14.
 */
@Getter
@Setter
@ToString
public class MenuManage {
    private Long id;
    private String name;
    private String type;
    private String link;
    private MenuManage parent;

}
