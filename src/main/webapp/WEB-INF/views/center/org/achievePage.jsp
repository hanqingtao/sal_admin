<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
		
      <input type="hidden" id="ajaxSubmit" value="staffContainer" />
		<input id="orgId" name="orgId" type="hidden" value="${orgId}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="firstPage" name="firstPage" type="hidden" value="${page.firstPage}"/>
		<input id="lastPage" name="lastPage" type="hidden" value="${page.lastPage}"/>
		<input id="length" name="length" type="hidden" value="${fn:length(page.list)}"/>
	      <c:choose>
    		<c:when test="${!empty page && !empty page.list }">
    			<c:forEach items="${page.list }" var="orgAchieve" varStatus="inde">
    	<form id="form_${inde.index + 1 }" action="${ctxFront}/org/orgAchieve/save"  method="post">
    		<table class="table1">
	        <tr>
	        <input type="hidden" name="id" value="${orgAchieve.id }" />
			<td style="width:60px;"><input style="width:90%; height:100%;" name="num" class="flag" value="${orgAchieve.num}" title="${orgAchieve.num}"  maxlength="16"/></td>
			<td style="width:50px;"><input style="width:90%; height:100%;"  name="name" class="flag" value="${orgAchieve.name}" title="${orgAchieve.name}" maxlength="32"/></td>
			<td style="width:40px;">
			<select style="width:90%; height:100%;"  id="achieveType" class="sel" name="type"">
				<option value="1" <c:if test="${orgAchieve.type==1}" >selected</c:if>>工程</option>
				<option value="2" <c:if test="${orgAchieve.type==2}" >selected</c:if>>货物</option>
				<option value="3" <c:if test="${orgAchieve.type==3}" >selected</c:if>>服务</option>
			     
		     </select>
			 </td>
			<td style="width:40px;">
			<select style="width:90%; height:100%;" class="sel" name="isCenter"">
				<option value="1" <c:if test="${orgAchieve.isCenter==1}" >selected</c:if>>是</option>
				<option value="0" <c:if test="${orgAchieve.isCenter==0}" >selected</c:if>>否</option>
			     
		     </select>
			 </td>
			<td style="width:100px;"><input style="width:90%; height:100%;" class="flag" name="entrustUnit"  value="${orgAchieve.entrustUnit}" title="${orgAchieve.entrustUnit}"  maxlength="32"/></td>
			<td style="width:40px;"><input style="width:90%; height:100%;" class="flag" type="number" name="entrustMoney"  value="${orgAchieve.entrustMoney}" title="${orgAchieve.entrustMoney}"  maxlength="10"/></td>
			<td style="width:40px;"><input  type="number" type="number" class="flag" style="width:90%; height:100%;" name="bidMoney"  value="${orgAchieve.bidMoney}" title="${orgAchieve.bidMoney}"  maxlength="10"/></td>
			<td style="width:50px;"><input style="width:90%; height:100%;"  class="flag" name="tenderOpenTime" value="<fmt:formatDate value='${orgAchieve.tenderOpenTime}' pattern='yyyy-MM-dd '/>" title="<fmt:formatDate value='${orgAchieve.tenderOpenTime}' pattern='yyyy-MM-dd '/>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:false});" /></td>
			<td style="width:50px;"><input  style="width:90%; height:100%;" class="flag" name="bidTime" value="<fmt:formatDate value='${orgAchieve.bidTime}' pattern='yyyy-MM-dd '/>" title="<fmt:formatDate value='${orgAchieve.bidTime}' pattern='yyyy-MM-dd '/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:false});" /></td>
			<td style="width:50px;"><input style="width:90%; height:100%;"  class="flag" maxlength="16" name="noticeMedia"  value="${orgAchieve.noticeMedia}" title="${orgAchieve.noticeMedia}"/></td>
			<td style="width:50px;"><input style="width:90%; height:100%;" class="flag" name="noticeDate"  value="<fmt:formatDate value='${orgAchieve.noticeDate}' pattern='yyyy-MM-dd '/>" title="<fmt:formatDate value='${orgAchieve.noticeDate}' pattern='yyyy-MM-dd '/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:false});" /></td>
			<td style="width:35px;"><input   onclick="orgAchieveSubmit(this,'form_${inde.index + 1 }')" type="submit" style="display:none"  class="save" value="保存" />&nbsp;&nbsp;
			<input class="del"   onclick="delNote(this,${orgAchieve.id})" type="button" value=""/></td>
	        </tr>
		    </table>
	      </form>
    			</c:forEach>
    			
    		</c:when>
    		<c:otherwise>
    		<form id="form_1" action="${ctxFront}/org/orgAchieve/save"  method="post">
			 	<table class="table1">
		        <tr>
				<td style="width:50px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;" name="num"  value="${orgAchieve.num}"  maxlength="16"/></td>
				<td style="width:40px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;"  name="name"  value="${orgAchieve.name}"  maxlength="32"/></td>
				<td style="width:30px;padding: 0 5px;">
				<select style="width:100%; border:solid 1px #dee2ed;"  id="achieveType"  type="text" name="type" value="${orgAchieve.type} ">
					<option value="1" <c:if test="${orgAchieve.type==1}" >selected</c:if>>工程</option>
					<option value="2" <c:if test="${orgAchieve.type==2}" >selected</c:if>>货物</option>
					<option value="3" <c:if test="${orgAchieve.type==3}" >selected</c:if>>服务</option>
			     </select>
				</td>
				<td style="width:30px;padding: 0 5px;">
					<select style="width:100%; border:solid 1px #dee2ed;"  name="isCenter"">
						<option value="1" <c:if test="${orgAchieve.isCenter==1}" >selected</c:if>>是</option>
						<option value="0" <c:if test="${orgAchieve.isCenter==0}" >selected</c:if>>否</option>
				     </select>
			 	</td>
				<td style="width:90px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;"  name="entrustUnit"  value="${orgAchieve.entrustUnit}"  maxlength="32"/></td>
				<td style="width:30px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;" type="number" name="entrustMoney"  value="${orgAchieve.entrustMoney}"  maxlength="10"/></td>
				<td style="width:30px;padding: 0 5px;"><input  type="number" type="number" style="width:100%; border:solid 1px #dee2ed;" name="bidMoney"  value="${orgAchieve.bidMoney}"  maxlength="10"/></td>
				<td style="width:40px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;"  name="tenderOpenTime" value="<fmt:formatDate value='${orgAchieve.tenderOpenTime}' pattern='yyyy-MM-dd'/>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></td>
				<td style="width:40px;padding: 0 5px;"><input  style="width:100%; border:solid 1px #dee2ed;" name="bidTime" value="<fmt:formatDate value='${orgAchieve.bidTime}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></td>
				<td style="width:40px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;"  maxlength="16" name="noticeMedia"  value="${orgAchieve.noticeMedia}"/></td>
				<td style="width:40px;padding: 0 5px;"><input style="width:100%; border:solid 1px #dee2ed;" name="noticeDate"  value="<fmt:formatDate value='${orgAchieve.noticeDate}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></td>
				<td style="width:35px;"><input style="display:none"  onclick="orgAchieveSubmit(this,'form_1')" type="submit"  class="save" value="保存" />&nbsp;&nbsp;<input class="del" onclick="delNote(this,${orgAchieve.id})" type="button" value=" "/></td>
		        </tr>
			    </table>
	      	</form>
			</c:otherwise>
    	</c:choose>
	      
       
       <script>
       		$(function(){
				/* var firstPage = eval($('#firstPage').val());
				var lastPage = eval($('#lastPage').val());
				//alert(firstPage);
				//alert(typeof lastPage);
				if(firstPage && !lastPage){
					$('#nextPage').show();
				}else{
					$('#nextPage').hide();
				}
				if(!firstPage){
					$('#upPage').show();
				}else{
					$('#upPage').hide();
				}
				if(!firstPage && !lastPage){
					$('#nextPage').show();
					$('#upPage').show();
				}
				if(firstPage && lastPage){
					$('#nextPage').hide();
					$('#upPage').hide();
				} */
       			var length = $('#length').val();
				if(parseInt(length) == 0){
					length = 1;
				}
				$('#addRowNum').val(length);
				
       		});
       </script>