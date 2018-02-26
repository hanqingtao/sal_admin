<%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
<script type="text/javascript" src="${ctxStatic}/js/login.js" ></script>
</head>
<body style="background:#fff;">
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
<div id="pageContaienr"> 
<div class="login_kuang" style="margin-top:30px;">
	<div class="top">
    	<div class="left">找回密码</div>
        <div class="right"><span>*</span>为必填项</div>
    </div>
    <div class="clear"></div>
   
    <ul>
    	<li><span><em>*</em>用户名：</span><input type="text" class="text" name="loginName" id="userName"  required maxlength="32" minlength="2"><i id="msg" class="msgInfo"  ></i></li>
        <li><span><em>*</em>邮箱：</span><input type="email" class="text" name="email" id="mail" email:true required maxlength="32"></li>
        <li><span><em>*</em>验证码：</span><input type="text" class="text1" required  id="randNum"    maxlength="4" />
        <img onclick="javascript: getYanZhengMa();" src="${ctxStatic}/common/image.jsp?sRand=6529" width="107" height="44" id="checkcode"  class="yzm" />&nbsp;&nbsp;<i id="infoLog" class="info"></i>
        <input id="sRandNum" value="6529" type="hidden"> 
        </li>
        <li><span>&nbsp;</span><input type="submit" class="button"  onclick="onFindPsw();" value="发送邮件" /></li>
        <div class="clear"></div>
    </ul>
   
    <div class="clear"></div>
</div>
</div>
  <%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
</body>
</html>
<body>
