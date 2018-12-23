package com._520it.es.domain;

import com._520it.es.genertor.ObjectProp;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ydm on 2017/7/15.
 */
@Setter
@Getter
public class OrderBill {
    private Long id;
    @ObjectProp("订单编号")
    private String sn;
    @ObjectProp("状态")
    private Integer status;  // 0 待付款 1.待发货 2. 已发货 3. 交易完成
    @ObjectProp("件数")
    private Long totalNumber;
    @ObjectProp("单价")
    private Long price;
    @ObjectProp("订单金额")
    private BigDecimal totalAmount;
    @ObjectProp("订单时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date vdate;
    @ObjectProp("货品名称")
    private Product product;
    @ObjectProp("收货人")
    private UserInfo user ;
}
