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
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/org/org/listApprove">已审批机构列表</a></li>
		  
	</ul>
	<form:form id="searchForm" modelAttribute="org" action="${ctx}/org/org/listApproved" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label style="width: 100px;">社会信用代码：</label>
				<form:input path="scCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<c:if test="${false && !empty isSysM && isSysM == 1 }">
			<li><label>申报状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('org_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			</c:if>
			<li><label>企业性质：</label>
				<form:select path="nature" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('org_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${org.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${org.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('org_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>企业名称</th>
				<th>社会信用代码</th>
				<th>申报状态</th>
				<th>企业性质</th>
				<th>创建时间</th>
				<th>类型</th>
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
					${org.scCode}
				</td>
				<td>
					${fns:getDictLabel(org.status, 'org_status', '')}
				</td>
				<td>
					${fns:getDictLabel(org.nature, 'org_nature', '')}
				</td>
				<td>
					<fmt:formatDate value="${org.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(org.type, 'org_type', '')}
				</td>
				<shiro:hasPermission name="org:org:edit"><td>
    				<a href="${ctx}/org/org/details?id=${org.id}">查看</a>
    				<a href="${ctx}/org/org/delete?id=${org.id}" onclick="return confirmx('确认要删除该项目信息表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>