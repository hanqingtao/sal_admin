<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="fnc" uri="/WEB-INF/tlds/fnc.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="act" tagdir="/WEB-INF/tags/act" %>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/cms" %>

<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getAdminPath()}"/>
<c:set var="ctxFront" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="imgServer" value="http://192.168.2.238:1899"/>
<c:set var="domain" value="http://192.168.1.185:8080"/>
<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}',ctxFront='${ctxFront}';var imgServer = '${imgServer}'; var domain='${domain}'</script>