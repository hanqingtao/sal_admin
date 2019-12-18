<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>批次管理</title>
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
		//跳转到 激活码列表页面，同时根据 batch.id 作为查询条件
		function toCdkeyByBatchId(id){
			//alert(batchId);
			//$("#[cdkey.batch.id]").val(batchId);
			//alert("abc"+$("#batchId").val());
			//$("#frm").submit();
			//alert(id);
			var url = ctx+"/users/batch/batchExport?id="+id ;
			//alert(url);
			window.parent.open(url);
			
		}
		</script>
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/users/batch/">批次列表</a></li>
		<shiro:hasPermission name="users:batch:edit"><li><a href="${ctx}/users/batch/form">批次添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="batch" action="${ctx}/users/batch/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>批次名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>开始时间：</label>
				<input name="beginBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${batch.beginBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${batch.endBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="beginEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${batch.beginEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${batch.endEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>批次名称</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>数量</th>
				<shiro:hasPermission name="users:batch:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="batch">
			<tr>
				<td><a href="${ctx}/users/batch/form?id=${batch.id}">
					${batch.name}
				</a></td>
				<td>
					<fmt:formatDate value="${batch.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${batch.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${batch.count}
				</td>
				<shiro:hasPermission name="users:batch:edit"><td>
    					<a href="${ctx}/users/batch/form?id=${batch.id}">修改</a>
    					<a href="${ctx}/users/batch/batchExport?id=${batch.id}" target="_blank" onclick="return confirmx('导出激活码？', this.href)">导出</a>
    					<!-- <a href="#" onclick="toCdkeyByBatchId(${batch.id})" 	>导出</a>-->
					 <!--   ${ctx}/users/batch/batchExport?id=${batch.id} 
					 target="_blank" 
					 onclick="return confirmx('导出激活码？', this.href)" -->
						<a href="${ctx}/users/cdkey/listByBatch?batchId=${batch.id}">查看激活码</a>
					<!-- 
					<a href="#" onclick="toCdkeyByBatchId('${batch.id}')" >查看激活码</a>
					 -->
					<a href="${ctx}/users/batch/delete?id=${batch.id}" onclick="return confirmx('确认要删除该批次吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<!-- 
	<form id="frm" name="frm" type="hidden" action="${ctx}/users/cdkey/listByBatch" method="post">
		<input type="hidden" name="cdkey.batch.id" id="cdkey.batch.id"  value="8" />
	</form>
	 -->
</body>


</html>