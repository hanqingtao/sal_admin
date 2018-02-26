<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信用记录管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/main.css" />
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var con;
					con=confirm("提交之后不可更改，不可删除，确定要提交吗？"); 
					if(con==true){ 
					loading('正在提交，请稍等...');
					form.submit();
					}else{
						return false;
					}
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
		<li><a href="${ctx}/pro/creditRecord/find?id=${org.id}">信用记录列表</a></li>
		<!-- <li class="active"><a href="${ctx}/pro/creditRecord/form?id=${creditRecord.id}">信用记录<shiro:hasPermission name="pro:creditRecord:edit">${not empty creditRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pro:creditRecord:edit">查看</shiro:lacksPermission></a></li> -->
		<li class="active"><a href="${ctx}/pro/creditRecord/form?id=${creditRecord.id}">添加信用记录</a></li>
	</ul><br/>
	<div class="orgNameContent">
	<h4>机构名称：${org.name}</h4>
	</div>
	<form:form id="inputForm" modelAttribute="creditRecord" action="${ctx}/pro/creditRecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<input type="hidden" name="orgId" value="${org.id  }" />
		<div class="control-group">
			<label class="control-label">记录时间：</label>
			<div class="controls">
				<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${creditRecord.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目编号：</label>
			<div class="controls">
				<form:input path="projectCode" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">直接负责人：</label>
			<div class="controls">
				<form:input path="leader" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违规情况：</label>
			<div class="controls">
				<form:textarea path="instruction" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理结果：</label>
			<div class="controls">
				<form:textarea path="result" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${creditRecord.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> -->
		<div class="form-actions">
			<shiro:hasPermission name="pro:creditRecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>