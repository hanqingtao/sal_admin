<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <style type="text/css">
  
</style>
  </head>
    <script type="text/javascript" src="${ctxStatic}/js/achieve.js"></script>
<body>
<%@ include file="/WEB-INF/views/center/common/orgTop.jsp"%>
  
<div class="login_top">
	<p class="on"><span>1</span>帐号注册</p>
	<p class="on"><span>2</span>填写企业基本信息</p>
	<p class="on"><span>3</span>上传企业资质</p>
	<p class="on"><span>4</span>上传专职人员情况</p>
	<p class="on"><span>5</span>上传招标业绩明细</p>
	<p  ><span>6</span>注册成功</p>
</div> 
<div class="login_kuang">
	<div class="top">
    	<div class="left">上传招标业绩明细</div>
		<div class="right m_b">
		   <form     method="post" enctype="multipart/form-data">
		      <input type="file" onchange="ajaxImportOrgAchieve();" name="imporgAchieve" class="uploadFile" id="achieveFile" /><span class="info"></span>
		        <input type="button" class="buttonright" onclick="uploadAchieve()" value="导入" />支持导入，请下载模版   <a href="${ctxStatic}/download/orgAchieveImport.xlsx">模版.xsl</a><br /></div>
          </form>
    </div>
    <div class="clear"></div>
	<table class="table1 m_t">
    	<tr>
        	<th style="width:60px;">项目编号</th>
            <th style="width:50px;">项目名称</th>
            <th style="width:40px;">项目类型</th>
            <th style="width:40px;">是否中央投资项目</th>
            <th style="width:100px;">委托单位</th>
            <th style="width:40px;">委托金额<br>（万元）</th>
            <th style="width:40px;">中标金额<br>（万元）</th>
            <th style="width:50px;">开标时间</th>
            <th style="width:50px;">中标时间</th>
            <th style="width:50px;">公告媒体</th>
            <th style="width:50px;">公告时间</th>
            <th style="width:35px;">操作</th>
        </tr>
      </table>
      <div id="AchieveContainer">
      	<script>
            	$(function(){
            		$.ajax({
            			async:true,
            			url: ctxFront + "/org/orgAchieve/achievePage",
            			type:"get", 
            			success:function(data){
            				$("#AchieveContainer").html(data);
            			}
            		});
            	});
           	</script>
      <%-- <form id="orgAchieveInput"   method="post">
      <table class="table1">
        <tr>
	<td style="width:60px;"><input style="width:90%; height:100%;" name="num"  value="${orgAchieve.num}"  maxlength="16"/></td>
	<td style="width:50px;"><input style="width:90%; height:100%;"  name="name"  value="${orgAchieve.name}"  maxlength="32"/></td>
	<td style="width:40px;"><select style="width:90%; height:100%;"  id="achieveType"  type="text" name="type" value="${orgAchieve.type} ">
	     </select></td>
	<td style="width:100px;"><input style="width:90%; height:100%;"  name="entrustUnit"  value="${orgAchieve.entrustUnit}"  maxlength="32"/></td>
	<td style="width:40px;"><input style="width:90%; height:100%;" type="number" name="entrustMoney"  value="${orgAchieve.entrustMoney}"  maxlength="10"/></td>
	<td style="width:40px;"><input  type="number" type="number" style="width:90%; height:100%;" name="bidMoney"  value="${orgAchieve.bidMoney}"  maxlength="10"/></td>
	<td style="width:50px;"><input style="width:90%; height:100%;"  name="tenderOpenTime" value="<fmt:formatDate value='${orgAchieve.tenderOpenTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></td>
	<td style="width:50px;"><input  style="width:90%; height:100%;"name="bidTime" value="<fmt:formatDate value='${orgAchieve.bidTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></td>
	<td style="width:50px;"><input style="width:90%; height:100%;"  maxlength="16" name="noticeMedia"  value="${orgAchieve.noticeMedia}"/></td>
	<td style="width:50px;"><input style="width:90%; height:100%;" name="noticeDate"  value="<fmt:formatDate value='${orgAchieve.noticeDate}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></td>
	<td style="width:35px;"><input   onclick="orgAchieveSubmit(this)" type="submit"  class="save" value="保存" />&nbsp;&nbsp;<input class="del" onclick="delNote(this,${orgAchieve.id})" type="button" value="删除"/></td>
        </tr>
         
    </table>
         </form> --%>
    </div>
    <div id="pagenations" class="pagination" >
   		<input id="upPage" style="display:none;" type="button" class="zjyh" onclick="upPage()" value="上一页" />
		<input id="nextPage" style="display:none;" type="button" class="zjyh" onclick="nextPage()" value="下一页" />
   	</div>
   	<input type="hidden" id="addRowNum" value="1" />
    <input type="button" class="zjyh" onclick="addAchieve()" value="+增加一行" />
    <div class="xieyi"><span class="info" id="infoLog"></span><input id="acceptanceAgreement" type="checkbox" class="checkbox" />&nbsp;阅读并接受<a href="#">《中央投资项目服务与管理平台用户协议》</a></div> 
    <div class="login_kuang_button">
    	 <input type="button" class="button1" onclick="orgSuccess()" value="下一步" /> 
    </div>
    <div class="clear"></div>
</div>
  <%@ include file="/WEB-INF/views/center/common/footer.jsp"%> 
 
</body>
</html>