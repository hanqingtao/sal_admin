<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商贩信息管理</title>
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
		<li class="active"><a href="${ctx}/pedlar/pedlar/">商贩信息列表</a></li>
		<shiro:hasPermission name="pedlar:pedlar:edit"><li><a href="${ctx}/pedlar/pedlar/form">商贩信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pedlar" action="${ctx}/pedlar/pedlar/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商贩名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>所属集市名称：</label>
				<form:input path="marketName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商贩名称</th>
				<th>所属集市名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pedlar:pedlar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pedlar">
			<tr>
				<td><a href="${ctx}/pedlar/pedlar/form?id=${pedlar.id}">
					${pedlar.name}
				</a></td>
				<td>
					${pedlar.marketName}
				</td>
				<td>
					<fmt:formatDate value="${pedlar.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pedlar.remarks}
				</td>
				<shiro:hasPermission name="pedlar:pedlar:edit"><td>
    				<a href="${ctx}/pedlar/pedlar/form?id=${pedlar.id}">修改</a>
					<a href="${ctx}/pedlar/pedlar/delete?id=${pedlar.id}" onclick="return confirmx('确认要删除该商贩信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>