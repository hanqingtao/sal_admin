<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息表管理</title>
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
		<li><a href="${ctx}/pro/project/">项目信息表列表</a></li>
		<li class="active"><a href="${ctx}/pro/project/form?id=${project.id}">项目信息表<shiro:hasPermission name="pro:project:edit">${not empty project.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pro:project:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="project" action="${ctx}/pro/project/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">招标编号：</label>
			<div class="controls">
				<form:input path="sn" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招标项目名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招标人：</label>
			<div class="controls">
				<form:input path="tenderee" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人及电话：</label>
			<div class="controls">
				<form:input path="contact" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招标代理机构：</label>
			<div class="controls">
				<form:input path="biddingAgency" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人：</label>
			<div class="controls">
				<form:input path="leader" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人电话：</label>
			<div class="controls">
				<form:input path="leaderPhone" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招标类型：</label>
			<div class="controls">
				<form:select path="tenderType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('tender_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<form:input path="entrustMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">委托时间：</label>
			<div class="controls">
				<input name="entrustTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${project.entrustTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招标方式：</label>
			<div class="controls">
				<form:select path="tenderMethod" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('tender_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公告媒体：</label>
			<div class="controls">
				<form:input path="noticeMedia" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公告时间：</label>
			<div class="controls">
				<input name="noticeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${project.noticeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开标时间：</label>
			<div class="controls">
				<input name="opentenderTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${project.opentenderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资质/资信要求：</label>
			<div class="controls">
				<form:input path="creditRequire" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招标内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge "/>
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
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程标识：</label>
			<div class="controls">
				<form:input path="engineerId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pro:project:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>