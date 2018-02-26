<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/operation.js"></script>
	
 <script type="text/javascript">
		 $.validator.setDefaults({
		     submitHandler: function() {
		     form.submit();
		     }
		 });
		 
		$(document).ready(function() {
			$.ajax({
		           type : "post",
		           async:true,
		           url : ctxFront + "/etc/area",
		           data : { "option" : "" },
		           dataType : "json",
		           success : function(dataJson) {
		               var str="";
		                 for(var i=0;i<dataJson.length;i++){
		                     str+="<option value="+dataJson[i].id+">"+dataJson[i].name+"</option>";
		                 }
		                 $("#area").html(str); 
		                 $("#area").find("option")[0].remove();
		           }
		       });
			$.ajax({
		           type : "post",
		           async:true,
		           url : ctxFront + "/etc/term",
		           data : { "option" : "org_nature" },
		           dataType : "json",
		           success : function(dataJson) {
		               var str="";
		                 for(var i=0;i<dataJson.length;i++){
		                     str+="<option value="+dataJson[i].value+">"+dataJson[i].label+"</option>";
		                 }
		                 $("#nature").html(str); 
		           }
		       });
			 $("#orgInfoForm").validate();
			  $("#scCode").blur(function(){
				  
				  $.ajax({
					   type: "post",
					   url: "${ctxFront}/org/org/repeat",
					   data: {"sc_code":$("#scCode").val()},
					   dataType:'json', 
					   success: function(msg){
						   if(msg=="该信用代码可以使用"){
							   $("#msg").css({"color":"green","margin-left":"10px"});   
						   }
						    $("#msg").html(msg);
					   } 
					});
	            })
	            $("#uploadMainSpot").click(function(){
	            	$("#uploadMain").click();	
	            	 
	            })
	            $("#UploadIDpoints").click(function(){
	            	$("#UploadID").click();	
	            	 
	            })
	            
		});
	</script>
</head>
<body>
<%@ include file="/WEB-INF/views/center/common/orgTop.jsp"%> 
<div id="pageContainer">    
<div class="login_top">
	<a href="#"><p class="on"><span>1</span>帐号注册</p></a>
    <a href="#"><p class="on"><span>2</span>填写企业基本信息</p></a>
    <p><span>3</span>上传企业资质</p>
    <p><span>4</span>上传专职人员情况</p>
    <p><span>5</span>上传招标业绩明细</p>
    <p><span>6</span>注册成功</p>
</div> 
<div class="login_kuang">
	<div class="top">
    	<div class="left">企业基本信息</div>
    	<div class="right"><span>*</span>为必填项</div>
    </div>
    <div class="clear"></div>
    <form id="orgInfoForm" modelAttribute="org" action="${ctxFront}/org/org/save"  class="form-horizontal" method="post"  enctype="multipart/form-data" >
    <ul>
    	<li><span><em>*</em>企业名称：</span><input type="text" name="name" class="text" required value="${org.name}"   maxlength="32" /></li>
		<li><span><em>*</em>统一社会信用代码：</span><input type="text" name="scCode" id="scCode" value="${org.scCode}"  class="text" required  maxlength="32"/><i id="msg"></i></li>
		<li><span><em>*</em>所属地区：</span><select class="text" name="area" id="area"   required > </select> </li>
      <li><span><em>*</em>企业性质：</span><select class="text" name="nature" id="nature"   required ></select> </li>
        <li><span><em>*</em>注册资本金（万元）：</span><input type="number" name="regMoney" class="text" required  value="${org.regMoney}" maxlength="10"/></li>
		<li><span><em>*</em>企业注册地址：</span><input type="text"  name="regAddress" class="text" required  value="${org.regAddress}" maxlength="128"/></li>
		<li><span>企业简介：</span><input type="text" name="description" class="text"   value="${org.regAddress}" maxlength="400"/><i>字数限制400</i></li>
        <li>
        	<span><em>*</em>上传主体单位的证件图片：</span>
            <div class="divpic">
            	示例<br />
                <img id="imgshow111" src="${ctxStatic}/images/tu1.png" width="144" height="191" /><br />
                <a href="javascript:show('PicAlert1','PicBoxShow1','#logoPathhidden','#imgshow111');" >查看大图</a>
            </div>
           <input type="button" class="button1" id="uploadMainSpot" value="点击上传"/>   
           <input type="hidden"  name="logoPath" id="logoPathhidden" />
           <input type="file" class="required" style="visibility: hidden;" onchange="ajaxUploadImage('uploadMain','imgshow111','logoPathhidden');" id="uploadMain" class="uploadMainStyle uploadFile" name="file"  value=""/>
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
		<li><span><em>*</em>法人代表身份证：</span><input type="text" name="legalrepCard" class="text" required value="${org.legalrepCard}" maxlength="18"/></li>
		<li><span>联系电话：</span><input type="number"  name="legalrepPhone" class="text"  value="${org.legalrepPhone}" maxlength="11"/></li>
		<li><span>通讯地址：</span><input type="text" name="legalrepAddress" class="text"   value="${org.legalrepAddress}" maxlength="255"/></li>
		<li><span>邮政编码：</span><input  type="number" name="legalrepZipcode" class="text"   value="${org.legalrepZipcode}" maxlength="32"/></li>
        <li>
        	<span><em>*</em>上传法人代表的证件图片：</span>
            <div class="divpic">
            	示例<br />
                <img id="imgshow222" src="${ctxStatic}/images/tu2.png" width="144" height="191" /><br />
                <a href="javascript:show('PicAlert1','PicBoxShow1','#legalrepPhotohidden','#imgshow222');">查看大图</a>
            </div>
            <input type="file" class="required" required  style="visibility: hidden;" name="file"   onchange="ajaxUploadImage('UploadID','imgshow222','legalrepPhotohidden');"  id="UploadID" class="uploadMainStyle uploadFile"  />
            <input type="hidden" name="legalrepPhoto" id="legalrepPhotohidden" />
            <input type="button"  id="UploadIDpoints" class="button1" value="点击上传" id="test"  /> 
             
        </li>
        <div class="clear"></div>
    </ul>
    <div class="login_kuang_button">
    	 <input type="submit" class="button" value="下一步" />
    </div>
   </form>
    <div class="clear"></div>
</div>
</div>
<div class="PicAlert" id="PicAlert1" onclick="hide('PicAlert1','PicBoxShow1')"></div>
<div class="PicBoxShow" id="PicBoxShow1" onclick="hide('PicAlert1','PicBoxShow1')"><span><img id="imgContainer"  src="" /></span></div>
 <%@ include file="/WEB-INF/views/center/common/footer.jsp"%> 
</body>
</html>