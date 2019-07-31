<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <head>
    <meta name="renderer" content="webkit">
 </head>
<body>
<%
	String url = request.getContextPath()+"/a";
	System.out.println(url);
	response.sendRedirect(url);
%>
 </body>
</html>
