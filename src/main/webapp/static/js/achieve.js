//定义页面全局变量
var projectTypeStr = "";
$(function(){
	/**
	 * 页面一加载获取项目类型选项
	 *
	 */
	getOptions()
})

function getOptions() {
	/**
	 *获取项目类型
	 */
	if(null != projectTypeStr && projectTypeStr != ""){
		$.ajax({
			type : "post",
			url : ctxFront + "/etc/term.do",
			data : {
				"option" : "project_type"
			},
			dataType : "json",
			success : function(data) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					str += "<option value=" + data[i].value + ">" + data[i].label
							+ "</option>";
				}
				projectTypeStr = str;
		         $("#AchieveContainer").find("select").eq(-2).html(str); 	 
		         
			}
		});
	}else{
		 $("#AchieveContainer").find("select").eq(-2).html(projectTypeStr); 
	}
}
/**
 * 提交一条业绩记录
 */
function orgAchieveSubmit(obj,formId){
  	 $('#'+formId).submit(function(){
  	 	if($('#xieYi').is(':checked')) {
	     jQuery.ajax({
	               url: ctxFront+"/org/orgAchieve/save",
	               data: $('#'+formId).serialize(),
	               type: "POST",
	               dataType:'json',
	               beforeSend: function() { },
	               success: function(data) {
	              	 if(data['code'] == 200){
	              		 $(obj).css("color","grey");
	              	 }
	               }
	           });
        }else{
        	
        	$("#xieYiLog").html("请阅读并接受该协议!").css("color","red");
        	return false;
        }
           
       return false;
       });        	
  }
//全部 业绩  数据提交 
function submitAllForm(){
	 
	var submitArray = $('input[type="submit"]');
	if($('#xieYi').is(':checked')) {
	for(var i=0;i<submitArray.length ;i++){
		//var t = submitArray[i].type;
		//var t1 = submitArray[i].value;
		var t3 = submitArray[i].className;
		if(null != t3 && t3== "save"){
			submitArray[i].click();
		}
	}
	showMode();
	}else{
		$("#xieYiLog").html("请阅读并接受该协议!").css("color","red");
        	return false;
	}
}
 
/**
 * 增加一条招标业绩记录
 */
function addAchieve(){
	var addRowNum = $('#addRowNum').val();
	var thisNum = parseInt(addRowNum) + 1;
	$('#addRowNum').val(thisNum);
	
	 var basic="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});";
    var str='<form id="form_'+thisNum+'"   method="post" enctype="multipart/form-data">'+
        '<table class="table1">'+
        '<tr>'+
        '<td style="width:50px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;" name="num"  value=" "  maxlength="16"/></td>'+
        '<td style="width:40px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;"  name="name"  value=" "  maxlength="32"/></td>'+
        '<td style="width:30px;padding: 0 5px;"><select style="width:100%; border:solid 1px #dee2ed;"  id="achieveType"    name="type" value="">'+
        '</select></td>'+
        '<td style="width:30px;padding: 0 5px;"><select style="width:100%; border:solid 1px #dee2ed;"  name="isCenter""><option value="1">是</option><option value="0" selected>否</option></select></td>'+
    '<td style="width:90px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;"  name="entrustUnit"  value=""  maxlength="32"/></td>'+
        '<td style="width:30px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;" type="number" name="entrustMoney"  value=""  maxlength="10"/></td>'+
        '<td style="width:30px;padding: 0 5px;"><input  type="number" type="number" type="number" style="width:100%; border:solid 1px #dee2ed;" name="bidMoney"  value=""  maxlength="10"/></td>'+
        '<td style="width:40px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;"  name="tenderOpenTime" value=""  ' +
        'onclick="'+basic+'" /></td>'+
        '<td style="width:40px;padding: 0 5px;"><input  style="width:100%; border:solid 1px #dee2ed;" name="bidTime" value="" onclick="'+basic+'" /></td>'+
        '<td style="width:40px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;"   maxlength="16" name="noticeMedia"  value=""/></td>'+
        '<td style="width:40px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;" name="noticeDate"  value="" onclick="'+basic+'" /></td>'+
        '<td style="width:35px;"><input   type="submit" onclick="orgAchieveSubmit(this,\'form_'+thisNum+'\')"  class="save" value="保存" style="display:none;"/>&nbsp;&nbsp;<input class="del" onclick="delNote(this)" type="button" value=""/></td>'+
        '</tr>'+
        '</form>'
	$("#AchieveContainer").append(str); 
    getOptions();
}

