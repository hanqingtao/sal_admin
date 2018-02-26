<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构招标业绩管理</title>
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
		<li class="active"><a href="${ctx}/org/orgAchieve/">代理机构招标业绩列表</a></li>
		<shiro:hasPermission name="org:orgAchieve:edit"><li><a href="${ctx}/org/orgAchieve/form">代理机构招标业绩添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orgAchieve" action="${ctx}/org/orgAchieve/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目编号：</label>
				<form:input path="num" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>项目类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>委托金额：</label>
				<form:input path="entrustMoney" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>中标金额：</label>
				<form:input path="bidMoney" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>开标时间：</label>
				<input name="beginTenderOpenTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orgAchieve.beginTenderOpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endTenderOpenTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orgAchieve.endTenderOpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>中标时间：</label>
				<input name="beginBidTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orgAchieve.beginBidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endBidTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orgAchieve.endBidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>项目编号</th>
				<th>项目名称</th>
				<th>项目类型</th>
				<th>委托金额</th>
				<th>中标金额</th>
				<th>开标时间</th>
				<th>中标时间</th>
				<shiro:hasPermission name="org:orgAchieve:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orgAchieve">
			<tr>
				<td><a href="${ctx}/org/orgAchieve/form?id=${orgAchieve.id}">
					${orgAchieve.num}
				</a></td>
				<td>
					${orgAchieve.name}
				</td>
				<td>
					${fns:getDictLabel(orgAchieve.type, 'project_type', '')}
				</td>
				<td>
					${orgAchieve.entrustMoney}
				</td>
				<td>
					${orgAchieve.bidMoney}
				</td>
				<td>
					<fmt:formatDate value="${orgAchieve.tenderOpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${orgAchieve.bidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="org:orgAchieve:edit"><td>
    				<a href="${ctx}/org/orgAchieve/form?id=${orgAchieve.id}">修改</a>
					<a href="${ctx}/org/orgAchieve/delete?id=${orgAchieve.id}" onclick="return confirmx('确认要删除该代理机构招标业绩吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>