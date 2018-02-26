<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		
     <%-- <form  action="${ctxFront}/org/orgStaff/save"  method="post" enctype="multipart/form-data"> --%>
      <input type="hidden" id="ajaxSubmit" value="staffContainer" />
		<input id="orgId" name="orgId" type="hidden" value="${orgId}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="firstPage" name="firstPage" type="hidden" value="${page.firstPage}"/>
		<input id="lastPage" name="lastPage" type="hidden" value="${page.lastPage}"/>
		<input id="length" name="length" type="hidden" value="${fn:length(page.list)}"/>
		<%-- <input id="last" name="last" type="hidden" value="${page.last}"/>
		<input id="first" name="first" type="hidden" value="${page.first}"/>
		<input id="funcName" name="funcName" type="hidden" value="${page.funcName}"/>
		 --%>
	      <c:choose>
    		<c:when test="${!empty page && !empty page.list }">
    			<c:forEach items="${page.list }" var="orgStaff" varStatus="inde">
    	<form id="form_${inde.index + 1 }" action="${ctxFront}/org/orgStaff/save"  method="post" enctype="multipart/form-data">
    		<table  class="table1" id="staffTbody">
	        <tr >
				<input name="id" type="hidden" value="${orgStaff.id}"/>
				<td style="width:50px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " class="flag" type="text" name="name" title="${orgStaff.name}" value="${orgStaff.name}"  maxlength="16"></td>
				<td style="width:40px; padding: 0 5px;">
					<select style="width:100%;border:solid 1px #dee2ed; " class="sel"  type="text" name="sex"   value="${orgStaff.sex}"> 
		               <option value="1" <c:if test="${orgStaff.sex==1}" >selected</c:if>>男</option>
		               <option value="2" <c:if test="${orgStaff.sex==2}" >selected</c:if>>女</option>
					</select>
				</td>
				<td style="width:90px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed;" class="flag" type="text"  name="card" title="${orgStaff.card}" value="${orgStaff.card}"  maxlength="18"/></td>
				<td style="width:70px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " class="flag"  name="workStart" type="text" title="<fmt:formatDate value='${orgStaff.workStart}' pattern='yyyy-MM-dd'/>" value="<fmt:formatDate value='${orgStaff.workStart}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" ></td>
				<td style="width:90px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " class="flag" type="text" title="${orgStaff.university}" maxlength="64" name="university" value="${orgStaff.university}"/></td>
				<td style="width:50px; padding: 0 5px;">
					 <select style="width:100%;border:solid 1px #dee2ed; "  name="degree" class="sel" >
						<option value="1" <c:if test="${orgStaff.degree==1}" >selected</c:if>>专科</option>
						<option value="2" <c:if test="${orgStaff.degree==2}" >selected</c:if>>本科</option>
						<option value="3" <c:if test="${orgStaff.degree==3}" >selected</c:if>>研究生</option>
					 </select>
				 </td>
				<td style="width:90px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " class="flag" style="width:95%;" type="text" name="ssn" title="${orgStaff.ssn}" value="${orgStaff.ssn}"  maxlength="32"/></td>
				<td style="width:40px; padding: 0 5px;"><input  style="width:100%;border:solid 1px #dee2ed; " class="flag" type="text" name="proTitle" title="${orgStaff.proTitle}"  value="${orgStaff.proTitle}"  maxlength="32"/></td>
				<td style="width:50px; padding: 0 5px;"><input  style="width:100%;border:solid 1px #dee2ed; " class="flag" type="text" name="dept" title="${orgStaff.dept}" value="${orgStaff.dept}"  maxlength="32"/></td>
				<td style="width:50px; padding: 0 5px;"><select  style="width:100%;border:solid 1px #dee2ed; " class="sel"   name="workType" >
				<option value="1" <c:if test="${orgStaff.workType==1}" >selected</c:if>>在职</option>
	            <option value="2" <c:if test="${orgStaff.workType==2}" >selected</c:if>>退休返聘</option>
				 </select></td>
				<td style="width:50px;">
					<img style="display: none;" id="card_photoShow_${inde.index + 1 }" src="">
					 <input  type="file" onchange="ajaxUploadImage('card_photoFile_${inde.index + 1 }','card_photoShow_${inde.index + 1 }','card_photo_${inde.index + 1 }');" id="card_photoFile_${inde.index + 1 }" class="uploadFile"  name="file"/> 
					 <input type="button" class="upload" onclick="uploadIDPictures(this)" class="" value="上传" /> 
					 <input type="hidden" value="${orgStaff.cardPhoto }" name="cardPhoto" id="card_photo_${inde.index + 1 }" /><!-- 身份证图片 --> 
				</td>
				<td style="width:50px;">
				<img style="display: none;" id="protitle_photoShow_${inde.index + 1 }" src="">
				<input   type="file" onchange="ajaxUploadImage('protitle_photoFile_${inde.index + 1 }','protitle_photoShow_${inde.index + 1 }','protitle_photo_${inde.index + 1 }');" id="protitle_photoFile_${inde.index + 1 }"  name="file"  class="uploadFile"/> 
				<input type="button" class="upload"  onclick="uploadTitlePictures(this)" class="" value="上传" />
				<input type="hidden" value="${orgStaff.protitlePhoto }" name="protitlePhoto" id="protitle_photo_${inde.index + 1 }" /><!-- 职称证图片 -->
				 </td>
				<td style="width:40px;">
				<input type="submit" onclick="submitForm(this,'form_${inde.index + 1 }');" class="save" value="保存" style="display:none;" />&nbsp;&nbsp;<input class="del" type="button" onclick="del(this,${orgStaff.id})" value=""/>
				</td>
	        </tr>
	       
	      </table>
	      </form>
    			</c:forEach>
    			
    		</c:when>
    		<c:otherwise>
    		<form id="form_1" action="${ctxFront}org/orgStaff/save"  method="post" enctype="multipart/form-data">
    		
			 	<table  class="table1" id="staffTbody">
		        <tr >
					<td style="width:50px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " type="text" name="name" value="${orgStaff.name}"  maxlength="16"></td>
					<td style="width:40px; padding: 0 5px;">
						<select style="width:100%;border:solid 1px #dee2ed; " type="text" name="sex" value="${orgStaff.sex}"> 
			               <option value="1" <c:if test="${orgStaff.sex==1}" >selected</c:if>>男</option>
			               <option value="2" <c:if test="${orgStaff.sex==2}" >selected</c:if>>女</option>
						</select>
					</td>
					<td style="width:90px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed;" type="text"  name="card" value="${orgStaff.card}"  maxlength="18"/></td>
					<td style="width:70px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; " name="workStart" type="text" value="<fmt:formatDate value='${orgStaff.workStart}' pattern='yyyy-MM-dd '/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:false});" ></td>
					<td style="width:90px; padding: 0 5px;"><input style="width:100%;border:solid 1px #dee2ed; "  type="text"  maxlength="64" name="university" value="${orgStaff.university}"/></td>
					<td style="width:50px; padding: 0 5px;">
						 <select style="width:100%;border:solid 1px #dee2ed; "  name="degree" >
							<option value="1" <c:if test="${orgStaff.degree==1}" >selected</c:if>>专科</option>
							<option value="2" <c:if test="${orgStaff.degree==2}" >selected</c:if>>本科</option>
							<option value="3" <c:if test="${orgStaff.degree==3}" >selected</c:if>>研究生</option>
						 </select>
					 </td>
					<td style="width:90px; padding: 0 5px;"><input   style="width:100%;border:solid 1px #dee2ed; " type="text" name="ssn" value="${orgStaff.ssn}"  maxlength="32"/></td>
					<td style="width:40px; padding: 0 5px;"><input  style="width:100%;border:solid 1px #dee2ed; " type="text" name="proTitle" value="${orgStaff.proTitle}"  maxlength="32"/></td>
					<td style="width:50px; padding: 0 5px;"><input  style="width:100%;border:solid 1px #dee2ed; " type="text" name="dept" value="${orgStaff.dept}"  maxlength="32"/></td>
					<td style="width:50px; padding: 0 5px;"><select  style="width:100%;border:solid 1px #dee2ed; " name="workType" >
					<option value="1" <c:if test="${orgStaff.workType==1}" >selected</c:if>>在职</option>
		            <option value="2" <c:if test="${orgStaff.workType==2}" >selected</c:if>>退休返聘</option>
					 </select></td>
					<td style="width:50px;">
					<img style="display: none;" id="card_photoShow_1" src="">
					 <input  type="file" onchange="ajaxUploadImage('card_photoFile_1','card_photoShow_1','card_photo_1');" id="card_photoFile_1" class="uploadFile"  name="file"/> 
					 <input type="button" class="upload" onclick="uploadIDPictures(this)" value="上传" /> 
					 <input type="hidden" value="${orgStaff.cardPhoto }" name="cardPhoto" id="card_photo_1" /><!-- 身份证图片 --> 
					</td>
					<td style="width:50px;">
					<img style="display: none;" id="protitle_photoShow_1" src="">
					<input   type="file" onchange="ajaxUploadImage('protitle_photoFile_1','protitle_photoShow_1','protitle_photo_1');" id="protitle_photoFile_1"  name="file"  class="uploadFile"/> 
					<input type="button" class="upload"  onclick="uploadTitlePictures(this)" value="上传" />
					<input type="hidden" value="${orgStaff.protitlePhoto }" name="protitlePhoto" id="protitle_photo_1" /><!-- 职称证图片 -->
					 </td>
					
					<td style="width:40px;">
					<input type="submit" onclick="submitForm(this,'form_1');" class="save" value="保存" style="display:none;" />
					<input class="etc del" type="button" onclick="del(this,${orgStaff.id})" value=" "/>
					</td>
		        </tr>
	      	</table>
	      	</form>
			</c:otherwise>
    	</c:choose>
	      
      <!--  </form>   -->
       
       <script>
       		$(function(){
				var firstPage = eval($('#firstPage').val());
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
				} 
				var length = $('#length').val();
				if(parseInt(length) == 0){
					length = 1;
				}
				$('#addRowNum').val(length);
				
       		});
       </script>