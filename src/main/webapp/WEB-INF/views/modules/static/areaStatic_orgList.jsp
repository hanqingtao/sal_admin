<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script type="text/javascript">
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		//$("#searchForm").submit();
       	//return false;
       	form_sumbit();
    }
	function form_sumbit(){
		var form_areaId = $('#form_areaId').val();
		if(form_areaId == null || form_areaId == ''){
			alert('请先点击左侧地图中选择需要查看统计信息的区域！');
		}
		$.ajax({
    		type : "post",
            async:true,
            url: ctx+"/static/getOrgListByArea",
            data: $('#searchForm').serialize(),
            success: function(data) {
            	//alert('-----------' + data);
            	$("#div_list_content").html(data);
            }
    	})
	}
	
</script>
<form:form id="searchForm" name="searchForm" modelAttribute="org" action="${ctx}/static/getOrgListByArea" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<input id="form_areaId" name="area.id" type="hidden" value="${org.area.id}"/>
	<ul class="ul-form" style="margin-bottom: 5px;">
 		<li>
			<label style="margin-left: 0px; width: 65px;">选择年月:</label>
			<select name="yearSearch" id="" style="width: 76px;" onchange="javascript: form_sumbit();">
				<option value="">全部</option>
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
            </select> 年 -
            <select name="monthSearch" id="" style="width: 70px;" onchange="javascript: form_sumbit();">
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
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr style="font-size: 15px;">
				<th style="text-align: center; width: 53%;">企业名称</th>
				<!-- 
				<th style="text-align: center; width: 16%;">地区</th>
				 -->
				<th style="text-align: center; width: 20%;">企业性质</th>
				<th style="text-align: center; ">总交易金额（万元）</th>
			</tr>
		</thead>
		<tbody style="font-size: 14px;">
			<c:forEach items="${page.list}" var="temp">
				<tr>
					<td>${temp.name}</td>
					<!-- 
					<td style="text-align: center;" >北京</td>
					 -->
					<td style="text-align: center;" >${fns:getDictLabel(temp.nature, 'org_nature', '')}</td>
					<td style="text-align: center;" >${temp.recentMoney }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</form:form>
