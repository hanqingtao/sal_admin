 <%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <script type="text/javascript" src="${ctxStatic}/js/jquery.placeholder.min.js" ></script>
  <script type="text/javascript" src="${ctxStatic}/js/pro.js" ></script>
  
 <div class="dailibox">
	<div class="jigou_top">
    	<div class="top">
        	<div class="left">按条件选择</div>
            <div class="right">共找到 <b>${page.count}</b> 条相关信息</div>
        </div>
        
        <div class="cer">
        	<div class="left1"><img src="${ctxStatic}/images/ico5.png" /> 地区：</div>
            <div class="right1" id="area">
            </div>
        	<div class="clear"></div>
        </div>
       <input type="hidden" id="protoTypeContainer" value="${project.tenderType }"/>
      <input type="hidden" id="keyWordFlag" value="${keyWord }"/>
        <form id="searchProFrom"  method="get" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
             <input  type="hidden"  id="areaFlag" id="areaContainer" name="areaId" value="${project.areaId }" />
	        <div class="bom">
	            <input type="text" id="startTime"   value="<fmt:formatDate value="${project.startBidTime }" pattern="yyyy-MM-dd"/>" name="startBidTime" class="text1" placeholder="开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span>--
	            </span><input type="text" id="endTime" value="<fmt:formatDate value="${project.endBidTime }" pattern="yyyy-MM-dd"/>"  name="endBidTime" class="text1" placeholder="结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
	            <select id="proType" name="tenderType">
		            <option value="">项目类型</option>
	            </select>
	            
	        	<select name="keyWord" id="keyWordContainer">
	        	     <option value="">关键字</option>
	        	     <option value="1">项目名称</option>
	        	     <option value="2">核准文件及文号</option>
	        	     <option value="3">代理机构</option>
	        	</select> 
	            <div class="header_search">
					<input type="text" name="keyValue" class="text" value="${keyValue }" placeholder="请输入代理机构名称">
	            	<input type="submit" onclick="searchProInfo()"  id="searchPro" class="button" value="搜索"><span id="infoLog" class="info"></span>
	            </div>
	        </div>
           </form>
         
    </div>
    <table class="jigou_table">
    	<tr>
        	<th style="border-left:1px solid #dee2ed;">核准文件及文号</th>
            <th>项目名称</th>
            <th>是否中央投资项目</th>
            <th>项目类型</th>
            <th>代理机构</th>
            <th>地区</th>
            <th>中标单位</th>
            <th>中标金额（万元）</th>
            <th style="border-right:1px solid #dee2ed;">中标时间</th>
            
        </tr>
        <c:forEach items="${page.list}" var="project">
			  <tr>
        	<td>${project.sn}</td>
            <td><a href="javascript:;" onclick="lookProjectDetails(${project.id})">${project.name}</a></td>
            <td> ${fns:getDictLabel(project.isCenter, 'yes_no', '')}</td>
            <td>  ${fns:getDictLabel(project.tenderType, 'tender_type', '')}</td>
            <td>${project.biddingAgency}</td>
            <td>${project.areaName}</td>
            <td>${project.entrustUnit}</td>
            <td>
            
            <fmt:formatNumber value="${project.entrustMoney}" pattern="#,##0.0#"/>
            
            </td>
            <td><fmt:formatDate value="${project.bidTime}" pattern="yyyy-MM-dd "/></td>
            
        </tr>
		</c:forEach>
    </table>
    <div class="clear"></div>
     <div class="pagination">${page}</div>
</div>
<script>
$(function(){
	$('input, textarea').placeholder();
})

</script>