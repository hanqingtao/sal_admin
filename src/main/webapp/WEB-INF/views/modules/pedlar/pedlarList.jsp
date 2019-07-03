<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商家信息管理</title>
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
		<li class="active"><a href="${ctx}/pedlar/pedlar/">商家信息列表</a></li>
		<shiro:hasPermission name="pedlar:pedlar:edit"><li><a href="${ctx}/pedlar/pedlar/form">商家信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pedlar" action="${ctx}/pedlar/pedlar/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商家名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>用户：</label>
				<sys:treeselect id="user" name="user.id" value="${pedlar.user.id}" labelName="user.name" labelValue="${pedlar.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pedlar_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>商家名称</th>
				<th>用户</th>
				<th>创建者</th>
				<th>更新时间</th>
				<th>状态</th>
				<th>审核时间</th>
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
					${pedlar.user.name}
				</td>
				<td>
					${pedlar.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${pedlar.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(pedlar.status, 'pedlar_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${pedlar.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pedlar:pedlar:edit"><td>
    				<a href="${ctx}/pedlar/pedlar/form?id=${pedlar.id}">修改</a>
					<a href="${ctx}/pedlar/pedlar/delete?id=${pedlar.id}" onclick="return confirmx('确认要删除该商家信息吗？', this.href)">删除</a>
					<a href="${ctx}/pedlar/pedlarMarket/form?id=${pedlar.id}">集市列表</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>