<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目流程管理</title>
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
		<%-- <li><a href="${ctx}/pro/projectFlow/">项目流程列表</a></li> --%>
		<li class="active"><a href="${ctx}/pro/projectFlow/form?id=${projectFlow.id}">项目流程<shiro:hasPermission name="pro:projectFlow:edit">${not empty projectFlow.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pro:projectFlow:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="projectFlow" action="${ctx}/pro/projectFlow/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目标识：</label>
			<div class="controls">
				<form:input path="projectId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公告附件：</label>
			<div class="controls">
				<form:input path="noticeFile" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">tender_file：</label>
			<div class="controls">
				<form:input path="tenderFile" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评标记录附件：</label>
			<div class="controls">
				<form:input path="fileEvaluation" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评标专家：</label>
			<div class="controls">
				<form:input path="fileEvaluationexpert" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">阶段：</label>
			<div class="controls">
				<form:input path="bidWinner" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="contact" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中标金额：</label>
			<div class="controls">
				<form:input path="bidMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">节资率：</label>
			<div class="controls">
				<form:input path="capitalsavingRatio" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中标通知书发出时间：</label>
			<div class="controls">
				<input name="bidnoticeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${projectFlow.bidnoticeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中标通知书附件：</label>
			<div class="controls">
				<form:input path="fileBid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">顺序号：</label>
			<div class="controls">
				<form:input path="orderNum" htmlEscape="false" maxlength="4" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pro:projectFlow:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>