/**
 * 删除一条业绩记录
 * @param arg
 * @returns
 */
function delNote(arg,id){
	  
		$.ajax({
			type:"post",
			url:ctxFront+"/pro/project/delete",
			data:{"id":id},
			success:function(data){
               $(arg).parent().parent().remove();
			}
		});
		
	 
	 
}
/**
 * 导入业绩明细
 */
function uploadAchieve(){
	$("#achieveFile").click();
}


  
  /**
   * 机构注册成功
   */
  function orgSuccess(){
	  if(!$('#acceptanceAgreement').is(':checked')) {
		    $("#infoLog").html("请接受协议!").css("margin-right","15px");
		    return 
		}
     window.location.href=ctxFront+"/org/org/succ";
  }
  /**
   * 机构修改成功，提交，修改Org状态为提交
   */
  function ajaxSucessOrg(){
  	
  	if($('#xieYi').is(':checked')) {
	
	
	  	//先将业绩每条上报 保存
	  	submitAllForm();
	  
	 
			$.ajax({
				async:false,
				url: ctxFront+"/org/org/ajaxSucess",
				type:"get", 
				success:function(data){
				}
			});
			$.ajax({
					async:true,
					url: ctxFront + "/org/orgChange/toOrgTip",
					data:{"tagNum":1},
					type:"get", 
					success:function(data){
						switchPage(5);
					},error:function(){
						alert('error');
					}
				});
		 
	
	$("#achieveFlag").click();
	}else{
		return $("#xieYiLog").html("请接受该协议!").css("color","red");
	}
		
		
	}
  
/**
 * 导入Excel
 */  
function ajaxImportOrgAchieves(){
	console.log("导入");
	jQuery.ajaxFileUpload({
        url:ctxFront+"/org/orgAchieve/importAchieve", 
        secureuri:false,  
        fileElementId:'achieveFile',
        dataType: 'text',
        success: function (dataJson) {
        	console.log(dataJson);
            var dataT=eval('(' + $(dataJson).html() + ')')
		 	if(dataT['messageCode'] == 200){
		 		$.ajax({
					async:true,
					url: ctxFront+"/org/orgAchieve/achievePage",
					type:"get", 
					success:function(data){
						$("#AchieveContainer").html(data);
					}
				});
		 	}
		 	
  		 },
  		 error:function(data,status,e){  
           alert('上传失败！'+e);  
  		 }
	 });
}

/**
 * 上一页
 */
function upPage(){
	var pageNo = $("#pageNo").val();
	var nextPageNo = parseInt(pageNo) - 1;
	$(function(){
		$.ajax({
			async:true,
			url: ctxFront + "/org/orgAchieve/achievePage",
			data:{"pageNo":nextPageNo},
			type:"get", 
			success:function(data){
				$("#AchieveContainer").html(data);
			}
		});
	});
}
/**
 * 下一页
 */
function nextPage(){
	var pageNo = $("#pageNo").val();
	var nextPageNo = parseInt(pageNo) + 1;
	$(function(){
		$.ajax({
			async:true,
			url: ctxFront + "/org/orgAchieve/achievePage",
			data:{"pageNo":nextPageNo},
			type:"get", 
			success:function(data){
				$("#AchieveContainer").html(data);
			}
		});
	});
}

function saveAchieve(){
	if($('#xieYi').is(':checked')) {
	
	submitAllForm();
	
	$("#achieveFlag").click();
	}else{
		return $("#xieYiLog").html("请接受该协议!").css("color","red");
	}
}
