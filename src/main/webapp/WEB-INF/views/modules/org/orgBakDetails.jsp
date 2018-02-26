<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构变更详情</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css" />
</head>
<body> 
    
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/org/orgbak/orgBakList?id=${orgBak.orgId}"> 机构备案列表</a></li>
	    <li class="active"><a href="${ctx}/org/orgbak/orgBakDetail?id=${orgBak.id}">机构变更详细信息</a></li> 
	</ul><br/>
  
	<div class="BackstageTop">
		<p>备案编号：<b><span>${orgBak.sn }</span>(<fmt:formatDate value="${orgBak.bakTime }" pattern="yyyy-MM-dd"/>)</b></p>
	</div>
	<div class="dailibox">
		<div class="gongshi" style="height:auto; padding-bottom:10px;">
			<p class="p13">代理机构备案申请
			<span>（<c:if test="${orgBak.bakType == 0 }">首次备案</c:if>
					<c:if test="${orgBak.bakType == 1 }">变更</c:if>
			）</span>
			</p>
			<p class="p1">企业名称&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.name }</p>
			<p class="p1">统一社会信用代码&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.scCode }</p>
			<p class="p1">企业性质&nbsp;&nbsp;</p>
			<p class="p8">${fns:getDictLabel(orgBak.nature, 'org_nature', '')}</p>
			<p class="p1">所属地区&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.area.name }</p>
			<p class="p1">注册资本金&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.regMoney }万元</p>
			<p class="p1">企业注册地址&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.regAddress }</p>
			 
			<p class="p4">企业简介&nbsp;&nbsp;</p>
			<p class="p5">${orgBak.description }</p>
			<p class="p1">法人代表姓名&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.legalrepName }</p>
			<p class="p1">法人代表身份证&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.legalrepCard }</p>
			  
			<p class="p1">联系电话&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.legalrepPhone }</p>
			<p class="p1">通讯地址&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.legalrepAddress }</p>
			<p class="p1">邮政编码&nbsp;&nbsp;</p>
			<p class="p8">${orgBak.legalrepZipcode }</p>
			<p class="p1"></p>
			<p class="p8"></p>
			 <c:if test="${not empty orgBak.legalrepPhoto }">
			<p class="p6">法人身份证</p>
			<ul style="margin-bottom:10px;">
			   
				<li style="width:30%;margin-bottom:20px;">
					<img src="${imgServer}${orgBak.legalrepPhoto }"  width="360px" height="470px"  /><br /> ${orgBak.legalrepName }
				</li>
			
			</ul>
			 </c:if>
			<p class="p6">资质情况</p>
			<ul>
			 <c:forEach items="${orgBakQualification }"   var="orgQualification" >
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
       
          <c:forEach items="${orgBakStaff }" varStatus="i" begin="0" step="1" var="orgStaff" >
     
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
		            <td>${orgStaff.pro_title }</td>
		            <td>${orgStaff.dept }</td>
		            <td>  ${fns:getDictLabel(orgStaff.work_type, 'work_type', '')} </td>
		            <td  >
		               <a data="${imgServer}${orgStaff.card_photo }" href="javascript:;" onclick="show('PicAlert1','PicBoxShow1',this)">查看</a>
                    </td>
                    <td  >
                    <a href="javascript:;" data="${imgServer}${orgStaff.protitle_photo }"  onclick="show('PicAlert1','PicBoxShow1',this)">查看</a></td>
		        </tr>
				  
		  </c:forEach>
    </table>
     <%-- <div class="table1_biaoti"><span></span>信用记录</div>
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
    </table> --%>
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
       
        
         <c:forEach items="${orgBakAchieve }" varStatus="i" begin="0" step="1"  var="project">
     
     <tr>
            <td>${i.index + 1}</td>
            <td>${project.num }</td>
            <td>${project.name }</td>
            <td>${fns:getDictLabel(project.type, 'project_type', '')}</td>
            <td>${project.entrust_unit }</td>
            <td>${project.entrust_money }万元</td>
            <td>${project.projectFlow.bid_money }万元</td>
            <td><fmt:formatDate value="${project.tender_open_time }" pattern="yyyy-MM-dd "/></td>
            <td><fmt:formatDate value="${project.bid_time }" pattern="yyyy-MM-dd "/></td>
            <td>${project.notice_media }</td>
            <td><fmt:formatDate value="${project.notice_date }" pattern="yyyy-MM-dd "/></td>
        </tr>
			 
		  </c:forEach>
    </table>
	</div>
	
	
</body>
</html>