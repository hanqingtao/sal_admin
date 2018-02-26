<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <script type="text/javascript" src="${ctxStatic}/js/jquery.placeholder.min.js" ></script>
<!-- 储存机构性质 -->
<input  type="hidden" id="orgNaturePassValue" value="" />
<input  type="hidden" id="newsPassValue" value="" />
<input type="hidden" id="supervisePassValue" /> 
<input type="hidden" id="agentListPassValue" /> 
<div class="top">
     
    	<div class="gongyong_kuan">
			<div class="left">欢迎来到中央投资项目服务与管理平台！</div>
            <div class="right">
            <c:if test="${empty user}">
               <a href="${ctxFront}/toLogin">请登录</a>&nbsp;|&nbsp;<a href="${ctxFront}/org/orgUser/entry">免费注册</a>
            </c:if>
            <c:if test="${not empty user}">
                                          你好 ${user.loginName }，<a href="javascript:;" id="userContainer" style="color:#2783e1" onclick="qualificationManagement()">我的应用</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:loginOut();">退出</a>
            </c:if>
            &nbsp;&nbsp;&nbsp;&nbsp;<a href="#"></a><a href="javascript:complaintEntry();">违规投诉</a></div>
		</div>
    </div>
    <div class="cer">
    <form id="searchOrgForm"  method="post" >
    	<div class="gongyong_kuan">
			<div class="logo"><a href="${ctxFront }"><img src="${ctxStatic}/images/logo.png" width="222" height="61" /></a></div>
            <div class="header_search">
				<input type="text" name="name" id="orgName" class="text" placeholder="请输入代理机构名称">
            	<input type="submit" class="button" onclick="searchOrgInfo()"  value="搜索">
            </div>
        </div>
        </form>
	</div>