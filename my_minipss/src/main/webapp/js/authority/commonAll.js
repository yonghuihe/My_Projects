/** table鼠标悬停换色* */
$(function() {
	$(".btn_delete").click(function(){
		var delUrl = $(this).data("url");
		$.dialog({
			icon:"question",
			title:"温馨提示",
			drag:false,
			resize:false,
			content:"您确定删除这条记录吗？",
			ok:function(){
				$.ajax({
					type:"post",
					url:delUrl,
					icon:"face-smile",
					success:function(data) {
						art.dialog({icon:'succeed', title:'提示', drag:false, resize:false, content:data, 
			        		ok:true,close:function(){window.location.reload()}});
					}
				})
			},
			cancel:true
		})
	})
	
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({
			background : "#CDDAEB"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#1D1E21"
			});
		});
	}).mouseout(function() {
		$(this).css({
			background : "#FFF"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#909090"
			});
		});
	});
	
	$("#cancelbutton").click(function(){
		
	})
	
	//My97DatePicker插件
	$(".beginDate").click(function(){
		WdatePicker({
			maxDate:$(".endDate").val(),
			skin:'twoer'
		});
	})
	$(".endDate").click(function(){
		WdatePicker({
			minDate:$(".beginDate").val(),
			maxDate:'%y-%M-%d'
		});
	})
});
function showDialog(title,content,callback){
	art.dialog({
		title:title,
		content:content,
		ok:callback||function(){}
	});
}
