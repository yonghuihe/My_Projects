<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
	<script type="text/javascript" src="/js/highcharts/highcharts.js"></script>
	
	<script type="text/javascript">
		$(function () {
		    $('#container').highcharts({
		        title: {
		            text: '销售报表',
		            x: -20 //center
		        },
		        subtitle: {
		            text: '按<s:property value="groupByValue" escapeHtml="true"/>分组',
		            x: -20
		        },
		        xAxis: {
		            categories: <s:property value="#groupByNames" escapeHtml="false"/>
		        },
		        yAxis: {
		            title: {
		                text: '总的销售金额 '
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        tooltip: {
		            valueSuffix: '元'
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		            name: '销售金额',
		            data: <s:property value="#totalAmount" escapeHtml="false"/>
		        }]
		    });
		});
	</script>
</head>
<body>
	<div id="container" style="min-width: 310px; height: 90%; margin: 0 auto"></div>
</body>
</html>