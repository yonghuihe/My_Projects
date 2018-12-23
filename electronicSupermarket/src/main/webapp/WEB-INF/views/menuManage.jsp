<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>menuManage管理</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/menuManage.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="menuManage_datagrid"></table>

<!-- 新增编辑对话框 -->
<div id="menuManage_dialog">
    <form id="menuManage_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">
            <tr>
                <td>菜单名称</td>
                <td><input type="text" name="name" placeholder="请输入菜单名"></td>
            </tr>
            <tr>
                <td>类型</td>
                <td><select class="easyui-combobox" name="type" style="width:173px;" data-options="panelHeight:'auto'">
                    <option>click</option>
                    <option>view</option>
                </select></td>
                <%--<td><input type="text" name="type" placeholder="请输入菜单类型"></td>--%>
            </tr>
            <tr>
                <td>链接</td>
                <td><input type="text" name="link" placeholder="请输入链接"></td>
            </tr>
            <tr>
                <td>父菜单</td>
                <td><input type="text" class="easyui-combobox" name="parent.id" placeholder="请选择父菜单"
                           data-options="url:'/menuManage_listParent.do',valueField:'id',textField:'name',panelHeight:'auto'"
                /></td>
            </tr>
        </table>
    </form>
</div>
<!-- 数据表格CRUD按钮 -->
<div id="menuManage_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add2" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit2" plain="true" data-cmd="edit">編輯</a>
        <a class="easyui-linkbutton" iconCls="icon-delete1" plain="true" data-cmd="del">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <a class="easyui-linkbutton" iconCls="icon-undo" plain="true" data-cmd="reloadMenu">重新加载菜单</a>
    </div>
</div>
<!-- 对话框保存取消按钮 -->
<div id="menuManage_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>