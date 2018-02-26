<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>咨询中心管理</title>
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
		<li class="active"><a href="${ctx}/counsel/counselCenter/">咨询中心列表</a></li>
		<shiro:hasPermission name="counsel:counselCenter:edit"><li><a href="${ctx}/counsel/counselCenter/form">咨询中心添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="counselCenter" action="${ctx}/counsel/counselCenter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工作时间</th>
				<th>咨询中心地址</th>
				<th>联系方式</th>
				<shiro:hasPermission name="counsel:counselCenter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="counselCenter">
			<tr>
				<td><a href="${ctx}/counsel/counselCenter/form?id=${counselCenter.id}">
					${counselCenter.workHour}
				</a></td>
				<td>
					${counselCenter.address}
				</td>
				<td>
					${counselCenter.contactWay}
				</td>
				<shiro:hasPermission name="counsel:counselCenter:edit"><td>
    				<a href="${ctx}/counsel/counselCenter/form?id=${counselCenter.id}">修改</a>
					<a href="${ctx}/counsel/counselCenter/delete?id=${counselCenter.id}" onclick="return confirmx('确认要删除该咨询中心吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>