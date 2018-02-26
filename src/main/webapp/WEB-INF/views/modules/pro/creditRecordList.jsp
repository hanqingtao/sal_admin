<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信用记录管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/main.css" />
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
		<li class="active"><a href="${ctx}/pro/creditRecord/find?id=${org.id}">信用记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="creditRecord" action="${ctx}/pro/creditRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!--<ul class="ul-form">
			<li><label>记录时间：</label>
				<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${creditRecord.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="projectName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>项目编号：</label>
				<form:input path="projectCode" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>直接负责人：</label>
				<form:input path="leader" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>-->
	</form:form>
	<sys:message content="${message}"/>
	
	   
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>记录时间</th>
				<th>项目编号</th>
				<th>项目名称</th>
				<th>直接负责人</th>
				<th>违规情况</th>
				<th>行政处理决定</th>
				<th>来源</th>
				<!--<shiro:hasPermission name="pro:creditRecord:edit"><th>操作</th></shiro:hasPermission>-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="creditRecord">
			<tr>
				<td> 
					<fmt:formatDate value="${creditRecord.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				 </td>
				<td>
					${creditRecord.projectCode}
				</td>
				<td>
					${creditRecord.projectName}
				</td>
				<td>
					${creditRecord.leader}
				</td>
				<td>
					${creditRecord.instruction}
				</td>
				<td>
					${creditRecord.result}
				</td>
				<td>
					 
				</td>
				<!--<shiro:hasPermission name="pro:creditRecord:edit"><td>
    				<a href="${ctx}/pro/creditRecord/form?id=${creditRecord.id}">修改</a>
					<a href="${ctx}/pro/creditRecord/delete?id=${creditRecord.id}" onclick="return confirmx('确认要删除该信用记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>