<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户激活码关系管理</title>
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
	<%@ include file="/WEB-INF/views/include/comcss.jsp"%>
	<link href="${ctxStatic}/modules/BiddingCenter/css/cdkeyUsersList.css" rel="stylesheet">
</head>
<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInUp">
	<div class="row">
		<div class="col-sm-12">

			<div class="ibox">

				<div class="ibox-content">
					<ul class="nav nav-tabs">
						<li class="active"><a href="${ctx}/users/cdkeyUsers/">用户激活码关系列表</a></li>
						<shiro:hasPermission name="users:cdkeyUsers:edit"><li><a href="${ctx}/users/cdkeyUsers/form">用户激活码关系添加</a></li></shiro:hasPermission>
					</ul>
					
					<form:form id="searchForm" modelAttribute="cdkeyUsers" action="${ctx}/users/cdkeyUsers/" method="post">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					
					<div class="row m-b-sm m-t-sm marginTop20">

							<div class="col-md-1">

								<button id="btnSubmit" class="btn btn-primary "   
									type="submit" value="查询">搜索</button>
								
							</div>
						</div>
					</form:form>
					
					<sys:message content="${message}"/>
					
					<div class="project-list">
						<div class="table-responsive marginTop20">
							<table class="table table-bordered">
								<thead>
								<shiro:hasPermission name="users:cdkeyUsers:edit"><th>操作</th></shiro:hasPermission>
								</thead>
								<tbody>
								<c:forEach items="${page.list}" var="cdkeyUsers">
									<tr>
										<shiro:hasPermission name="users:cdkeyUsers:edit"><td>
											<a class="btn btn-xs btn-white btn-outline" href="${ctx}/users/cdkeyUsers/form?id=${cdkeyUsers.id}"><i class="fa fa-pencil"></i> 编辑</a>
											<a class="btn btn-xs btn-white btn-outline deletebut" href="${ctx}/users/cdkeyUsers/delete?id=${cdkeyUsers.id}" onclick="return confirmx('确认要删除该用户激活码关系吗？', this.href)"><i class="fa fa-trash"></i>删除</a>
										</td></shiro:hasPermission>
									</tr>
								</c:forEach>

								</tbody>
								
							</table>

						</div>

					</div>

				</div>

			</div>
		</div>
	</div>
</div>

	<div class="pagination">${page}</div>
					</div>				
				</div>
			</div>
		</div>
    </div>
	
	<%@ include file="/WEB-INF/views/include/comjs.jsp"%>
	<script src="${ctxStatic}/modules/BiddingCenter/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${ctxStatic}/modules/BiddingCenter/js/cdkeyUsersList.js"></script>
	
</body>
</html>