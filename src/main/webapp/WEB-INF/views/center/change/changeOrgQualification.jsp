<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/center/common/meta.jsp"%>
<script type="text/javascript">
 //接受后台传来的 证书级别的list str 
 var qualOptions='';//'${qualStr}';

		$(document).ready(function() {
			  getOption();
		});
		
		//获取 证书类型 
		 function  getOption(){
			
			if(null == qualOptions || qualOptions==""){
			 $.ajax({
	                type:"post",
	                url:ctxFront+"/etc/term.do",
	                data:{"option":"qualification_grade"},
	                dataType : "json",
	                success:function(data){
	                    var str='<option value="">请选择</option>';
	                    for(var i=0;i<data.length;i++){
	                        str+="<option value="+data[i].value+">"+data[i].label+"</option>";
	                    }
	                    qualOptions  = str;
	                    $("#qualificationContent").find("select").last().html(str);
	                }
	            });
			}else{
				$("#qualificationContent").find("select").last().html(qualOptions);
			}
		 }
		
	</script>
</head>
<div class="tops">
	<div class="left">代理机构资质备案管理</div>
</div>
<div class="jindubox" id="bodyContainer">
	<p onclick="switchPage(1);" class="on1">
		<span>1</span>填写企业基本信息
	</p>
	<p onclick="switchPage(2);" id="qualicationIcon" class="on">
		<span>2</span>上传资质证明
	</p>
	<p <c:if test="${org.staffOn == 1 }">class="on1" onclick="switchPage(3);" </c:if>>
		<span>3</span>上传专职人员情况
	</p>
	<p <c:if test="${org.achieveOn == 1 }">class="on1" onclick="switchPage(4);"</c:if>>
		<span>4</span>上传招标业绩明细
	</p>
	<c:if test="${org.status==3 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}"><p  ><span>5</span>变更成功</p></c:if>
	
	<c:if test="${org.id==null||org.status==0||org.status==4||org.status==5||org.status==6||org.status==1||org.status==2||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
	<p
		<c:if test="${org.status == 2 || org.status == 5 || org.status == 3 || org.status == 6 }">class="on1" onclick="switchPage(5);" </c:if>>
		<span>5</span>省发展改革委初审
	</p>
	<p <c:if test="${org.status == 3 || org.status == 6 }">class="on1" onclick="switchPage(6);"</c:if>>
		<span>6</span>招标中心终审
	</p>
	</c:if>
