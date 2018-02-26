<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程信息表管理</title>
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
		<li><a href="${ctx}/pro/engineer/">工程信息表列表</a></li>
		<li class="active"><a href="${ctx}/pro/engineer/form?id=${engineer.id}">工程信息表<shiro:hasPermission name="pro:engineer:edit">${not empty engineer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pro:engineer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="engineer" action="${ctx}/pro/engineer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目业主：</label>
			<div class="controls">
				<form:input path="owner" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可研报告批准文件及文号：</label>
			<div class="controls">
				<form:input path="reportNumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">核准文件及文号：</label>
			<div class="controls">
				<form:input path="approvalNumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备案文件及文号：</label>
			<div class="controls">
				<form:input path="recordNumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行业：</label>
			<div class="controls">
				<form:select path="industry" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建设地点：</label>
			<div class="controls">
				<form:input path="builtSite" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建设规模：</label>
			<div class="controls">
				<form:input path="builtScale" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目总投资：</label>
			<div class="controls">
				<form:input path="totalMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中央投资额：</label>
			<div class="controls">
				<form:input path="centralMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中央投资资金来源：</label>
			<div class="controls">
				<form:input path="centralinvestType" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中央投资资金使用方式：</label>
			<div class="controls">
				<form:input path="centraluseType" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中央投资资金数额：</label>
			<div class="controls">
				<form:input path="centralinvestMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地区标识：</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="${engineer.area.id}" labelName="area.name" labelValue="${engineer.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建人：</label>
			<div class="controls">
				<form:input path="createId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${engineer.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构标识：</label>
			<div class="controls">
				<form:input path="orgId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pro:engineer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>