
$(function(){
	$("#dl_dd_a_color dd").click(function (event){
		var i=$(this).index();
		 if($("#orgFlag").val().length!=0){
		 	$('#dl_dd_a_color dd').attr("class","");
		    $(this).attr("class","on");
		 }
		
		
	})
	
})
 

/**
 * 代理机构资质管理和中央项目投资管理
 * @param arg
 * @param obj
 */
function  ApplicationSwitch(arg,obj){
 
	 if(arg==="prjectManagement"){
		 
			$.ajax({
	            async:true,
	            url:ctxFront+"/pro/project/entry",
	            type:"post",
	            success:function(data){
	                $("#applicationContainer").html(data);
	                
	               
	            }
	        }); 
		};
	
	 if(arg==="qualificationManagement"){
		 
		 $.ajax({
	            async:true,
	            url:ctxFront+"/org/org/enter.do",
	            type:"post",
	            success:function(data){
	                $("body").html(data);
	                 
	            }
	        });
		};
	
	
}



  /**
   * 创建项目
   */
   function createProject(){
	   $.ajax({
	         async:true,
	         url:ctxFront+"/pro/project/add",
	         type:"post",
	         success:function(data){
	        	 $("#applicationContainer").html(data);
	         }
	 });
   }
   
   
   /**
    * 进入项目上传招标文件的入口
    */
   function uploadingBiddingDocumentsEntry(projectFlowId){
	   /*$.ajax({
	         async:true,
	         url:ctxFront+"/pro/project/uploadBiddingDocEntry",
	         type:"post",
	         success:function(data){
	        	 $("#applicationContainer").html(data);
	         }
	 });*/
	   //alert("projectFlowId:"+projectFlowId);
	   window.location.href=ctxFront+"/pro/projectFlow/uploadBiddingDocEntry?id="+projectFlowId;
   }
  
   /**
    * 进入项目上传相关资料的入口
    */
   function projectUploadDataEntry(){
   
	   /*$.ajax({
	         async:true,
	         url:ctxFront+"/pro/project/uploadAnnouncement",
	         type:"post",
	         success:function(data){
	        	 $("#applicationContainer").html(data);
	         }
	 });*/
	   window.location.href=ctxFront+"/pro/project/uploadAnnouncement?id="+proId;
   }
   
   
   
   
   
   
   