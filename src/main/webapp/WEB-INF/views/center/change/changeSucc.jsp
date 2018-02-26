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
             <p  class="on"><span>5</span>变更成功</p>
               
        </div>
        <div class="login_kuang congxin">
            
           <dl>
             
            
              <dt class="zt"><img src="${ctxStatic}/images/yuan.png" /> 恭喜您完成变更!</dt>
                
                <dd><input type="button" class="button" value="返回我的应用中心" onClick="qualificationManagement();" /></dd>
            </dl>
        </div>
