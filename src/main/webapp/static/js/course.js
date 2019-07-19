/**
 * 用于 课程相关信息的页面处理
 */

var courseVideoFlag = false;
function uploadCourseFile(obj) {
    /*
	if ($(obj).data("type") == "file") {
		if (!/\.(mp3|mp4)$/.test($(obj).val())) {
			toTipAnimateAlertEntry("请上传音频文件！");
			return false;
		}
	}else if ($(obj).data("type") == "img") {
		if (!/\.(jpg|JPG|png|PNG)$/.test($(obj).val())) {
			toTipAnimateAlertEntry("请上传jpg或PNG文件！");
			return false;
		}
	}
	*/
	var file = $("input[name='uploadFile']").val();
	var filename=file.replace(/.*(\/|\\)/, ""); 
	var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : ''; 
	//暂时先不限制后缀 
	//if (!/\.(mp3|mp4)$/.test(fileExt)) {
	/*
	if(!(fileExt == "mp3" || fileExt == "mp4")){
		courseVideoFlag = false;
		alert("请上传音频文件！");
		return false;
	}*/
	/*
	if (!/\.(jpg|JPG|png|PNG)$/.test(fileExt)) {
		courseVideoFlag = false;
		alert("请上传jpg或PNG文件！");
		return false;
	}
	*/
	courseVideoFlag = true;
	 
	//$("#toUpladAnimateEntry").click();
	
	jQuery.ajaxFileUpload({
		url :ctx+"/upload/videoUpload?r="+Math.random(),
		secureuri : false,
		fileElementId : $(obj).attr("id"), //上传文件选择框的id属性  
		dataType : 'text', //json，与后台对应，text和json
		data:{"dir":$($(obj).data("id")).val(),"oldUrl":$("#replayFilePath").val()},
		success : function(data) { //后台ajax返回Imgurl
			var data = eval('(' + $(data).html() + ')');
			if (data.body.code == "200") {
				//alert(data.body.fileUrl);
				$("#videoPath").val(data.body.fileUrl);
				$("#videoName").val(data.body.fileName);
				//alert(data.body.fileName+"#####"+data.body.fileUrl);
				$("vName").val(data.body.fileName);
				//modeInfoChange("上传成功！");
				/*$("#closeTitleFlagAnimate").click();				
				$("#fileNameContainer").val(data.body.fileName);
				$("#replayFileNameContainer").val(data.body.fileName);
				
				$("#adjustFileContainer").show(); 
				$("#adjustFileNames").html(data.body.fileName);
				$("#adjustFileDelete").attr("onclick","deleteAdjustFile('"+data.body.fileUrl+"')");
				*/
				clearFileData("UploadFile");
				
			} else {
				alert("上传失败！");
			}

		},
		error : function(dataJson, status, e) {
			//errorMsg();
			
			//ajaxFileAlert();
			//$("#closeTitleFlagAnimate").click();
			clearFileData("UploadFile");
		}
	});

}

function deleteAdjustFile(url){
	
  if(deleteFile(url)){
  	 modeInfoChange("删除成功！");
  	 $("#closeTitleFlagAnimate").click();
  	 $("#adjustFileContainer").hide();
  	 $("#adjustFileNames").html("");
  	 
  }
}

var checkImportFileFlag = false;
/*
 * check 课程导入的 excel 文件检查
 */
function checkCourseImportFile(obj) {
	var file = $("input[name='uploadFile']").val();
	var filename=file.replace(/.*(\/|\\)/, ""); 
	var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : ''; 
	var msg ="";
	//alert(fileExt);
	if (	fileExt == "xls" || fileExt == "xlsx") {
		//alert("out"+fileExt);
		msg = "导入文件格式正确!";
		$("#myTipModalContainer").html(msg);
		checkImportFileFlag = true;
		//alert(checkImportFileFlag);
	}else{
	
	//alert("lat"+fileExt);
	checkImportFileFlag = false;
	var msg = "请标准的课程导入excel 表格文件！";
	$("#myTipModalContainer").html(msg);
	//toTipAnimateAlertEntry("请标准的课程导入excel 表格文件！");
	return false;
	}
}


//$("#name").focus();
function submitImport(){
	
	if(!checkImportFileFlag){
		var msg = "请标准的课程导入excel 表格文件！";
		//$("#messageBox").text(msg);
		$("#myTipModalContainer").html(msg);
		//alert("not psss");
		return false;
	}
	msg = "导入文件格式正确!";
	$("#myTipModalContainer").html(msg);
	//alert(" psss");
	loading('正在提交，请稍等...');
	$("form").submit();
}