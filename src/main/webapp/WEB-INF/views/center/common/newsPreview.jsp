 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 </head>
<body style="background:#fff;">
<span id="message" class="uploadFile">${message }</span>
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
  <div id="pageContaienr">
   <div class="newbox">
  <div class="biaoti">${article.title }</div>
    <div class="time">来源：<span>${articleData.copyfrom }</span>&nbsp;&nbsp;&nbsp;发布时间：<fmt:formatDate value='${article.createDate }' pattern='yyyy-MM-dd'/></div>
    <div class="text">
    	<p>${articleData.content }</p>
    </div>
  </div>
</div>
  <%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
   
 </body>
</html>
