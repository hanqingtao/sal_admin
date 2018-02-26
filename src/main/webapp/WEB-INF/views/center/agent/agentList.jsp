
 <%@ page contentType="text/html;charset=UTF-8"%>
   <%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
  <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js" ></script>
  <script type="text/javascript" src="${ctxStatic}/js/agents.js" ></script>  
    
  <div class="dailibox">
     <input  id="natureContainer"  type="hidden"  value="${nature }"/>
	<div class="jigou_top">
    	<div class="top">
        	<div class="left">按条件选择</div>
            <div class="right">共找到 <b>${page.count}</b> 条相关信息</div>
        </div>                              
        <form id="searchAgentList"    method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	        <div class="cer">
	        	<div class="left"><img src="${ctxStatic}/images/ico4.png" /> 机构所在地：</div>
	            <div class="right" id="area">
	            </div>
	        	<div class="clear"></div>
	        	<input type="hidden"  id="areaContainer"  name="areaParentids"  value="${areaParentids }"/>
	        	
	        </div>
	        <div class="bom">
	        	<select id="nature" name="nature">
	        	
	        	</select>
	            <div class="header_search">
					<input type="text" class="text" name="name" value="${name}" placeholder="请输入代理机构名称">
	            	<input type="submit" id="searchAgentInfn" onclick="searchAgent()"  class="button" value="搜索">
	            </div>
	        </div>
        </form>
    </div>
    
    
    <c:forEach items="${page.list}" var="org">
 
		    <dl class="jigou_dl">
		    	<dt  >
		    	  <a href="javascript:;"  onclick="lookOrgContent('${org.id}')" title="${org.name}">${org.name}</a> 
		    	 
		    	</dt>
		        <dd><span class="ys1">　企业所在地：</span>${org.area.name}</dd>
		        <dd><span class="ys1">　　企业性质：</span>${fns:getDictLabel(org.nature, 'org_nature', '')}</dd>
		        <dd><span class="ys1">　注册资本金：</span><span class="ys2">
		        <fmt:formatNumber value="${org.regMoney}" pattern="#,##0.0#"/><c:if test="${not empty org.regMoney}"> 万元</c:if>
		        
		        
		        </dd>
		        <dd><span class="ys1">近三年总业绩：</span><span class="ys2">
		        
		        <fmt:formatNumber value="${org.recentMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty org.recentMoney}"> 万元</c:if>
		        </span>
		        
		        
		        </dd>
		    </dl>
    
			 
		</c:forEach>
      
    <div class="clear"></div>
        <div class="pagination">${page}</div>
    </div>
   