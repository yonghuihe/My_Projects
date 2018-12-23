package com._520it.es.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ydm on 2017/7/4.
 */
@Getter
@Setter
public class QueryObject {
    private Integer page =1;
    private Integer rows =3;
    public  Integer getStart(){
        return (page-1)*rows;
    }
}
