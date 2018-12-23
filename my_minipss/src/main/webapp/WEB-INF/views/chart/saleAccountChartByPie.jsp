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
			    chart: {
			        plotBackgroundColor: null,
			        plotBorderWidth: 1,//null,
			        plotShadow: false
			    },
			    title: {
			        text: '销售报表-按<s:property value="groupByValue" escapeHtml="true"/>分组 '
			    },
			    tooltip: {
			        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			    },
			    plotOptions: {
			        pie: {
			            allowPointSelect: true,
			            cursor: 'pointer',
			            dataLabels: {
			                enabled: true,
			                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                style: {
			                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                }
			            }
			        }
			    },
			    series: [{
			        type: 'pie',
			        name: '占总销售额的比例：',
			        data: <s:property value="#datas" escapeHtml="false"/>
			    }]
			});
		});
	</script>
</head>
<body>
	<div id="container" style="min-width: 310px; height: 90%; margin: 0 auto"></div>
</body>
</html>