$(document).ready(function() {
	       if($("#statusFlag").val()=="1"||$("#statusFlag").val()=="2"||$("#statusFlag").val()=="5"){
	       	  var length=$("#loginBox").find(".text").length;
	       	  for(var i=0;i<length;i++){
	       	  	$($("#loginBox").find(".text")[i]).attr("disabled","disabled").css({"background-color":"#eee","color":"gray","cursor":"not-allowed","pointer-events":"none"});;
	       	  }
	       	  var btnLength=$("#loginBox").find(".ts").length;
	       	  for(var i=0;i<btnLength;i++){
	       	  	$($("#loginBox").find(".ts")[i]).attr("disabled","disabled").css({"background-color":"#eee","color":"gray","cursor":"not-allowed","pointer-events":"none"});
	       	  }
	       	  var saveLength=$('#login_box_button').find("input").length;
	       	  for(var i=0;i<saveLength;i++){
	       	  	$($('#login_box_button').find("input")[i]).attr("disabled","disabled").css({"background-color":"#eee","color":"gray","cursor":"not-allowed","border":"1px solid #ccc","pointer-events":"none"});
	       	  }
	       }
	       
	       
			//页面加载 后,自动跳转到上次最后操作的步骤
			if ($("#flag").val() == "1") {
				var num=1; 
				//填写完资质证明
				if($("#qualificationOn").val() == "1"){
			       
			        num=2;
				}
				//填写完专职
				if($("#staffOn").val() == "1"){
				    num=3;
				    
				}
				//填写完业绩
				if($("#achieveOn").val() == "1"){
				  num=4;
				    
				}
				//省发改委通过或不通过
				if($("#statusFlag").val()=="2"||$("#statusFlag").val()=="5"||$("#statusFlag").val()=="1"){
					 
					num=5;
				}
				//招标中心通过或不通过
				if($("#statusFlag").val()=="6"){
					 
					num=6;
				}
				 
				 switchPage(num);
			}
			 
			//判断 代理机构code的唯一性
			/* $("#scCode").blur(function(){
				  $.ajax({
					   type: "post",
					   url: ctxFront +"/org/org/repeat",
					   data: {"sc_code":$("#scCode").val()},
					   dataType:'json', 
					   success: function(msg){
						   
						   if(msg.code==401){
							   $("#scCodeMsg").html(msg.message).css("color","red");   
						   }else if(msg.code==200){
							   $("#scCodeMsg").html(msg.message).css("color","green");
						   }
						   
						   
					   } 
					});
	           })*/
	           
			$("input").focus(function() {
				$(this).css("border", "1px solid #0095eb")
			}).blur(function() {
				$(this).css("border", "1px solid #dee2ed")
			});

			var areaIdinner = $('#areaIdinner').val();
			$.ajax({
				type : "post",
				async : true,
				url : ctxFront + "/etc/area",
				data : {
					"option" : ""
				},
				dataType : "json",
				success : function(dataJson) {
					var str = "";
					for (var i = 0; i < dataJson.length; i++) {
						if (areaIdinner == dataJson[i].id) {
							str += "<option selected value=" + dataJson[i].id
									+ ">" + dataJson[i].name + "</option>";
						} else {
							str += "<option value=" + dataJson[i].id + ">"
									+ dataJson[i].name + "</option>";
						}
					}
					$("#area").html(str);
					$("#area").find("option").first().html("请选择").attr("value","0");
					$("#area").find("option").css({"padding-left":"30px","display":"inline-block"});
					
				}
			});
		});

/**
 * 提交保存org备案信息
 * 
 * @param formId
 */
