$(function () {
    $("#myTree").tree({
        url: '/static/json/tree.json',
        animate: true,
        onClick: function (node) {
            // $("#myTree").tree("toggle",node.target);
            //添加选项卡
            //判断选项卡是否存在不存在打开 存在则选中
            console.log(node.url)
            var flag = $('#myTabs').tabs("exists", node.text);
            if(flag){
                $('#myTabs').tabs("select", node.text);
            }
            else{
                $('#myTabs').tabs('add', {
                    fit:true,
                    title: node.text,
                    closable:true,
                    content:"<iframe src="+node.url+" width='100%' height='100%' ></iframe>"
                });
            }
        }
    })

    $("#myTabs").tabs({
        fit:true
    })
})