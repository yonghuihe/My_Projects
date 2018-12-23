package com._520it.es.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by ydm on 2017/7/4.
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class PageResult {
    private Long     total;
    private List<?>  rows;
}
