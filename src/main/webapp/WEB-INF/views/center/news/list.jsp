  <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <ul>
    <c:forEach items="${page.list}" var="article">
   	<li><div class="left"><a  title="${article.title} " href="javascript:lookCon(${article.id});">${article.title} </a></div><span><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd  hh:mm:ss"/></span></li>
      </c:forEach>
   </ul>
   <div class="pagination">${page}</div>

