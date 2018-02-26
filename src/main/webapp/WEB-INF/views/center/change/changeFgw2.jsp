<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 	</head>
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
        <div class="jindubox">
            <p onclick="switchPage(1);" class="on1"><span>1</span>填写企业基本信息</p>
           <p onclick="switchPage(2);" <c:if test="${org.qualificationOn == 1 }">class="on1"</c:if>><span>2</span>上传资质证明</p>
            <p onclick="switchPage(3);" <c:if test="${org.staffOn == 1 }">class="on1"</c:if>><span>3</span>上传专职人员情况</p>
            <p onclick="switchPage(4);" <c:if test="${org.achieveOn == 1 }">class="on1"</c:if>><span>4</span>上传招标业绩明细</p>
             <p onclick="switchPage(5);" <c:if test="${org.status == 2 || org.status == 5 || org.status == 3 || org.status == 6 }">class="on1"</c:if>><span>5</span>省发展改革委初审</p>
              <p onclick="switchPage(6);" class="on"><span>6</span>招标中心终审</p>
            
        </div>
        <div class="login_kuang congxin">
            
           <dl>
            <c:if test="${org.status==1 }">
            
                <dt><img src="${ctxStatic}/images/yuan1.png" /> 备案申请已提交到省发展改革委，<br />审核一般需要5-10个工作日，请耐心等待</dt>
                </c:if>
                 <c:if test="${org.status==2 }">
            
               <dt><img src="${ctxStatic}/images/yuan.png" /> 省发展改革委初审已经通过，等待招标中心审核。</dt>
                </c:if>
                <c:if test="${org.status==3 }">
            
              <dt class="zt"><img src="${ctxStatic}/images/yuan.png" />&nbsp;招标中心审核通过！</dt>
               <dd><input type="button" class="button" value="返回我的应用中心" onClick="qualificationManagement();" /></dd>
                </c:if>
                 <c:if test="${org.status==5 }">
            
              <dt class="zt"><img src="${ctxStatic}/images/yuan.png" /> 省发改委拒绝</dt>
                </c:if>
                <c:if test="${org.status==6 }">
            
              <dt class="zt"><img src="${ctxStatic}/images/yuan2.png" />&nbsp;招标中心审核未通过！</dt>
                </c:if>
            </dl>
        </div>
