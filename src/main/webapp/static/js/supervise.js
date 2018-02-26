/**
 * 日常监督相关js
 */
$(function(){
	  
	   
	   function comSuperviseContent(arg){
		   $.ajax({
	        async:true,
	        url:ctxFront+"/cms/article/vise", 
	        data:{"categoryId":arg,"delFlag":"1"},
	        type:"post",
	        success:function(data){
	            $("#pageContaienr").html(data);
	            
	        }
	    });
	   }
	  
	   
	
	//获取日常监督的4个标题
	$.ajax({
        type : "post",
        async:true,
        url : ctxFront + "/cms/article/title",
        data : {  "inMenu":"0","site.id":"1","parent.id":"1"},
        dataType : "json",
        success : function(dataJson) {
        	 var length=dataJson.length;
             if(length>=9){
             	length=8;
             }
            var str="";
              for(var i=0;i<length;i++){
                  str+="<p data="+dataJson[i].id+" title="+dataJson[i].name+">"+dataJson[i].name+"</p>";
              }
              $("#superviseTiterContainer").html(str); 
              
               $("#superviseTiterContainer").find("p").first().attr("class","on"); 
               
              for(var j=0;j<=$("#superviseTiterContainer").find("p").length;j++){
       			   
       			  if($($("#superviseTiterContainer").find("p")[j]).attr("data")==$("#categoryIdInfo").val()){
       				$($("#superviseTiterContainer").find("p")[j]).attr("class","on").siblings().removeClass('on');
       			  }
       			   
       		  }
              
              
              
              $('#superviseTiterContainer p').click(function() {
   	           var i = $(this).index();
   	            
   	        	  var n=$(this).attr("data");
   	        	   comSuperviseContent(n);
   	        	    
   	           $(this).addClass('on').siblings().removeClass('on');
   	            $('#superviseContainer #flag').eq(i).show().siblings().hide();
   	           
	   	        
   	       });
        }
    }); 
	 
	 
	   
  })
  
  
    function searchSupervise(){
		   $('#searchSuperviseList').submit(function(){
	       jQuery.ajax({
	           url:ctxFront+"/cms/article/vise",
	           data: $('#searchSuperviseList').serialize(),
	           type: "get",
	           beforeSend: function() {},
	           success: function(data) {
	        	    $("#pageContaienr").html(data);
	           }
	           });
	          return false;
	       });       	
	   }
  

 
function lookCon(id){
	 
	  $.ajax({
         async:true,
         url:ctxFront+"/cms/article/con?id="+id,
         type:"post",
         success:function(data){
        	  
             $("#superviseContainer").html(data);
         }
     }); 
	 
}
  
  //分页
  function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchSuperviseInfo").click();
    	return false;
  }
