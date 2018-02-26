<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li class="active"><a href="${ctx}/org/orgUser/">用户列表</a></li>
		<!--<shiro:hasPermission name="org:orgUser:edit"><li><a href="${ctx}/org/orgUser/form">用户添加</a></li></shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" modelAttribute="orgUser" action="${ctx}/org/orgUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>代理机构：</label>
				<form:input path="org.name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>登录名：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="32" class="input-small"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-small"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-small">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>登录名</th>
				<th>真实姓名</th>
				<th>代理机构名称</th>
				<th>用户状态</th>
				<!--<shiro:hasPermission name="org:orgUser:edit"><th>操作</th></shiro:hasPermission>-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orgUser">
			<tr>
				<td> 
					${orgUser.loginName}
				 </td>
				<td>
					${orgUser.userName}
				</td>
				<td>
					${orgUser.org.name}
				</td>
				<td>
					${fns:getDictLabel(orgUser.status, 'user_status', '')}
				</td>
				<!--<shiro:hasPermission name="org:orgUser:edit"><td>
    				<a href="${ctx}/org/orgUser/form?id=${orgUser.id}">修改</a>
					<a href="${ctx}/org/orgUser/delete?id=${orgUser.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>