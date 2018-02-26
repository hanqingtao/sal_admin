 <%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
   <script type="text/javascript" src="${ctxStatic}/js/pro.js" ></script>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <div class="dailibox">
	<div class="biaoti">${project.name}</div> 
    <div class="daohang">注册时间：<fmt:formatDate value='${project.opentenderTime}' pattern='yyyy-MM-dd '/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:showReport();">举报</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div class="table1_biaoti"><span></span>项目基本信息</div>
    <div class="gongshi" style="height:468px; margin-top:0px;">
    	<p class="p1">项目名称&nbsp;&nbsp;</p>
        <p class="p7">${project.name}</p>
        <p class="p1">项目业主&nbsp;&nbsp;</p>
        <p class="p7">${engineer.owner}</p>
        <p class="p9"><span class="por_top">相关批文及文号&nbsp;&nbsp;</span> </p>
        <p class="p10">可研报告批准文件及文号</p>
		<p class="p11">${engineer.reportNumber}</p>
        <p class="p10">核准文件及文号</p>
        <p class="p11">${engineer.approvalNumber}</p>
        <p class="p10">备案文件及文号</p>
        <p class="p11">${engineer.recordNumber}</p>
        <p class="p1">项目所在省份（区、市）&nbsp;&nbsp;</p>
        <p class="p8">${engineer.area.name}</p>
        <p class="p1">所属行业&nbsp;&nbsp;</p>
        <p class="p8">${fns:getDictLabel(engineer.industry, 'industry', '')}   </p>
        <p class="p1">具体建设地点&nbsp;&nbsp;</p>
        <p class="p8">${engineer.builtSite}</p>
        <p class="p1">建设规模&nbsp;&nbsp;</p>
        <p class="p8">${engineer.builtScale}</p>
        <p class="p1">项目总投资&nbsp;&nbsp;</p>
        <p class="p8">
         <fmt:formatNumber value="${engineer.totalMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty engineer.totalMoney}"> 万元</c:if>
        
                  </p>
        <p class="p1">中央投资额&nbsp;&nbsp;</p>
        <p class="p8">
           
            <fmt:formatNumber value="${engineer.centralMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty engineer.centralMoney}"> 万元</c:if>
        
        </p>
    	<p class="p1">中央投资资金具体来源与数额&nbsp;&nbsp;</p>
        <p class="p7">${fns:getDictLabel(engineer.centralinvestType, 'centralinvest_type', '')}&nbsp;&nbsp;
         <fmt:formatNumber value="${engineer.centralinvestMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty engineer.centralinvestMoney}"> 万元</c:if>
        
         </p>
        <p class="p1">中央投资资金使用方式&nbsp;&nbsp;</p>
        <p class="p7">${fns:getDictLabel(engineer.centraluseType, 'centraluse_type', '')}</p>
    </div>
    <div class="table1_biaoti"><span></span>招标情况</div>
    <div class="gongshi" style="height:516px; margin-top:0px;">
    	<p class="p1">招标项目名称&nbsp;&nbsp;</p>
        <p class="p8">${project.name}</p>
        <p class="p1">招标编号&nbsp;&nbsp;</p>
        <p class="p8">${project.sn}</p>
        <p class="p1">招标人&nbsp;&nbsp;</p>
        <p class="p8">${project.tenderee}</p>
        <p class="p1">联系人及电话&nbsp;&nbsp;</p>
        <p class="p8">${project.tenderee}</p>
		<p class="p9">招标代理机构&nbsp;&nbsp;<br />招标项目负责人&nbsp;&nbsp;<br />招标类型/内容&nbsp;&nbsp;</p>
		<p class="p7">${project.biddingAgency}</p>
		<p class="p8">${project.leader}</p>
        <p class="p1">联系电话&nbsp;&nbsp;</p>
        <p class="p8">${project.contact}</p>  
          
		<p class="p7">${fns:getDictLabel(project.tenderType, 'project_type', '')}</p>
        <p class="p1">委托时间&nbsp;&nbsp;</p>
        <p class="p8"> <fmt:formatDate value='${project.entrustTime}' pattern='yyyy-MM-dd'/></p>
        <p class="p1">委托金额&nbsp;&nbsp;</p>
        <p class="p8">
        
         <fmt:formatNumber value="${project.entrustMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty project.entrustMoney}"> 万元</c:if>
		        
         </p>
        <p class="p1">公告发布时间&nbsp;&nbsp;</p>
        <p class="p7"><fmt:formatDate value='${project.noticeDate}' pattern='yyyy-MM-dd'/></p>
        <p class="p1">公告媒体&nbsp;&nbsp;</p>
        <p class="p8">${project.noticeMedia}</p>
        <p class="p1">对投标人资质、资信要求&nbsp;&nbsp;</p>
        <p class="p8">${project.creditRequire}</p>
        <p class="p1">招标人&nbsp;&nbsp;</p>
        <p class="p8">${project.tenderee}</p>
        <p class="p1">开标时间&nbsp;&nbsp;</p>
        <p class="p8"><fmt:formatDate value='${project.opentenderTime}' pattern='yyyy-MM-dd'/></p>
        <p class="p1">招标方式&nbsp;&nbsp;</p>
        <p class="p7">${fns:getDictLabel(project.tenderType, 'tender_type', '')}</p>
        <p class="p1">招标内容&nbsp;&nbsp;</p>
        <p class="p7">${project.content}</p>
    </div>
    <div class="table1_biaoti"><span></span>中标情况</div>
    <div class="gongshi" style="height:140px; margin-top:0px;">
    	<p class="p1">中标单位名称&nbsp;&nbsp;</p>
        <p class="p8">${projectFlow.bidUnit}</p>
        <p class="p1">联系人及电话&nbsp;&nbsp;</p>
        <p class="p8">${projectFlow.contact}&nbsp;&nbsp;${projectFlow.phone}</p>
        <p class="p1">中标金额&nbsp;&nbsp;</p>
        <p class="p8">
        
        
         
        
         <fmt:formatNumber value="${projectFlow.bidMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty projectFlow.bidMoney}"> 万元</c:if>
        
        </p>
        <p class="p1">节资率&nbsp;&nbsp;</p>
        <p class="p8">${projectFlow.capitalsavingRatio}</p>
        <p class="p1">中标通知书发出时间&nbsp;&nbsp;</p>
        <p class="p7"><fmt:formatDate value='${projectFlow.bidnoticeTime}' pattern='yyyy-MM-dd'/></p>
    </div>
    <div class="table1_biaoti"><span></span>附件</div>
    <div class="gongshi" style="height:93px; margin-top:0px;">
       <c:if test="${not empty projectFlow.noticeFile}">
        <p class="p12"><a target="_blank" href="${imgServer}${projectFlow.noticeFile }">招标公告或投标邀请书</a></p>
        </c:if>
        
        <c:if test="${ empty projectFlow.noticeFile}">
        <p class="p12"><a href="javascript:alert('未上传招标公告或投标邀请书');">招标公告或投标邀请书</a></p>
        </c:if>
        
        <c:if test="${not empty projectFlow.fileBid}">
        <p class="p12"><a target="_blank" href="${imgServer}${projectFlow.fileBid }">中标通知书</a></p>
        </c:if>
        
         <c:if test="${ empty projectFlow.fileBid}">
        <p class="p12"><a href="javascript:alert('未上传中标通知书');">中标通知书</a></p>
        </c:if>
    </div>
