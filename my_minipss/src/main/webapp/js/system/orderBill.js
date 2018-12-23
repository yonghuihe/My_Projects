$(function(){
	//统一事件监听
	$("#edit_table_body").on("click",".searchproduct",function(){//监听放大镜按钮
		var tr = $(this).closest("tr");
		//弹出一个窗体
		$.dialog.open("/product_view.action",{
			title:'货品信息',
			height:'90%',
			width:'80%',
			close:function(){
				//接收参数
				var productVal = art.dialog.data("product");
				//回填参数
				tr.find("[tag='name']").val(productVal.name)
				tr.find("[tag='brand']").html(productVal.brandName)
				tr.find("[tag='costPrice']").val(productVal.costPrice)
				tr.find("[tag='pid']").val(productVal.id)
			}
		});
	}).on("click",".removeItem",function(){//监听删除明细按钮
		//获取最近的tr删除掉
		var tr =$(this).closest("tr");
		//如果tr是只剩一个了，将tr中的内容设置为空，否则删除掉tr元素
		if($("#edit_table_body tr").length == 1){
			tr.find("[tag]").val("");
			tr.find("[tag]").html("");
		} else {
			tr.remove();
		}
	}).on("blur","[tag='costPrice'],[tag='number']",function(){//监听tag="costPrice"和tag="number"
		var tr = $(this).closest("tr");
		var constPriceVal = tr.find("[tag='costPrice']").val();
		var numberVal = tr.find("[tag='number']").val();
		//保留两位小数
		var amount = (constPriceVal * numberVal).toFixed(2);
		tr.find("[tag='amount']").html(amount);
	})
	
	//监听添加明细按钮
	$(".appendRow").click(function(){
		//找到第一个tr，并拷贝，带上参数：true
		var tr = $("#edit_table_body tr:first").clone(true);
		//清空克隆的tr中的内容分
		tr.find("[tag]").val("");
		tr.find("[tag]").html("");
		//追加到edit_table_body中
		tr.appendTo("#edit_table_body");
	})
	
	//监听保存按钮
	$(".btn_submit").click(function(){
		//找到所有的tr,遍历tr集合，维护索引
		$.each($("#edit_table_body tr"),function(index,item){
			$(item).find("[tag='pid']").prop("name","orderBill.orderBillItem[" + index + "].product.id");
			$(item).find("[tag='costPrice']").prop("name","orderBill.orderBillItem[" + index + "].costPrice");
			$(item).find("[tag='number']").prop("name","orderBill.orderBillItem[" + index + "].number");
			$(item).find("[tag='remark']").prop("name","orderBill.orderBillItem[" + index + "].remark");
		})
		$("#editForm").submit();
	});
	
	//审核
	$(".btn_audit").click(function(){
		var auditUrl = $(this).data("url");
		$.dialog({
			icon:"question",
			title:"温馨提示",
			drag:false,
			resize:false,
			content:"您确定审核这条记录吗？",
			ok:function(){
				$.ajax({
					type:"post",
					url:auditUrl,
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
})