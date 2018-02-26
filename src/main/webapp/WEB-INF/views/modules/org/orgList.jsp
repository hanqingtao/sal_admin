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
		function selectAll(flag){
			if(searchForm.ids == undefined){
					return ;
			}else{
				if(flag == "true"){
					searchForm.chkall.checked = true;
				}
				searchForm.ids.checked = searchForm.chkall.checkecd;
				for(var i=0;i<searchForm.ids.length;i++){
					searchForm.ids[i].checked = searchForm.chkall.checked;
				}
			}
		}
		 
		function operation(OperationFlag){
			var checkBox = document.getElementsByName("ids");
			 var num = checkBox.length;
			 var ids = "";
			 for(var i=0;i<num;i++){
				 if(checkBox[i].checked){
					 ids += checkBox[i].value+",";
				 }
			 }
			 if(ids==""){
				    alert("请至少选择一条记录！");
					return false;
				}
			 ids=ids.substring(0,ids.length-1);
			 $.ajax({
			        type : "post",
			        async:true,
			        url : ctx+ "/org/org/operation",
			        data : {"OperationFlag":OperationFlag,"ids":ids},
			        dataType : "json",
			        success : function(data) {
			        	$('#btnSubmit').click();
			        }   
			    });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/org/org/listPass">名录管理</a></li>
		<!--<shiro:hasPermission name="org:org:edit"><li><a href="${ctx}/org/org/form">代理机构添加</a></li></shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" name="searchForm" modelAttribute="org" action="${ctx}/org/org/listPass" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主体单位名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<!--  <li><label style="width: 100px;">社会信用代码：</label>
				<form:input path="scCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>-->
			<!-- 
			<li><label>申报状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('org_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			 -->
			<li><label>企业性质：</label>
				<form:select path="nature" class="input-medium">
					<form:option value="" label=""/>
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('org_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<!--
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${org.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${org.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> 
			<li><label style="width: 80px;">状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('org_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			-->
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			 
			
			<li> 
			&nbsp;&nbsp;&nbsp;&nbsp;<input   class="btn" onclick="operation('8')"  type="button" value="暂停"/>&nbsp;&nbsp;
			<!-- <input  class="btn  " onclick="selectAll()"  onclick="operation('9')"  type="button" value="启动"/>&nbsp;&nbsp; -->
			<input  class="btn  "  onclick="operation('9')"  type="button" value="启动"/>&nbsp;&nbsp;
			<input   class="btn  "  type="button"  onclick="operation('4')"  value="列入黑名单"/> &nbsp;&nbsp;
			 
			</li>
			<li class="clearfix"></li>
		</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="chkall"  onClick="javascript:selectAll()"  />选择</th>
				<th style="text-align: center;">主体单位名称</th>
				<th style="text-align: center;">状态</th>
				<th style="text-align: center;">企业性质</th>
				<th style="text-align: center;">注册日期</th>
				<shiro:hasPermission name="org:org:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="org">
			<tr>
				<td>
					<input type="checkbox" name = "ids" value="${org.id}" /> 
				</td>
				<td>
				 <a href="${ctx}/org/org/details?id=${org.id}">  
					${org.name}
				 </a> 
				</td>
				<td style="text-align: center;">
					${fns:getDictLabel(org.status, 'org_status', '')}
				</td>
				<td style="text-align: center;">
					${fns:getDictLabel(org.nature, 'org_nature', '')}
				</td>
				<td style="text-align: center;">
					<fmt:formatDate value="${org.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			 
				<shiro:hasPermission name="org:org:edit"><td style="text-align: center;">
    				 	<a href="${ctx}/org/orgbak/orgBakList?id=${org.id}" >备案记录</a>
					<a href="${ctx}/pro/creditRecord/find?id=${org.id}" >信用记录</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	</form:form>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">
/*
	//1.获取全选按钮
    var chkAllObj = document.getElementById("chkAll");
    //2.获取所有单选框
    var chkOneObjs = document.getElementsByClassName("chkOne");
    //3.设置点击事件
    chkAllObj.onclick = function () {
        //3.1获取全选框的选中状态
        var isChecked = this.checked;
        //3.2for循环设置所有的单选框的选中状态
        for (var i = 0; i < chkOneObjs.length; i++) {
            chkOneObjs[i].checked = isChecked;
        }
    }
    */
     
    </script>
</html>