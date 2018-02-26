function searchBlack(){
	$('#blackListForm').submit(function(){
	       jQuery.ajax({
	           url: ctxFront+"/black",
	           data: $('#blackListForm').serialize(),
	           type: "get",
	           beforeSend: function() {},
	           success: function(data) {
	               $("#pageContaienr").html(data);
	               }
	           });
	          return false;
	       });       
}

//分页
   function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchBlackInfn").click();
     	return false;
   }