<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jsp" %>
<title>Insert title here</title>
<script type="text/javascript" src="/js/employee.js"></script>
</head>
<body>
	<table id="emp_datagrid"></table>
	
	<div id="emp_dialog">
		<form id="emp_form" method="post"  style="margin-top: 10px;margin-left: 5px">
			<input type="hidden" name="id"/>
			<table>
				<tr>
					<td>用户名:</td><td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>真实名字:</td><td><input type="text" name="realname"/></td>
				</tr>
				<tr>
					<td>手机号码:</td><td><input type="text" name="tel"/></td>
				</tr>
				<tr>
					<td>邮箱:</td><td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>部门:</td>
					<td>
						<select class="easyui-combobox" name="dept.id" placeholder="请选择部门" 
						data-options="valueField:'id',textField:'name',url:'/getSimpleDept'
						,panelHeight:'auto',width:150"></select>
					
					</td>
				</tr>
				<tr>
					<td>入职时间:</td><td><input type="text" name="inputtime" class="easyui-datebox"/></td>
				</tr>
				<tr>
					<td>是否管理员:</td>
					<td>
						<select class="easyui-combobox" name="admin"
						data-options="valueField:'value',textField:'label'
						,panelHeight:'auto',width:150,
						data:[{
							label: '是',
							value: 'true'
						},{
							label: '否',
							value: 'false'
						}]" 
						></select>
					</td>
				</tr>
				<tr>
					<td>角色:</td>
					<td>
						<select class="easyui-combobox"  id="roleCombo"
						data-options="valueField:'id',textField:'name',multiple:true
						,panelHeight:'auto',width:150,url:'/role_selectAll'" 
						></select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="emp_toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" plain=true data-cmd="add">新增</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain=true data-cmd="edit">编辑</a>
		<a id="removeBtn" class="easyui-linkbutton" iconCls="icon-remove" plain=true data-cmd="updateState">离职</a>
		
		<a class="easyui-linkbutton" iconCls="icon-save" plain=true data-cmd="exportFile">导出</a>
		
		
		<a class="easyui-linkbutton" iconCls="icon-reload" plain=true data-cmd="reload">查询全部</a>
		<input type="text" name="keyword" />
		<a class="easyui-linkbutton" iconCls="icon-search" plain=true data-cmd="searchKey">搜索</a>
		
		
	</div>
	
	<div id="emp_buttons">
		<a class="easyui-linkbutton" iconCls="icon-save" plain=true data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain=true data-cmd="cancel">取消</a>
	</div>
	
	<div id="file_dialog">
		<form id="file_form" method="post"  enctype="multipart/form-data" action="/employee_import">
				文件:<input type="file" name="file"><br>
				<input type="submit" value="提交">		
		</form>
	</div>
	
	
</body>
</html>