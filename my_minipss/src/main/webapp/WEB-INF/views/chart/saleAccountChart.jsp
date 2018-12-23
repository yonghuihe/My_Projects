<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/common_page.js"></script>
	<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(function(){
			$("[name='sqo.groupType']").change(function(){
				$("#searchForm").submit();
			})
			$(".btn_search").click(function(){
				$("#searchForm").submit();
			})
			$(".btn_chart").change(function(){
				var chartType = $("#chartType").val();
				if(chartType == 'LINE'){
					$.dialog.open("/chart_saleAccountChartByLine?"+$("#searchForm").serialize(),{
						title:'线性图',
						height:'85%',
						width:'90%',
						/* close:function(){
							window.location.reload(true);
						} */
					}); 
				}else if(chartType == 'PIE'){
					$.dialog.open("/chart_saleAccountChartByPie?"+$("#searchForm").serialize(),{
						title:'饼状图',
						height:'85%',
						width:'90%',
						/* close:function(){
							window.location.reload(true);
						} */
					}); 
				}
			})
		})
	</script>
	<title>PSS-销售报表管理</title>
	<style>
		.alt td{ background:black !important;}
	</style>
</head>
<body>
	<%@include file="../common/common_msg.jsp" %>
	<s:form id="searchForm" action="chart_saleAccountChart" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间：
							<s:date name="sqo.beginDate" format="yyyy-MM-dd" var="beginDate"/>
							<s:textfield class="ui_input_txt02 Wdate beginDate" name="sqo.beginDate" value="%{#beginDate}"/>
							~
							<s:date name="sqo.beginDate" format="yyyy-MM-dd" var="endDate"/>
							<s:textfield class="ui_input_txt02 Wdate endDate" name="sqo.endDate" value="%{#endDate}"/>
							分组：
							<s:select name="sqo.groupType" cssClass="ui_select01" list="#groupByValues"  listValue="value"></s:select>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_search"/>
							<s:select id="chartType" list="#{'-1':'图表分析','LINE':'线形图','PIE':'饼状图'}" class="ui_select01 btn_chart"></s:select>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th>分组类型</th>
							<th>订货数量</th>
							<th>订货金额</th>
							<th>毛利润</th>
						</tr>
						<tbody>
							<s:iterator value="#result" var="item">
								<tr>
									<td><s:property value="#item.groupByName"/></td>
									<td><s:property value="#item.totalNumber"/></td>
									<td><s:property value="#item.totalAmount"/></td>
									<td><s:property value="#item.grossProfit"/></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>
