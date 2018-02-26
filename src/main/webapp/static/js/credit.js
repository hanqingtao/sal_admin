function searchCredit(){
	$('#creditListForm').submit(function(){
	       jQuery.ajax({
	           url: ctxFront+"/pro/creditRecord/list",
	           data: $('#creditListForm').serialize(),
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
		$("#searchCreditInfn").click();
     	return false;
   }