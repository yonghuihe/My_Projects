<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<script type="text/javascript" src="/static/js/employee/employee.js"></script>
</head>
<body>
	<table id="emp_dataGrid"></table>

	<div id="emp_toolbar">
		<shiro:hasPermission name="employee:add">
			<a class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true" data-cmd="add">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="employee:update">
			<a class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
		</shiro:hasPermission>
		<a class="easyui-linkbutton" id="remove_btn"
			data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">离职</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" data-cmd="searchAll">查询全部</a>
		<input name="keyword"> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-cut',plain:true" data-cmd="doExport">导出</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-cut',plain:true" data-cmd="doImport">导入</a>
	</div>
	
	<div id="emp_dialog">
		<form id="emp_form" action="emp_form" method="post">
			<input type="hidden" name="id">
			<table style="margin-top: 20px">
				<tbody align="right">
					<tr>
						<td>用户名：</td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td>真实名称：</td>
						<td><input type="text" name="realName"></td>
					</tr>
					<tr id=passwordInfo>
						<td>密码：</td>
						<td><input type="text" name="password"></td>
					</tr>
					<tr>
						<td>电话号码：</td>
						<td><input type="text" name="tel"></td>
					</tr>
					<tr>
						<td>邮箱：</td>
						<td><input type="text" name="email"
							class="easyui-validatebox"
							data-options="required:true,validType:'email'"></td>
					</tr>
					<tr>
						<td>入职时间：</td>
						<td><input type="text" name="inputTime"
							class="easyui-datebox"></td>
					</tr>
					<tr>
						<td>部门：</td>
						<td><input type="text" name="dept.id" class="easyui-combobox"
							data-options="panelHeight:'auto',valueField:'id',textField:'name',url:'/department_list'">
						</td>
					</tr>
					<tr>
						<td>角色：</td>
						<td><input type="text" class="easyui-combobox"
							id="role_combobox"
							data-options="multiple:true,panelHeight:'auto',valueField:'id',textField:'name',url:'/role_listAll'">
						</td>
					</tr>
					<tr>
						<td>是否是管理员：</td>
						<td><input type="text" name="admin" class="easyui-combobox"
							data-options="required:true,panelHeight:'auto',valueField:'value',textField:'text',
								data:[{
									text:'是',value:'true'
								},{
									text:'否',value:'false'
								}]">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div id="emp_button">
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
	</div>
	
	<div id="emp_import">
		<form id="importForm" action="employee_import" method="post" enctype="multipart/form-data">
			<table style="margin-top: 20px">
				<tbody align="right">
					<tr>
						<td>
							请选择文件：<input type="file" name="file">
						</td>
					</tr>
					<tr>
						<td>
							<a class="easyui-linkbutton"
								data-options="iconCls:'icon-save',plain:true" data-cmd="importButton">提交</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>