</div>
<div id="reportContainer" class="reportBox"  >
<div class="laertbg"></div>
<div class="laertbox" >
	<div class="top">
    	<div class="left">举报</div>
        <div class="right" onclick="closeReport()"><img src="${ctxStatic}/images/guan.png" /></div>
    </div>
        
         <input type="hidden"  id="orgId" name="orgId"  value="${org.id }"/>
         <input type="hidden"  id="projectId" name="projectId"  value="${project.id }"/>
		    <ul>
		    	<li class="li1">请您准确填写以下信息，以便及时与您取得联系。感谢您的配合</li>
		    	<li><span>举报项目名称：</span>${project.name }</li>
		        <li><span>联系人：</span><input type="text" id="contact" name="contact" class="text" maxlength="32" required/>  </li>
		        <li><span>联系电话：</span><input type="text" id="phone" name="phone" class="text"  minlength="11" maxlength="11" required/>  </li>
		        <li><span>联系邮箱：</span><input type="text" id="emailInfo" name="email" email class="text" maxlength="32" required/> </li>
		        <li><span>举报内容：</span><textarea name="content" id="content" maxlength="1800" required></textarea></li>
		        <li class="li2"><span>&nbsp;</span>(最多输入1800字)</li>
		        <li><span>验证码：</span><input type="text" class="text1" id="randNum" maxlength="4" minlength="4"/> <img onclick="getYanZhengMa();" id="checkcode"  src="${ctxStatic}/common/image.jsp?sRand=6529" width="107" height="34" class="yzm">
		          <input id="sRandNum" value="6529" type="hidden"> 
		        </li>
		        <li class="li3"><span>&nbsp;</span><input id="igreement" type="checkbox" class="checkbox" required /> 我承诺以上信息舒适并愿意承担相应的法律责任&nbsp;</li>
		    </ul>
      
    <div class="login_kuang_button"  style="text-align: center;">
    	<input type="submit" class="button" onclick="reportSave()" value="提交" /><span class="info" id="infoLog">${message }</span>
    </div>
     
</div>
</div>