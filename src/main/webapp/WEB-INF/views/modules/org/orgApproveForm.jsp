<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构审批</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
				},
				messages: {
					//confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
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
		<li ><a href="${ctx}/org/org/listApprove">未审批代理机构列表</a></li>
	    <li class="active"><a href="#">代理机构审批</a></li> 
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orgAudit" action="${ctx}/org/org/saveApproveForm" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orgId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">代理机构名称:</label>
			<div class="controls">
				<input maxlength="32" class="input-xlarge required" value="${orgName }" readonly="readonly" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批状态:</label>
			<div class="controls">
				<c:if test="${ostatus == 1 }">
					<input type="radio" name="orgStatus" value="2" checked="checked"/>通过
					&nbsp;&nbsp;&nbsp;<input type="radio" name="orgStatus" value="5" />拒绝
				</c:if>
				<c:if test="${ostatus == 2 || ostatus == 5 }">
					<input type="radio" name="orgStatus" value="3" checked="checked"/>通过
					&nbsp;&nbsp;&nbsp;<input type="radio" name="orgStatus" value="6" />拒绝
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批意见:</label>
			<div class="controls">
				<input id="content" name="content" type="text" value="" maxlength="80" minlength="2" class="required" style="width: 260px;" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
</body>
</html>