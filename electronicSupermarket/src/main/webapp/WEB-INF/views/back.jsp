<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微信公众号-歪秀</title>
    <link rel="stylesheet" type="text/css" href="/static/plugin/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugin/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/static/plugin/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugin/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/js/back.js"></script>
</head>
<body>
<div id="cc" class="easyui-layout" style="width:600px;height:300px;" data-options="fit:true,border:false">
    <div style="background-color: #c5fda2" data-options="region:'north',split:true" style="height:200px;">
               <center><font size="18">歪秀管理系统</font></center>
    </div>
 <%--       <div data-options="region:'south',split:true" style="height:60px;">
            版权所有
        </div>--%>
    <div data-options="region:'west',split:true" style="width:180px;">
        <div id="aa" class="easyui-accordion" style="width:300px;height:1800px;" data-options="fit:true">
            <div title="菜单" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                <ul id="myTree"></ul>
            </div>
        </div>
    </div>
    <div data-options="region:'center'" style="padding:5px; background:#eee;">
        <div id="myTabs"></div>
    </div>
</div>
</body>
</html>
