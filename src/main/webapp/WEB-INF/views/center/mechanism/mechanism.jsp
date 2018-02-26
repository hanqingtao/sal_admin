<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css" />

<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
 <div id="pageContaienr">
 <div class="main" id="mechanismMainContaienr">

	 <%@ include file="/WEB-INF/views/center/common/orgApplication.jsp"%>
    <div class="main_right">
    <div id="applicationContainer">
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
	        <b>备案申请列表</b>
	        <table class="table1">
	            <tr>
	                <th>申请ID</th>
	                <th>代理机构名称</th>
	                <th>进展</th>
	                <th>创建时间</th>
	                <th>操作</th>
	            </tr>
	            <c:if test="${empty org}">
			            <tr>
			            	<td colspan="5">您还未备案，<a href="javascript:changeEntry(${org.id});">点击此处备案</a></td>
			            </tr>
	            </c:if>
	            <c:if test="${not empty org}">
			            <tr id="orgInfo">
			            	<td>${org.scCode}</td>
			            	<td>${org.name}</td>
			            	<td>
			            	<c:if test="${org.status==0}">未提交</c:if>
			            	<c:if test="${org.status==1}">提交省发改委审核</c:if>
			            	<c:if test="${org.status==2}">省发改委审核通过</c:if>
			            	<c:if test="${org.status==5}">省发改委审核拒绝</c:if>
			            	<c:if test="${org.status==3}">招标中心审核通过</c:if>
			            	<c:if test="${org.status==6}">招标中心审核拒绝</c:if>
			            	<c:if test="${org.status==4}">黑名单</c:if>
			            	<c:if test="${org.status==7}">注销</c:if>
			            	</td>
			            	<td><fmt:formatDate value='${org.createDate}' pattern='yyyy-MM-dd'/></td>
			            	<td> 
			            	    <a href="javascript:changeEntry(${org.id});">查看</a>
			            	&nbsp;&nbsp;<a href="javascript:cancellation(${org.id});">删除</a></td>
			            	<script>
			            	$(function(){
			            		if(${org.status}==7){
			            			$("#orgInfo td").css("color","#ccc");
			            	         $("#orgInfo td>a").attr("href","javascript:;").css("color","#ccc");
			            		}
			            	})
			            		
			            
			            	function cancellation(orgId){
			            		$.ajax({
			            	        async:true,
			            	        url:ctxFront+"/org/org/cancellation",
			            	        type:"post",
			            	        data:{"orgId":orgId},
			            	        success:function(data){
			            	         $("body").html(data);
			            	         $("#orgInfo td").css("color","#ccc");
			            	         $("#orgInfo td>a").attr("href","javascript:;").css("color","#ccc");
			            	        }
			            	    });
			            	}
			            	</script>
			            </tr>
	            </c:if>
	        </table>
	        <b>备案介绍</b>
	        <dl class="tablebox">
	        	<dt>备案介绍 </dt>
	            <dd>首先在管理平台注册帐号，然后填写主体信息、提交省发改委初审，招标中心终审。</dd>
	            <dt>备案流程</dt>
	            <dd>首先在管理平台注册帐号，然后填写主体信息、提交省发改委初审，招标中心终审。</dd>
	            <dd><img src="${ctxStatic}/images/tu3.png" width="803" height="175" /></dd>
	        </dl>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div class="login_top" style="width:84%;display:none;position:absolute;top:15%;right:8%;border:1px solid #ccc;background-color:#fff;" id="orgStatus">
    
	<p class="on"><a href="${ctxFront}"><span>1</span>帐号注册</a></p>
	<p class="on"><a href="${ctxFront}/org/org/changeEntry?id=${org.id}"><span>2</span>填写企业基本信息</a></p>
	<p class="on"><a href="${ctxFront}/org/orgQualification/changeEntry"><span>3</span>上传企业资质</a></p>
	<p class="on"><a href="${ctxFront}/org/orgStaff/changeEntry"><span>4</span>上传专职人员情况</a></p>
	<p class="on"><a href="${ctxFront}/org/orgAchieve/changeEntry"><span>5</span>上传招标业绩明细</a></p>
	<p  class="on"><a href="${ctxFront}/org/org/fgwEntry"><span>6</span>注册成功</a></p>
	 
	 <c:choose>
	    <c:when test="${org.status==1}">
	         <p ><a href="${ctxFront}/org/org/fgwEntry"><span>7</span>省发展改革委初审</a></p>
                 <p  ><a href="javascript:;"><span>8</span>招标中心终审</a></p>
	    </c:when>
	    <c:when test="${org.status==2}">
	        <p ><a href="${ctxFront}/org/org/fgwEntry"><span>7</span>省发展改革委审核通过</a></p>
                 <p  ><a href="javascript:;"><span>8</span>招标中心终审</a></p>
	    </c:when>
	    
	    <c:when test="${org.status==3}">
	         <p class="on"><a href="${ctxFront}/org/org/fgwEntry"><span>7</span>省发展改革委审核拒绝</a></p>
	         <p  class="on"><a href="${ctxFront}/org/org/fgwEntry"><span>8</span>招标中心终审通过</a></p>
	    </c:when>
	    <c:when test="${org.status==5}">
	         <p class="on"><a href="${ctxFront}/org/org/fgwEntry"><span>7</span>省发展改革委审核拒绝</a></p>
                 <p  ><a href="javascript:;"><span>8</span>招标中心终审</a></p>
	    </c:when>
	    <c:when test="${org.status==6}">
	         <p class="on"><a href="${ctxFront}/org/org/fgwEntry"><span>7</span>省发展改革委审核拒绝</a></p>
	                 <p  class="on"><a href="${ctxFront}/org/org/fgwEntry"><span>8</span>招标中心终审拒绝</a></p>
	    </c:when>
	    
	    <c:otherwise>
	          <p ><a href=""><span>7</span>省发展改革委初审</a></p>
         	 <p  ><a href=""><span>8</span>招标中心终审</a></p>
	    </c:otherwise>
	</c:choose>
	 
</div> 
<button id="close" style="position:absolute;top:15%;right:8%;display:none">关闭</button>
 
<script>
   function lookStatus(status){
	 
	   $("#orgStatus").show();
	   $("#close").show();
	   
   }
   $("#close").click(function(){
	   $("#orgStatus").hide();
	   $(this).hide();
   })
</script>
</div>
<%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
