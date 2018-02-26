/**
 * 中央投资项目列表和详情js
 */ 
$(function(){
	
	 for(var j=0;j<$("#keyWordContainer").find("option").length;j++){
    	 if($($("#keyWordContainer").find("option")[j]).attr("value")==$("#keyWordFlag").val()){
    		  $($("#keyWordContainer").find("option")[j]).attr("selected","selected");
    	 }
     }
	
	
	   //查询地区列表
	   $.ajax({
           type : "post",
           async:true,
           url : ctxFront + "/etc/area",
           data : { "option" : "" },
           dataType : "json",
           success : function(dataJson) {
               var str="";
                 for(var i=0;i<dataJson.length;i++){
                     str+="<a href='javascript:;' data="+dataJson[i].id+" onclick='flag("+dataJson[i].id+",this)'>"+dataJson[i].name+"</a>";
                 }
                 $("#area").html(str); 
                 
                 $("#area").find("a").first().css("color","blue");
                 
                 
                 $("#area").find("a").first().attr("data","");
                 for(var j=0;j<$("#area").find("a").length;j++){
                	 if($($("#area").find("a")[j]).attr("data")==$("#areaFlag").val()){
                		  $($("#area").find("a")[j]).css({"color":"#fff","padding":"0 5px","background-color":"#2783e1"});
                		  $($("#area").find("a")[j]).siblings().css("color","#000");
                	 }
                 }
                 
                  if($("#areaFlag").val()==null){
                	 $($("#area").find("a")[0]).css({"color":"#fff","padding":"0 5px","background-color":"#2783e1"});
           		  $($("#area").find("a")[0]).siblings().css("color","#000");
                 } 
                 
           }
       });
	   //查询项目类型
	   $.ajax({
           type : "post",
           async:true,
           url : ctxFront + "/etc/term",
           data : { "option" : "project_type" },
           dataType : "json",
           success : function(dataJson) {
               var str="";
                 for(var i=0;i<dataJson.length;i++){
                	 
                     str+="<option value='"+dataJson[i].value+"' >"+dataJson[i].label+"</option>";
                      
                 }
                 $("#proType").append(str); 
                 for(var j=0;j<$("#proType").find("option").length;j++){
                	 if($($("#proType").find("option")[j]).attr("value")==$("#protoTypeContainer").val()){
                		  $($("#proType").find("option")[j]).attr("selected","selected");
                	 }
                 }
           }
       });
	  
 })
//选中地区标识然后塞到隐藏域中   
function flag(arg,obj){
	if(arg=="1"){
	  arg=null;	
	}
	$("#areaFlag").val(arg);
	$("#areaContainer").val(arg);
	console.log($("#areaFlag").val());
	$(obj).siblings().css("color","#000");
	$(obj).css("color","blue");
	$("#searchPro").click();
}

//查询中央投资项目  
function searchProInfo(){
	   var formStr = $('#searchProFrom').serialize();
         $('#searchProFrom').submit(function(){
                jQuery.ajax({
                    url: ctxFront+"/pro/project/search",
                    data: formStr,
                    type: "post",
                    beforeSend: function() {},
                    success: function(data) {
                        $("#pageContaienr").html(data);
                            
                    }
                });
               return false;
            }); 
        }

//查看详情
function lookProjectDetails(id){
	 
	$.ajax({
        async:true,
        url:ctxFront+"/pro/project/details?id="+id,
        type:"post",
        success:function(data){
        	var pageContaienr=document.getElementById("pageContaienr");
            pageContaienr.innerHTML=data;
             
        }
    });
}
//分页
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchPro").click();
 	return false;
}

//显示举报容器
function showReport(){
	   $("#reportContainer").slideDown();
}
//关闭举报容器
function closeReport(){
	   $("#reportContainer").slideUp();
}
//保存举报内容
function reportSave(){
	   var orgId=$("#orgId").val();
	   var projectId=$("#projectId").val();
	   var  contact=$("#contact").val();
	   var  phone=$("#phone").val();
	   var  email=$("#emailInfo").val();
	   var content=$("#content").val();
	   var yzm=$("#randNum").val();
	 if($.trim(contact) == ''){
		   $("#infoLog").html("联系人不能为空"); return;
		}
	  if($.trim(phone) == ''){
		   $("#infoLog").html("联系电话不能为空"); return;
		}
	   
	   if($.trim(email) == ''){
		   $("#infoLog").html("邮箱不能为空"); return;
		}else{
			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
			if(!reg.test($.trim(email))){
				 $("#infoLog").html("邮箱格式不正确"); return;
			}
		} 
	   
	  if($.trim(content) == ''){
		   $("#infoLog").html("举报内容不能为空"); return;
		} 
	   
	    if($.trim(yzm) == ''){
	    	$("#infoLog").html("验证码不能为空"); return;
	    	
	    }else if($.trim(yzm) !=$("#sRandNum").val()){
	    	$("#infoLog").html("验证码不正确"); return;
	    }
	     if(!$('#igreement').is(':checked')) {
		   $("#infoLog").html("请接受协议！"); return;
		} 
	   
	   
	 
	   
	   $.ajax({
        type : "post",
        async:true,
        url : ctxFront + "/pro/orgReport/save",
        data :{"orgId":orgId,"contact":contact,"phone":phone,"email":email,"content":content,"projectId":projectId},
        dataType : "json",
        success : function(data) {
        	if(data=="举报成功！"){
                alert(data);
                $("#reportContainer").hide();
           }
        }
    });
	   
}
/**
 * 刷新验证码
 */
function getYanZhengMa() {
	var sRandNum = "";
	for (var i = 0; i < 4; i++) {
		var randNum = Math.floor(Math.random() * 10);
		sRandNum = sRandNum + randNum;
	}
	$("#checkcode").attr("src",ctxStatic+"/common/image.jsp?sRand=" + sRandNum);
	$("#sRandNum").val(sRandNum);
}