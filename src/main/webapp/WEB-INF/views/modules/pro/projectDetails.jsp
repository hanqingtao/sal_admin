<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息表管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css" />
	<style>
	p{ margin-bottom:0px;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pro/project/">项目列表</a></li>
		<li class="active"><a href="${ctx}/pro/project/form?id=${project.id}">项目详情 </a></li>
	</ul><br/>
	 <div class="dailibox">
	<div class="biaoti">${project.name}</div> 
	<div class="daohang"><span  >项目进度：
	    <c:if test="${projectFlow.noticeStatus==1}">已上传招标公告或投标邀请书</c:if>
	    <c:if test="${projectFlow.tenderStatus==1}">已上传招标文件</c:if>
	    <c:if test="${projectFlow.reportStatus==1}">已上传评标报告</c:if>
	    <c:if test="${projectFlow.bidStatus==1}">已上传中标结果</c:if>
	</span>发布时间：<fmt:formatDate value='${project.opentenderTime}' pattern='yyyy-MM-dd '/>&nbsp;&nbsp;&nbsp;&nbsp; </div>
    
    <div class="table1_biaoti"><span></span>项目基本信息</div>
    <div class="gongshi" style="height:570px; margin-top:0px;">
    	<p class="p1">项目名称&nbsp;&nbsp;</p>
        <p class="p7">${project.name}</p>
        <p class="p1">项目业主&nbsp;&nbsp;</p>
        <p class="p7">${engineer.owner}</p>
        <p class="p9"><span class="por_top">相关批文及文号&nbsp;&nbsp;</span> </p>
        <p class="p10">可研报告批准文件及文号</p>
		<p class="p11">${engineer.reportNumber}</p>
        <p class="p10">核准文件及文号</p>
        <p class="p11">${engineer.approvalNumber}</p>
        <p class="p10">备案文件及文号</p>
        <p class="p11">${engineer.recordNumber}</p>
        <p class="p1">项目所在省份（区、市）&nbsp;&nbsp;</p>
        <p class="p8">${engineer.area.name}</p>
        <p class="p1">所属行业&nbsp;&nbsp;</p>
        <p class="p8">${fns:getDictLabel(engineer.industry, 'industry', '')}   </p>
        <p class="p1">具体建设地点&nbsp;&nbsp;</p>
        <p class="p8">${engineer.builtSite}</p>
        <p class="p1">建设规模&nbsp;&nbsp;</p>
        <p class="p8">${engineer.builtScale}</p>
        <p class="p1">项目总投资&nbsp;&nbsp;</p>
        <p class="p8"><fmt:formatNumber type="number" value="${engineer.totalMoney}" pattern="0.00" maxFractionDigits="2"/>万元</p>
        <p class="p1">中央投资额&nbsp;&nbsp;</p>
        <p class="p8"><fmt:formatNumber type="number" value="${engineer.centralMoney} " pattern="0.00" maxFractionDigits="2"/>万元</p>
    	<p class="p1">中央投资资金具体来源与数额&nbsp;&nbsp;</p>
        <p class="p7">${fns:getDictLabel(engineer.centralinvestType, 'centralinvest_type', '')}&nbsp;&nbsp;<fmt:formatNumber type="number" value="${engineer.centralinvestMoney}" pattern="0.00" maxFractionDigits="2"/>万元 </p>
        <p class="p1">中央投资资金使用方式&nbsp;&nbsp;</p>
        <p class="p7">${fns:getDictLabel(engineer.centraluseType, 'centraluse_type', '')}</p>
    </div>
    <div class="table1_biaoti"><span></span>招标情况</div>
    <div class="gongshi" style="height:615px; margin-top:0px;">
    	<p class="p1">招标项目名称&nbsp;&nbsp;</p>
        <p class="p8">${project.name}</p>
        <p class="p1">招标编号&nbsp;&nbsp;</p>
        <p class="p8">${project.sn}</p>
        <p class="p1">招标人&nbsp;&nbsp;</p>
        <p class="p8">${project.tenderee}</p>
        <p class="p1">联系人及电话&nbsp;&nbsp;</p>
        <p class="p8">${project.tenderee}</p>
		<p class="p9">招标代理机构&nbsp;&nbsp;<br />招标项目负责人&nbsp;&nbsp;<br />招标类型/内容&nbsp;&nbsp;</p>
		<p class="p7">${project.biddingAgency}</p>
		<p class="p8">${project.leader}</p>
        <p class="p1">联系电话&nbsp;&nbsp;</p>
        <p class="p8">${project.contact}</p>  
		<p class="p7">${fns:getDictLabel(project.tenderType, 'project_type', '')}</p>
        <p class="p1">委托时间&nbsp;&nbsp;</p>
        <p class="p8"> <fmt:formatDate value='${project.entrustTime}' pattern='yyyy-MM-dd'/></p>
        <p class="p1">委托金额&nbsp;&nbsp;</p>
        <p class="p8"><fmt:formatNumber type="number" value="${project.entrustMoney}" pattern="0.00" maxFractionDigits="2"/>万元</p>
        <p class="p1">公告发布时间&nbsp;&nbsp;</p>
        <p class="p7"><fmt:formatDate value='${project.noticeDate}' pattern='yyyy-MM-dd'/></p>
        <p class="p1">公告媒体&nbsp;&nbsp;</p>
        <p class="p8">${project.noticeMedia}</p>
        <p class="p1">对投标人资质、资信要求&nbsp;&nbsp;</p>
        <p class="p8">${project.creditRequire}</p>
        <p class="p1">招标人&nbsp;&nbsp;</p>
        <p class="p8">${project.tenderee}</p>
        <p class="p1">开标时间&nbsp;&nbsp;</p>
        <p class="p8"><fmt:formatDate value='${project.opentenderTime}' pattern='yyyy-MM-dd'/></p>
        <p class="p1">招标方式&nbsp;&nbsp;</p>
        <p class="p7">${fns:getDictLabel(project.tenderType, 'tender_type', '')}</p>
         <p class="p1">招标内容&nbsp;&nbsp;</p>
        <p class="p7">${project.content}</p>
    </div>
    <div class="table1_biaoti"><span></span>中标情况</div>
    <div class="gongshi" style="height:180px; margin-top:0px;">
    	<p class="p1">中标单位名称&nbsp;&nbsp;</p>
        <p class="p8">${projectFlow.bidUnit}</p>
        <p class="p1">联系人及电话&nbsp;&nbsp;</p>
        <p class="p8">${projectFlow.contact}&nbsp;&nbsp;${projectFlow.phone}</p>
        <p class="p1">中标金额&nbsp;&nbsp;</p>
        <p class="p8"><fmt:formatNumber type="number" value="${projectFlow.bidMoney}" pattern="0.00" maxFractionDigits="2"/>万元</p>
        <p class="p1">节资率&nbsp;&nbsp;</p>
        <p class="p8">${projectFlow.capitalsavingRatio}</p>
        <p class="p1">中标通知书发出时间&nbsp;&nbsp;</p>
        <p class="p7"><fmt:formatDate value='${projectFlow.bidnoticeTime}' pattern='yyyy-MM-dd'/></p>
    </div>
    <div class="table1_biaoti"><span></span>附件</div>
    <div class="gongshi" style="height:220px; margin-top:0px;">
        
        <c:if test="${projectFlow.noticeStatus==1}">
             <p class="p12"><a target="_blank" href="${imgServer}${projectFlow.noticeFile }">1、招标公告或投标邀请书</a></p>
        </c:if>
        
        
	    <c:if test="${projectFlow.tenderStatus==1}">
             <p class="p12"><a target="_blank" href="${imgServer}${projectFlow.tenderFile }">2、招标文件</a></p>
        </c:if>
        
        
	    <c:if test="${projectFlow.reportStatus==1}">
              <p class="p12"><a target="_blank" href="${imgServer}${projectFlow.fileEvaluation }">3、开标记录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="${imgServer}${projectFlow.fileEvaluationexpert }">评标专家组成</a></p>
        </c:if>
        
	    <c:if test="${projectFlow.bidStatus==1}">
              <p class="p12"><a target="_blank" href="${imgServer}${projectFlow.fileBid }">4、中标通知书</a></p>
        </c:if>
        
        
    </div>
</div>
</body>
</html>