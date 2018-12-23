$(function() {
	$(".loadpermission_btn").click(function() {
		art.dialog({
			title : "确定加载",
			content : "重新加载系统权限可能会消耗比较长的时间，是否继续？",
			ok : function() {
				$.ajax({
					url : $(this).data("href"),
					type : "POST",
					dataType : "json",
					success : function(result) {
						art.dialog({
							icon : 'succeed',
							title : '提示',
							drag : false,
							resize : false,
							content : '重新加载成功',
							ok : function() {
								window.location.reload();
							}
						})
					}
				})
			},
			cancel : true
		})
	})
})