<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
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
	<%@ include file="/WEB-INF/views/include/head.jsp"  %>
	<script type="text/javascript" src="${ctxStatic}/js/course.js" ></script>
	<script type="text/javascript" src="${ctxStatic}/js/ajaxfileupload.js" ></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/course/course/">课程列表</a></li>
		<li class="active"><a href="${ctx}/course/course/form?id=${course.id}">课程<shiro:hasPermission name="course:course:edit">${not empty course.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="course:course:edit">查看</shiro:lacksPermission></a></li>
		<shiro:hasPermission name="course:course:courseImport"><li><a href="${ctx}/course/course/toCourseImport">课程导入</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="course" action="${ctx}/course/course/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		<div class="control-group">
			<label class="control-label">课程分类：</label>
			<div class="controls">
			<!-- <form:hidden id="categoryId" path="categoryId"  value="1" htmlEscape="false" maxlength="128" class="input-xlarge"/> -->
			<sys:treeselect id="categoryId" name="categoryId" value="${course.categoryId}" labelName="course.categoryName" labelValue="${course.categoryName}"
					title="部门" url="/course/courseCategory/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">课程名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程类型：</label>
			<div class="controls">
				<form:select path="courseType" class="input-xlarge ">
					<%--<form:option value="" label=""/>--%>
					<form:options items="${fns:getDictList('course_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频状态：</label>
			<div class="controls">
			<form:select path="status" class="input-xlarge ">
					<%--<form:option value="" label=""/>--%>
					<form:options items="${fns:getDictList('course_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回答：</label>
			<div class="controls">
				<form:textarea path="reply" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">搜索名称：</label>
			<div class="controls">
				<form:input path="nameSearch" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">多个搜索名称，请用,号分隔!*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">视频上传：</label>
			<div class="controls">
				<form:hidden id="videoPath" path="videoPath" />
				<form:hidden id="videoName" path="videoName" />
				<input id="uploadFile" class="form-control eHide" 
					name="uploadFile" onChange="uploadCourseFile(this)"  type="file" />&nbsp;&nbsp;
					<div id="vName"> ${course.videoName}</div>
				<!--  class="form-control required eHide" 
				<a herf="javascript:;" id="toUpLoadFileFlag" class="btn btn-primary  setUploadWidth">上传</a>
				-->
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<!--
		<div class="control-group">
			<label class="control-label">课程唯一标识：</label>
			<div class="controls">
				<form:input path="courseCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属分类名称：</label>
			<div class="controls">
				<form:input path="categoryName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频分类：</label>
			<div class="controls">
				<form:input path="categoryCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程封面：</label>
			<div class="controls">
				<form:input path="coverPath" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时长 **:**:**：</label>
			<div class="controls">
				<form:input path="duration" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时长：</label>
			<div class="controls">
				<form:input path="durationLong" htmlEscape="false" maxlength="8" class="input-xlarge  digits"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">课程的播放次数：</label>
			<div class="controls">
				<form:input path="playNum" htmlEscape="false" maxlength="9" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推荐排序：</label>
			<div class="controls">
				<form:input path="orderNum" htmlEscape="false" maxlength="5" class="input-xlarge  digits"/>
			</div>
		</div>
		-->
		
		
		<!-- 
		<div class="control-group">
			<label class="control-label">分类ids：</label>
			<div class="controls">
				<form:input path="categoryIds" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		-->
		<div class="form-actions">
			<shiro:hasPermission name="course:course:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>