//加载当前日期
function loadDate() {
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."
			+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 */
function switchSysBar(flag) {
	var side = $('#side');
	var left_menu_cnt = $('#left_menu_cnt');
	if (flag == true) { // flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({
			width : '280px'
		});
		$('#top_nav').css({
			width : '77%',
			left : '304px'
		});
		$('#main').css({
			left : '280px'
		});
	} else {
		if (left_menu_cnt.is(":visible")) {
			left_menu_cnt.hide(10, 'linear');
			side.css({
				width : '60px'
			});
			$('#top_nav').css({
				width : '100%',
				left : '60px',
				'padding-left' : '28px'
			});
			$('#main').css({
				left : '60px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'images/common/nav_show.png');
		} else {
			left_menu_cnt.show(500, 'linear');
			side.css({
				width : '280px'
			});
			$('#top_nav').css({
				width : '77%',
				left : '304px',
				'padding-left' : '0px'
			});
			$('#main').css({
				left : '280px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'images/common/nav_hide.png');
		}
	}
}

function loadMenu(rootMenu) {
	// zTree的setting对象
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		async : {
			enable : true,
			url : "systemMenu_loadCurrentEmployeeMenus.action",
			autoParam : [ "sn=qo.parentSn" ]
		},
		callback : {
			onClick : function(event, treeId, treeNode) {
				$("#rightMain").attr("src", treeNode.action + ".action");
				$("#here_area").html("当前位置：系统&nbsp;>&nbsp;" + treeNode.name);
			}
		}
	};

	// 准备ztree的数据
	var datas = {
		"business" : [ {
			id : 1,
			pId : 0,
			name : "业务模块",
			open : true,
			sn : "business",
			isParent : true
		} ],
		"systemManage" : [ {
			id : 1,
			pId : 0,
			name : "系统管理模块",
			open : true,
			sn : "systemManage",
			isParent : true
		} ],
		"charts" : [ {
			id : 1,
			pId : 0,
			name : "报表模块",
			open : true,
			sn : "charts"
		} ]
	};
	$.fn.zTree.init($("#dleft_tab1"), setting, datas[rootMenu]);
}

$(function() {
	loadDate();
	loadMenu("business");

	// 注册大模块的点击事件
	$("#TabPage2 li").click(
			function() {
				// 先去掉所有的selected样式;
				$("#TabPage2 li").removeClass("selected").each(
						function(index, item) {
							$(item).children("img").attr("src",
									"images/common/" + (index + 1) + ".jpg");
						});

				// 修改选中的模块样式;
				// $(this).index():index方法返回当前的这个对象在所有选中(过滤)到的对象数组中的索引号
				$(this).addClass("selected").children("img")
						.attr(
								"src",
								"images/common/" + ($(this).index() + 1)
										+ "_hover.jpg");
				// 修改选中的模块的图片
				$("#nav_module img").attr(
						"src",
						"images/common/module_" + ($(this).index() + 1)
								+ ".png");

				loadMenu($(this).data("rootmenu"));
			});

	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
		switchSysBar();
	});
});