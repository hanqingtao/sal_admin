<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <dl class="main_left" id="dl_dd_a_color">
        <input type="hidden" id="orgFlag" value="${org.id }"/>
    	<dt>我的应用</dt>
    	<dd class="on" onclick="ApplicationSwitch('qualificationManagement',this)"><a id="applicationSwitch"  href="#" ><em class="em2"></em>&nbsp;&nbsp;资质管理</a></dd>
    	<c:if test="${empty org}">
          <dd onclick="alert('请您先注册代理机构信息！')"><a style="cursor:default"  href="javascript:;" ><em class="em1"></em>&nbsp;&nbsp; 项目管理</a></dd>
        </c:if>
        <c:if test="${!empty org}">
        <dd onclick="ApplicationSwitch('prjectManagement',this)"><a id="projectSwitch"  href="#" ><em class="em1"></em>&nbsp;&nbsp; 项目管理</a></dd>
        </c:if>
        
    </dl>
<script src="${ctxStatic}/js/switch.js"></script>
    
    