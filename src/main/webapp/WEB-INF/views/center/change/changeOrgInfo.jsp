<%@ page contentType="text/html;charset=UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中央投资项目服务与管理平台</title>
    <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/main.css" />
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/orgInfo.js"> </script>
</head>
<body>
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
<div id="pageContaienr">
 <div class="main">

	 <%@ include file="/WEB-INF/views/center/common/orgApplication.jsp"%>
    <div id="applicationContainer" class="main_right">
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
        <div class="jindubox">
        <c:if test="${ empty org.id }">
            <p class="on"><span>1</span>填写企业基本信息</p>
            <p><span>2</span>上传资质证明</p>
            <p><span>3</span>上传专职人员情况</p>
            <p><span>4</span>上传招标业绩明细</p>
             <p><span>5</span>省发展改革委初审</p>
              <p><span>6</span>招标中心终审</p>
        </c:if>
        <c:if test="${ !empty org.id }">
            <p onclick="switchPage(1);" class="on"><span>1</span>填写企业基本信息</p>
            <p <c:if test="${org.qualificationOn == 1 }">class="on1" onclick="switchPage(2);" </c:if>><span>2</span>上传资质证明</p>
            <p <c:if test="${org.staffOn == 1 }">class="on1"   onclick="switchPage(3);"</c:if>><span>3</span>上传专职人员情况</p>
            <p  <c:if test="${org.achieveOn == 1 }">class="on1" onclick="switchPage(4);"</c:if>><span>4</span>上传招标业绩明细</p>
             <p  <c:if test="${org.status == 2 || org.status == 5 || org.status == 3 || org.status == 6 }">class="on1" onclick="switchPage(5);"</c:if>><span>5</span>省发展改革委初审</p>
              <p <c:if test="${org.status == 3 || org.status == 6 }"  >class="on1" onclick="switchPage(6);"</c:if>><span>6</span>招标中心终审</p>
        </c:if>
        </div>
            	<input type="hidden" id="areaId" value="${org.area.id}" />
        
        <div  class="login_kuang congxin">
            <div class="top">
                <div class="left">企业基本信息ddd</div>
            </div>
            <div class="clear"></div>
            <form id="orgInfoForm" modelAttribute="org" action="${ctxFront}/org/org/edit"   class="form-horizontal" method="post" >
            <ul>
            	<input type="hidden" name="status" value="${org.status}" />
            	<input type="hidden" name="id" value="${org.id}" />
               <li><span><em>*</em>企业名称：</span><input type="text" name="name" class="text" required  maxlength="32" value="${org.name}"/></li>
		        <li><span><em>*</em>统一社会信用代码：</span><input type="text" name="scCode" id="scCode" value="${org.scCode}" class="text" required  maxlength="32"/><i id="msg"></i></li>
		        <li><span><em>*</em>所属地区：</span><select class="text" id="area" name="area">
		        </select></li>
		        <li><span><em>*</em>注册资本金（万元）：</span><input type="text" name="regMoney" value="${org.regMoney}" class="text" required maxlength="10"/></li>
				<li><span><em>*</em>企业注册地址：</span><input type="text"  name="regAddress" class="text" required  value="${org.regAddress}" maxlength="128"/></li>
		        <li><span>企业简介：</span><input type="text" name="description" class="text" value="${org.description}" maxlength="400"/> </li>
		        <li>
		        	<span><em>*</em>上传主体单位的证件图片：</span>
		            <div class="divpic">
		            	示例<br />
		                <c:if test="${empty org.logoPath }">
		                <img id="logoImg" src="${ctxStatic}/images/tu1.png" width="144" height="191" /><br />
		            	</c:if>
		            	<c:if test="${!empty org.logoPath }">
		                <img id="logoImg" src="${imgServer }${org.logoPath }" width="144" height="191" /><br />
		            	</c:if>
		                <a href="javascript:show('PicAlert1','PicBoxShow1','#org_logoPath','#logoImg');">查看大图</a>
		            </div>
           				<input type="file" onchange="ajaxUploadImage('uploadMain','logoImg','org_logoPath');" id="uploadMain" class="uploadMainStyle uploadFile" name="file"/>
		                 <input type="button" onclick="clickUpload('uploadMain')" class="button1" id="uploadMainSpot" value="点击上传"/>   
		            <input type="hidden" value="${org.logoPath }" name="logoPath" id="org_logoPath" />
		        </li>
	        <div class="clear"></div>
	    </ul>
		    <div class="top">
		    	<div class="left">法人代表信息</div>
		    </div>
		    <div class="clear"></div>
		    <ul>
		    	<li><span><em>*</em>法人代表姓名：</span><input type="text" name="legalrepName" class="text" required value="${org.legalrepName}" maxlength="32"/></li>
		        <li><span><em>*</em>法人代表身份证：</span><input type="text" name="legalrepCard" class="text" required  value="${org.legalrepCard}" maxlength="18"/></li>
		        <li><span>联系电话：</span><input type="text" name="legalrepPhone" class="text" value="${org.legalrepPhone}" maxlength="11"></li>
		        <li><span>通讯地址：</span><input type="text" name="legalrepAddress" class="text" value="${org.legalrepAddress}" maxlength="225"/></li>
				<li><span>邮政编码：</span><input type="text" name="legalrepZipcode" class="text" value="${org.legalrepZipcode}" maxlength="32"/></li>
		        <li>
		        	<span><em>*</em>上传法人代表的证件图片：</span>
		            <div class="divpic">
		            	示例<br />
		            	<c:if test="${empty org.legalrepPhoto }">
		                <img id="logoImg2" src="${ctxStatic}/images/tu2.png" width="144" height="191" /><br />
		            	</c:if>
		            	<c:if test="${!empty org.legalrepPhoto }">
		                <img id="logoImg2" src="${imgServer }${org.legalrepPhoto }" width="144" height="191" /><br />
		            	</c:if>
		                <a href="javascript:show('PicAlert1','PicBoxShow1','#org_legalrepPhoto','#logoImg2');">查看大图</a>
		            </div>
		            <input type="file" onchange="ajaxUploadImage('UploadID','logoImg2','org_legalrepPhoto');" name="file" id="UploadID" class="uploadFile"/>
            <input type="button" id="UploadIDpoints" onclick="clickUpload('UploadID')" class="button1" value="点击上传" id="test"  /> 
            <input type="hidden" name="legalrepPhoto" value="${org.legalrepPhoto }" id="org_legalrepPhoto" />
		             
		        </li>
		        <div class="clear"></div>
		            </ul>
		            <div class="login_kuang_button">
		            
		            
		           <c:if test="${empty org.id||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
                <input type="submit" onclick="nextSubmit('orgInfoForm','reload',2);" class="button" value="下一步" />&nbsp;
               </c:if>
		                <c:if test="${not empty org.id and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}">
		                 <input type="submit" onclick="ajaxSubmit('orgInfoForm','reload');" class="button" value="保存修改" />&nbsp;
		            	<input id="bakAjax" type="button"  onclick="ajaxBak();" class="button1" value="重新提交"  />
		            	  </c:if>
		            </div>
		             
		    </form>
		        </div>
		    </div>
		    
		    <div class="clear"></div>
		    </div>
		    <div class="PicAlert" id="PicAlert1" onclick="hide('PicAlert1','PicBoxShow1')"></div>
          <div class="PicBoxShow" id="PicBoxShow1" onclick="hide('PicAlert1','PicBoxShow1')"><span><img id="imgContainer"  src="" /></span></div>
	</div>	  
	<input value="${org.id }"/>:org<input value="${org.staffOn }"/>:staff<input value="${org.qualificationOn }"/>:zizhi<input value="${org.achieveOn }"/>:yeji  
   <%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
   </body>
   </html>