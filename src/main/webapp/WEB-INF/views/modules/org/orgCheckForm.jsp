<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抽查管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					if($("#rateOrg").val()>100){
						alert("机构抽查比例数值不合法");
						return false;
					}
					if($("#rateProject").val()>100){
						alert("项目抽查比例数值不合法");
						return false;
					}
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
		<li><a href="${ctx}/org/orgCheck/">抽查管理列表</a></li>
		<li class="active"><a href="${ctx}/org/orgCheck/form?id=${orgCheck.id}">抽查管理<shiro:hasPermission name="org:orgCheck:edit">${not empty orgCheck.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:orgCheck:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orgCheck" action="${ctx}/org/orgCheck/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<form:input path="year" htmlEscape="false" maxlength="6" class="input-xlarge  digits number date required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构抽查比例：</label>
			<div class="controls">
				<form:input  path="rateOrg" id="rateOrg"   htmlEscape="false" min="1"    maxlength="3"  class="input-xlarge number required"/> <span>%</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目抽查比例：</label>
			<div class="controls">
				<form:input  path="rateProject"  id="rateProject"   htmlEscape="false" min="1"    maxlength="3" class="input-xlarge number required"/><span>%</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="32" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="org:orgCheck:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="开始随机抽查"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
<script type="text/javascript">
	function setPercent(obj){
		alert($(obj).val());
	}
</script>
</html>