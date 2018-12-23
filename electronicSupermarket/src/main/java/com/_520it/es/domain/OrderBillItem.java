package com._520it.es.domain;

import com._520it.es.genertor.ObjectProp;

import java.math.BigDecimal;

/**
 * Created by ydm on 2017/7/15.
 */
public class OrderBillItem {
    private Long id;
    @ObjectProp("商品名称")
    private Product product;
    @ObjectProp("成本价")
    private BigDecimal costPrice;
    @ObjectProp("数量")
    private Long number;
    @ObjectProp("金额小计")
    private BigDecimal amount;
    @ObjectProp("备注")
    private String remark;
    @ObjectProp("采购订单")
    private OrderBill bill;
}
