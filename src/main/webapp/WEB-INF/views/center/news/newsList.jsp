 <%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <script type="text/javascript" src="${ctxStatic}/js/index.js"></script>
 <script type="text/javascript" src="${ctxStatic}/js/Designer.js"></script>
 </head>
  <script type="text/javascript" src="${ctxStatic}/js/news.js" ></script>
  <body style="background:#fff;">
<span id="message" class="uploadFile">${message }</span>
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
<input id="newsFlag" type="hidden"  value="${categoryId }"/>
 <input id="articleDataFlag"  type="hidden"  value="${articleData.id }"/>
  <input id="idFlag"   value="${id }"  type="hidden"/>
 <div id="pageContaienr">
 <div class="newbox">
 <form id="searchNewsList"    method="get" >
        <input id="pageNo"  name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="submit" id="searchNewsInfn" onclick="searchNews()" value="保存" style="display:none" >
</form>
	<div class="top" id="newsTitleContainer"></div>
    <div id="newsContainer" class="newsContent"></div>
</div>
</div>
   <%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
 </body>
</html>