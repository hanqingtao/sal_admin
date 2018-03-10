<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价信息管理</title>
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
		<li class="active"><a href="${ctx}/eval/comment/">评价信息列表</a></li>
		<shiro:hasPermission name="eval:comment:edit"><li><a href="${ctx}/eval/comment/form">评价信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comment" action="${ctx}/eval/comment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评价 差评0  中评 1 好评 2：</label>
				<form:select path="evaluate" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('evaluate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>评价 差评0  中评 1 好评 2</th>
				<shiro:hasPermission name="eval:comment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comment">
			<tr>
				<td><a href="${ctx}/eval/comment/form?id=${comment.id}">
					${fns:getDictLabel(comment.evaluate, 'evaluate', '')}
				</a></td>
				<shiro:hasPermission name="eval:comment:edit"><td>
    				<a href="${ctx}/eval/comment/form?id=${comment.id}">修改</a>
					<a href="${ctx}/eval/comment/delete?id=${comment.id}" onclick="return confirmx('确认要删除该评价信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>