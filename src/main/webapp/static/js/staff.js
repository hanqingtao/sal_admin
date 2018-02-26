//定义三个变量不需要每次加载
var sexStr ="";
var degreeStr = "";
var worktypeStr = "";
$(function(){
  
	
	
	/**
	 * 页面一开始加载下拉选选项
	 */
	getOptions();
 

})
function getOptions() {
	/**
	 * 性别
	 */
	if(null == sexStr || sexStr ==""){
		$.ajax({
			type : "post",
			url : ctxFront + "/etc/term.do",
			data : {
				"option" : "sex"
			},
			dataType : "json",
			success : function(data) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					str += "<option value=" + data[i].value + ">" + data[i].label
							+ "</option>";
				}
				sexStr = str;
				console.log(str);
				$("tbody tr").last().find("select").first().html(str);
			}
		});
	}else{
		$("tbody tr").last().find("select").first().html(sexStr);
	}
    /**
     * 学历
     */
	if(null == degreeStr || degreeStr ==""){
		$.ajax({
			type : "post",
			url : ctxFront + "/etc/term.do",
			data : {
				"option" : "degree"
			},
			dataType : "json",
			success : function(data) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					str += "<option value=" + data[i].value + ">" + data[i].label
							+ "</option>";
				}
				console.log(str);
				degreeStr = str;
				$("tbody tr").last().find("select").eq("1").html(str);
			}
		});
	}else{
		$("tbody tr").last().find("select").eq("1").html(degreeStr);
	}
	/**
	 * 劳动关系
	 */
	if(null == worktypeStr || worktypeStr ==""){
		$.ajax({
			type : "post",
			url : ctxFront + "/etc/term.do",
			data : {
				"option" : "work_type"
			},
			dataType : "json",
			success : function(data) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					str += "<option value=" + data[i].value + ">" + data[i].label
							+ "</option>";
				}
				worktypeStr = str;
				console.log(str);
				$("tbody tr").last().find("select").last().html(str);
			}
		});
	}else{
		$("tbody tr").last().find("select").last().html(worktypeStr);
	}
}
/**
 * 导入专职人员
 */
function uploadStaff() {
	$("#orgStaffFile").click();
}
/**
 * 上传社保证明
 */
function uploadSocialSecurityPoints() {
	$("#uploadSocialSecurity").click()
}
/**
 * 删除当前专职人员记录
 * @param arg
 */
function del(arg,id) {
	
		$.ajax({
			type:"post",
			url:ctxFront+"/org/orgStaff/delete",
			data:{"id":id},
			success:function(data){
				$(arg).parent().parent().remove();
			} 
		});
	
	
}
/**
 * 增加一条专职记录
 */
function addStaff() {
	var addRowNum = $('#addRowNum').val();
	var thisNum = parseInt(addRowNum) + 1;
	$('#addRowNum').val(thisNum);
	
	var basic="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});";
	var str = '<form id="form_'+thisNum+'" action="${ctxFront}/org/orgStaff/save"  ' +
    'method="post" enctype="multipart/form-data"> ' +
    '<table  class="table1" id="staffTbody"><tr >	 ' +
    '<td style="width:50px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " type="text" name="name" value=""  maxlength="16">' +
    '</td>	 <td style="width:40px; padding: 0 5px;"><select style="width:100%;border:solid 1px #dee2ed; "  name="sex" >  ' +
    '	</select></td>			<td style="width:90px; padding: 0 5px;">' +
    '<input style="width:100%;border:solid 1px #dee2ed;" type="text"  name="card"    maxlength="18"/></td>' +
    '	<td style="width:70px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " name="workStart" type="text" value=""  onclick="'+basic+'"></td>' +
    '<td style="width:90px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " type="text"  maxlength="64" name="university" value=""/></td>' +
    '<td style="width:50px; padding: 0 5px;"> <select style="width:100%;border:solid 1px #dee2ed;" name="degree" >	  </select></td>' +
    '<td style="width:90px; padding: 0 5px;"><input   style="width:100%;border:solid 1px #dee2ed; " type="text" name="ssn" value=""  maxlength="32"/></td>' +
    '<td style="width:40px; padding: 0 5px;"><input  style="width:100%;border:solid 1px #dee2ed; " type="text" name="proTitle" value=" "  maxlength="32"/></td>	' +
    ' <td style="width:50px; padding: 0 5px;"><input  style="width:100%;border:solid 1px #dee2ed; " type="text" name="dept" value=""  maxlength="32"/></td>	' +
    ' <td style="width:50px; padding: 0 5px;"><select  style="width:100%;border:solid 1px #dee2ed; " name="workType" >	  </select></td> <td style="width:50px;">' +
    
    '<img style="display: none;" id="card_photoShow_'+thisNum+'" src=""> 	' +
    
    '<input   type="file" onchange="ajaxUploadImage(\'card_photoFile_'+thisNum+'\',\'card_photoShow_'+thisNum+'\',\'card_photo_'+thisNum+'\');" id="card_photoFile_'+thisNum+'" class="uploadFile"  name="file"/> 	' +
    
    '  <input type="button" class="upload" onclick="uploadIDPictures(this)"   value="上传" />  ' +
    
    '  <input type="hidden" name="cardPhoto" id="card_photo_'+thisNum+'" />  ' +
    
    ' </td>	 <td style="width:50px;"> '+
    ' <img style="display: none;" id="protitle_photoShow_'+thisNum+'" src=""> '+
    ' <input  type="file" onchange="ajaxUploadImage(\'protitle_photoFile_'+thisNum+'\',\'protitle_photoShow_'+thisNum+'\',\'protitle_photo_'+thisNum+'\');" id="protitle_photoFile_'+thisNum+'"  name="file"  class="uploadFile"/>' +
    ' <input type="button" class="upload" onclick="uploadTitlePictures(this)"   value="上传" /><input type="hidden" name="protitlePhoto" id="protitle_photo_'+thisNum+'" /> </td>	 ' +
    '<td style="width:40px;"><input style="display:none;" type="submit" onclick="submitForm(this,\'form_'+thisNum+'\')";  value="保存" class="save"/>&nbsp;&nbsp;' +
    '<input type="button" onclick="del(this)" class="del" value=""/></td>    </tr></table></form>';
	$("#staffContainer").append(str);
	getOptions();
}
/**
 * 上传专职人员身份证图片
 * @param arg
 */
