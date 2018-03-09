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
		<li class="active"><a href="${ctx}/consumer/consumer/">用户信息列表</a></li>
		<shiro:hasPermission name="consumer:consumer:edit"><li><a href="${ctx}/consumer/consumer/form">用户信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="consumer" action="${ctx}/consumer/consumer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>真实姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>用户状态：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('consumer_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>用户类型 1 普通用户  2 商贩  3 4：</label>
				<form:select path="kind" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('consumer_kind')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>真实姓名</th>
				<th>用户状态</th>
				<th>用户类型 1 普通用户  2 商贩  3 4</th>
				<th>备注</th>
				<shiro:hasPermission name="consumer:consumer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="consumer">
			<tr>
				<td><a href="${ctx}/consumer/consumer/form?id=${consumer.id}">
					${consumer.name}
				</a></td>
				<td>
					${fns:getDictLabel(consumer.status, 'consumer_status', '')}
				</td>
				<td>
					${fns:getDictLabel(consumer.kind, 'consumer_kind', '')}
				</td>
				<td>
					${consumer.remarks}
				</td>
				<shiro:hasPermission name="consumer:consumer:edit"><td>
    				<a href="${ctx}/consumer/consumer/form?id=${consumer.id}">修改</a>
					<a href="${ctx}/consumer/consumer/delete?id=${consumer.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>