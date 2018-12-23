<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">	
	<s:if test="hasActionErrors()">
		var msg='<s:property value="actionErrors" />';
		art.dialog({
			title:"提示",
			content:msg,
			icon:"error",
			ok:function(){}
		});
	</s:if>
	
	<s:if test="hasActionMessages()">
	var msg2='<s:property value="actionMessages" />';
	art.dialog({
		title:"提示",
		content:msg2,
		icon:"succeed",
		ok:function(){}
	});
	</s:if>
</script>