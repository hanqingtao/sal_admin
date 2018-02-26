<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息表管理</title>
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
		<li class="active"><a href="${ctx}/pro/project/">项目列表</a></li>
		<%-- <shiro:hasPermission name="pro:project:edit"><li><a href="${ctx}/pro/project/form">项目信息表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="project" action="${ctx}/pro/project/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<!-- <li><label>招标编号：</label>
				<form:input path="sn" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>招标项目名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> -->
			<li><label class="longLabel">核准文件及文号：</label>
				<form:input path="approvalNumber" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			
			<li><label>代理机构：</label>
				<form:input path="biddingAgency" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			 
			<li>
			<label>地区：</label> 
				<sys:treeselect id="area" name="areaId" value="${project.areaId}" labelName="areaName" labelValue="${project.areaName}"
					title="区域"  url="/sys/area/treeData"   cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline">  </span>
			 </li>
			 <li><label  class="longLabel">是否中央投资项目：</label>
				 
				<form:select path="isCenter" class="input-xlarge " >
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label  >项目类型：</label>
				 
				<form:select path="tenderType" class="input-xlarge " >
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('tender_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>	<label>中标时间：</label> <input type="text" id="startTime"   value="<fmt:formatDate value="${project.startBidTime }" pattern="yyyy-MM-dd"/>" name="startBidTime" class="text1" placeholder="开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span>--
	            </span><input type="text" id="endTime" value="<fmt:formatDate value="${project.endBidTime }" pattern="yyyy-MM-dd"/>"  name="endBidTime" class="text1" placeholder="结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!--  <th>招标编号</th>
				<th>招标项目名称</th>
				<th>招标人</th>
				<th>委托单位</th>
				<th>招标类型</th>
				<shiro:hasPermission name="pro:project:edit"><th>操作</th></shiro:hasPermission> -->
				<th>核准文件及文号</th>
				<th>项目名称</th>
				<th>代理机构</th>
				<th>是否中央投资项目</th>
				<th>项目类型</th>
				<th>中标金额（万元）</th>
				<th>中标单位</th>
				<th>地区</th>
				<th>中标日期</th>
				<shiro:hasPermission name="pro:project:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="project">
			<tr>
				<%-- <td><a href="${ctx}/pro/project/form?id=${project.id}"> --%>
				<!--  <td><a href="${ctx}/pro/projectFlow/formByProId?projectId=${project.id}">
					${project.sn}
				</a></td>
				<td>
					${project.name}
				</td>
				<td>
					${project.tenderee}
				</td>
				<td>
					${project.entrustUnit}
				</td>
				<td>
					${fns:getDictLabel(project.tenderType, 'tender_type', '')}
				</td>
				<shiro:hasPermission name="pro:project:edit"><td> -->
    				<%-- <a href="${ctx}/pro/project/form?id=${project.id}">修改</a> --%>
					<!-- <a href="${ctx}/pro/project/delete?id=${project.id}" onclick="return confirmx('确认要删除该项目信息表吗？', this.href)">删除</a>-->
				<!-- </td></shiro:hasPermission> -->
				<td>
					${project.engineer.approvalNumber}
				</td>
				<td>
					${project.name}
				</td>
				<td>
					${project.biddingAgency}
				</td>
				<td>
				       ${fns:getDictLabel(project.isCenter, 'yes_no', '')}
				</td>
				<td>
				       ${fns:getDictLabel(project.tenderType, 'tender_type', '')}
				</td>
				<td>
				${project.projectFlow.bidMoney}
					
				</td>
				<td>
					${project.projectFlow.bidUnit}
				</td>
				<td>
					${project.areaName}
				</td>
				<td>
					<fmt:formatDate value="${project.bidTime}" pattern="yyyy-MM-dd  hh:mm:ss"/>
				</td>
				<shiro:hasPermission name="pro:project:view"><td> 
    				 <a href="${ctx}/pro/project/details?id=${project.id}">查看</a> 
				 </td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>