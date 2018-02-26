/**
 * validate验证表单
 */
 $.validator.setDefaults({
		     submitHandler: function() {
		    	 var randNum = $('#randNum').val();
		 		var sRandNum = $('#sRandNum').val();
		 		if (randNum.length < 1) {
		 			$("#infoLog").html('请输入验证码！');
		 			return;
		 		} else if (randNum != sRandNum) {
		 			$("#infoLog").html('验证码输入错误！');
		 			return;
		 		}
		        form.submit();
		     }
		 });
 $(function(){
	    //登录验证
		$("#loginForm").validate();
		 //注册验证
		 $("#orgUserForm").validate();
		 //重置密码验证
		 $("#RestPwdForm").validate();
		 //注册时校验登录名是否可用
		 $("#loginName").blur(function(){
			 var reg_name = $("#loginName").val();
			if($.trim(reg_name) == ''){
//				$("#msg").css("color","red");	
//				$("#msg").html('用户名不能为空!').show();
				return;
			}else{
				var reg = /^[0-9a-zA-Z]+$/;
				if(!reg.test($.trim(reg_name))){
					$("#msg").css("color","red");	
					$("#msg").html('用户名只能包含字母及数字！').show();
					return;
				}
			}
			 if($("#loginName").val().length>=2){
	            $.ajax({
	                type: "post",
	                url: ctxFront+"/org/orgUser/find",
	                data: {"loginName":$("#loginName").val()},
	                dataType:'json',
	                success: function(msg){
	                	if(msg=="该登录名可以使用"){
	                		$("#msg").css("color","green");	
	                	}
	                    $("#msg").html(msg).show();
	                }
	            });
			 }
	        });
	})
	
/**
 * 刷新验证码
 */
function getYanZhengMa() {
	var sRandNum = "";
	for (var i = 0; i < 4; i++) {
		var randNum = Math.floor(Math.random() * 10);
		sRandNum = sRandNum + randNum;
	}
	$("#checkcode").attr("src",ctxStatic+"/common/image.jsp?sRand=" + sRandNum);
	$("#sRandNum").val(sRandNum);
}
  
 
 
 function onFindPsw(){
		// 验证码
		var mail = document.getElementById("mail").value;
		var userName = document.getElementById("userName").value;
		var randNum = document.getElementById("randNum").value;
		if(userName == ''){
			/////
			alert('请您输入用户名！');
			document.getElementById("userName").focus();
			return false;
		}
		if(mail == ''){
			/////
			alert('请您输入邮箱！');
			document.getElementById("mail").focus();
			return false;12
		}
		

		if(randNum == ''){
			alert("请您输入验证码！");
			document.getElementById("randNum").focus();
			return false;
		}else{
			var sRandNum = document.getElementById("sRandNum").value;
			if(sRandNum != randNum){
				alert("您输入的验证码有误！");
				document.getElementById("randNum").focus();
				return false;
			}	
		}
		
		var isContinue = false;
		$.ajax({
	        type: "POST",
	        url : ctxFront + "/vailUserInfoForFindPsw",
	        data:{'mail':mail,'userName':userName},
	        async: false,
	        dataType:'json',
	        success: function(data) {
				if(data.m1 == 'yes'){
	        		isContinue = true;
	        	}else{
	        		alert("邮箱用户信息不匹配！");
	        		$('#randNum').val('');
	        	}
	       	 }
	    });
	    
		if(isContinue){
			$.ajax({
		       type: "POST",
		       url : ctxFront + "/sendPwdMail",
		       data:{'userName':userName},
		       async: false,
		       success: function(data) {
				   if(data == 'ok'){
			       		alert("邮件已经发送，请登录注册邮箱完成操作！");
			       		$('#mail').val('');
			       		$('#userName').val('');
			       		$('#randNum').val('');
			       	}
					
		      	 }
		   });
		}
		
	}
 
 
 function restPsw(){
		if($("#FirstPwd").val()!=$("#TwoPwd").val()){
			$("#infoLog").html('两次密码不一样！');
			return;
		}
		
 }