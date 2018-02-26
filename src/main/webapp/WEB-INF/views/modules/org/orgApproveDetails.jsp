<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理机构管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css" />
	<style>
	p{ margin-bottom:0px;}
	</style>
</head>
<body> 
    
	<ul class="nav nav-tabs">
		<li ><a href="javascript:history.go(-1);"> 代理机构1列表  </a></li>
	    <li class="active"><a href="${ctx}/org/org/details?id=${org.id}">代理机构审批详细信息</a></li> 
	</ul><br/>
  
	<div class="BackstageTop">
		<p>申请ID：<b><span>${org.name }</span>(<fmt:formatDate value="${org.createDate }" pattern="yyyy-MM-dd"/>)</b></p>
	</div>
	<div class="dailibox">
		<div class="gongshi" style="height:auto; padding-bottom:10px;">
			<p class="p13">代理机构备案申请<span><!-- （变更） --></span></p>
			<p class="p1">企业名称&nbsp;&nbsp;</p>
			<p class="p8">${org.name }</p>
			<p class="p1">统一社会信用代码&nbsp;&nbsp;</p>
			<p class="p8">${org.scCode }</p>
			<p class="p1">企业性质&nbsp;&nbsp;</p>
			<p class="p8">${fns:getDictLabel(org.nature, 'org_nature', '')}</p>
			<p class="p1">所属地区&nbsp;&nbsp;</p>
			<p class="p8">${org.area.name }</p>
			<p class="p1">注册资本金&nbsp;&nbsp;</p>
			<p class="p8">${org.regMoney }万元</p>
			<p class="p1">企业注册地址&nbsp;&nbsp;</p>
			<p class="p8">${org.regAddress }</p>
			 
			<p class="p4">企业简介&nbsp;&nbsp;</p>
			<p class="p5">${org.description }</p>
			<p class="p1">法人代表姓名&nbsp;&nbsp;</p>
			<p class="p8">${org.legalrepName }</p>
			<p class="p1">法人代表身份证&nbsp;&nbsp;</p>
			<p class="p8">${org.legalrepCard }</p>
			  
			<p class="p1">联系电话&nbsp;&nbsp;</p>
			<p class="p8">${org.legalrepPhone }</p>
			<p class="p1">通讯地址&nbsp;&nbsp;</p>
			<p class="p8">${org.legalrepAddress }</p>
			<p class="p1">邮政编码&nbsp;&nbsp;</p>
			<p class="p8">${org.legalrepZipcode }</p>
			<p class="p1"></p>
			<p class="p8"></p>
			 <c:if test="${not empty org.legalrepPhoto }">
			<p class="p6">法人身份证</p>
			<ul style="margin-bottom:10px;">
			   
				<li style="width:30%;margin-bottom:20px;">
					<img src="${imgServer}${org.legalrepPhoto }"  width="360px" height="470px"  /><br /> ${org.legalrepName }
				</li>
			
			</ul>
			 </c:if>
			<p class="p6">资质情况</p>
			<ul>
			 <c:forEach items="${orgQualificationList }"   var="orgQualification" >
				<li style="width:30%;">
					<img src="${imgServer}${orgQualification.path }"  width="360px" height="470px"  /><br /> ${orgQualification.name }<br />
				</li>
			 </c:forEach>
			</ul>
		</div>
		 <div class="table1_biaoti"><span></span>专职从业人员名单</div>
		 
		 <table class="table1">
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>身份证号</th>
            <th>在本单位工作起始时间</th>
            <th>毕业院校</th>
            <th>学历/学位</th>
            <th>社会保险号</th>
            <th>职称</th>
            <th>所属部门</th>
            <th>劳动关系</th>
            <th>身份证图片</th>
            <th>职称证图片</th>
        </tr>
       
          <c:forEach items="${orgStaffList }" varStatus="i" begin="0" step="1" var="orgStaff" >
     
		      <tr  rowspan="1">
		            <td colspan="1">${i.index + 1}</td>
		            <td>${orgStaff.name }</td>
		            <td>
		             ${fns:getDictLabel(orgStaff.sex, 'sex', '')}
		            </td>
		            <td>${orgStaff.card }</td>
		            <td><fmt:formatDate value="${orgStaff.workStart}" pattern="yyyy-MM-dd"/></td>
		            <td>${orgStaff.university }</td>
		            <td>
		              ${fns:getDictLabel(orgStaff.degree, 'degree', '')}
		           </td>
		           <td>${orgStaff.ssn }</td>
		            <td>${orgStaff.proTitle }</td>
		            <td>${orgStaff.dept }</td>
		            <td>  ${fns:getDictLabel(orgStaff.workType, 'work_type', '')} </td>
		            <td  >
		               <a data="${imgServer}${orgStaff.cardPhoto }" href="javascript:;" onclick="show('PicAlert1','PicBoxShow1',this)">查看</a>
                    </td>
                    <td  >
                    <a href="javascript:;" data="${imgServer}${orgStaff.protitlePhoto }"  onclick="show('PicAlert1','PicBoxShow1',this)">查看</a></td>
		        </tr>
				  
		  </c:forEach>
    </table>
     <div class="table1_biaoti"><span></span>信用记录</div>
    <table class="table1">
        <tr>
            <th>序号</th>
            <th>记录时间</th>
            <th>项目编号</th>
            <th>项目名称</th>
            <th>直接负责人</th>
            <th>违规情况</th>
            <th>行政处理决定</th>
        </tr>
        <c:forEach items="${creditRecordList }" varStatus="i" begin="0" step="1" var="creditRecord" >
        <tr>
            <td>${i.index + 1}</td>
            <td><fmt:formatDate value="${creditRecord.reportTime }" pattern="yyyy-MM-dd"/></td>
            <td>${creditRecord.projectCode }</td>
            <td>${creditRecord.projectName }</td>
            <td>${creditRecord.leader }</td>
            <td>${creditRecord.instruction }</td>
            <td>${creditRecord.result }</td>
        </tr>
        </c:forEach>
    </table>
    <div class="table1_biaoti"><span></span>近三年业绩</div>
    <table class="table1">
        <tr>
            <th>序号</th>
            <th>项目编号</th>
            <th>项目名称</th>
            <th>项目类型</th>
            <th>委托单位</th>
            <th>委托金额</th>
            <th>中标金额</th>
            <th>开标时间</th>
            <th>中标时间</th>
            <th>公告媒体</th>
            <th>公告时间</th>
        </tr>
       
        
         <c:forEach items="${orgAchieveList }" varStatus="i" begin="0" step="1"  var="orgAchieve">
     
     <tr>
            <td>${i.index + 1}</td>
            <td>${orgAchieve.num }</td>
            <td>${orgAchieve.name }</td>
            <td>${fns:getDictLabel(orgAchieve.type, 'project_type', '')}</td>
            <td>${orgAchieve.entrustUnit }</td>
            <td>${orgAchieve.entrustMoney }万元</td>
            <td>${orgAchieve.bidMoney }万元</td>
            <td><fmt:formatDate value="${orgAchieve.tenderOpenTime }" pattern="yyyy-MM-dd "/></td>
            <td><fmt:formatDate value="${orgAchieve.bidTime }" pattern="yyyy-MM-dd "/></td>
            <td>${orgAchieve.noticeMedia }</td>
            <td><fmt:formatDate value="${orgAchieve.noticeDate }" pattern="yyyy-MM-dd "/></td>
        </tr>
			 
		  </c:forEach>
    </table>
	</div>
	
	
	
	
	
	<c:if test="${not empty orgAuditList}">
	<dl class="BackstageBottom">
		<dt>处理意见</dt>
		<c:forEach items="${orgAuditList }"   var="orgAudit" >
				<dd><p class="p1">${orgAudit.user.name }</p><p class="p2"> 
				  <c:if test="${orgAudit.orgStatus==2 }">同意</c:if>
				  <c:if test="${orgAudit.orgStatus==5 }">不同意</c:if>
				</p><p class="p3"><fmt:formatDate value="${orgAudit.createDate }" pattern="yyyy-MM-dd  hh:mm:ss"/></p></dd>
			 </c:forEach>
	</dl>
	 </c:if>
	  	<c:if test="${s_sys==true}">
	  	<form  action="${ctx}/org/org/saveApproveForm" method="get" >
	  	 
	          <input value="${org.id }" type="hidden" name="orgId" />
    	      <input value="${ostatus }" type="hidden" />
				<dl class="BackstageBottom1">
				    
					<dt>协同</dt>
					<dd>
					   <c:if test="${ostatus == 1 }">
								<input type="radio" name="orgStatus" value="2" checked="checked"/>同意
								&nbsp;&nbsp;&nbsp;<input type="radio" name="orgStatus" value="5" />不同意
							</c:if>
							<c:if test="${ostatus == 2 || ostatus == 5 }">
								<input type="radio" name="orgStatus" value="3" checked="checked"/>同意
								&nbsp;&nbsp;&nbsp;<input type="radio" name="orgStatus" value="6" />不同意
							</c:if>
					</dd>
					<dd><textarea name="content"></textarea></dd>
					<dd>
						<input type="button" value="取消" class="button" onclick="history.go(-1)"/>
						<input type="submit" value="提交" class="button1" />
						<div class="clear"></div>
					</dd>
					
				</dl>
	</form>
	</c:if>
	
	<c:if test="${s_sys==false}">
	<form  action="${ctx}/org/org/saveApproveForm" method="get" >
	          <input value="${org.id }" type="hidden" name="orgId" />
    	      <input value="${ostatus }" type="hidden" />
				<dl class="BackstageBottom1">
				    
					<dt>协同</dt>
					<dd>
					   <c:if test="${ostatus == 1 }">
								<input type="radio" name="orgStatus" value="2" checked="checked"/>同意
								&nbsp;&nbsp;&nbsp;<input type="radio" name="orgStatus" value="5" />不同意
							</c:if>
							<c:if test="${ostatus == 2 || ostatus == 5 }">
								<input type="radio" name="orgStatus" value="3" checked="checked"/>同意
								&nbsp;&nbsp;&nbsp;<input type="radio" name="orgStatus" value="6" />不同意
							</c:if>
					</dd>
					<dd><textarea name="content"></textarea></dd>
					<dd>
						<input type="button" value="取消" class="button" onclick="history.go(-1)"/>
					 <input type="submit" value="提交" class="button1" /> 
					 
				 
						<div class="clear"></div>
					</dd>
					
				</dl>
				</form>
	</c:if>
	
 
<div class="PicAlert" id="PicAlert1" onclick="hide('PicAlert1','PicBoxShow1')"></div>
<div class="PicBoxShow" id="PicBoxShow1" onclick="hide('PicAlert1','PicBoxShow1')"><span><img id="imgContainer"  src="${ctxStatic}/images/tu1.png" /></span></div>
<script>
	function show(id1,id2,obj){
		$("#imgContainer").attr("src",$(obj).attr("data"));
		document.getElementById(id1).style.display="block";
		document.getElementById(id2).style.display="table";
	}
	function hide(id1,id2){
		document.getElementById(id1).style.display="none";
		document.getElementById(id2).style.display="none";
	}
</script>	
</body>
</html>