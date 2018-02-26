 <%@ page contentType="text/html;charset=UTF-8" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <div class="biaoti">${article.title }</div>
    <div class="time">来源：<span>${articleData.copyfrom }</span>&nbsp;&nbsp;&nbsp;发布时间：<fmt:formatDate value='${article.createDate }' pattern='yyyy-MM-dd'/></div>
    <div class="text">
    	<p>${articleData.content }</p>
    </div>



