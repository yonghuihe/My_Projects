$(function(){
	//初始化选项卡
	$("#MyTree").tree({
		url:'/menu_loadTree',
		animate:true,
		lines:true,
		onClick:function(node){
			var text = node.text;
			var exists=$("#MyTabs").tabs("exists",text);
			//折叠/展开树节点
			$("#MyTree").tree("toggle",node.target)
			if(node.url){
				if(exists){
					//选中选项卡
					$("#MyTabs").tabs("select",text);
				}else{
					//添加选项卡
					$("#MyTabs").tabs("add",{
						title:node.text,
						//href只会引入页面的body部门
						//href:node.url
						content:'<iframe scrolling="auto" frameborder="0"  src='+ node.url + ' " style="width:100%;height:100%;"></iframe>',
						closable:true
					})
				}
			}
		}
	})
})