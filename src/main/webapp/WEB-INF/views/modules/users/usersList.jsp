<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学员信息管理</title>
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
		<li class="active"><a href="${ctx}/users/users/">学员信息列表</a></li>
		<!-- 
		<shiro:hasPermission name="users:users:edit"><li><a href="${ctx}/users/users/form">学员信息添加</a></li></shiro:hasPermission>
		 -->
	</ul>
	<form:form id="searchForm" modelAttribute="users" action="${ctx}/users/users/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<!-- 
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>年龄：</label>
				<form:input path="age" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>年级：</label>
				<form:select path="grade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			-->
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学员微信</th>
				<th>激活码</th>
				<th>密码</th>
				<th>状态</th>
				<th>激活时间</th>
				<th>有效期-开始时间</th>
				<th>有效期-截止时间</th>
				<shiro:hasPermission name="users:users:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="users">
			<tr>
				<td><a href="${ctx}/users/users/form?id=${users.id}">
					${users.openId}
				</a></td>
				<td>
					${users.cdkey.code}
				</td>
				<td>
					${users.cdkey.password}
				</td>
				<td>
					${fns:getDictLabel(users.cdkey.status, 'cdkey_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${users.cdkey.activeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${users.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${users.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				
				<shiro:hasPermission name="users:users:edit"><td>
    				<a href="${ctx}/users/users/form?id=${users.id}">修改有效期</a>
				<!-- 
					<a href="${ctx}/users/users/delete?id=${users.id}" onclick="return confirmx('确认要删除该学员信息吗？', this.href)">删除</a>
				 -->
				</td></shiro:hasPermission>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>