</div>
<div id="parcelContent" class="login_kuang congxin">
	<input type="hidden" id="orgIdFlag" value="${org.status }">
	<c:if test="${org.status==1 || org.status==2 || org.status==5}">
            <div class="promptBox">
                 <div class="promptContent" ><span>备案提交后的审核期间，禁止修改企业信息和相关资料！</span><span onclick="closePrompt()" class="promptClose">×</span></div>
            </div>
            </c:if>
	<div class="top">
		<div class="left">上传资质证明</div>
	</div>
	<div class="clear"></div>
	<input id="flag2" type="hidden" value="1" />
	<c:choose>
		<c:when test="${!empty orgQualificationList }">

			<div id="qualificationContent" style="margin-top: 20px;">
				<input id="flag" name="flag" type="hidden" value="${fn:length(orgQualificationList) }" /> <input
					id="mainId" name="orgId" type="hidden" value="${org.id}" />
				<c:forEach items="${orgQualificationList}" var="orgQualification" varStatus="indd">
					<form id="forminner${indd.index + 1 }" action="${ctxFront}/org/orgQualification/edit"
						class="form-horizontal" method="post" enctype="multipart/form-data">

						<input id="mainId" name="id"  type="hidden"  value="${orgQualification.id}" />
						<ul class="ulys"  >
						     
							<a  class="aTag zjyhxadd" href="javascript:;" onclick="addGroupEd()"><img src="${ctxStatic}/images/daa.png" />&nbsp;增加一组&nbsp;</a>
							<a  class="aTag zjyhxdelete"  href="javascript:;" onclick="delGroupEd(this,${orgQualification.id})" ><img  src="${ctxStatic}/images/daa1.png" /> 删除</a>
							 
							 
							 
							 
							<input type="submit" onclick="submitForm(this,'forminner${indd.index + 1 }');" class="save" style="display: none;" value="保存修改" />&nbsp;
							<span id="mess${indd.index + 1 }"></span>
							<li><span>资格名称：</span><input type="text" class="text" name="name"   value="${orgQualification.name }" maxlength="32" /></li>
							<li><span>资格等级：</span> <select name="grade" style="width: 300px;" class="text" id="prohibit" value="${orgQualification.grade}">
									<option value="0">请选择</option>
									<c:forEach items="${fns:getDictList('qualification_grade')}" var="qualification"
										varStatus="status">
										<option value="${qualification.value }"
											<c:if test="${orgQualification.grade==qualification.value}">selected="selected"</c:if>>${qualification.label }</option>
									</c:forEach>
							</select></li>
							<li><span>上传资格证书图片：</span>
								<div class="divpic" style="height:auto">
									示例<br />
									<c:if test="${empty orgQualification.path }">
										<img id="zgzsImg${indd.index+1 }" src="${ctxStatic}/images/pic3.jpg" width="144"
											  />
										<br />
									</c:if>
									<c:if test="${!empty orgQualification.path }">
										<img id="zgzsImg${indd.index+1 }" src="${imgServer }${orgQualification.path }" width="144"
											  />
										<br />
									</c:if>
									<a href="javascript:show('PicAlert1','PicBoxShow1','#dbName${indd.index+1 }','#zgzsImg${indd.index+1 }');">查看大图</a>
								</div> <input name="file" id="fileImg${indd.index+1 }"
								onchange="ajaxUploadImage('fileImg${indd.index+1 }','zgzsImg${indd.index+1 }','dbName${indd.index+1 }');"
								type="file" class="uploadFile"> <input type="button"
								onclick="clickUpload('fileImg${indd.index+1 }')" class="text button1" style="margin-top:0" id="prohibit"  value="点击上传" /> <input
								type="hidden" value="${orgQualification.path }" name="path" class="text"
								id="dbName${indd.index+1 }" /></li>
							<div class="clear"></div>
						</ul>
					</form>
				</c:forEach>
			</div>
			<div class="login_kuang_button">
				<c:if test="${org.status==0||org.status==4||org.status==5||org.status==6||org.status==1||org.status==2||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
					<input type="submit" onclick="nextQualificationSubmit();" class="text button"
						style="width: auto; padding: 0 20px" value="下一步，填写专职人员情况" />&nbsp;&nbsp;
		            	   
					<input type="button" onclick="submitAllForm();" class="text button1" value="保&nbsp;&nbsp;存" />
					<!-- <input type="submit" id="orgQualificationFlag" style="display: none" onclick="ajaxSubmit('forminner','mess',2);" class="button1" value="保&nbsp;&nbsp;存" /> -->
				</c:if>
				<c:if test="${org.status==3 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}">
					<input type="submit" onclick="submitAllForm();" id="saveSubmit" class="button" value="保存修改" />&nbsp;
		            	<input id="bakAjax" type="button1" onclick="ajaxBak();" class="button1" value="重新提交" />
				</c:if>
			</div>
		</c:when>
		<c:otherwise>
			<form id="forminnernew1" action="${ctxFront}/org/orgQualification/edit" class="form-horizontal"
				method="post">
				<input id="flag" name="flag" type="hidden" value="1" /> 
				<input id="mainId" name="orgId" type="hidden" value="${org.id}" />
				<div id="qualificationContent" style="margin-top: 20px;">
					<ul class="ulys">
				 
						<a href="javascript:;" onclick="addGroupEd()" class="aTag zjyhxadd"><img src="${ctxStatic}/images/daa.png" />&nbsp;增加一组&nbsp;</a>
						<a href="javascript:;" onclick="delGroup(this)" class="aTag zjyhxdelete"><img src="${ctxStatic}/images/daa1.png" /> 删除</a> 
						<input type="submit" style="display: none" class="save" value="保存" onclick="submitForm(this,'forminnernew1');" />
						
						<li><span>资格名称：</span><input type="text" class="text" name="name" value="" maxlength="32" /><i
							id="info"></i></li>
						<li><span>资格等级：</span><select name="grade" class="text" style="width: 300px;">
						</select><i id="info"></i></li>
						<li><span>上传资格证书图片：</span>
							<div class="divpic">
								示例<br /> <img id="zgzsImgnew" src="${ctxStatic}/images/pic3.jpg" width="144" height="191" /><br />
								<a href="javascript:show('PicAlert1','PicBoxShow1','#dbNamenew','#zgzsImgnew');">查看大图</a>
							</div> <input name="file" id="fileImgnew"
							onchange="ajaxUploadImage('fileImgnew','zgzsImgnew','dbNamenew');" type="file"
							class="uploadFile"> <input type="button" onclick="clickUpload('fileImgnew')" class="text button1" value="点击上传" /> <input type="hidden" name="path" value="" class="text"
							id="dbNamenew" /><i id="info"></i></li>
						<div class="clear"></div>
					</ul>
				</div>
			</form>
			<div class="login_kuang_button" id="loginButton">
				<input type="submit" onclick="nextQualificationSubmit();" class="text button"
					style="width: auto; padding: 0 20px" value="下一步，填写专职人员情况" />&nbsp;&nbsp; <input type="button"
					onclick="save();" class="text button1" value="保&nbsp;&nbsp;存" />
				<!-- <input type="submit" id="orgQualificationFlag" style="display: none" onclick="submitForm();" class="button1" value="保&nbsp;&nbsp;存" /> -->
			</div>
		</c:otherwise>
	</c:choose>
