<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构招标业绩管理</title>
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
		<li><a href="${ctx}/org/orgAchieve/">代理机构招标业绩列表</a></li>
		<li class="active"><a href="${ctx}/org/orgAchieve/form?id=${orgAchieve.id}">代理机构招标业绩<shiro:hasPermission name="org:orgAchieve:edit">${not empty orgAchieve.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:orgAchieve:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orgAchieve" action="${ctx}/org/orgAchieve/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目编号：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">委托单位：</label>
			<div class="controls">
				<form:input path="entrustUnit" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">委托金额：</label>
			<div class="controls">
				<form:input path="entrustMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中标金额：</label>
			<div class="controls">
				<form:input path="bidMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开标时间：</label>
			<div class="controls">
				<input name="tenderOpenTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${orgAchieve.tenderOpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中标时间：</label>
			<div class="controls">
				<input name="bidTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${orgAchieve.bidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公告媒体：</label>
			<div class="controls">
				<form:input path="noticeMedia" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公告时间：</label>
			<div class="controls">
				<input name="noticeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${orgAchieve.noticeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="512" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构标识：</label>
			<div class="controls">
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="org:orgAchieve:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>