<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="login_header">
	<div class="gongyong_kuan">
        <div class="logo"><a href="${ctxFront }"><img src="${ctxStatic}/images/login_logo.png" width="310" height="62" /></a></div>
         
        <c:if test="${empty user}">
               <div class="right">已有帐号？<a href="${ctxFront }/toLogin">请登录</a></div>
            </c:if>
            <c:if test="${not empty user}">
                <div class="right">  你好，${user.loginName } &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:loginOut();">退出</a></div>
            </c:if>
    </div>
</div>