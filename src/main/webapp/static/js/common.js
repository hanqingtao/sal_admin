
/**
	 * 公共方法用来设置导航当前栏目背景
	 */
	function navCom(obj){
		$(obj).siblings().attr("class"," ");
        $(obj).attr("class","on");
	};
	
	
	function footerCom(obj){
		$("#"+obj).siblings().attr("class"," ");
        $("#"+obj).attr("class","on");
	}; 

/**
 * 导航切换
 * @param arg
 * @param obj
 */
function navSwitch(arg,obj){
	/**
	 * 机构名录
	 */
	if(arg=="nav_agent"){
		   $.ajax({
               url:ctxFront+"org/org/search",
               type:"post",
               success:function(data){
 
            	  $("#pageContaienr").html(data);
            	   navCom(obj);
                   footerCom('footer_agent');
            	 
                   
               }
           });
		
	};
	/**
	 * 中央投资项目
	 */
	if(arg=="nav_project"){
		$.ajax({
            async:true,
            url:ctxFront+"/pro/project/search",
            type:"post",
            success:function(data){
                $("#pageContaienr").html(data);
                navCom(obj);
                footerCom('footer_project');
            }
        });
	};
	/**
	 * 日常监督
	 */
	if(arg=="nav_supervise"){
		$.ajax({
	        type : "post",
	        async:true,
	        url : ctxFront + "/cms/article/title",
	        data : {  "inMenu":"0","site.id":"1","parent.id":"1"},
	        dataType : "json",
	        success : function(dataJson) {
	             $.ajax({
	                 async:true,
	                 url:ctxFront+"/cms/article/vise",
	                 data : {"categoryId":dataJson[0].id,"delFlag":"1"},
	                 type:"post",
	                 success:function(data){
	                	 $("#pageContaienr").html(data);
	                     navCom(obj);
	                     footerCom('footer_supervise');
	                 }
	             });
	             
	        }
	    });
		
		
		 
	};
	/**
	 * 黑名单
	 */
	if(arg=="nav_blacklist"){
		$.ajax({
            async:true,
            url:ctxFront+"/black",
            type:"post",
            success:function(data){
                $("#pageContaienr").html(data);
                navCom(obj);
                footerCom('footer_blacklist');
            }
        });
	};
	/**
	 * 信用记录
	 */
	if(arg=="nav_creditRecord"){
		 $.ajax({
	            async:true,
	            url:ctxFront+"/credit",
	            type:"post",
	            success:function(data){
	                $("#pageContaienr").html(data);
	                navCom(obj);
	                footerCom('footer_creditRecord');
	                
	            }
	        });
	};
	
	
	 

	
}

function footerSwitch(arg,obj){
	/**
	 * 机构名录
	 */
	if(arg=="footer_agent"){
		$.ajax({
            async:true,
            url:ctxFront+"/org/org/search",
            type:"post",
            success:function(data){
            	var pageContaienr=document.getElementById("pageContaienr");
                pageContaienr.innerHTML=data;
                footerCom('nav_agent');
                navCom(obj)
            }
        });
	};
	
	 
	
	
	/**
	 * 中央投资项目
	 */
	if(arg=="footer_project"){
		$.ajax({
            async:true,
            url:ctxFront+"/pro/project/search",
            type:"post",
            success:function(data){
                $("#pageContaienr").html(data);
                footerCom('nav_project');
                navCom(obj)
            }
        });
	};
	/**
	 * 日常监督
	 */
	if(arg=="footer_supervise"){
		 
		
		
		$.ajax({
	        type : "post",
	        async:true,
	        url : ctxFront + "/cms/article/title",
	        data : {  "inMenu":"0","site.id":"1","parent.id":"1"},
	        dataType : "json",
	        success : function(dataJson) {
	             
	             $.ajax({
	                 async:true,
	                 url:ctxFront+"/cms/article/vise",
	                 data : {"categoryId":dataJson[0].id,"delFlag":"1"},
	                 type:"post",
	                 success:function(data){
	                	 $("#pageContaienr").html(data);
	                     footerCom('nav_supervise');
	                     navCom(obj)
	                 }
	             });
	             
	             
	        }
	    });
	};
	/**
	 * 黑名单
	 */
	if(arg=="footer_blacklist"){
		$.ajax({
            async:true,
            url:ctxFront+"/black",
            type:"post",
            success:function(data){
                $("#pageContaienr").html(data);
                footerCom('nav_blacklist');
                navCom(obj)
            }
        });
	};
	/**
	 * 信用记录
	 */
	if(arg=="footer_creditRecord"){
		 $.ajax({
	            async:true,
	            url:ctxFront+"/credit",
	            type:"post",
	            success:function(data){
	                $("#pageContaienr").html(data);
	                footerCom('nav_creditRecord');
	                navCom(obj)
	            }
	        });
	};
	 
}

function searchOrgInfo(){
	var nature=$("#orgNaturePassValue").val();
	$('#searchOrgForm').submit(function(){
	    jQuery.ajax({
	        url: ctxFront+"/org/org/search?nature="+nature,
	        data: $('#searchOrgForm').serialize(),
	        type: "post",
	        beforeSend: function() {},
	        success: function(data) {
	            $("#pageContaienr").html(data);
	            footerCom('nav_agent');
	            footerCom('footer_agent');
	            
	            }
	        });
	       return false;
	    });     
}


function qualificationManagement(){
	$.ajax({
        async:true,
        url:ctxFront+"/org/org/enter",
        type:"post",
        success:function(data){
            $("body").html(data).attr("style","");
            
        }
    });
}
/**
 * 进入违规投诉内容入口
 */