</div>

<div class="PicAlert" id="PicAlert1" onclick="hide('PicAlert1','PicBoxShow1')"></div>
<div class="PicBoxShow" id="PicBoxShow1" onclick="hide('PicAlert1','PicBoxShow1')">
	<span><img id="imgContainer" src="" /></span>
</div>
<script>
        $(function(){
        	if($("#orgIdFlag").val()=="1"||$("#orgIdFlag").val()=="2"||$("#orgIdFlag").val()=="5"){
        		 var length=$("#parcelContent").find(".text").length;
        		 for(var i=0;i<length;i++){
        			 $($("#parcelContent").find(".text")[i]).attr("disabled","disabled").css({"background-color":"#eee","border":"1px solid #ccc","color":"gray","cursor":"not-allowed"});
        		 }
        		 var aLength=$("#qualificationContent").find(".aTag").length;
        		 
        		 for(var i=0;i<aLength;i++){
        			 $($("#qualificationContent").find(".aTag")[i]).removeAttr("onclick").css({"color":"red","cursor":"not-allowed","filter":"grayscale(100%)"}); 
        		 }
        	}
        	
        	 
        })
       
        function save(){
        	submitAllForm();
        	$("#qualicationIcon").click();
        	
        }
       

        
        
        /**
         * 保存提交单个表单
         * @param formId
         */
        function submitForm(obj,formId){

        	$('#'+formId).submit(function(){
                jQuery.ajax({
                    url: ctxFront+"/org/orgQualification/edit",
                    data: $('#'+formId).serialize(),
                    type: "POST",
                    beforeSend: function() { },
                    success: function(data) {
                   	 if(data['code'] == 200){
                   		
                   		//alert("保存成功!");
                   	 }
                    }
                });
                return false;
            });
        }
      //点击  保存   的动作 全部 专职人员 数据提交  
      function submitAllForm(){
      	var submitArray = $('input[type="submit"]');
      	for(var i=0;i<submitArray.length ;i++){
      		//var t = submitArray[i].type;
      		//var t1 = submitArray[i].value;
      		var t3 = submitArray[i].className;
      		if(null != t3 && t3== "save"){
      			submitArray[i].click();
      		}
      	}
      	showMode();
      }
      //暂时未用
        function addGroup(){
        	var num = parseInt($('#flag').val());
            num++;
            $("#flag").val(num);
             var str='<form id="forminnernew'+num+'"  action="${ctxFront}/org/orgQualification/edit"   class="form-horizontal" method="post">'+
             '<input type="submit" onclick="submitForm(this,\'forminnernew'+num+'\');" class="save" style="display:none" value="保存" /><span id="messnew'+num+'"></span>'+
            '<ul class="ulys"><a href="javascript:addGroup();" class="zjyhxadd" ><img src="${ctxStatic}/images/daa.png" />&nbsp;增加一组&nbsp;</a><a href="javascript:;" onclick="delGroup(this)"  class="zjyhxdelete" ><img src="${ctxStatic}/images/daa1.png"/> 删除</a><li><span>资格名称：</span><input type="text" class="text"  name="name" value="${orgQualification.name }" maxlength="32"/></li><li><span>资格等级：</span>'+
            '<select name="grade" class="text" style="width:300px;"><select> </li>  <li>  <span>上传资格证书图片：</span> <div class="divpic"> 示例<br /> <img id="zgzsImgnew'+num+'" src="${ctxStatic}/images/pic3.jpg" width="144" height="191" /><br />'+
            '<a href="javascript:show(\'PicAlert1\',\'PicBoxShow1\',\'#dbNamenew'+num+'\',\'#zgzsImgnew'+num+'\');">查看大图</a> '+
            '</div> <input name="file" id="fileImgnew'+num+'" onchange="ajaxUploadImage(\'fileImgnew'+num+'\',\'zgzsImgnew'+num+'\',\'dbNamenew'+num+'\');"  type="file" class="uploadFile" > <input type="button" class="button1" onclick="clickUpload(\'fileImgnew'+num+'\')" value="上传文件" /><input type="hidden" name="path"  class="text" id="dbNamenew'+num+'" /> </li>'+
            '<li > <span>&nbsp;</span> <span>&nbsp;</span> <span>&nbsp;</span></li> <div class="clear"></div></ul></form>';
             $("#qualificationContent").append(str);
             getOption();
        }
        //正在使用 增加一行
        function addGroupEd(){
        	var num = parseInt($('#flag').val());
             num++;
             $("#flag").val(num);
             var str='<form id="forminnernew'+num+'"  action="${ctxFront}/org/orgQualification/edit"   class="form-horizontal" method="post">'+
            '<input type="submit" onclick="submitForm(this,\'forminnernew'+num+'\');" class="save" style="display:none" value="保存" /><span id="messnew'+num+'"></span>'+
            '<input id="mainId" name="id" type="hidden" value=""/><ul class="ulys"><a href="javascript:addGroupEd();" class="zjyhxadd" ><img src="${ctxStatic}/images/daa.png" />&nbsp;增加一组&nbsp;</a><a href="javascript:;" onclick="delGroup(this)"  class="zjyhxdelete" ><img src="${ctxStatic}/images/daa1.png"/> 删除</a><li><span><em>*</em>资格名称：</span><input type="text" class="text"  name="name" required" value="${orgQualification.name }" maxlength="32"/></li><li><span><em>*</em>资格等级：</span>'+
            '<select name="grade" class="text"  style="width: 300px;"><option value="1">二级</option><option value="2" selected>一级</option>  <select> </li>  <li>  <span><em>*</em>上传资格证书图片：</span> <div class="divpic"> 示例<br /> <img id="zgzsImg'+num+'" src="${ctxStatic}/images/pic3.jpg" width="144" height="191" /><br />'+
            '<a href="javascript:show(\'PicAlert1\',\'PicBoxShow1\',\'#dbName'+num+'\',\'#zgzsImg'+num+'\');">查看大图</a> '+
            '</div> <input name="file" id="fileImg'+num+'" onchange="ajaxUploadImage(\'fileImg'+num+'\',\'zgzsImg'+num+'\',\'dbName'+num+'\');"  type="file" class="uploadFile" > <input type="button" class="button1" onclick="clickUpload(\'fileImg'+num+'\')" value="上传文件" /><input type="hidden" value="" name="path" id="dbName'+num+'" /> </li>'+
            '<li > <span>&nbsp;</span> <span>&nbsp;</span> <span>&nbsp;</span></li> <div class="clear"></div></ul></form>';
             $("#qualificationContent").append(str);
             getOption();
             
        }
        //删除 一行 暂时未用
		  function delGroup(obj){
			    $(obj).parent().remove();
		  }
		  //删除 一行 暂时未用
		  function delGroupEd(obj,arg){
			  
				  $.ajax({
			           type : "post",
			           async:true,
			           url : ctxFront + "/org/orgQualification/delete",
			           data : { "id" : arg },
			           dataType : "json",
			           success : function(data) {
			        	   if(data.status=="succ"){
			        		   switchPage(2);
			        	   }
			           }
			       });
			  }
		  function nextQualificationSubmit(){
			  submitAllForm();
			  $.ajax({
					async:true,
					url: ctxFront + "/org/orgChange/toOrgTip",
					data:{"tagNum":1},
					type:"get", 
					success:function(data){
						switchPage(3);
					},error:function(){
						alert('error');
					}
				});
			  
		  }
    </script>