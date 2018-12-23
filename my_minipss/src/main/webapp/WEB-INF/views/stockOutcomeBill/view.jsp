<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>信息管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form name="editForm" action="" method="post" id="editForm">
		<div id="container">
			<div id="nav_links">
				<div id="page_close">
					<a>
						<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
					<tr>
						<td class="ui_text_rt" width="140">订单编号</td>
						<td class="ui_text_lt">
							<s:textfield disabled="true" name="stockOutcomeBill.sn" cssClass="ui_input_txt02"/>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">仓库</td>
						<td class="ui_text_lt">
							<s:select list="#depots" disabled="true" name="stockOutcomeBill.depot.id" listKey="id" listValue="name" cssClass="ui_input_txt04"/>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">客户</td>
						<td class="ui_text_lt">
							<s:select list="#clients" disabled="true" name="stockOutcomeBill.client.id" listKey="id" listValue="name" cssClass="ui_input_txt04"/>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">业务时间</td>
						<td class="ui_text_lt">
							<s:date name="stockOutcomeBill.vdate" format="yyyy-MM-dd" var="datefmt"/>
							<s:textfield disabled="true" name="stockOutcomeBill.vdate" value="%{#datefmt}" cssClass="ui_input_txt02"/>
						</td>
					</tr>	
					<tr>
	                    <td class="ui_text_rt" width="140">明细</td>
	                </tr>
	                <tr>
	                    <td></td>
	                    <td>
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
	                            </tr>
	                            </thead>
	                            <tbody id="edit_table_body">
                            		<s:iterator value="stockOutcomeBill.stockOutcomeBillItem">
                            			<tr>
	                                    <td></td>
	                                    <td>
	                                        <s:textfield disabled="true" name="product.name" readonly="true" cssClass="ui_input_txt02" tag="name"/>
	                                    </td>
	                                    <td>
	                                    	<span tag="brand"><s:property value="product.brand.name"/> </span>
	                                    </td>
	                                    <td>
	                                    	<s:textfield disabled="true"  tag="salePrice" name="salePrice" cssClass="ui_input_txt02"/>
	                                    </td>
	                                    <td>
	                                    	<s:textfield disabled="true"  tag="number" name="number" cssClass="ui_input_txt02"/>
	                                    </td>
	                                    <td>
	                                    	<span tag="amount"><s:property value="amount"/> </span>
	                                    </td>
	                                    <td>
	                                    	<s:textfield disabled="true"  tag="remark" name="remark" cssClass="ui_input_txt02"/>
	                                    </td>
	                                </tr>
                            		</s:iterator>
	                            </tbody>
	                        </table>
	                    </td>
	                </tr>
					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">
							&nbsp;<input type="button" value="返回上一级" onclick="window.history.back()"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>