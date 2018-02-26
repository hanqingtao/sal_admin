<%@ page language="java" import="java.lang.String" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> <%=appName%></title>
	<%@ include file="/common/meta.jsp"%>
	 <script type="text/javascript" src="<%=contextPath %>/js/des.js"></script>
	 <script type="text/javascript" src="<%=contextPath %>/js/login.js"></script>
	 <script language="JavaScript"> 
	 	//debugger;
	 	if(top.location != this.location){
		    top.location=location;
		}
		//if (window != top) 
		//top.location.href = location.href; 
	</script>
</head>
	<c:choose>
	<c:when test="${empty sessionScope.user}">
<body class="body1">
    <div class="top">
		<a href="<%=contextPath %>/index/index.shtml"><img src="<%=contextPath%>/images/index_6.gif" width="316" height="52"  /></a>
	    <div>找回密码</div>
	</div>
    <div class="mainn" style="height:400px;">
    	
        <form id="loginForm" action="<%=contextPath%>/user/frontLogin.do" method="post">
        <div class="findps">
        	<ul>
        		<%
					//生成随机类
					Random random = new Random();
					String sRand = "";
					for (int i=0;i<4;i++){
						String rand=String.valueOf(random.nextInt(10));
						sRand += rand;
					}
					//sRand = "2015";
				%>
            	<li><label>邮箱：</label><input type="text" placeholder="邮箱" name="mail"  id="mail" maxlength="64"/></li>
                <li><label>用户名：</label><input type="text" placeholder="用户名" name="userName" id="userName"  maxlength="64"/></li>
                <li class="dli">
                	<label>验证码：</label>
	                <input type="text" placeholder="验证码" name="randNum" id="randNum" maxlength="4" style="position: static;"/>
	                <img id="checkcode" width="113" height="40" src="<%=contextPath%>/common/image.jsp?sRand=<%=sRand%>" onclick="javascript: getYanZhengMa();" style="margin-left: 6px;" /> 
	                <input type="hidden" id="sRandNum" name="sRandNum" value="<%=sRand%>"/>
                </li>
                <li class="dli1"><label>&nbsp;</label><input name="" type="button" value="发送邮件" onclick="onFindPsw();"/></li>
            	<li><span id="message" ></span></li>
            </ul>
        </div>
        
    		
        </form>
    </div>
   	<%@ include file="/common/footer.jsp"%>
</body>
 <%@ include file="/common/script.jsp"%>
<script type="text/javascript" >
function onFindPsw(){
	// 验证码
	var mail = document.getElementById("mail").value;
	var userName = document.getElementById("userName").value;
	var randNum = document.getElementById("randNum").value;
	if(mail == ''){
		/////
		alert('请您输入邮箱！');
		document.getElementById("mail").focus();
		return false;
	}
	if(userName == ''){
		/////
		alert('请您输入用户名！');
		document.getElementById("userName").focus();
		return false;
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
        url : contextPath + "/user/vailUserInfoForFindPsw.do",
        data:{'mail':mail,'userName':userName},
        async: false,
        success: function(data) {
			if(data == 'yes'){
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
	       url : contextPath + "/user/sendPwdMail.do",
	       data:{'userName':userName},
	       async: false,
	       success: function(data) {
			   if(data == 'ok'){
		       		$("#message").val("邮件已经发送请登录注册邮箱完成操作!");
		       		//$('#mail').val('');
		       		//$('#userName').val('');
		       		//$('#randNum').val('');
		       	}
	      	 }
	   });
	}
}
</script>
    </c:when>
    <c:otherwise>
		<jsp:forward page="/index/index.shtml"/>
    </c:otherwise>
	</c:choose>
</html>
