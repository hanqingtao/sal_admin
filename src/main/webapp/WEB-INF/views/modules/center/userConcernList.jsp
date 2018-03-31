<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的关注管理</title>
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
		<li class="active"><a href="${ctx}/center/userConcern/">我的关注列表</a></li>
		<shiro:hasPermission name="center:userConcern:edit"><li><a href="${ctx}/center/userConcern/form">我的关注添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="userConcern" action="${ctx}/center/userConcern/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>关注类型：</label>
				<form:input path="concernType" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>关注的内容：</label>
				<form:input path="concernName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>关注类型</th>
				<th>关注的内容</th>
				<shiro:hasPermission name="center:userConcern:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userConcern">
			<tr>
				<td><a href="${ctx}/center/userConcern/form?id=${userConcern.id}">
					${userConcern.concernType}
				</a></td>
				<td>
					${userConcern.concernName}
				</td>
				<shiro:hasPermission name="center:userConcern:edit"><td>
    				<a href="${ctx}/center/userConcern/form?id=${userConcern.id}">修改</a>
					<a href="${ctx}/center/userConcern/delete?id=${userConcern.id}" onclick="return confirmx('确认要删除该我的关注吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>