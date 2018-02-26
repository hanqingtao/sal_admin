<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抽查管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#searchForm").validate({
				errorContainer: "#messageBox2"
			});
			/* var date = new Date();
			var yearc = date.getFullYear();
			var rc = '${orgCheckS.year}';
			alert(rc);
			for(var i = yearc-10; i <= yearc+10; i++){
				var html = '';
				if(i == rc){
					html = '<option selected="selected" value="'+ i +'">'+ i +'</option>';
				}else{
					html = '<option value="'+ i +'">'+ i +'</option>';
				}
				$('#yearSearch').append(html);
			} */
			
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
		<li class="active"><a href="${ctx}/org/orgCheck/">抽查管理列表</a></li>
		<shiro:hasPermission name="org:orgCheck:edit"><li><a href="${ctx}/org/orgCheck/form">抽查管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orgCheck" action="${ctx}/org/orgCheck/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				 <form:input path="year" htmlEscape="false" maxlength="4" class="input-medium number date"/>
				<!-- <select id="yearSearch" name="year" class="input-medium number date">
				</select> -->
			</li>
			<%-- <li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orgCheck.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<span style="display:inline-block; float: left" id="messageBox2"></span>
			</li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年份</th>
				<th>机构比例</th>
				<th>项目比例</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="org:orgCheck:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orgCheck">
			<tr>
				<td>
				<%-- <a href="${ctx}/org/orgCheck/form?id=${orgCheck.id}"> --%>
					${orgCheck.year}
				<!-- </a> -->
				</td>
				<td>
					${orgCheck.rateOrg * 100}%
				</td>
				<td>
					${orgCheck.rateProject * 100}%
				</td>
				<td>
					<fmt:formatDate value="${orgCheck.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${orgCheck.remarks}
				</td>
				<shiro:hasPermission name="org:orgCheck:edit"><td>
    				<a href="${orgCheck.path }">查看抽查结果</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>