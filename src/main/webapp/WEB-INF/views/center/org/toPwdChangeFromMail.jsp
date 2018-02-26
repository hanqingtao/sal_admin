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
    	<div class="left">新密码</div>
        <div class="right"><span>*</span>为必填项</div>
    </div>
    <div class="clear"></div>
    <form  method="post"  action="${ctxFront}/passChange" id="RestPwdForm">
    <input type="hidden" value="${loginName }" name="loginName"/>
    <ul>
        <li><span><em>*</em>新密码：</span><input type="password" class="text"  name="NewPwd"  id="FirstPwd"   required maxlength="60"></li>
          <li><span><em>*</em>重复新密码：</span><input type="password" class="text" name="NewPwd" id="TwoPwd"   required maxlength="60"></li>
        <li><span>&nbsp;</span><input type="submit" class="button"     value="提交" /></li>
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
