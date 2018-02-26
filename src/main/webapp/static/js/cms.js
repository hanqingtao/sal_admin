 $(function(){
	 $("#test").click(function(){
		 $("#UploadID").click();
	 })
 })

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
 	//alert(iFileId + "  $   " + imgId + "  $  " + hiddenId);
//	var imgServer = $('#imgServer').val();
	jQuery.ajaxFileUpload({
         url:ctxFront+"/cms/article/ajaxUploadImage", 
         secureuri:false,  
         fileElementId:iFileId,    //上传文件选择框的id属性  
         dataType: 'text',   //json，与后台对应，text和json
         success: function (data) {        //后台ajax返回Imgurl
           //console.log(imgServer + eval('(' + $(data).html() + ')').resPath);
		 	$("#"+imgId).attr("src",imgServer + eval('(' + $(data).html() + ')').resPath);  //添加到回显的img标签上，同时该标签自动发送该图片的url
	        $("#"+hiddenId).val(eval('(' + $(data).html() + ')').resPath);    //要保存的图片地址
   		 },
   		 error:function(data,status,e){  
            alert('上传失败！'+e);  
   		 }
	 }); 
}