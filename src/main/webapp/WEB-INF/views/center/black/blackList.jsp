 <%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <script type="text/javascript" src="${ctxStatic}/js/black.js" ></script>
<div class="heimingdan">
<form id="blackListForm"    modelAttribute="org" method="get" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="submit" id="searchBlackInfn" onclick="searchBlack()" style="display:none"  >
</form>
 	<div class="heimingdan_left">
    	<div class="top">
    		<p class="on">黑名单</p>
        </div>
        <ul>
         <c:forEach items="${page.list}" var="org">
        	<li>
            	 
               ${org.name}
            </li>
          </c:forEach>
             
        </ul>
       
    </div>
     
    <div class="heimingdan_right">
    	<div class="toushubox">
        	<div class="top"><span></span> 代理机构黑名单</div>
            <p>对于招标投标过程(包括招标、投标、开标、评标、中标)中泄漏保密资料、泄漏标底、串通招标、歧视排斥投标等违法活动的监督执法，按现行职责分工，分别由有关行政主管部门负责并受理投标人和其他利害关系人的投诉。</p>
        </div>
        <div class="toushu">
        	<p>违规投诉邮箱：</p>
            abc@ddd.com
        </div>
    </div>

    <div class="clear"></div>
        <div class="pagination">${page}</div>
</div>