<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>集市信息管理管理</title>
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
		<li class="active"><a href="${ctx}/market/market/">集市信息管理列表</a></li>
		<shiro:hasPermission name="market:market:edit"><li><a href="${ctx}/market/market/form">集市信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="market" action="${ctx}/market/market/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>集市名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>推荐指数：</label>
				<form:select path="recommend" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('market_reco')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>集市名称</th>
				<th>关注数</th>
				<th>推荐指数</th>
				<th>创建者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="market:market:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="market">
			<tr>
				<td><a href="${ctx}/market/market/form?id=${market.id}">
					${market.name}
				</a></td>
				<td>
					${market.collectCount}
				</td>
				<td>
					${fns:getDictLabel(market.recommend, 'market_reco', '')}
				</td>
				<td>
					${market.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${market.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${market.remarks}
				</td>
				<shiro:hasPermission name="market:market:edit"><td>
    				<a href="${ctx}/market/market/form?id=${market.id}">修改</a>
					<a href="${ctx}/market/market/delete?id=${market.id}" onclick="return confirmx('确认要删除该集市信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>