 
<%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 
 
<div class="dailibox">
	<div class="biaoti">${org.name }</div>
    <div class="daohang">注册时间：<fmt:formatDate value="${org.createDate }" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:showReport();">举报</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div class="gongshi">
    	<div class="left">
            <p class="p1">企业名称&nbsp;&nbsp;</p>
            <p class="p2">${org.name }</p>
            <p class="p1">统一社会信用代码&nbsp;&nbsp;</p>
            <p class="p2">${org.scCode }</p>
            <p class="p1">所属地区&nbsp;&nbsp;</p>
            <p class="p2">${org.area.name }</p>
            <p class="p1">注册资本金 &nbsp;&nbsp;</p>
            <p class="p2"><fmt:formatNumber value="${org.regMoney}" pattern="#,##0.0#"/><c:if test="${not empty org.regMoney}"> 万元</c:if></p>
            <p class="p1">企业注册地址&nbsp;&nbsp;</p>
            <p class="p2">${org.regAddress }</p>
            <p class="p1">企业性质&nbsp;&nbsp;</p>
			<p class="p3">
			  ${fns:getDictLabel(org.nature, 'org_nature', '')}  
			</p>
            <p class="p1">近三年总业绩&nbsp;&nbsp;</p>
			<p class="p3"> <fmt:formatNumber value="${org.recentMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty org.recentMoney}"> 万元</c:if></p>
        </div>
        <div class="right" id="output"></div>
       
        <p class="p4">企业简介&nbsp;&nbsp;</p>
		<p class="p5">${org.description }</p>
    	<p class="p6">资质情况</p>
        <ul>
        <c:forEach items="${orgQualificationList }"   var="orgQualification" >
             
        	<li>
            	   <img src="${imgServer}${orgQualification.path }" width="350"/><br/>
            	   ${orgQualification.name }
            </li>
            
         </c:forEach>
        </ul>
    </div>
    <div class="table1_biaoti"><span></span>专职从业人员名单</div>
  <script>
$(function(){
	jQuery('#output canvas').css({"width":"229px","height":"220px"})
	jQuery('#output').qrcode({width: 229,height: 220,text:domain+"${ctxFront}org/org/h5?id="+${org.id}});
	 

})

