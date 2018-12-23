<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/common/common_resource.jsp" %>
<script type="text/javascript" src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
	function chooseA(){
		$.dialog.open("/test2.jsp",{
			title:'test',
			height:'80%',
			width:'50%',
			close:function(){
				console.log(art.dialog.data("product"));
			}
		});
	}
</script>
		<script type="text/javascript">
		$(function () {
		    $('#container').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: 1,//null,
		            plotShadow: false
		        },
		        title: {
		            text: '总的销售金额 '
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
		            name: '销售所占比例',
		            data: [
		                ['360儿童手表', 45.0],
		                ['360智能摄像机', 26.8],
		                {
		                    name: '360N4S手机',
		                    y: 12.8,
		                    sliced: true,
		                    selected: true
		                }
		            ]
		        }]
		    });
		});
		</script>
</head>
<body>
	<a href="javascript:;" onclick="chooseA();">点我</a>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>