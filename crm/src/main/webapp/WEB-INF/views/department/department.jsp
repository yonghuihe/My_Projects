<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<script type="text/javascript" src="/static/js/department/department.js"></script>
</head>
<body>
	<table id="dept_dataGrid"></table>

	<div id="dept_toolbar">
		<shiro:hasPermission name="department:add">
			<a class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true" data-cmd="add">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="department:update">
			<a class="easyui-linkbutton" id="edit_btn"
				data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
		</shiro:hasPermission>
		<a class="easyui-linkbutton" id="remove_btn"
			data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">停用</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" data-cmd="searchAll">查询全部</a>
	</div>
	
	<div id="dept_dialog">
		<form id="dept_form" action="dept_form" method="post">
			<input type="hidden" name="id">
			<table style="margin-top: 20px;">
				<tbody align="right">
					<tr>
						<td>部门名称：</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>部门编号：</td>
						<td><input type="text" name="sn"></td>
					</tr>
					<tr>
						<td>部门经理：</td>
						<td><input type="text" name="manager.id" class="easyui-combobox"
							data-options="panelHeight:'auto',valueField:'id',textField:'username',url:'/employee_selectAll'">
						</td>
					</tr>
					<tr>
						<td>上级部门：</td>
						<td><input type="text" name="parent.id" class="easyui-combobox"
							data-options="panelHeight:'auto',valueField:'id',textField:'name',url:'/department_list'">
						</td>
					</tr>
					<tr>
						<td>状态：</td>
						<td><input type="text" name="state" class="easyui-combobox"
							data-options="required:true,panelHeight:'auto',valueField:'value',textField:'text',
								data:[{
									text:'正常',value:'1'
								},{
									text:'停用',value:'0'
								}]">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div id="dept_button">
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
	</div>
	
</body>
</html>