<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
<title>中央投资项目服务与管理平台</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/h5style.css" />
</head>

<body style="background:#fff;">
<div class="dailibox">
	<div class="biaoti">${org.name }</div>
    <div class="daohang">注册时间：<fmt:formatDate value="${org.createDate }" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;</div>
   
	<table class="table1">
        <tr>
            <td style="text-align:right;width:16%;">企业名称:&nbsp;&nbsp;</td>
            <td style="text-align:left; padding:0% 2%;">${org.name }</td>
        </tr>  
		<tr>  
			<td style="text-align:right;width:16%;">统一社会信用代码:&nbsp;&nbsp;</td>
			<td style="text-align:left; padding:0% 2%;">${org.scCode }</td>
		</tr>	
		<tr>  
			<td style="text-align:right;width:16%;">所属地区:&nbsp;&nbsp;</td>
            <td style="text-align:left; padding:0% 2%;">${org.area.name }</td>
		</tr>
		<tr>  
            <td style="text-align:right;width:16%;">注册资本金:&nbsp;&nbsp;</td>
			<td style="text-align:left; padding:0% 2%;"><fmt:formatNumber value="${org.regMoney}" pattern="#,##0.0#"/><c:if test="${not empty org.regMoney}"> 万元</c:if></td>
        </tr>
		<tr>  
            <td style="text-align:right;width:16%;">企业注册地址:&nbsp;&nbsp;</td>
			<td style="text-align:left; padding:0% 2%;">${org.regAddress }</td>
        </tr>
		<tr>  
            <td style="text-align:right;width:16%;">企业性质:&nbsp;&nbsp;</td>
			<td style="text-align:left; padding:0% 2%;">${fns:getDictLabel(org.nature, 'org_nature', '')}</td>
        </tr>
		<tr>  
            <td style="text-align:right;width:16%;">近三年总业绩:&nbsp;&nbsp;</td>
			<td style="text-align:left; padding:0% 2%;"><fmt:formatNumber value="${org.recentMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty org.recentMoney}"> 万元</c:if></td>
        </tr>
		<tr>  
            <td style="text-align:right;width:16%;">企业简介:&nbsp;&nbsp;</td>
			<td style="text-align:left; padding:2% 2%;">${org.description }</td>
        </tr>
		<tr>  
            <td style="text-align:right;width:16%;">资质情况:&nbsp;&nbsp;</td>
			<td style="text-align:left; padding:0% 2%;">
				<ul>
					<c:forEach items="${orgQualificationList }"   var="orgQualification" >
             
					        	<li>
					            	   <img src="${imgServer}${orgQualification.path }"/>${orgQualification.name }
					            </li>
            
                    </c:forEach>
				</ul>
			</td>
        </tr>
    </table>
    <div class="table1_biaoti">专职从业人员名单</div>
    <table class="table1">
        <tr>
            <th>序号</th>
            <th>姓名</th>
			<th>毕业院校</th>
			<th>职称</th>
            <th>所属部门</th>
            <th>劳动关系</th>
        </tr>
         <c:forEach items="${orgStaffList }" varStatus="i" begin="0" step="1" var="orgStaff" >
        <tr>
           <td>${i.index + 1}</td>
            <td>${orgStaff.name }</td>
            <td>${orgStaff.university }</td>
            <td>${orgStaff.proTitle }</td>
            <td>${orgStaff.dept }</td>
            <td>${orgStaff.workType }</td>
        </tr>
        </c:forEach>
    </table>
    <div class="table1_biaoti">信用记录</div>
    <table class="table1">
        <tr>
            <th>序号</th>
            <th>记录时间</th>
            <th>项目编号</th>
            <th>项目名称</th>
            <th>直接负责人</th>
            <th>违规情况</th>
        </tr>
        <c:forEach items="${creditRecordList }" varStatus="i" begin="0" step="1" var="creditRecord" >
	          <tr>
	             <td>${i.index + 1}</td>
	         <td><fmt:formatDate value="${creditRecord.reportTime }" pattern="yyyy-MM-dd"/></td>
	            <td>${creditRecord.projectCode }</td>
	             <td>${creditRecord.projectName }</td>
	              <td>${creditRecord.leader }</td>
	               <td>${creditRecord.result }</td>
	        </tr>
          </c:forEach>
    </table>
    <div class="table1_biaoti">中央投资项目</div>
    <table class="table1">
        <tr>
             <th>序号</th>
            <th>项目编号</th>
            <th>项目名称</th>
            <th>项目类型</th>
            <th>委托单位</th>
            <th>委托金额（万元）</th>
        </tr>
       <c:forEach items="${projectList }" varStatus="i" begin="0" step="1"  var="project">
	        <tr>
	            <td>${i.index + 1}</td>
	             <td>${project.sn }</td>
	            <td>${project.name }</td>
	              <td>${project.tenderType }</td>
	                <td>${project.entrustUnit }</td>
	                  <td><fmt:formatNumber value="${project.entrustMoney}" pattern="#,##0.0#"/>
		         </td>
	        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
