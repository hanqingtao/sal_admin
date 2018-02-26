 <%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <script type="text/javascript" src="${ctxStatic}/js/supervise.js" ></script>
  <form id="searchSuperviseList"   method="get" >
        <input id="pageNo"  name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="categoryIdInfo" type="hidden" name="categoryId"   value="${categoryId}"/>
		<input type="submit" id="searchSuperviseInfo" onclick="searchSupervise()" value="保存" style="display:none" >
   </form>
 <div class="newbox">
	<div class="top" id="superviseTiterContainer"></div>
    <div id="superviseContentContainer">
    <div id="superviseContainer" class="newsContent">
        <div id="flag" class="flag1">
	    <ul>
	     <c:forEach items="${page.list}" var="supervise">
	    	<li><div class="left"><a   href="javascript:lookCon(${supervise.id});">111111${supervise.title} </a></div><span><fmt:formatDate value="${supervise.updateDate}" pattern="yyyy-MM-dd  hh:mm:ss"/></span></li>
	       </c:forEach>
	    </ul>
	    <div class="pagination">${page}</div>
       </div>
    </div>
 </div>
     
</div>