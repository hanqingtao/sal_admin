<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
<script type="text/javascript" src="${ctxStatic}/js/login.js" ></script>
</head>
<script type="text/javascript">
//如果在框架或在对话框中，则弹出提示并跳转到首页
if(self.frameElement && self.frameElement.tagName == "IFRAME" ||
		$('#dl_dd_a_color').length > 0 || 
		$('.main_left').length > 0){
	alert('未登录或登录超时。请重新登录，谢谢！');
	top.location = "${ctxFront}";
}
</script>
<body style="background:#fff;">
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
<div id="pageContaienr">
  <div class="login_kuang" style="margin-top:30px;">
	<div class="top">
    	<div class="left">用户登录</div>
        <div class="right"><i class="info">${message }</i>&nbsp;&nbsp;&nbsp;<span>*</span>为必填项</div>
    </div>
    <div class="clear"></div>
     <form  method="post"  action="${ctxFront}/login" id="loginForm">  
     <input type="hidden" value="${qualificationManagement }" name="qualificationManagement" /> 
    <ul>
    	<li><span><em>*</em>用户名：</span><input type="text" class="text" name="loginName" id="loginName" required name="loginName" maxlength="32" value="${loginName }"/><i class="info" id="userNameLog"></i></li>
        <li><span><em>*</em>密码：</span><input type="password" class="text" required name="password" value="${password }" minlength="6" maxlength="64"/></li>
        <li><span><em>*</em>验证码：</span><input type="text" id="randNum" required  maxlength="4" class="text1" name="verification" value="${verification }"/>
        <img onclick="javascript: getYanZhengMa();" src="${ctxStatic}/common/image.jsp?sRand=6529" width="107" height="44" id="checkcode"  class="yzm" />&nbsp;&nbsp;<i id="infoLog" class="info"></i>
        <input id="sRandNum" value="6529" type="hidden"> 
     				<script>
             			$(function(){
             				getYanZhengMa();
             			})
             		</script>
        </li>
        
        <li><span>&nbsp;</span><input type="submit"   class="button"  value="登录" />&nbsp;&nbsp;<a target="_balck" href="${ctxFront}/toValidateEmail">找回密码?</a> </li>
            <div class="clear"></div>
            
    </ul>

    </form>
    <div class="clear"></div>
</div>
</div>
 <%@ include file="/WEB-INF/views/center/common/footer.jsp"%> 
</body>
</html>