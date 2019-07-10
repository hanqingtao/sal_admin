<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"  %>
	<title>课程管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
			/*
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
			});*/
		});
	</script>

	<script type="text/javascript" src="${ctxStatic}/js/course.js" ></script>
	<script type="text/javascript" src="${ctxStatic}/js/ajaxfileupload.js" ></script>
	<script type="text/javascript" src="${ctxStatic}/js/common.js" ></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/course/course/">课程列表</a></li>
		<li ><a href="${ctx}/course/course/form?id=${course.id}">课程<shiro:hasPermission name="course:course:edit">${not empty course.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="course:course:edit">查看</shiro:lacksPermission></a></li>
		<shiro:hasPermission name="course:course:courseImport"><li class="active"><a href="${ctx}/course/course/toCourseImport">课程导入</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="course" 
		action="${ctx}/course/course/courseImport"
			 enctype="multipart/form-data"  method="post" class="form-horizontal">
		
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		<!-- 
		<div class="control-group">
			<label class="control-label">课程分类：</label>
			<div class="controls">
			<sys:treeselect id="categoryId" name="categoryId" value="${course.categoryId}" labelName="course.categoryName" labelValue="${course.categoryName}"
					title="部门" url="/course/courseCategory/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">课程类型：</label>
			<div class="controls">
				<form:select path="courseType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('course_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('course_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		
		<form:hidden id="videoPath" path="videoPath" htmlEscape="false" maxlength="128" class="input-xlarge"/>
		-->
		
		<div class="control-group">
			<label class="control-label">选择文件上传：</label>
			<div class="controls">
				<input id="uploadFile" name="uploadFile" class="form-control eHide" 
					name="file" onChange="checkCourseImportFile(this)"   type="file" />
				<!-- onChange="uploadCourseFile(this)"   
				class="form-control required eHide" 
				<a herf="javascript:;" id="toUpLoadFileFlag" class="btn btn-primary  setUploadWidth">上传</a>
				-->
				<span class="help-inline"><font color="red"><div id ="myTipModalContainer" ></div>*</font> </span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="course:course:courseImport">
				<input id="btnSubmit" class="btn btn-primary" 
					type="button" onclick="submitImport();"  value="确 定"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>