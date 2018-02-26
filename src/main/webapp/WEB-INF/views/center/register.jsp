<%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
<script type="text/javascript" src="${ctxStatic}/js/login.js" ></script>
</head>
<body>
  <%@ include file="/WEB-INF/views/center/common/orgTop.jsp"%>
 <div id="pageContainer">  
<div class="login_kuang" style="margin-top:30px;">
	<div class="top">
    	<div class="left">帐号注册</div>
        <div class="right"><span>*</span>为必填项</div>
    </div>
    <div class="clear"></div>
     <form  action="${ctxFront}/org/orgUser/save"  id="orgUserForm">
    <ul>
    	<li><span><em>*</em>用户名：</span><input type="text" class="text" name="loginName" id="loginName"  required maxlength="32" minlength="2" autocomplete="off"><i id="msg" class="msgInfo"  ></i></li>
        <li><span><em>*</em>邮箱：</span><input type="email" class="text" name="email" id="email" email:true required maxlength="32" autocomplete="off"></li>
        <li><span><em>*</em>密码：</span><input type="password" class="text" name="password" id="password" required maxlength="64" minlength="6" autocomplete="off" /></li>
        <li><span><em>*</em>验证码：</span><input type="text" class="text1" required  id="randNum"    maxlength="4" />
        <img onclick="javascript: getYanZhengMa();" src="${ctxStatic}/common/image.jsp?sRand=6529" width="107" height="44" id="checkcode"  class="yzm" />&nbsp;&nbsp;<i id="infoLog" class="info"></i>
        <input id="sRandNum" value="" type="hidden"> 
        <script>
   			$(function(){
   				getYanZhengMa();
   			})
   		</script>
        </li>
        <li style="height:24px; margin-top:10px;"><span>&nbsp;</span><input type="checkbox" class="checkbox" id="agree" name="agree" required>&nbsp;阅读并接受<a href="#">《中央投资项目服务与管理平台用户协议》</a>
          <label for="agree" class="error"></label></li>
        <li><span>&nbsp;</span><input type="submit" class="button"  value="注册" /></li>
        <div class="clear"></div>
    </ul>
    </form>
    <div class="clear"></div>
</div>
</div>
  <%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
</body>
</html>
<body>
