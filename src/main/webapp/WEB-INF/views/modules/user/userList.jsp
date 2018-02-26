<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li class="active"><a href="${ctx}/user/user/">用户列表</a></li>
		<shiro:hasPermission name="user:user:edit"><li><a href="${ctx}/user/user/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/user/user/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>登录名：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>用户状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${user.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${user.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登录名</th>
				<th>真实姓名</th>
				<th>用户状态</th>
				<th>创建时间</th>
				<shiro:hasPermission name="user:user:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td><a href="${ctx}/user/user/form?id=${user.id}">
					${user.loginName}
				</a></td>
				<td>
					${user.userName}
				</td>
				<td>
					${fns:getDictLabel(user.status, 'user_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="user:user:edit"><td>
    				<a href="${ctx}/user/user/form?id=${user.id}">修改</a>
					<a href="${ctx}/user/user/delete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>