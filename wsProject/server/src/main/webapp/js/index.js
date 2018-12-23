$(function(){
	var tabsObj = $("#myTabs");
	
	$("#myTree").tree({
		url:'/getRootMenu',
		onClick:function(node){
			node.attributes = $.parseJSON(node.attributes);
			console.log(node.attributes.url);
			//如果该选项卡已经存在,则选中,否则就创建
			if(tabsObj.tabs("exists",node.text)){
				tabsObj.tabs("select",node.text)
			}else{
				//创建选项卡面板
				tabsObj.tabs("add",{ 
					title:node.text,
					closable:true,
					//href:node.attributes.url,//href只引入body里面的内容
					//以后统一使用content方式
					content:'<iframe src='+node.attributes.url
					   +' width=100% height=100% frameborder=0></iframe>'
				});
			}
		}
	});
	tabsObj.tabs({
		fit:true
	});
})