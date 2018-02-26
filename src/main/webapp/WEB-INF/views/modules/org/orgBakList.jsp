<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构管理</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/main.css" />
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
		function stop(){
			 
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">代理机构备案列表</a></li>
	</ul>
	<form:form id="searchForm" name="searchForm" modelAttribute="org" action="${ctx}/org/orgbak/orgBakList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <ul class="ul-form">
			<li>
			<h3>${org.name }，历史备案记录</h3>
			</li>
			
			<li style="float: left;">
			<input id="btnSubmit" onclick="javascript:window.location.href='${ctx}/org/org/listPass'" class="btn  "  type="button" value="返回"/> &nbsp;&nbsp;
			 
			</li>
		</ul> --%>
		<div class="customContent">
	    	<h4>${org.name } 历史备案记录</h4> 
		  <div class="rightContent">
			  <a href="${ctx}/org/org/listPass">返回</a>
		  </div>
	  </div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="chkall"  onClick="javascript:selectAll()"  />选择</th>
				<th>主体单位名称</th>
				<th>类型</th>
				<th>备案日期</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="org">
			<tr>
				<td>
					<input type="checkbox" id="ids" value="${org.id}" /> 
				</td>
				<td>
				 <a href="${ctx}/org/orgbak/orgBakDetail?id=${org.id}">  
					${org.name}
				 </a> 
				</td>
				<td>
					<c:if test="${org.bakType == 0 }">首次备案</c:if>
					<c:if test="${org.bakType == 1 }">变更</c:if>
				</td>
				 
				<td>
					<fmt:formatDate value="${org.bakTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</form:form>
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