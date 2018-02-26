<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构举报信息管理</title>
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
		<li class="active"><a href="${ctx}/pro/orgReport/">公众投诉信息列表</a></li>
		<!--<shiro:hasPermission name="pro:orgReport:edit"><li><a href="${ctx}/pro/orgReport/form">代理机构举报信息添加</a></li></shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" modelAttribute="orgReport" action="${ctx}/pro/orgReport/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<!-- <li><label>联系人：</label>
				<form:input path="contact" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> -->
			<li><label>主体单位名称：</label>
				<form:input path="orgName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="projectName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th>代理机构</th>
			<th>项目名称</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>联系邮箱</th>
				<th>举报内容</th>
				<th>举报时间</th>
				<!--<shiro:hasPermission name="pro:orgReport:edit"><th>操作</th></shiro:hasPermission>-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orgReport">
			<tr>
			    <td>${orgReport.org.name}</td>
			    <td>${orgReport.project.name}</td>
				<td> 
					${orgReport.contact}
				</a></td>
				<td>
					${orgReport.phone}
				</td>
				<td>${orgReport.email}</td>
				<td>${orgReport.content}</td>
				<td><fmt:formatDate value="${orgReport.createTime}" pattern="yyyy-MM-dd  hh:mm:ss"/></td>
				<!--<shiro:hasPermission name="pro:orgReport:edit"><td>
    				<a href="${ctx}/pro/orgReport/form?id=${orgReport.id}">修改</a>
					<a href="${ctx}/pro/orgReport/delete?id=${orgReport.id}" onclick="return confirmx('确认要删除该代理机构举报信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>