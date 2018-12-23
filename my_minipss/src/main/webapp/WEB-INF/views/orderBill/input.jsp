<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>信息管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/js/system/orderBill.js"></script>
	<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<s:debug></s:debug>
	<%@include file="../common/common_msg.jsp" %>
	<s:form name="editForm" action="orderBill_save" method="post" id="editForm">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">采购订单编辑</span>
				<div id="page_close">
					<a>
						<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
					<s:hidden name="orderBill.id" />
					<tr>
						<td class="ui_text_rt" width="140">订单编号</td>
						<td class="ui_text_lt">
							<s:textfield name="orderBill.sn" cssClass="ui_input_txt02"/>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">供应商</td>
						<td class="ui_text_lt">
							<s:select list="#suppliers" name="orderBill.supplier.id" listKey="id" listValue="name" cssClass="ui_input_txt04"/>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">业务时间</td>
						<td class="ui_text_lt">
							<s:date name="orderBill.vdate" format="yyyy-MM-dd" var="datefmt" />
							<s:textfield name="orderBill.vdate" value="%{#datefmt}" cssClass="ui_input_txt02 Wdate" onclick="WdatePicker();"/>
						</td>
					</tr>	
					<tr>
	                    <td class="ui_text_rt" width="140">明细</td>
	                </tr>
	                <tr>
	                    <td></td>
	                    <td>
	                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
	                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
	                            <thead>
	                            <tr>
	                                <th width="10"></th>
	                                <th width="200">货品</th>
	                                <th width="120">品牌</th>
	                                <th width="80">价格</th>
	                                <th width="80">数量</th>
	                                <th width="80">金额小计</th>
	                                <th width="150">备注</th>
	                                <th width="60"></th>
	                            </tr>
	                            </thead>
	                            <tbody id="edit_table_body">
	                            	<s:if test="orderBill.id == null">
		                                <tr>
		                                    <td></td>
		                                    <td>
		                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" tag="name"/>
		                                        <img src="/images/common/search.png" class="searchproduct"/>
		                                        <s:hidden name="orderBill.orderBillItem[0].product.id" tag="pid"/>
		                                    </td>
		                                    <td>
		                                    	<span tag="brand"/>
		                                    </td>
		                                    <td>
		                                    	<s:textfield tag="costPrice" name="orderBill.orderBillItem[0].costPrice" cssClass="ui_input_txt02"/>
		                                    </td>
		                                    <td>
		                                    	<s:textfield tag="number" name="orderBill.orderBillItem[0].number" cssClass="ui_input_txt02"/>
		                                    </td>
		                                    <td>
		                                    	<span tag="amount"/>
		                                    </td>
		                                    <td>
		                                    	<s:textfield tag="remark" name="orderBill.orderBillItem[0].remark" cssClass="ui_input_txt02"/>
		                                    </td>
		                                    <td>
		                                        <a href="javascript:;" class="removeItem">删除明细</a>
		                                    </td>
		                                </tr>
	                            	</s:if>
	                            	<s:else>
	                            		<s:iterator value="orderBill.orderBillItem">
	                            			<tr>
		                                    <td></td>
		                                    <td>
		                                        <s:textfield disabled="true" name="product.name" readonly="true" cssClass="ui_input_txt02" tag="name"/>
		                                        <img src="/images/common/search.png" class="searchproduct"/>
		                                        <s:hidden name="product.id" tag="pid"/>
		                                    </td>
		                                    <td>
		                                    	<span tag="brand"><s:property value="product.brand.name"/> </span>
		                                    </td>
		                                    <td>
		                                    	<s:textfield tag="costPrice" name="costPrice" cssClass="ui_input_txt02"/>
		                                    </td>
		                                    <td>
		                                    	<s:textfield tag="number" name="number" cssClass="ui_input_txt02"/>
		                                    </td>
		                                    <td>
		                                    	<span tag="amount"><s:property value="amount"/> </span>
		                                    </td>
		                                    <td>
		                                    	<s:textfield tag="remark" name="remark" cssClass="ui_input_txt02"/>
		                                    </td>
		                                    <td>
		                                        <a href="javascript:;" class="removeItem">删除明细</a>
		                                    </td>
		                                </tr>
	                            		</s:iterator>
	                            	</s:else>
	                            </tbody>
	                        </table>
	                    </td>
	                </tr>
					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">
							&nbsp;<input type="button" value="确定编辑" class="ui_input_btn01 btn_submit"/>
							&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>