<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="main_right">
        <input type="hidden" id="industryFlag" value="${engineer.industry }"/>
        <input type="hidden" id="centralinvestTypeFlag" value="${engineer.centralinvestType }"/>
        <input type="hidden" id="centraluseTypeFlag"  value="${engineer.centraluseType }"/>
        <input type="hidden" id="areaIdFlag" value="${project.areaId }"/>
    	<div class="tops"><div class="left">创建项目信息</div>
    	<div class="right" style="margin-top:30px"><span>*</span>为必填项</div>
    	</div>
        <div class="login_kuang congxin">
        <form id="engForm"   method="post">
        	<input type="hidden" name="engineer.id" value="${engineer.id }" />
            <ul>
                <li><span><em>*</em>项目名称：</span><input type="text" name="engineer.name" class="text" value="${engineer.name }" required maxlength="32"/></li>
                <li><span><em>*</em>项目业主：</span><input type="text" name="engineer.owner" class="text" value="${engineer.owner }"  required  maxlength="32"/></li>
                <li>
                	<span><em>*</em>相关批文及文号：</span>
                    <div class="divp">
                        <p> 可研报告批准文件及文号：<input   maxlength="32" type="text" name="engineer.reportNumber" class="text2" required value="${engineer.reportNumber }"/></p>
                        <p>　　　　 核准文件及文号：<input   maxlength="32" type="text" name="engineer.approvalNumber" class="text2" required value="${engineer.approvalNumber}"/></p>
                        <p>　　　　 备案文件及文号：<input   maxlength="32" type="text" name="engineer.recordNumber" class="text2"  required value="${engineer.recordNumber}"/></p>
                    </div>
                </li>
                <li><span><em>*</em>所属行业：</span>
                <select class="select2" name="engineer.industry" id="IndustryContainer" required>
                <!-- 
                 <option value="1" <c:if test="${engineer.industry==1}" >selected</c:if>>农、林、牧、渔业</option>
                  <option value="2" <c:if test="${engineer.industry==2}" >selected</c:if>>建筑业</option> -->
                </select></li>
                <li><span><em>*</em>具体建设地点：</span><input  maxlength="32" type="text" class="text" name="engineer.builtSite" required value="${engineer.builtSite }"/></li>
                <li><span><em>*</em>建设规模 ：</span><input  maxlength="32" type="text" class="text" name="engineer.builtScale" required value="${engineer.builtScale }"/></li>
                <li><span><em>*</em>项目总投资（万元）：</span><input type="number"   maxlength="10" type="text" class="text"  required name="engineer.totalMoney" value="${engineer.totalMoney }"/></li>
                <li><span style="width:280px;"><em>*</em>中央投资额（万元）：</span><input type="number"  maxlength="10" type="text" required class="text" name="engineer.centralMoney"value="${engineer.centralMoney }" /></li>
                <li>
                	<span  style="width:260px;text-align:left"><em>*</em>中央投资资金具体来源与数额（万元）：</span>
                    <select id="centralinvestTypeContainer" class="select1" name="engineer.centralinvestType" required value="${engineer.centralinvestType }">
                     
                     
                    </select>
                    <input type="number" name="engineer.centralinvestMoney" required maxlength="10" value="${engineer.centralinvestMoney }" class="text3" /> 
                </li>
                <li><span><em>*</em>中央投资资金使用方式：</span><select id="centraluseTypeContainer" class="select2" required name="engineer.centraluseType" value="${engineer.centraluseType }"></select></li>
                <div class="clear"></div>
            </ul>
            <!-- 
             <div class="login_kuang_button">
                 <input type="submit" class="button1" value="保  存" onclick="createEngineer()"/><span id="projectMst">${message}</span>
            </div>
            </form>
             -->
            <div class="xian"></div>
            <!--  
           <form id="projectForm"  method="post">
              <input type="hidden" name="engineerId" value="${engineer.id }">
               -->
               <input type="hidden" name="id" value="${project.id}">
            <ul>
                <li><span><em>*</em>招标项目名称：</span><input type="text" name="name" required class="text" value="${project.name }"  maxlength="32"/></li>
                <li><span><em>*</em>招标编号：</span><input type="text" name="sn"  required class="text" value="${project.sn }"  maxlength="16"/></li>
                <li class="li_isCenter"><span><em>*</em>是否中央投资项目：</span>
	                &nbsp;&nbsp;<input name="isCenter" type="checkbox" class="checkbox" <c:if test="${project.isCenter == 1}" >checked="checked" </c:if> value="1"/> 是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                <input name="isCenter" type="checkbox" class="checkbox" <c:if test="${empty project.isCenter || project.isCenter != 1}" >checked="checked" </c:if> value="0"/> 否
	            </li>
                <li><span><em>*</em>招标人：</span><input type="text" name="tenderee" required class="text" value="${project.tenderee }"  maxlength="32"/></li>
                <li><span><em>*</em>联系人及电话：</span><input type="text" name="contact" required class="text" value="${project.contact }"  maxlength="32"/></li>
                <li><span><em>*</em>招标代理机构：</span><input type="text" name="biddingAgency" required class="text" value="${project.biddingAgency }"  maxlength="32"/></li>
                <li><span><em>*</em>招标项目负责人：</span><input type="text" name="leader" class="text" required value="${project.leader }"  maxlength="32"/></li>
                 <li>
                	<span>项目所在省份（区、市）：</span>
                    <select class="select1" name="areaId" id="areaContainer" required>
                      
                      <!-- <option value="2" <c:if test="${project.areaId==2}" >selected</c:if>>北京市</option>
                        <option value="3" <c:if test="${project.areaId==3}" >selected</c:if>>天津市</option>
                      <option value="4" <c:if test="${project.areaId==4}" >selected</c:if>>河北省</option> -->
                       
                    </select> 
                    
                </li>
                <li class="li_tenderType"><span><em>*</em>招标类型/内容：</span>
                &nbsp;&nbsp;<input name="tenderType"  type="checkbox" class="checkbox"  <c:if test="${project.tenderType==2}" >checked="checked" </c:if> value="2" /> 货物&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="tenderType" type="checkbox" class="checkbox" <c:if test="${project.tenderType==1}" >checked="checked" </c:if> value="1"/> 工程&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="tenderType" type="checkbox" class="checkbox" <c:if test="${project.tenderType==3}" >checked="checked" </c:if> value="3"/> 服务</li> 
                <li><span><em>*</em>委托时间：</span><input name="entrustTime" required type="text" class="text" value="<fmt:formatDate value='${project.entrustTime }' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly"/></li>
                <li><span><em>*</em>委托金额：</span><input type="number" required name="entrustMoney"  type="text" class="text" value="${project.entrustMoney }"  maxlength="10"/></li>
                <!-- 
                <li><span>中央投资资金使用方式：</span><input type="text" required class="text" value=""  maxlength="32"/></li>
                 -->
                <li class="li_tenderMethod"><span><em>*</em>招标方式：</span>
	                &nbsp;&nbsp;<input name="tenderMethod" type="checkbox" class="checkbox" <c:if test="${project.tenderMethod==1}" >checked="checked" </c:if> value="1"/> 公开招标&nbsp;&nbsp;&nbsp;&nbsp;
	                <input name="tenderMethod" type="checkbox" class="checkbox" <c:if test="${project.tenderMethod==2}" >checked="checked" </c:if> value="2"/> 邀请招标
	            </li>  
                <li><span><em>*</em>公告发布时间：</span><input name="noticeDate" type="text" required value="<fmt:formatDate value='${project.noticeDate }' pattern='yyyy-MM-dd'/>" class="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly"/></li>
                <!--  <li><span>中标日期：</span><input name="bidTime" type="text" required class="text" value="<fmt:formatDate value='${project.bidTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></li> -->
                <li><span><em>*</em>公告媒体：</span><input  name="noticeMedia" type="text" required class="text" value="${project.noticeMedia }"  maxlength="32"/></li>
                <li><span><em>*</em>对投标人资质、资信要求：</span><textarea  name="creditRequire" required value="${project.creditRequire }"  maxlength="400">${project.creditRequire }</textarea></li>
                <li><span><em>*</em>开标时间：</span><input  name="opentenderTime" required value="<fmt:formatDate value='${project.opentenderTime }' pattern='yyyy-MM-dd'/>" type="text" class="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly"/></li>
                <li><span><em>*</em>招标内容：</span><textarea name="content" value="${project.content }" required maxlength="521">${project.content }</textarea></li>
                <div class="clear"></div>
            </ul>
            <div class="login_kuang_button">
                 <input type="submit" onclick="createProject_submit()" class="button1" value="保  存" /><span id="projectMst">${Info}</span>
            </div>
            </form>
        </div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/js/project.js" ></script>
    <script>
	    $(document).ready(function(){  
	    	$('.li_isCenter input').bind('click', function(){  
	            $('.li_isCenter input').not(this).attr("checked", false);  
	        });
	        $('.li_tenderType input').bind('click', function(){
	            $('.li_tenderType input').not(this).attr("checked", false);  
	        });  
	        $('.li_tenderMethod input').bind('click', function(){  
	            $('.li_tenderMethod input').not(this).attr("checked", false);  
	        });  
	    }); 
    </script>
