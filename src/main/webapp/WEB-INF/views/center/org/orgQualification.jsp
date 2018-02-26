<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script src="${ctxStatic}/js/ajaxfileupload.js"></script>
 <script type="text/javascript">
		 $.validator.setDefaults({
		     submitHandler: function() {
		     form.submit();
		     }
		 });
		 $(document).ready(function() {
			 getOption();
			 $("#orgQualificationForm").validate();
		 })
		  function getOption(){
			 $.ajax({
	                type:"post",
	                url:ctxFront+"/etc/term.do",
	                data:{"option":"qualification_grade"},
	                dataType : "json",
	                success:function(data){
	                    var str="";
	                    for(var i=0;i<data.length;i++){
	                        str+="<option value="+data[i].value+">"+data[i].label+"</option>";
	                    }
                    $("#parcelContent").find("select").last().html(str);
	                    
	                }
	            });
		 }
		  function uploadQualification(obj){
			  $(obj).parent().find('input')[0].click();
		  }
		  function delGroup(obj){
			  $(obj).parent().parent().remove();
		  }
	</script>
 </head>
<body>
<%@ include file="/WEB-INF/views/center/common/orgTop.jsp"%> 
<div class="login_top">
	<a href="#"><p class="on"><span>1</span>帐号注册</p></a>
    <a href="#"><p class="on"><span>2</span>填写企业基本信息</p></a>
    <a href="#"><p class="on"><span>3</span>上传企业资质</p></a>
    <p><span>4</span>上传专职人员情况</p>
    <p><span>5</span>上传招标业绩明细</p>
    <p><span>6</span>注册成功</p>
</div> 
<div class="login_kuang">
	<div class="top">
    	<div class="left">企业资质</div>
        <div class="right"><span>*</span>为必填项</div>
    </div>
    <div class="clear"></div>
    <form id="orgQualificationForm"  action="${ctxFront}/org/orgQualification/save"   class="form-horizontal" method="post"  enctype="multipart/form-data" >
     <input id="flag" name="flag" type="hidden" value="1"/>
    <div id="parcelContent">
     <ul class="ulys" id="qualificationContent">
         
	    	<li><span><em>*</em>资格名称：</span><input type="text" class="text"  name="name" required value="" maxlength="32" autocomplete="off"/></li>
	        <li><span><em>*</em>资格等级：</span>
	        <select name="grade" id="qualificationLevel" class="level">
	        <select> </li>
	        <li>
	        	<span><em>*</em>上传资格证书图片：</span>
	            <div class="divpic">
	            	示例<br />
	                <img id="zgzsImg1" src="${ctxStatic}/images/tu1.png" width="144" height="191" /><br />
	                <a href="javascript:show('PicAlert1','PicBoxShow1','#dbName1','#zgzsImg1');">查看大图</a>
	            </div>
	             <!-- <input name="file" onchange="javascript:alert('123');"  type="file" class="uploadFile" > -->
	             <input name="file" class="required" id="fileImg1" onchange="ajaxUploadImage('fileImg1','zgzsImg1','dbName1');"  style="visibility: hidden;" type="file" class="uploadFile"  />
	             <input type="button" class="button1" onclick="uploadQualification(this)" id="uploading" value="上传文件" />
	        	 <input type="hidden" name="dbName"  id="dbName1" />
	        </li>
	        <li >
        	<span>&nbsp;</span>
        	<span>&nbsp;</span>
        	<span>&nbsp;</span>
        	<input type="button" class="button2" onclick="delGroup(this)"  value="X删除该组" />
        </li>
        <div class="clear"></div>
    </ul>
    </div>
    <ul id="foundation">
        <li >
        	<span> </span>
        	<input type="button" class="button2"  onclick="addGroup()" value="+增加一组" />
        	
        </li>
        <div class="clear"></div>
    </ul>
    <div class="login_kuang_button">
    
    	 <input type="submit" class="button"  value="下一步"/>
    </div>
   </form>
    <div class="clear"></div>
   <script>
        var num=$("#flag").val();
        function addGroup(){
            num++;
           // console.log(num);
            $("#flag").val(num);
            var str='<ul class="ulys" id="qualificationContent"><li><span><em>*</em>资格名称：</span><input type="text" class="text"  name="name" required" value="${orgQualification.name }" maxlength="32"/></li><li><span><em>*</em>资格等级：</span>'+
            '<select name="grade" id="qualificationLevel" class="level">  <select> </li>  <li>  <span><em>*</em>上传资格证书图片：</span> <div class="divpic"> 示例<br /> <img id="zgzsImg'+num+'" src="${ctxStatic}/images/tu1.png" width="144" height="191" /><br />'+
            '<a href="javascript:;">查看大图</a> </div> <input name="file" id="fileImg'+num+'" onchange="ajaxUploadImage(\'fileImg'+num+'\',\'zgzsImg'+num+'\',\'dbName'+num+'\');"  type="file" class="uploadFile" > <input type="button" class="button1" onclick="uploadQualification(this)" id="uploading" value="上传文件" /><input type="hidden" name="dbName" id="dbName'+num+'" /> </li>'+
            '<li > <span>&nbsp;</span> <span>&nbsp;</span> <span>&nbsp;</span> <input type="button" class="button2" onclick="delGroup(this)"  value="X删除该组" />  </li> <div class="clear"></div></ul>';
            $("#parcelContent").append(str);
            getOption();
        }
        function orgStaffEntry(){
           window.location.href=ctxFront+"/org/orgStaff/entry";
        }
        
    </script>
<div class="PicAlert" id="PicAlert1" onclick="hide('PicAlert1','PicBoxShow1')"></div>
<div class="PicBoxShow" id="PicBoxShow1" onclick="hide('PicAlert1','PicBoxShow1')"><span><img id="imgContainer"  src="" /></span></div>   
    
</div>
  <%@ include file="/WEB-INF/views/center/common/footer.jsp"%> 
</body>
</html>