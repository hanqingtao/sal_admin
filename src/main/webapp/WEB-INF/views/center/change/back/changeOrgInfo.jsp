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
<script type="text/javascript" src="${ctxStatic}/js/orgInfo.js"> </script>
</head>
<body style="background:#fff;">
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
<div id="pageContaienr">
 <div class="main">

	 <%@ include file="/WEB-INF/views/center/common/orgApplication.jsp"%>
    <div id="mainContainer" class="main_right">
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
        <div class="jindubox">
            <p onclick="switchPage(1); class="on"><span>1</span>填写企业基本信息</p>
            <p onclick="switchPage(2);"><span>2</span>上传资质证明</p>
            <p onclick="switchPage(3);"><span>3</span>上传专职人员情况</p>
            <p onclick="switchPage(4);"><span>4</span>上传招标业绩明细</p>
             <p onclick="switchPage(5);"><span>5</span>省发展改革委初审</p>
              <p onclick="switchPage(6);"><span>6</span>招标中心终审</p>
        </div>
        <div  class="login_kuang congxin">
            <div class="top">
                <div class="left">企业基本信息222</div>
                <div class="right"><span>*</span>为必填项</div>
            </div>
            <div class="clear"></div>
            <form id="orgInfoForm" modelAttribute="org" action="${ctxFront}/org/org/change"   class="form-horizontal" method="post"  enctype="multipart/form-data" >
            <ul><input type="hidden" name="status" value="${org.status}" />
               <li><span><em>*</em>企业名称：</span><input type="text" name="name" class="text" required  maxlength="32" value="${org.name}"/></li>
		        <li><span><em>*</em>统一社会信用代码：</span><input type="text" name="scCode" id="scCode" value="${org.scCode}" class="text" required  maxlength="32"/><i id="msg"></i></li>
		        <li><span><em>*</em>所属地区：</span><select class="text" id="area" name="area">
		        <!-- <option value="2" <c:if test="${org.area.id==2}" >selected</c:if> >北京市</option>
		        <option value="3" <c:if test="${org.area.id==3}" >selected</c:if>>天津市</option>
		        <option value="4" <c:if test="${org.area.id==4}" >selected</c:if>>河北省</option> -->
		        </select></li>
		        <li><span><em>*</em>注册资本金（万元）：</span><input type="text" name="regMoney" value="${org.regMoney}" class="text" required maxlength="10"/></li>
				<li><span><em>*</em>企业注册地址：</span><input type="text"  name="regAddress" class="text" required  value="${org.regAddress}" maxlength="128"/></li>
		        <li><span>企业简介：</span><input type="text" name="description" class="text" value="${org.description}" maxlength="255"/></li>
		        <li>
		        	<span><em>*</em>上传主体单位的证件图片：</span>
		            <div class="divpic">
		            	示例<br />
		                <img src="${ctxStatic}/images/tu1.png" width="144" height="191" /><br />
		                <a href="#">查看大图</a>
		            </div>
		                 <input type="button" class="button1" id="uploadMainSpot" value="点击上传"/>   
           <input type="file" id="uploadMain" class="uploadMainStyle uploadFile" name="file1"  value="" required/>
		            
		        </li>
	        <div class="clear"></div>
	    </ul>
		    <div class="top">
		    	<div class="left">法人代表信息</div>
		        <div class="right"><span>*</span>为必填项</div>
		    </div>
		    <div class="clear"></div>
		    <ul>
		    	<li><span><em>*</em>法人代表姓名：</span><input type="text" name="legalrepName" class="text" required value="${org.legalrepName}" maxlength="32"/></li>
		        <li><span><em>*</em>法人代表身份证：</span><input type="text" name="legalrepCard" class="text" required  value="${org.legalrepCard}" maxlength="18"/></li>
		        <li><span>联系电话：</span><input type="text" name="legalrepPhone" class="text" required  value="${org.legalrepPhone}"/ maxlength="11"></li>
		        <li><span>通讯地址：</span><input type="text" name="legalrepAddress" class="text" required value="${org.legalrepAddress}" maxlength="225"/></li>
				<li><span>邮政编码：</span><input type="text" name="legalrepZipcode" class="text" required value="${org.legalrepZipcode}" maxlength="32"/></li>
		        <li>
		        	<span><em>*</em>上传法人代表的证件图片：</span>
		            <div class="divpic">
		            	示例<br />
		                <img src="${ctxStatic}/images/tu2.png" width="144" height="191" /><br />
		                <a href="#">查看大图</a>
		            </div>
		            <input type="file" name="file2" id="UploadID" class="uploadFile"/>
            <input type="button" id="UploadIDpoints" class="button1" value="点击上传" id="test"  /> 
		             
		        </li>
		        <div class="clear"></div>
		            </ul>
		            <div class="login_kuang_button">
		                 <input type="submit" class="button" value="保  存 下一步" />&nbsp; <span>${message }</span>
		            </div>
		             
		            <div class="clear"></div>
		    </form>
		        </div>
		    </div>
		    
		    <div class="clear"></div>
		    </div>
	</div>	    
   <%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
   </body></html>