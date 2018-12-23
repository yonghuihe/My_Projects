<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product管理</title>
<%@include file="/static/common/common.jsp" %>
<script type="text/javascript" src="/static/js/product.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="product_datagrid">
		<thead>
			<tr>
				<th data-options="field:'productName',width:1,align:'center'">商品品牌</th>
				<th data-options="field:'dept_id',width:1,align:'center'">dept_id</th>
				<th data-options="field:'price',width:1,align:'center'">单价</th>
				<th data-options="field:'depict',width:1,align:'center'">描述</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="product_dialog">
		<form id="product_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>描述</td>
				<td><input type="text" name="depict"></td>
			</tr>
			<tr>
				<td>dept_id</td>
				<td><input type="text" name="dept_id"></td>
			</tr>
			<tr>
				<td>单价</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>商品品牌</td>
				<td><input type="text" name="productName"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="product_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add2" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit2" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-delete1" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="product_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>