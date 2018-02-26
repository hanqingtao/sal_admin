 <%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 

	<div class="jindubox jindubox2">
	 	<p class="on" onclick="javascript: toUploadFile_projectFlow('/pro/project/uploadAnnouncement?projectId=${projectFlow.projectId}');"><span>1</span>招标公告或投标邀请书</p>
		<p <c:if test="${!empty projectFlow.tenderStatus && projectFlow.tenderStatus == 1 }">class="on1" onclick="javascript: toUploadFile_projectFlow('/pro/projectFlow/uploadBiddingDocEntry?id=${projectFlow.id }&projectId=${projectFlow.projectId}');"</c:if> ><span>2</span>上传招标文件</p>
		<p <c:if test="${!empty projectFlow.reportStatus && projectFlow.reportStatus == 1 }">class="on1" onclick="javascript: toUploadFile_projectFlow('/pro/projectFlow/uploadBidReportEntry?id=${projectFlow.id }&projectId=${projectFlow.projectId}');"</c:if> ><span>3</span>上传评标报告</p>
		<p <c:if test="${!empty projectFlow.bidStatus && projectFlow.bidStatus == 1 }">class="on1" onclick="javascript: toUploadFile_projectFlow('/pro/projectFlow/uploadBidResultEntry?id=${projectFlow.id }&projectId=${projectFlow.projectId}');"</c:if> ><span>4</span>上传中标结果</p>
	</div>
        
     <form action="${ctxFront}/pro/projectFlow/noticeSave?id=${projectFlow.id}" id="noticeForm"  method="post"  enctype="multipart/form-data">
      <input type="hidden" value="${projectFlow.id }" name="id"/>
      <input type="hidden" value="${projectFlow.noticeStatus }" id="id_noticeStatus"/>
      <div class="login_kuang congxin">
        
      	<ul>
      		<!-- 
           	<c:if test="${!empty projectFlow.noticeFile }">
            <li>
            	<font style="color: #777; padding-left: 60px; font-weight: bold;">提示：</font>
            	<font style="color: #777">您已上传过招标公告或投标邀请书，如需更新请重新上传！</font>
            	<c:if test="${!empty pstatus && pstatus == 1 }">
	            	<font style="color: #ff8417; padding-left: 15px; font-weight: bold;">您已完成所有资料上传。</font>
	            </c:if>
            </li>
            </c:if>
             -->
            <li><span>上传招标公告或投标邀请书：</span>
            	<input type="file"  class="uploadFile" id="biddingDocuments"  multiple="multiple"  name="file1" />
            	<input type="button" onclick="uploadingBiddingDocuments()" class="button3" value="点击上传" />
         		<i id="infoLog" class="info"></i>
         		<c:if test="${!empty projectFlow.noticeFile }">&nbsp;&nbsp;
         			<a href="${imgServer }${projectFlow.noticeFile }" style="color: blue; font-style: italic; text-decoration: underline;" title="点击可下载查看已上传的招标公告或投标邀请书文件">查看已上传文件</a>
         		</c:if>
            </li>
            <div class="clear"></div>
          </ul>
       
      </div>
      <div class="login_kuang_button">
      	<c:if test="${empty projectFlow.noticeStatus || projectFlow.noticeStatus != 1 }">
      		<input type="button" class="button" onclick="uploadAnnouncementVerification()"  value="下一步" style="width: 165px;"/>
      	</c:if>
      	<c:if test="${!empty projectFlow.noticeStatus && projectFlow.noticeStatus == 1 }">
      		<input type="button" class="button" onclick="uploadAnnouncementVerification()"  value="保存修改" style="width: 150px;"/>&nbsp;
      		<input type="button" class="button" onclick="javascript: toUploadFile_projectFlow('/pro/projectFlow/uploadBiddingDocEntry?id=${projectFlow.id }&projectId=${projectFlow.projectId}');"  value="下一步" style="width: 150px;"/>
      	</c:if>
      </div>
      </form>
 	<script type="text/javascript" src="${ctxStatic}/js/project.js" ></script>