function complaintEntry(){
	$.ajax({
        async:true,
        url:ctxFront+"/complaint.do",
        type:"post",
        success:function(data){
            $("#pageContaienr").html(data);
        }
    });
}
 
//退出登录
function loginOut(){
	if(confirm("您确定要退出当前用户吗？")){
		window.location.href = ctxFront+"/logout";
	}
}
 
 

// 项目（上传相关资料）公共方法
function toUploadFile_projectFlow(to_url){
	$.ajax({
        async:true,
        url:ctxFront + to_url,
        type:"post",
        success:function(data){
            $("#applicationContainer").html(data);
        }
    });        	
}


/**
 *获取上传的文件名，公共的
 */
$("#files").on("change", function () {
    
    var obj = document.getElementById("files");
    var length = obj.files.length;
    var temp=null;
    for (var i = 0; i < length; i++) {
        $("#infoLog").html("");
         temp = obj.files[i].name;
    }
    $("#infoLog").html(temp);
});
/**
 * 点击上传文件（公共方法）
 */
function  uploadFile(){
	$("#files").click();
}
/**
 * ajax上传图片，返回服务器图片URL显示图片
 */
//点击上传图片，选择图片后，上传该图片，并且返回该图片的url提供展示，ajaxfileUpload.js
function ajaxUploadImage(iFileId,imgId,hiddenId){
//	alert(iFileId + "  $   " + imgId + "  $  " + hiddenId);
//	var imgServer = $('#imgServer').val();
	jQuery.ajaxFileUpload({
         url:ctxFront+"/org/orgQualification/ajaxUploadImage", 
         secureuri:false,  
         fileElementId:iFileId,    //上传文件选择框的id属性  
         dataType: 'text',   //json，与后台对应，text和json
         success: function (data) {        //后台ajax返回Imgurl
           //console.log(imgServer + eval('(' + $(data).html() + ')').resPath);
		 	$("#"+imgId).attr("src",imgServer + eval('(' + $(data).html() + ')').resPath);  //添加到回显的img标签上，同时该标签自动发送该图片的url
	        $("#"+hiddenId).val(eval('(' + $(data).html() + ')').resPath);    //要保存的图片地址
   		 },
   		 error:function(data,status,e){  
            alert('上传失败！'+e+'data:'+data+'status:'+status);  
   		 }
	 }); 
}

/**
 * 保存变更备份
 */
function ajaxBak(){
	$.ajax({
		async:true,
		url: ctxFront + "/org/org/ajaxBak",
		type:"get", 
		success:function(data){
			 $("#applicationContainer").html(data);
		}
	});
	$('#bakAjax').attr('disabled',true) 
	$('#bakAjax').css('background-color','grey');
}
//图片放大效果
function show(id1,id2,obj,arg){
	console.log(id1+":"+id2+":"+obj+":"+arg);
	if($(obj).val()==null||$(obj).val()==""){
		$("#imgContainer").attr("src",$(arg).attr("src"));	
	}else{
		$("#imgContainer").attr("src",imgServer+$(obj).val());	
	}
	document.getElementById(id1).style.display="block";
	document.getElementById(id2).style.display="table";
}
function hide(id1,id2){
	document.getElementById(id1).style.display="none";
	document.getElementById(id2).style.display="none";
}






/**
 * 下一步保存
 * @param formId
 */
function nextSubmit(formId,messId,arg){
	 
	var targetUrl = $('#'+formId).attr("action");
	 
	$('#'+formId).submit(function(){
        jQuery.ajax({
            url: targetUrl,
            data: $('#'+formId).serialize(),
            type: "POST",
            beforeSend: function() { },
            success: function(data) {
            	switchPage(arg);
            },
            error:function(a,b,c){
            	alert(c);
            }
        });
        return false;
    });
}



/**
 * 修改机构注册信息，切换步骤
 * @param tagNum
 */
function switchPage(tagNum){
 
	$(function(){
		$.ajax({
			async:true,
			url: ctxFront + "/org/orgChange/toOrgTip",
			data:{"tagNum":tagNum},
			type:"get", 
			success:function(data){
				$("#applicationContainer").html(data);
			},error:function(){
				alert('error');
			}
		});
	});
}



function changeEntry(tagNum){
		$.ajax({
			async:true,
			url: ctxFront + "/org/org/changeEntry",
			data:{"tagNum":tagNum,"flag":"1"},
			type:"get", 
			success:function(data){
				$("#applicationContainer").html(data);
				
			},error:function(){
				alert('error');
			}
	 
	});
}

/**
 * 提交保存
 * @param formId
 */
function ajaxSubmit(formId,messId,tagNum){
	var targetUrl = $('#'+formId).attr("action");
	$("#formId").validate();
	$('#'+formId).submit(function(){
        jQuery.ajax({
            url: targetUrl,
            data: $('#'+formId).serialize(),
            type: "POST",
            beforeSend: function() { },
            success: function(data) {
            	 
            		$('#'+messId).text(data['message']);
            		switchPage(tagNum);
            	 
            },
            error:function(a,b,c){
            	alert(c);
            }
        });
        return false;
    });
}

function clickUpload(tarId){
	$('#'+tarId).click();
}


function lookOrgContent(arg){
 
	  $.ajax({
         async:true,
         url:ctxFront+"/org/org/details?id="+arg+"&random="+Math.random(),
         
         type:"post",
         success:function(data){
        	 $("#pageContaienr").html(data);
       	  
       	 /*var pageContaienr=document.getElementById("pageContaienr");
             pageContaienr.innerHTML=data;*/
              
         }
     });
}



function showMode(){
	    
	    $("#Mode").show();
	   //$("#Mode").fadeIn(2000);
	   $("#Mode").fadeToggle();
}
//关闭提示
function closePrompt(){
	$(".promptBox").hide();
}


 