function uploadIDPictures(arg){
	$(arg).parent().find("input")[0].click();
}
/**
 * 上传专职人员职称证图片
 * @param arg
 */
function uploadTitlePictures(arg){
	$(arg).parent().find("input")[0].click();
}


function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}


function ajaxImportOrgStaff(){
 	//alert('导入aa....');
	jQuery.ajaxFileUpload({
        url:ctxFront+"/org/orgStaff/imp", 
        secureuri:false,  
        fileElementId:'orgStaffFile',
         //dataType: 'json',
         dataType: 'text',
        success: function (dataJson) {
 	 	    var dataT=eval('(' + $(dataJson).html() + ')')
		 	if(dataT['messageCode'] == 200){
		 		$.ajax({
					async:true,
					url: ctxFront+"/org/orgStaff/staffPage",
					type:"get", 
					success:function(data){
						$("#staffContainer").html(data);
					}
				});
		 	}else{
		 		alert(dataT['message']);
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
			url: ctxFront + "/org/orgStaff/staffPage",
			data:{"pageNo":nextPageNo},
			type:"get", 
			success:function(data){
				$("#staffContainer").html(data);
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
			url: ctxFront + "/org/orgStaff/staffPage",
			data:{"pageNo":nextPageNo},
			type:"get", 
			success:function(data){
				$("#staffContainer").html(data);
			}
		});
	});
}
/**
 * 保存提交单个表单
 * @param formId
 */
function submitForm(obj,formId){
//	$('#'+formId).submit();
	$('#'+formId).submit(function(){
        jQuery.ajax({
            url: ctxFront+"/org/orgStaff/save",
            data: $('#'+formId).serialize(),
            type: "POST",
            beforeSend: function() { },
            success: function(data) {
           	 if(data['code'] == 200){
//           		 $(obj).attr("disabled","disabled");
           		 $(obj).css("color","grey");
           	 }
            }
        });
        return false;
    });
}
//全部 专职人员 数据提交 
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
 
//下一步，填写招标业绩  按钮 
function submitStaffNext(){
	nextSubmit('form3333','mess33',4);
	submitAllForm();
	$.ajax({
					async:true,
					url: ctxFront + "/org/orgChange/toOrgTip",
					data:{"tagNum":1},
					type:"get", 
					success:function(data){
						switchPage(4);
					},error:function(){
						alert('error');
					}
				});
	
}

function staffAjaxSubmit(formId,messId){
//	alert($('#'+formId).attr("action"));
	var targetUrl = $('#'+formId).attr("action");
	$('#'+formId).submit(function(){
        jQuery.ajax({
            url: targetUrl,
            data: $('#'+formId).serialize(),
            type: "POST",
            beforeSend: function() { },
            success: function(data) {
            	if(data['code'] == 200){
            		window.location.href=ctxFront+"/org/org/orgAchieve";
            	}else{
            		$('#'+messId).text(data['message']);
            	}
            },
            error:function(a,b,c){
            	alert(c);
            }
        });
        return false;
    });
}


function saveOrgStaff(){
	submitAllForm();
	$("#staffFlag").click();
}

