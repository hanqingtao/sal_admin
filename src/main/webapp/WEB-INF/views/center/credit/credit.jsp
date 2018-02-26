 <%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
   <script type="text/javascript" src="${ctxStatic}/js/credit.js" ></script>
 <form id="creditListForm"   method="get" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="submit" id="searchCreditInfn" onclick="searchCredit()" style="display:none"  >
</form>
 <div class="dailibox">
 	<div class="table1_biaoti" style="margin-top:-10px;"><span></span>代理机构信用记录公示</div>
    <table class="table2">
        <tr>
            <th style="width:158px;">被处理的代理机构名称</th>
            <th style="width:110px;">项目编号</th>
            <th style="width:186px;">项目名称</th>
            <th style="width:80px;">负责人</th>
            <th style="width:400px;">违规情况</th>
            <th style="width:132px;">处理决定</th>
            <th style="width:132px;">时间</th>
        </tr>
        <c:forEach items="${page.list}" var="credit">
        	<tr>
            <td style="word-break:break-all;" width="158">${credit.org.name}</td>
            <td style="word-break:break-all;" width="110">${credit.projectCode}</td>
            <td style="word-break:break-all;" width="186">${credit.projectName}</td>
            <td style="word-break:break-all;" width="80">${credit.leader}</td>
            <td style="word-break:break-all;" width="400">${credit.instruction}</td>
            <td style="word-break:break-all;" width="132">${credit.result}</td>
            <td style="word-break:break-all;" width="132"><fmt:formatDate value="${credit.createTime}" pattern="yyyy-MM-dd"/></td>
        </tr>
          </c:forEach>
         
    </table>
    <div class="clear"></div>
    <div class="pagination">${page}</div>
</div>