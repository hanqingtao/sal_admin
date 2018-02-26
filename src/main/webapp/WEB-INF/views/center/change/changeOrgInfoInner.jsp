  <%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <script type="text/javascript" src="${ctxStatic}/js/orgInfo.js"> </script>
         <input type="hidden" id="flag" value="${flag}"/> 
         <input type="hidden" id="qualificationOn" value="${org.qualificationOn}"/>  
         <input type="hidden" id="achieveOn" value="${org.achieveOn}"/> 
         <input type="hidden" id="staffOn" value="${org.staffOn}"/> 
         <input type="hidden" id="statusFlag" value="${org.status}"/> 
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
        <div class="jindubox">
            <p onclick="switchPage(1);" class="on"><span>1</span>填写企业基本信息</p>
            <p  <c:if test="${org.qualificationOn == 1 }">class="on1" onclick="switchPage(2);"</c:if>><span>2</span>上传资质证明</p>
            <p  <c:if test="${org.staffOn == 1 }">class="on1"  onclick="switchPage(3);"</c:if>><span>3</span>上传专职人员情况</p>
            <p  <c:if test="${org.achieveOn == 1 }">class="on1" onclick="switchPage(4);"</c:if>><span>4</span>上传招标业绩明细</p>
            <c:if test="${org.status==3 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}"><p  ><span>5</span>变更成功</p></c:if>
            <c:if test="${org.id==null||org.status==0||org.status==4||org.status==5||org.status==6||org.status==1||org.status==2||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
             <p  <c:if test="${org.status == 2 || org.status == 5 || org.status == 3 || org.status == 6 }">class="on1" onclick="switchPage(5);"</c:if>><span>5</span>省发展改革委初审</p>
              <p  <c:if test="${org.status == 3 || org.status == 6 }">class="on1" onclick="switchPage(6);"</c:if>><span>6</span>招标中心终审</p>
              
              </c:if>
        </div>
        <div  class="login_kuang congxin" id="loginBox">
           <c:if test="${org.status==1 || org.status==2 || org.status==5}">
            <div class="promptBox">
                 <div class="promptContent" ><span>备案提交后的审核期间，禁止修改企业信息和相关资料！</span><span onclick="closePrompt()" class="promptClose">×</span></div>
            </div>
            </c:if>
            <div class="top">
                <div class="left">企业基本信息</div>
            </div>
            <input type="hidden" id="areaIdinner" value="${org.area.id}" />
            <div class="clear"></div>
            <form id="orgInfoForm" modelAttribute="org" action="${ctxFront}/org/org/edit"   class="form-horizontal" method="post"  enctype="multipart/form-data" >
            <ul>
            	<input type="hidden" name="status" value="${org.status}" />
            	<input type="hidden" id="orgId" name="id" value="${org.id}" />
               <li><span><em>*</em>企业名称：</span><input type="text" id="orgInfoName"  name="name" class="text"    maxlength="32" value="${org.name}" <c:if test="${org.status==1 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}">disabled="disabled"</c:if>/><i id="orgInfoNameMsg"></i></li>
		        <li><span><em>*</em>统一社会信用代码：</span><input type="text" name="scCode" id="scCode" value="${org.scCode}"  <c:if test="${org.status==1  and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}">disabled="disabled"</c:if>  class="text"    maxlength="32"/><i id="scCodeMsg"></i></li>
		        <li><span><em>*</em>所属地区：</span><select class="text" id="area" style="width:200px;" name="area"></select><i id="areaMsg"></i></li>
		        <li><span><em>*</em>注册资本金（万元）：</span><input type="number" id="regMoney" min="0" name="regMoney" value="<fmt:formatNumber value="${org.regMoney}" pattern="#,##0.0#"/>" class="text"   maxlength="10"/><i id="regMoneyMsg"></i></li>
				<li><span><em>*</em>企业注册地址：</span><input type="text" id="regAddress"  name="regAddress" class="text"    value="${org.regAddress}" maxlength="128"/><i id="regAddressMsg"></i></li>
		        <li><span>企业简介：</span>
		        <textarea id="corporateProfile" name="description" class="text" maxlength="400" style="display:inline-block;width:489px;height:142px;"  >${org.description}</textarea>&nbsp;&nbsp;<i id="corporateProfileLog"></i>
		        <li>
		        	<span><em>*</em>上传主体单位的证件图片：</span>
		            <div class="divpic">
		            	示例<br />
		                <c:if test="${empty org.logoPath }">
		                <img id="logoImg" src="${ctxStatic}/images/pic2.jpg" width="144" height="191" /><br />
		            	</c:if>
		            	<c:if test="${!empty org.logoPath }">
		                <img id="logoImg" src="${imgServer }${org.logoPath }" width="144" height="191" /><br />
		            	</c:if>
		                <a href="javascript:show('PicAlert1','PicBoxShow1','#org_logoPath','#logoImg');">查看大图</a>
		            </div>
           				<input type="file" onchange="ajaxUploadImage('uploadMain','logoImg','org_logoPath');" id="uploadMain" class="uploadMainStyle uploadFile" name="file" /><i id="uploadMainMsg"></i>
		                 <input type="button" onclick="clickUpload('uploadMain')" class="button1 ts" id="uploadMainSpot" value="点击上传"/>   
		            <input type="hidden" value="${org.logoPath }" name="logoPath" id="org_logoPath" />
		        </li>
	        <div class="clear"></div>
	    </ul>
		    <div class="top">
		    	<div class="left">法人代表信息</div>
		    </div>
		    <div class="clear"></div>
		    <ul>
		    	<li><span><em>*</em>法人代表姓名：</span><input type="text" id="legalrepName" name="legalrepName" class="text"   value="${org.legalrepName}" maxlength="32"/><i id="legalrepNameMsg"></i></li>
		        <li><span><em>*</em>法人代表身份证：</span><input type="text" id="legalrepCard" name="legalrepCard" class="text"   value="${org.legalrepCard}" maxlength="18"/><i id="legalrepCardMsg"></i></li>
		        <li><span>联系电话：</span><input type="text" name="legalrepPhone" id="legalrepPhone" class="text"    value="${org.legalrepPhone}" maxlength="11"></li>
		        <li><span>通讯地址：</span><input type="text" name="legalrepAddress" id="legalrepAddress" class="text"   value="${org.legalrepAddress}" maxlength="225"/></li>
				<li><span>邮政编码：</span><input type="text" name="legalrepZipcode" id="legalrepZipcode" class="text"   value="${org.legalrepZipcode}" maxlength="32"/></li>
		        <li>
		        	<span><em>*</em>上传法人代表的证件图片：</span>
		            <div class="divpic">
		            	示例<br />
		            	<c:if test="${empty org.legalrepPhoto }">
		                <img id="logoImg2" src="${ctxStatic}/images/pic1.jpg" width="144" height="191" /><br />
		            	</c:if>
		            	<c:if test="${!empty org.legalrepPhoto }">
		                <img id="logoImg2" src="${imgServer }${org.legalrepPhoto }" width="144" height="191" /><br />
		            	</c:if>
		                 <a href="javascript:show('PicAlert1','PicBoxShow1','#org_legalrepPhoto','#logoImg2');">查看大图</a>
		            </div>
		            <input type="file" onchange="ajaxUploadImage('UploadID','logoImg2','org_legalrepPhoto');" name="file" id="UploadID" class="uploadFile"/><i id="UploadIDMsg"></i>
            <input type="button" id="UploadIDpoints" onclick="clickUpload('UploadID')" class="button1 ts" value="点击上传" id="test"  /> 
            <input type="hidden" name="legalrepPhoto" value="${org.legalrepPhoto }" id="org_legalrepPhoto" />
            
            
		             <input type="hidden" name="saveFlag" id="saveFlag" />
		        </li>
		       
		        <div class="clear"></div>
		            </ul>
		            <div id="login_box_button"  class="login_kuang_button">
		             <c:if test="${org.id==null||org.status==0||org.status==4||org.status==5||org.status==6||org.status==1||org.status==2||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
		             <input type="submit" onclick="nextOrgInfoSubmit();" class="button" style="width:auto;padding:0 20px"  value="下一步，上传资质证件" />&nbsp;&nbsp;
		             <input type="submit" onclick="ajaxOrgSubmit();" class="button1" value="保&nbsp;&nbsp;存" />&nbsp;&nbsp;<span id="saveInfo" ></span>
		            
		             </c:if>
		              <c:if test="${org.status==3 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}">
		                 <input type="submit" onclick="ajaxOrgSubmit()" class="button" value="保存修改" />&nbsp; <span id="mess"></span>
		           		<input id="bakAjax" type="button"  onclick="ajaxBak();" class="button1" value="重新提交"  />
		           		</c:if>
		            </div>
		            <div class="clear"></div>
		    </form>
		        </div>
		         <div class="PicAlert" id="PicAlert1" onclick="hide('PicAlert1','PicBoxShow1')"></div>
          <div class="PicBoxShow" id="PicBoxShow1" onclick="hide('PicAlert1','PicBoxShow1')"><span><img id="imgContainer"  src="" /></span></div>
 