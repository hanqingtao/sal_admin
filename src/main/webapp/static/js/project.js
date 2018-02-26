 $(function(){
	   /**
	    * 工程表单验证
	    */
		//$("#engForm").validate();
		/**
		 * 项目表单验证
		 */
		//$("#projectForm").validate();
	 	createProject_submit();
		
		
		$("#resultForm").validate();
		//获取行业
		 $.ajax({
 	        type : "post",
 	        async:true,
 	        url : ctxFront + "/etc/term",
 	        data : { "option" : "industry" },
 	        dataType : "json",
 	        success : function(data) {
 	        	$(data).each(function(){
 	        		var str="<option value="+this.value+">"+this.label+"</option>";
 	        		$("#IndustryContainer").append(str);
				});
 	        	if($("#industryFlag").val()!=null||$("#industryFlag").val()!=" "){
 				   var length=$("#IndustryContainer").find("option").length;
 				   for(var i=0;i<length;i++){
 					   if($($("#IndustryContainer").find("option")[i]).attr("value")==$("#industryFlag").val()){
 						  $($("#IndustryContainer").find("option")[i]).attr("selected","selected");
 						  return;
 					  }  
 				   }
 			   }
 	        }
 	    });
		 //中央投资资金具体来源
		 $.ajax({
	 	        type : "post",
	 	        async:true,
	 	        url : ctxFront + "/etc/term",
	 	        data : { "option" : "centralinvest_type" },
	 	        dataType : "json",
	 	        success : function(data) {
	 	        	$(data).each(function(){
	 	        		var str="<option value="+this.value+">"+this.label+"</option>";
	 	        		$("#centralinvestTypeContainer").append(str);
					});
	 	        	if($("#centralinvestTypeFlag").val()!=null||$("#centralinvestTypeFlag").val()!=" "){
	  				   var length=$("#centralinvestTypeContainer").find("option").length;
	  				   for(var i=0;i<length;i++){
	  					   if($($("#centralinvestTypeContainer").find("option")[i]).attr("value")==$("#centralinvestTypeFlag").val()){
	  						  $($("#centralinvestTypeContainer").find("option")[i]).attr("selected","selected");
	  						  return;
	  					  }  
	  				   }
	  			   }
	 	        }
	 	    });
		//中央投资资金使用方式
		 $.ajax({
	 	        type : "post",
	 	        async:true,
	 	        url : ctxFront + "/etc/term",
	 	        data : { "option" : "centraluse_type" },
	 	        dataType : "json",
	 	        success : function(data) {
	 	        	$(data).each(function(){
	 	        		var str="<option value="+this.value+">"+this.label+"</option>";
	 	        		$("#centraluseTypeContainer").append(str);
					});
	 	        	 if($("#centraluseTypeFlag").val()!=null||$("#centraluseTypeFlag").val()!=" "){
	 	  				   var length=$("#centraluseTypeContainer").find("option").length;
	 	  				   for(var i=0;i<length;i++){
	 	  					   if($($("#centraluseTypeContainer").find("option")[i]).attr("value")==$("#centraluseTypeFlag").val()){
	 	  						  $($("#centraluseTypeContainer").find("option")[i]).attr("selected","selected");
	 	  						  return;
	 	  					  }  
	 	  				   }
	 	  			   }
	 	        }
	 	      
	 	    });
		 
		//查询地区列表
		   $.ajax({
	           type : "post",
	           async:true,
	           url : ctxFront + "/etc/area",
	           data : { "option" : "" },
	           dataType : "json",
	           success : function(data) {
	        	   $(data).each(function(){
	 	        		var str="<option value="+this.id+">"+this.name+"</option>";
	 	        		$("#areaContainer").append(str);
					});
	        	   $("#areaContainer").find("option").first().remove();
	        	   if($("#areaIdFlag").val()!=null||$("#areaIdFlag").val()!=" "){
	  				   var length=$("#areaContainer").find("option").length;
	  				   for(var i=0;i<length;i++){
	  					   if($($("#areaContainer").find("option")[i]).attr("value")==$("#centraluseTypeFlag").val()){
	  						  $($("#areaContainer").find("option")[i]).attr("selected","selected");
	  						  return;
	  					  }  
	  				   }
	  			   }
	           }
	       });
		   
		   
		   
		   
	})
	
	
	 function createEngineer(){
        $('#engForm').submit(function() {
            jQuery.ajax({
                url: ctxFront+"/pro/engineer/save",
                data: $('#engForm').serialize(),
                type: "POST",
                beforeSend: function() {},
                success: function(data) { 
                    $("#applicationContainer").html(data);
                }
            });
           return false;
        });        	
    }
    
	 function createProject_submit(){
		 $("#engForm").validate({
			 submitHandler: function(form){
				 //alert('正确可以提交');
				 createProject(); 
			 },
			 errorPlacement: function(error, element) {
				 if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					 error.appendTo(element.parent().parent());
				 } else {
					 error.insertAfter(element);
				 }
			 }
		 });
	 }
    function createProject(){
    	$.ajax({
    		type : "post",
            async:true,
            url: ctxFront+"/pro/project/save",
            data: $('#engForm').serialize(),
            success: function(data) {
            	alert('项目信息保存成功！');
            	$('#projectSwitch').click();
            }
    	})
//    	$('#engForm').submit(function(){
//            jQuery.ajax({
//                url: ctxFront+"/pro/project/save",
//                data: $('#engForm').serialize(),
//                type: "POST",
//                beforeSend: function() {},
//                success: function(data) {
//                	alert('项目信息保存成功！');
//                }
//            });
//        });        	
    }
    
    /**
     * 上传招标公告或投标邀请书
     */
    function uploadingBiddingDocuments(){
    	$("#biddingDocuments").click();
    }
    
    
    /**
     * 获取上传公告名称
     */
    $("#biddingDocuments").on("change", function () {
        
        var obj = document.getElementById("biddingDocuments");
        var length = obj.files.length;
        var temp=null;
        for (var i = 0; i < length; i++) {
            $("#infoLog").html("");
             temp = obj.files[i].name;
        }
        $("#infoLog").html(temp);
    });
    
    /**
     * 上传公告验证
     */
    function uploadAnnouncementVerification(){
    	var id_noticeStatus = $("#id_noticeStatus").val();
    	if(id_noticeStatus != '1'){
    		if($("#infoLog").html().length==0||$("#infoLog").html()==" "){
    			$("#infoLog").html("请上传招标公告或投标邀请书");
    			return false;
    		}
    	}
//    	var noticeForm=$("#noticeForm");
//    	noticeForm.submit();
    	$.ajax({
    	    url: ctxFront+'/pro/projectFlow/noticeSave',
    	    type: 'POST',
    	    cache: false,
    	    data: new FormData($('#noticeForm')[0]),
    	    processData: false,
    	    contentType: false
    	}).done(function(res) {
    		alert('信息保存成功！');
    		//toUploadFile_projectFlow('/pro/projectFlow/uploadBiddingDocEntry?id='+id+'&projectId='+projectId);
    		$("#applicationContainer").html(res);
    	}).fail(function(res) {
    		alert('信息保存失败！');
    	});
    }
    
    
    /**
     * 上传招标文件
     */
    function  uploadingTenderFile(){
    	$("#tenderFile").click();
    }
    
    /**
     * 获取上传招标文件名称
     */
    $("#tenderFile").on("change", function () {
        
        var obj = document.getElementById("tenderFile");
        var length = obj.files.length;
        var temp=null;
        for (var i = 0; i < length; i++) {
            $("#infoLog").html("");
             temp = obj.files[i].name;
        }
        $("#infoLog").html(temp);
    });
    /**
     * 上传招标公告校验
     * @returns {Boolean}
     */
    
    function uploadingTenderVerification(){
    	var id_tenderStatus = $("#id_tenderStatus").val();
    	if(id_tenderStatus != '1'){    		
    		if($("#infoLog").html().length==0||$("#infoLog").html()==" "){
    			$("#infoLog").html("请上传招标文件");
    			return false;
    		}
    	}
//    	var tenderForm=$("#tenderForm");
//    	tenderForm.submit();
    	$.ajax({
    	    url: ctxFront+'/pro/projectFlow/tenderSave',
    	    type: 'POST',
    	    cache: false,
    	    data: new FormData($('#tenderForm')[0]),
    	    processData: false,
    	    contentType: false
    	}).done(function(res) {
    		alert('信息保存成功！');
    		//toUploadFile_projectFlow('/pro/projectFlow/uploadBidReportEntry?id='+id+'&projectId='+projectId);
    		$("#applicationContainer").html(res);
    	}).fail(function(res) {
    		alert('信息保存失败！');
    	});
    }
    
    /**
     * 上传开标记录
     */
    function uploadNoteFile(){
    	$("#noteFile").click();
    }
    /**
     * 获取上传开标记录文件名称
     */
    $("#noteFile").on("change", function () {
        
        var obj = document.getElementById("noteFile");
        var length = obj.files.length;
        var temp=null;
        for (var i = 0; i < length; i++) {
            $("#infoLogNote").html("");
             temp = obj.files[i].name;
        }
        $("#infoLogNote").html(temp);
    });
    /**
     * 上传评标专家组成
     */
    function uploadGroupFile(){
    	$("#groupFile").click();
    }
    
    /**
     * 获取上传评标专家文件名称
     */
    $("#groupFile").on("change", function () {
        
        var obj = document.getElementById("groupFile");
        var length = obj.files.length;
        var temp=null;
        for (var i = 0; i < length; i++) {
            $("#infoLogGroup").html("");
             temp = obj.files[i].name;
        }
        $("#infoLogGroup").html(temp);
    });
    
    
    /**
     * 上传开标记录和上传评标专家组成校验
     * @returns {Boolean}
     */
    
    function uploadNoteGroupVerification(){
    	var id_reportStatus = $("#id_reportStatus").val();
    	if(id_reportStatus != '1'){
	    	if($("#infoLogNote").html().length==0||$("#infoLogNote").html()==" "){
	    		$("#infoLogNote").html("请上传开标记录");
	    		return;
	    	}
	    	if($("#infoLogGroup").html().length==0||$("#infoLogNote").html()==" "){
	    		$("#infoLogGroup").html("请上传评标专家组成");
	    		 return;
	    	}
    	}
//    	var reportForm=$("#reportForm");
//      reportForm.submit();
    	$.ajax({
    	    url: ctxFront+'/pro/projectFlow/reportSave',
    	    type: 'POST',
    	    cache: false,
    	    data: new FormData($('#reportForm')[0]),
    	    processData: false,
    	    contentType: false
    	}).done(function(res) {
    		alert('信息保存成功！');
    		//toUploadFile_projectFlow('/pro/projectFlow/uploadBidResultEntry?id='+id+'&projectId='+projectId);
    		$("#applicationContainer").html(res);
    	}).fail(function(res) {
    		alert('信息保存失败！');
    	});
    }
    
    
    function rtn(){
   	 window.location.href = ctxFront+"?flag=1";
    }
    
    
    
    function uploadBidVerification(){
    	var id_bidStatus = $('#id_bidStatus').val();
    	if(id_bidStatus != '1'){
	    	if($("#infoLog").html().length==0||$("#infoLog").html()==" "){
	    		$("#infoLog").html("请上传中标通知书");
	    		return;
	    	}
    	}
    	$.ajax({
    	    url: ctxFront+'/pro/projectFlow/bidSave',
    	    type: 'POST',
    	    cache: false,
    	    data: new FormData($('#resultForm')[0]),
    	    processData: false,
    	    contentType: false
    	}).done(function(res) {
    		alert('信息保存成功！您已完成所有资料上传。');
    		//toUploadFile_projectFlow('/pro/projectFlow/uploadBidResultEntry?id='+id+'&projectId='+projectId);
    		$("#applicationContainer").html(res);
    	}).fail(function(res) {
    		alert('信息保存失败！');
    	});
    }
    
    
    /**
     * 进入项目上传评标报告的入口
     */ 

     function UploadingBidEvaluationReportEntry(id){
  	   
  	   window.location.href=ctxFront+"/pro/projectFlow/uploadBidReportEntry?id="+id;
     }
    
     /**
      * 进入中标结果入口
      */
     function UploadingBidResultEntry(id){
 
  	   /*$.ajax({
  	         async:true,
  	         url:ctxFront+"/pro/project/uploadBidResultEntry",
  	         type:"post",
  	         success:function(data){
  	        	 $("#applicationContainer").html(data);
  	         }
  	 });*/
  	 window.location.href=ctxFront+"/pro/projectFlow/uploadBidResultEntry?id="+id;
     }
    
    