function ajaxOrgSubmit() {
	$("#orgInfoNameMsg").html("");
	$("#scCodeMsg").html("");
	$("#areaMsg").html("");
	$("#regMoneyMsg").html("");
	$("#regAddressMsg").html("");
	$("#uploadMainMsg").html("");
	$("#legalrepNameMsg").html("");
	$("#legalrepCardMsg").html("");
	$("#UploadIDMsg").html("");
	
	
	var orgInfoName = $("#orgInfoName").val();
	var scCode = $("#scCode").val();
	var area = $("#area").val();
	var regMoney = $("#regMoney").val();
	var regAddress = $("#regAddress").val();
	var corporateProfile = $("#corporateProfile").val();
	var uploadMain = $("#org_logoPath").val();
	var legalrepName = $("#legalrepName").val();
	var legalrepCard = $("#legalrepCard").val();
	var legalrepAddress = $("#legalrepAddress").val();
	var legalrepPhone = $("#legalrepPhone").val();
	var legalrepZipcode = $("#legalrepZipcode").val();
	var UploadID = $("#org_legalrepPhoto").val();
	
	
	$('#orgInfoForm').submit(function(event) {
	if($.trim(orgInfoName) == '' && $.trim(scCode) == '' && $.trim(area) == '0'
			&& $.trim(regMoney) == '' && $.trim(regAddress) == ''
			&& $.trim(corporateProfile) == '' && $.trim(uploadMain) == ''
			&& $.trim(legalrepName) == ''&& $.trim(legalrepCard) == ''&& $.trim(legalrepAddress) == ''
			&& $.trim(legalrepPhone) == '' && $.trim(legalrepZipcode) == ''&& $.trim(UploadID) == '' ){
			 	
		$("#saveInfo").html("请填写后再保存！").css("color","red");
		
		//禁止表单提交
		 event.preventDefault();
	}else{
		$("#saveInfo").html("");
        $("#saveFlag").val(1);
           if($.trim(corporateProfile).length>400){
           	  $("#corporateProfileLog").html("简介字数不能大于400字！").css("color","red");
           	  return false;
           }
          
			jQuery.ajax({
				 async:true,
				url : $('#orgInfoForm').attr("action"),
				data : $('#orgInfoForm').serialize(),
				type : "POST",
				beforeSend : function() {
				 
				},
				success : function(data) {
					 
					$("#orgId").val(data.body.orgId);
					
				  
				},
				error : function(a, b, c) {
					alert(c);
				}
			});
 
			return false;
		 
	} 
		
			
	}) 
	
	 
}


 

 function nextOrgInfoSubmit(){
	var orgInfoName = $("#orgInfoName").val();
	var scCode = $("#scCode").val();
	var area = $("#area").val();
	var regMoney = $("#regMoney").val();
	var regAddress = $("#regAddress").val();
	var corporateProfile = $("#corporateProfile").val();
	var uploadMain = $("#org_logoPath").val();
	var legalrepName = $("#legalrepName").val();
	var legalrepCard = $("#legalrepCard").val();
	var legalrepAddress = $("#legalrepAddress").val();
	var legalrepPhone = $("#legalrepPhone").val();
	var legalrepZipcode = $("#legalrepZipcode").val();
	var UploadID = $("#org_legalrepPhoto").val();
	$("#saveInfo").html("");
	$("#saveFlag").val("");
	
	
			$('#orgInfoForm').submit(function(){
				
				
				if($.trim(orgInfoName) == ''){
			    $("#orgInfoNameMsg").html("&nbsp;请填写企业名称！").css("color","red");
			    event.preventDefault();
		   } 
		   if($.trim(scCode) == ''){
			  
			   $("#scCodeMsg").html("&nbsp;请填写统一社会信用代码！").css("color","red");
			    event.preventDefault();
		   }
		  
		   if($.trim(area) == '0'){
			   
			   $("#areaMsg").html("&nbsp;请选择所属地区！").css("color","red");
			    event.preventDefault();
		   }
		 
		   if($.trim(regMoney) == ''){
			    
			   $("#regMoneyMsg").html("&nbsp;请填写注册资本金！").css("color","red");
			    event.preventDefault();
		   }
		
		    if($.trim(regAddress) == ''){
			  
		    	$("#regAddressMsg").html("&nbsp;请填写注册地址！").css("color","red");
			    event.preventDefault();
		   }
		
		     if($.trim(uploadMain) == ''){
			    
		    	 $("#uploadMainMsg").html("&nbsp;请上传主体单位的证件图片！").css("color","red");
			    event.preventDefault();
		   }
		   
		     if($.trim(legalrepName) == ''){
			    
		    	 $("#legalrepNameMsg").html("&nbsp;请填写法人代表姓名！").css("color","red");
			   event.preventDefault();
		   }
		
		      if($.trim(legalrepCard) == ''){
			    
		    	  $("#legalrepCardMsg").html("&nbsp;请填写法人代表身份证！").css("color","red");
			    event.preventDefault();
		   }
		
		      if($.trim(UploadID) == ''){
			    
		    	  $("#UploadIDMsg").html("&nbsp;请上传法人代表的证件图片！").css("color","red");
			    event.preventDefault();
		   }
   
	 
   if(!$.trim(orgInfoName) == '' && !$.trim(scCode) == '' && !$.trim(area) == '0'&& !$.trim(regMoney) == '' && !$.trim(regAddress) == ''&& !$.trim(uploadMain) == ''
	    && !$.trim(legalrepName) == ''&& !$.trim(legalrepCard) == '' && !$.trim(UploadID) == '' ){
	    	 if($.trim(corporateProfile).length>400){
           	  $("#corporateProfileLog").html("简介字数不能大于400字！").css("color","red");
           	  return false;
           }
	        jQuery.ajax({
	        	async:false,
            url: $("#orgInfoForm").attr("action"),
            data: $('#orgInfoForm').serialize(),
            type: "POST",
            beforeSend: function() { },
            success: function(data) {
            	 
                if(data.code=="401"){
                		$("#saveInfo").html(data.message).css("color","red");
             	}else if(data.code=="300"){
             		$("#saveInfo").html(data.message).css("color","red");
             	}else{
            	    switchPage(2);
             	}
            	 
            },
            error:function(a,b,c){
            	alert(c);
            }
        });
	   
	} 
		 
        return false;
    });
    
    
   
	
}
 
