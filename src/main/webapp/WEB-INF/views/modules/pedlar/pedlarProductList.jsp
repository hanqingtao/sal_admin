<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商贩产品信息管理</title>
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
		<li class="active"><a href="${ctx}/pedlar/pedlarProduct/">商贩产品信息列表</a></li>
		<shiro:hasPermission name="pedlar:pedlarProduct:edit"><li><a href="${ctx}/pedlar/pedlarProduct/form">商贩产品信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pedlarProduct" action="${ctx}/pedlar/pedlarProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分类名称：</label>
				<form:input path="catalogName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分类名称</th>
				<shiro:hasPermission name="pedlar:pedlarProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pedlarProduct">
			<tr>
				<td><a href="${ctx}/pedlar/pedlarProduct/form?id=${pedlarProduct.id}">
					${pedlarProduct.catalogName}
				</a></td>
				<shiro:hasPermission name="pedlar:pedlarProduct:edit"><td>
    				<a href="${ctx}/pedlar/pedlarProduct/form?id=${pedlarProduct.id}">修改</a>
					<a href="${ctx}/pedlar/pedlarProduct/delete?id=${pedlarProduct.id}" onclick="return confirmx('确认要删除该商贩产品信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>