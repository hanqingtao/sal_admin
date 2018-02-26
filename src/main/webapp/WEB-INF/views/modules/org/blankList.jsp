<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchAgentList").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/org/org/listBlank">黑名单列表</a></li>
		<!--<shiro:hasPermission name="org:org:edit"><li><a href="${ctx}/org/org/form">代理机构添加</a></li></shiro:hasPermission>-->
	</ul>
	<form:form id="searchAgentList" modelAttribute="org" action="${ctx}/org/org/listBlank" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主体单位名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			 
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主体单位名称</th>
				<th>状态</th>
				<th>注册日期</th>
				<shiro:hasPermission name="org:org:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="org">
			<tr>
				<td><a href="${ctx}/org/org/details?id=${org.id}">
					${org.name}
				</a></td>
				 
				<td>
					${fns:getDictLabel(org.status, 'org_status', '')}
				</td>
				 
				<td>
					<fmt:formatDate value="${org.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			 
				<shiro:hasPermission name="org:org:edit"><td>
    				 	<a href="${ctx}/org/org/removeBlank?id=${org.id}" onclick="return confirmx('确定移除黑名单，将恢复到机构目录中向公众显示。', this.href)">移出黑名单</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>