<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构资格管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/org/orgQualification/">代理机构资格列表</a></li>
		<shiro:hasPermission name="org:orgQualification:edit"><li><a href="${ctx}/org/orgQualification/form">代理机构资格添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orgQualification" action="${ctx}/org/orgQualification/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>资格名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>资格类型：</label>
				<form:select path="grade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('qualification_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资格名称</th>
				<th>资格类型</th>
				<shiro:hasPermission name="org:orgQualification:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orgQualification">
			<tr>
				<td><a href="${ctx}/org/orgQualification/form?id=${orgQualification.id}">
					${orgQualification.name}
				</a></td>
				<td>
					${fns:getDictLabel(orgQualification.grade, 'qualification_grade', '')}
				</td>
				<shiro:hasPermission name="org:orgQualification:edit"><td>
    				<a href="${ctx}/org/orgQualification/form?id=${orgQualification.id}">修改</a>
					<a href="${ctx}/org/orgQualification/delete?id=${orgQualification.id}" onclick="return confirmx('确认要删除该代理机构资格吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>