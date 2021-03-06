<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>批次管理</title>
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
		<li><a href="${ctx}/users/batch/">批次列表</a></li>
		<li class="active"><a href="${ctx}/users/batch/form?id=${batch.id}">批次<shiro:hasPermission name="users:batch:edit">${not empty batch.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="users:batch:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="batch" action="${ctx}/users/batch/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">批次名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效期--开始时间：</label>
			<div class="controls">
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${batch.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效期--结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${batch.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">激活卡号前缀：</label>
			<div class="controls">
			<c:if test="${!empty batch.id}">
					<!-- 修改时的操作 -->
					${batch.pre }
				</c:if>
				<c:if test="${empty batch.id}">
					<form:input path="pre" htmlEscape="false" maxlength="16" class="input-xlarge "/>（注意：前缀+数字总长度不能超过18位）
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">激活卡数字生成规则：</label>
			<div class="controls">
				<c:if test='${!empty batch.id}'>
				<form:radiobuttons path="rule" items="${fns:getDictList('code_rule')}" 
					itemLabel="label" itemValue="value" htmlEscape="false" class="radio i-checks " 
					  disabled='true'  />
					  </c:if>
					  <c:if test="${empty batch.id}">
					  <form:radiobuttons path="rule" items="${fns:getDictList('code_rule')}" 
					itemLabel="label" itemValue="value" htmlEscape="false" class="radio i-checks " 
					    />
					  </c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生成的数量：</label>
			<div class="controls">
				<c:if test="${!empty batch.id}">
					<!-- 修改时的操作 -->
					${batch.count }
				</c:if>
				<c:if test="${empty batch.id}">
					<form:input path="count" htmlEscape="false" maxlength="5" class="input-xlarge "/>
				</c:if>
			</div>
		</div>
		<c:if test="${!empty batch.id}">
		<div class="control-group">
			<label class="control-label">创建人：</label>
			<div class="controls">
				${batch.createName }
				<!--
				<form:input path="createName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
				-->
			</div>
		</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="users:batch:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>