 function proDetails(){
	 $.ajax({
	        async:true,
	        url:ctxFront+"/pro/project/details",
	        type:"post",
	        success:function(data){
	        	$("#pageContainer").html(data);
	        }
	    });
 }