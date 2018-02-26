<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构专职人员管理</title>
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
		<li class="active"><a href="${ctx}/org/orgStaff/">代理机构专职人员列表</a></li>
		<shiro:hasPermission name="org:orgStaff:edit"><li><a href="${ctx}/org/orgStaff/form">代理机构专职人员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orgStaff" action="${ctx}/org/orgStaff/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<shiro:hasPermission name="org:orgStaff:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orgStaff">
			<tr>
				<td><a href="${ctx}/org/orgStaff/form?id=${orgStaff.id}">
					${orgStaff.name}
				</a></td>
				<shiro:hasPermission name="org:orgStaff:edit"><td>
    				<a href="${ctx}/org/orgStaff/form?id=${orgStaff.id}">修改</a>
					<a href="${ctx}/org/orgStaff/delete?id=${orgStaff.id}" onclick="return confirmx('确认要删除该代理机构专职人员吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>