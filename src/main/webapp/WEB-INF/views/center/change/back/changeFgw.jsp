<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 	</head>
<body style="background:#fff;">
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
<div id="pageContaienr">
 <div class="main">

	 <%@ include file="/WEB-INF/views/center/common/orgApplication.jsp"%>
    <div class="main_right">
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
        <div class="jindubox">
            <p class="on"><span>1</span>填写企业基本信息</p>
            <p class="on"><span>2</span>上传资质证明</p>
            <p class="on"><span>3</span>上传专职人员情况</p>
            <p class="on"><span>4</span>上传招标业绩明细</p>
            
            <p  class="on"><span>5</span>省发展改革委初审</p>
           
            <c:if test="${org.status==1 }">
              <p ><span>6</span>招标中心终审</p>
               </c:if>
                <c:if test="${org.status==2 }">
              <p class="on"><span>6</span>招标中心终审</p>
               </c:if>
            
        </div>
        <div class="login_kuang congxin">
            
           <dl>
            <c:if test="${org.status==1 }">
            
                <dt><img src="${ctxStatic}/images/yuan1.png" /> 备案申请已提交到省发展改革委，<br />审核一般需要5-10个工作日，请耐心等待</dt>
                </c:if>
                 <c:if test="${org.status==2 }">
            
               <dt><img src="${ctxStatic}/images/yuan.png" /> 省发展改革委初审已经通过，请继续完成接下来的步骤。</dt>
                </c:if>
                <c:if test="${org.status==3 }">
            
              <dt class="zt"><img src="${ctxStatic}/images/yuan.png" />  恭喜您完成更新!</dt>
                </c:if>
                 <c:if test="${org.status==5 }">
            
              <dt class="zt"><img src="${ctxStatic}/images/yuan.png" /> 省发改委拒绝</dt>
                </c:if>
                <c:if test="${org.status==6 }">
            
              <dt class="zt"><img src="${ctxStatic}/images/yuan.png" /> 招标中拒绝</dt>
                </c:if>
            </dl>
        </div>
    </div>
    <div class="clear"></div>
</div>
</div>
<%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
</body>
</html>