</script>
    <table class="table1">
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>在本单位工作起始时间</th>
            <th>毕业院校</th>
            <th>学历/学位</th>
            <th>职称</th>
            <th>所属部门</th>
            <th>劳动关系</th>
        </tr>
       
          <c:forEach items="${orgStaffList }" varStatus="i" begin="0" step="1" var="orgStaff" >
     
		      <tr>
		            <td>${i.index + 1}</td>
		            <td>${orgStaff.name }</td>
		            <td>
		             ${fns:getDictLabel(orgStaff.sex, 'sex', '')}
		            </td>
		            <td><fmt:formatDate value="${orgStaff.workStart}" pattern="yyyy-MM-dd"/></td>
		            <td>${orgStaff.university }</td>
		            <td>
		              ${fns:getDictLabel(orgStaff.degree, 'degree', '')}
		           </td>
		            <td>${orgStaff.proTitle }</td>
		            <td>${orgStaff.dept }</td>
		            <td>  ${fns:getDictLabel(orgStaff.workType, 'work_type', '')} </td>
		        </tr>
			 
		  </c:forEach>
    </table>
  
    <div class="table1_biaoti"><span></span>信用记录</div>
    <table class="table1">
        <tr>
            <th>序号</th>
            <th>记录时间</th>
            <th>项目编号</th>
            <th>项目名称</th>
            <th>直接负责人</th>
            <th>违规情况</th>
            <th>行政处理决定</th>
        </tr>
        <c:forEach items="${creditRecordList }" varStatus="i" begin="0" step="1" var="creditRecord" >
        <tr>
            <td>${i.index + 1}</td>
            <td><fmt:formatDate value="${creditRecord.reportTime }" pattern="yyyy-MM-dd"/></td>
            <td>${creditRecord.projectCode }</td>
            <td>${creditRecord.projectName }</td>
            <td>${creditRecord.leader }</td>
            <td>${creditRecord.instruction }</td>
            <td>${creditRecord.result }</td>
        </tr>
        
        </c:forEach>
    </table>
    <div class="table1_biaoti"><span></span>中央投资项目</div>
    <table class="table1">
        <tr>
            <th>序号</th>
            <th>项目编号</th>
            <th>项目名称</th>
            <th>项目类型</th>
            <th>委托单位</th>
            <th>委托金额</th>
            <th>中标金额</th>
            <th>开标时间</th>
            <th>中标时间</th>
            <th>公告媒体</th>
            <th>公告时间</th>
        </tr>
       
        
         <c:forEach items="${projectList }" varStatus="i" begin="0" step="1"  var="project">
     
     <tr>
            <td>${i.index + 1}</td>
            <td>${project.sn }</td>
            <td>${project.name }</td>
            <td>${fns:getDictLabel(project.tenderType, 'project_type', '')}</td>
            <td>${project.entrustUnit }</td>
            <td> 
            
            <fmt:formatNumber value="${project.entrustMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty project.entrustMoney}"> 万元</c:if>
            </td>
            <td>
             <fmt:formatNumber value="${project.projectFlow.bidMoney}" pattern="#,##0.0#"/>
		        <c:if test="${not empty project.projectFlow.bidMoney}"> 万元</c:if>
            
            </td>
            <td><fmt:formatDate value="${project.opentenderTime }" pattern="yyyy-MM-dd "/></td>
            <td><fmt:formatDate value="${project.bidTime }" pattern="yyyy-MM-dd "/></td>
            <td>${project.noticeMedia }</td>
            <td><fmt:formatDate value="${project.noticeDate }" pattern="yyyy-MM-dd "/></td>
        </tr>
			 
		  </c:forEach>
    </table>
</div>
<div id="reportContainer" class="reportBox"  >
<div class="laertbg"></div>
<div class="laertbox" >
	<div class="top">
    	<div class="left">举报</div>
        <div class="right" onclick="closeReport()"><img src="${ctxStatic}/images/guan.png" /></div>
    </div>
        
         <input type="hidden" id="orgId" name="orgId"  value="${org.id }"/>
		    <ul>
		    	<li class="li1">请您准确填写以下信息，以便及时与您取得联系。感谢您的配合</li>
		    	<li><span>举报机构名称：</span>${org.name }</li>
		        <li><span>联系人：</span><input type="text" id="contact" name="contact" class="text" maxlength="32" required/>  </li>
		        <li><span>联系电话：</span><input type="text" id="phone" name="phone" class="text"  minlength="11" maxlength="11" required/>  </li>
		        <li><span>联系邮箱：</span><input type="text" id="emailInfo" name="email" email class="text" maxlength="32" required /> </li>
		        <li><span>举报内容：</span><textarea name="content" id="content" maxlength="1800" required></textarea></li>
		        <li class="li2"><span>&nbsp;</span>(最多输入1800字)</li>
		         <li><span>验证码：</span><input type="text" class="text1" id="randNum" maxlength="4" minlength="4"/> <img onclick="getYanZhengMa();" id="checkcode"  src="${ctxStatic}/common/image.jsp?sRand=6529" width="107" height="34" class="yzm">
		          <input id="sRandNum" value="6529" type="hidden"> 
		        </li>
		        <li class="li3"><span>&nbsp;</span><input id="igreement" type="checkbox" class="checkbox" required /> 我承诺以上信息舒适并愿意承担相应的法律责任&nbsp;</li>
		    </ul>
      
    <div class="login_kuang_button" style="text-align: center;">
    	<input type="submit" class="button" onclick="reportSave()" value="提交" /><span class="info" id="infoLog">${message }</span>
    </div>
     
</div>
</div>
 