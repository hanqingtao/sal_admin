<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/center/common/meta.jsp"%>

    <div class="jindubox jindubox2">
    	<p class="on1" onclick="javascript: toUploadFile_projectFlow('/pro/project/uploadAnnouncement?projectId=${projectFlow.projectId}');"><span>1</span>招标公告或投标邀请书</p>
        <p class="on1" onclick="javascript: toUploadFile_projectFlow('/pro/projectFlow/uploadBiddingDocEntry?id=${projectFlow.id }&projectId=${projectFlow.projectId}');"><span>2</span>上传招标文件</p>
        <p class="on1" onclick="javascript: toUploadFile_projectFlow('/pro/projectFlow/uploadBidReportEntry?id=${projectFlow.id }&projectId=${projectFlow.projectId}');"><span>3</span>上传评标报告</p>
        <p class="on" onclick="javascript: toUploadFile_projectFlow('/pro/projectFlow/uploadBidResultEntry?id=${projectFlow.id }&projectId=${projectFlow.projectId}');"><span>4</span>上传中标结果</p>
    </div>
        
    <form action="${ctxFront}/pro/projectFlow/bidSave?id=${projectFlow.id}" id="resultForm" modelAttribute="projectFlow"  method="post" enctype="multipart/form-data">
 		<input type="hidden" value="${projectFlow.id }" name="id"/>
 		<input type="hidden" value="${projectFlow.bidStatus }" id="id_bidStatus"/>
 		<div class="login_kuang congxin">
       		<ul>
       			<!-- 
       			<c:if test="${!empty projectFlow.fileBid }">
	            <li>
	            	<font style="color: #777; padding-left: 60px; font-weight: bold;">提示：</font>
	            	<font style="color: #777">您已上传过中标通知书，如需更新请重新上传！</font>
	            	<c:if test="${!empty pstatus && pstatus == 1 }">
	            	<font style="color: #ff8417; padding-left: 15px; font-weight: bold;">您已完成所有资料上传。</font>
	            	</c:if>
	            </li>
	            </c:if>
	             -->
            	<li><span>联系人：</span><input type="text" name="contact" class="text" value="${projectFlow.contact }" required maxlength="32"/></li>
                <li><span>电话：</span><input type="text" name="phone"  class="text" value="${projectFlow.phone }"  required maxlength="32"/></li>
                <li><span>中标金额（万元）：</span><input type="number" name="bidMoney" class="text" value="${projectFlow.bidMoney }" required maxlength="10"/></li>
                <li><span>节资率（%）：</span><input type="number" name="capitalsavingRatio" class="text" value="${projectFlow.capitalsavingRatio }" required maxlength="10"/></li>
                <li><span>中标通知书发出时间：</span><input name="bidnoticeTime"  type="text" class="text" required value="<fmt:formatDate value='${projectFlow.bidnoticeTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
                </li></li>
                <li>
                	<span>上传中标通知书：</span> <input type="file" name="file1" id="files" class="uploadFile"  /><input onclick="uploadFile()" type="button" class="button3" value="点击上传" /><i class="info" id="infoLog"></i>
                	<c:if test="${!empty projectFlow.fileBid }">&nbsp;&nbsp;
         				<a href="${imgServer }${projectFlow.fileBid }" style="color: blue; font-style: italic; text-decoration: underline;" title="点击可下载查看已上传的中标通知书文件" >查看已上传文件</a>
         			</c:if>	
                </li>
               	<div class="clear"></div>
         	</ul>
    	</div>
          
        <div class="login_kuang_button">
            <c:if test="${empty projectFlow.bidStatus || projectFlow.bidStatus != 1 }">
      			<input type="button" class="button" onclick="uploadBidVerification()"  value="保  存" style="width: 150px;"/>
      		</c:if>
      		<c:if test="${!empty projectFlow.bidStatus && projectFlow.bidStatus == 1 }">
      			<input type="button" class="button" onclick="uploadBidVerification()"  value="保存修改" style="width: 150px;"/>
      		</c:if>
        </div>
          
  	</form>
  	
   	<script type="text/javascript" src="${ctxStatic}/js/project.js" ></script>
