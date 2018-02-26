<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>业绩排行</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/static/orderStatic">业绩排行</a></li>
	</ul>
	
	<!-- 
	 <div class="tab">
        <div class="tabTitle" id="tabTotal" >
        	  <p  class="on">年份</p>
        	<p  >地区</p>
            <p  >企业性质</p>
        </div>
        <div id="tabContentContainer" class="tabContainer">
	          <dl > 
	                 <div>
		                  &nbsp;&nbsp;<label>选择年份：</label>
		                <select name="" id="">
		                      <option>2017</option>
		                 </select>       
		                 &nbsp;&nbsp;<label>选择月份：</label>  
		                 <select name="" id="">
		                      <option>01月</option>
		                 </select>   
	                 </div>    
	         </dl> 
	          <dl style="display:none">
	                 <div class="area_nav" id="areaTitleContainer">
	                 </div>
	         </dl>  
	          
	          <dl style="display:none">
			      <div class="nature_nav" id="natureTitleContainer"></div>
	         </dl> 
         </div>
    </div>
     -->
<form:form id="searchForm" modelAttribute="org" action="${ctx}/static/orderStatic" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	 	<ul class="ul-form">
	 		<li>
				<label style="margin-left: 0px; width: 65px;">选择年月:</label>
				<select name="yearSearch" id="" style="width: 76px;" onchange="javascript: $('#btnSubmit').click()">
					<option value="">全部</option><!--   begin="expression" end="expression" step="expression"  -->
					<c:forEach var="year" items="${yearList}" varStatus="status" 
                            >
                         <option value="${year}"  
                         	<c:if test="${org.yearSearch == year}">selected="selected"</c:if>
                         	 >${year}</option>
                    </c:forEach>
                    <!-- 
                	<option value="2020" <c:if test="${org.yearSearch == 2020}">selected="selected"</c:if> >2020</option>
                	<option value="2019" <c:if test="${org.yearSearch == 2019}">selected="selected"</c:if> >2019</option>
                	<option value="2018" <c:if test="${org.yearSearch == 2018}">selected="selected"</c:if> >2018</option>
                	<option value="2017" <c:if test="${org.yearSearch == 2017}">selected="selected"</c:if> >2017</option>
                	<option value="2016" <c:if test="${org.yearSearch == 2016}">selected="selected"</c:if> >2016</option>
                	<option value="2015" <c:if test="${org.yearSearch == 2015}">selected="selected"</c:if> >2015</option>
                	<option value="2014" <c:if test="${org.yearSearch == 2014}">selected="selected"</c:if> >2014</option>
                	<option value="2013" <c:if test="${org.yearSearch == 2013}">selected="selected"</c:if> >2013</option>
                	<option value="2012" <c:if test="${org.yearSearch == 2012}">selected="selected"</c:if> >2012</option>
                	<option value="2011" <c:if test="${org.yearSearch == 2011}">selected="selected"</c:if> >2011</option>
                	<option value="2010" <c:if test="${org.yearSearch == 2010}">selected="selected"</c:if> >2010</option>
                	 -->
                </select> 年 -
                <select name="monthSearch" id="" style="width: 70px;" onchange="javascript: $('#btnSubmit').click()">
                	<option value="">全部</option>
                	<option value="1" <c:if test="${org.monthSearch == 1}">selected="selected"</c:if> >01</option>
                	<option value="2" <c:if test="${org.monthSearch == 2}">selected="selected"</c:if> >02</option>
                	<option value="3" <c:if test="${org.monthSearch == 3}">selected="selected"</c:if> >03</option>
                	<option value="4" <c:if test="${org.monthSearch == 4}">selected="selected"</c:if> >04</option>
                	<option value="5" <c:if test="${org.monthSearch == 5}">selected="selected"</c:if> >05</option>
                	<option value="6" <c:if test="${org.monthSearch == 6}">selected="selected"</c:if> >06</option>
                	<option value="7" <c:if test="${org.monthSearch == 7}">selected="selected"</c:if> >07</option>
                	<option value="8" <c:if test="${org.monthSearch == 8}">selected="selected"</c:if> >08</option>
                	<option value="9" <c:if test="${org.monthSearch == 9}">selected="selected"</c:if> >09</option>
                	<option value="10" <c:if test="${org.monthSearch == 10}">selected="selected"</c:if> >10</option>
                	<option value="11" <c:if test="${org.monthSearch == 11}">selected="selected"</c:if> >11</option>
                	<option value="12" <c:if test="${org.monthSearch == 12}">selected="selected"</c:if> >12</option>
                </select> 月 
			</li>
			<li>
				<label style="margin-left: 0px; width: 73px;">选择地区:</label>
				<select name="area.id" style="width: 147px;" onchange="javascript: $('#btnSubmit').click();">
					<option value="">全国</option>
					<c:forEach items="${areaList }" var="temp" begin="1">
                		<option value="${temp.id }" <c:if test="${temp.id == org.area.id}">selected="selected"</c:if> >${temp.name }</option>
                	</c:forEach>
                </select>
			</li>
			<li><label style="margin-left: 0px; width: 73px;">企业性质:</label>
				<form:select path="nature" class="input-medium" onchange="javascript: $('#btnSubmit').click();">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('org_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="margin-left: 0px; width: 73px;">企业名称:</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<font style="font-weight: bold; font-size: 14px; color: #777"><b style="color: #999; font-size: 16px;">></b> <c:if test="${!empty org.yearSearch }"><b style="color: #ff8417;">${org.yearSearch }</b>年 </c:if>
	<c:if test="${!empty org.monthSearch }"><b style="color: #ff8417;">${org.monthSearch }</b>月份</c:if> 企业业绩排行信息：</font>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center; width: 34%;" >企业名称</th>
				<th style="text-align: center; width: 22%;" >地区</th>
				<th style="text-align: center; width: 22%;" >企业性质</th>
				<th style="text-align: center; width: 22%;" >累计交易金额（万元）</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="org">
			<tr>
				<td>${org.name}</td>
				<td style="text-align: center;" >${org.area.name }</td>
				<td style="text-align: center;" >${fns:getDictLabel(org.nature, 'org_nature', '')}</td>
				<td style="text-align: center;" >${org.recentMoney }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>