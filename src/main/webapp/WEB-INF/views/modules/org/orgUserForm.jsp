<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/org/orgUser/">用户列表</a></li>
		<li class="active"><a href="${ctx}/org/orgUser/form?id=${orgUser.id}">用户<shiro:hasPermission name="org:orgUser:edit">${not empty orgUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:orgUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orgUser" action="${ctx}/org/orgUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">登录名：</label>
			<div class="controls">
				<form:input path="loginName" htmlEscape="false" readonly="true"
					maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label">登录密码：</label>
			<div class="controls">
				<form:input path="password" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>-->
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理机构名称：</label>
			<div class="controls">
				<form:input path="Org.name" htmlEscape="false" 
				readonly="true" class="input-xlarge"/><!--   digits -->
			</div>
		</div>
		<form:input  path="org.id" type="hidden" />
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="telphone" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户状态：</label>
			<div class="controls">
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:select path="sex" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生日期：</label>
			<div class="controls">
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${orgUser.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历：</label>
			<div class="controls">
				<form:select path="degree" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('degree')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<!--
		<div class="control-group">
			<label class="control-label">头像：</label>
			<div class="controls">
				<form:input path="photoPath" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<fmt:formatDate value="${orgUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<!-- 
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${orgUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				-->
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="org:orgUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>