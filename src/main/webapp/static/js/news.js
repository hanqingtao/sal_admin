$(function(){
	$.ajax({
        type : "post",
        async:true,
        url : ctxFront + "/cms/article/title",
        data : {  "inMenu":"1","site.id":"1","parent.id":"1"},
        dataType : "json",
        success : function(dataJson) {
            var str="";
            var length=dataJson.length;
            if(length>=9){
            	length=8;
            }
            for(var i=0;i<length;i++){
           	 str+="<p data="+dataJson[i].id+" title="+dataJson[i].name+">"+dataJson[i].name+"</p>";
            }
            $("#newsTitleContainer").html(str);
            $("#newsTitleContainer").find("p").first().attr("class","on");
            for(var j=0;j<=$("#newsTitleContainer").find("p").length;j++){
    			   
     			  if($($("#newsTitleContainer").find("p")[j]).attr("data")==$("#newsFlag").val()){
     				$($("#newsTitleContainer").find("p")[j]).attr("class","on").siblings().removeClass('on');
     			  }
     		  }
            //新闻tab切换    
     	   $('#newsTitleContainer   p').click(function() {
     	           var i = $(this).index();
     	            var n=$(this).attr("data");
 	            	$("#newsFlag").val(n);
 	            	$.ajax({
 	                   async:true,
 	                   url:ctxFront+"/cms/article/list?categoryId="+n,
 	                   type:"post",
 	                   success:function(data){
 	                  	 $("#newsContainer").html(data);
 	                   }
 	               }); 
     	           $(this).addClass('on').siblings().removeClass('on');
     	           $('#newsContainer #flag').eq(i).show().siblings().hide();
     	       });
        }
    });
	 
	  if($("#idFlag").val()==0){
	  $.ajax({
	         async:true,
	         url:ctxFront+"/cms/article/list?categoryId="+$("#newsFlag").val(),
	         type:"post",
	         success:function(data){
	        	 $("#newsContainer").html(data);
	         }
	     });
	  }
	
	 if($("#articleDataFlag").val()==null||$("#articleDataFlag").val()==""){
	 }else{
		 $.ajax({
             async:true,
             url:ctxFront+"/cms/article/con?id="+$("#articleDataFlag").val(),
             type:"post",
             success:function(data){
            	 $("#newsContainer").html(data);
             }
         }); 
	 }
	   
  })
  
 

  function searchNews(){
	 var categoryId=$("#newsFlag").val();
		   $('#searchNewsList').submit(function(){
	       jQuery.ajax({
	           url:ctxFront+"/cms/article/list?categoryId="+categoryId,
	           data: $('#searchNewsList').serialize(),
	           type: "get",
	           beforeSend: function() {},
	           success: function(data) {
	        	   $("#newsContainer").html(data);
	           }
	           });
	          return false;
	       });       	
	   }
  

//查看新闻详情
function lookCon(id){
	  $.ajax({
         async:true,
         url:ctxFront+"/cms/article/con?id="+id,
         type:"post",
         success:function(data){
        	 $("#newsContainer").html(data);
         }
     }); 
	 
}

//分页
  function page(n,s){
	  $("#newsFlag").val($("#newsTitleContainer .on").attr("data"));
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchNewsInfn").click();
    	return false;
  }