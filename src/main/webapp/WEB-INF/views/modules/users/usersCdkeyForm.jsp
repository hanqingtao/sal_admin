<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户激活码关系管理</title>
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
	
		
	<%@ include file="/WEB-INF/views/include/comcss.jsp"%>
	
	<link href="${ctxStatic}/modules/BiddingCenter/css/cdkeyUsersForm.css" rel="stylesheet">
</head>
<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInUp">
<div class="row">
		<div class="col-sm-12">

			<div class="ibox">
				<div class="ibox-content">
					
				<ul class="nav nav-tabs">
					<li><a href="${ctx}/users/cdkeyUsers/">用户激活码关系列表</a></li>
					<li class="active"><a href="${ctx}/users/cdkeyUsers/form?id=${cdkeyUsers.id}">用户激活码关系<shiro:hasPermission name="users:cdkeyUsers:edit">${not empty cdkeyUsers.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="users:cdkeyUsers:edit">查看</shiro:lacksPermission></a></li>
				</ul><br/>
				
				
	<form:form id="inputForm" modelAttribute="cdkeyUsers" action="${ctx}/users/cdkeyUsers/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
			<div class="form-group">
			
			<label class="col-sm-2 control-label"><span class="text-danger">
			</span> cdkey_id：</label>
			<div class="col-sm-3">
				<form:input path="cdkeyId" htmlEscape="false" maxlength="10"  class="form-control  digits"/>
			
			</div>
		</div>
			<div class="form-group">
			
			<label class="col-sm-2 control-label"><span class="text-danger">
			</span> 用户：</label>
			<div class="col-sm-3">
				<form:input path="usersId" htmlEscape="false" maxlength="10"  class="form-control  digits"/>
			
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="users:cdkeyUsers:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
		</div>

				</div>
			</div>
		</div>
		</div>
	
<%@ include file="/WEB-INF/views/include/comjs.jsp"%>

<script src="${ctxStatic}/modules/BiddingCenter/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctxStatic}/modules/BiddingCenter/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctxStatic}/modules/BiddingCenter/js/plugins/iCheck/icheck.min.js"></script>
<script src="${ctxStatic}/modules/BiddingCenter/js/cdkeyUsersForm.js"></script>
	
</body>
</html>