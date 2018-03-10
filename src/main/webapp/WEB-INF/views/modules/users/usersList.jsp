<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息管理</title>
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
		<li class="active"><a href="${ctx}/users/users/">用户信息列表</a></li>
		<shiro:hasPermission name="users:users:edit"><li><a href="${ctx}/users/users/form">用户信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="users" action="${ctx}/users/users/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>登录名  不可修改：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>用户状态  1 正常  0停用：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('users_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>用户类型 1 普通用户  2 商贩  3 4：</label>
				<form:select path="kind" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('users_kind')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>登录名  不可修改</th>
				<th>真实姓名</th>
				<th>用户状态  1 正常  0停用</th>
				<th>用户类型 1 普通用户  2 商贩  3 4</th>
				<th>备注</th>
				<shiro:hasPermission name="users:users:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="users">
			<tr>
				<td><a href="${ctx}/users/users/form?id=${users.id}">
					${users.loginName}
				</a></td>
				<td>
					${users.name}
				</td>
				<td>
					${fns:getDictLabel(users.status, 'users_status', '')}
				</td>
				<td>
					${fns:getDictLabel(users.kind, 'users_kind', '')}
				</td>
				<td>
					${users.remarks}
				</td>
				<shiro:hasPermission name="users:users:edit"><td>
    				<a href="${ctx}/users/users/form?id=${users.id}">修改</a>
					<a href="${ctx}/users/users/delete?id=${users.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>