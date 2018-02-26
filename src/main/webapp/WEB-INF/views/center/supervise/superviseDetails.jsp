 <%@ page contentType="text/html;charset=UTF-8" %> 
<div class="newbox">
<div class="top" id="superviseTiterContainer"></div>
     
		<div class="biaoti">${article.title }</div>
	    <div class="time">来源：<span>${articleData.copyfrom }&nbsp;&nbsp;&nbsp;发布时间：<fmt:formatDate value='${article.createDate }' pattern='yyyy-MM-dd '/></div>
	    <div class="text">
	    	<p>${articleData.content }</p>
	    </div>
   
</div>
 

