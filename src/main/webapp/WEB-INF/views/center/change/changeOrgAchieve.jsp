<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <script type="text/javascript" src="${ctxStatic}/js/achieve.js"></script>
 	</head>
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
        <div class="jindubox">
            <p onclick="switchPage(1);" class="on1"><span>1</span>填写企业基本信息</p>
              <p  <c:if test="${org.qualificationOn == 1 }">class="on1" onclick="switchPage(2);"</c:if>><span>2</span>上传资质证明</p>
            <p  <c:if test="${org.staffOn == 1 }">class="on1" onclick="switchPage(3);"</c:if>><span>3</span>上传专职人员情况</p>
            <p onclick="switchPage(4);" id="achieveFlag" class="on"><span>4</span>上传招标业绩明细</p>
            <c:if test="${org.status==3 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}"><p   ><span>5</span>变更成功</p></c:if>
            <c:if test="${org.id==null||org.status==0||org.status==4||org.status==5||org.status==6||org.status==1||org.status==2||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
             <p <c:if test="${org.status == 2 || org.status == 5 || org.status == 3 || org.status == 6 }">class="on1" onclick="switchPage(5);" </c:if>><span>5</span>省发展改革委初审</p>
              <p  <c:if test="${org.status == 3 || org.status == 6 }">class="on1" onclick="switchPage(6);"</c:if>><span>6</span>招标中心终审</p>
              </c:if>
            
        </div>
        <input id="orgStatusFlag" type="hidden" value="${org.status}"/>
        <div class="login_kuang congxin">
         <c:if test="${org.status==1 || org.status==2 || org.status==5}">
            <div class="promptBox">
                 <div class="promptContent" ><span>备案提交后的审核期间，禁止修改企业信息和相关资料！</span><span onclick="closePrompt()" class="promptClose">×</span></div>
            </div>
            </c:if>
            <div class="top">
                <div class="left">上传招标业绩明细</div>
                <input type="file" onchange="ajaxImportOrgAchieves();" name="imporgAchieve" class="uploadFile" id="achieveFile" /><span class="info"></span>
                <div class="right">  <a href="${ctxStatic}/download/orgAchieveImport.xlsx">模版下载</a> <input type="button" class="btn buttonright" onclick="uploadAchieve()" value="导入" />  </div>
        
            </div>
            <div class="clear"></div>
            <span style="float:left;color:#aaa">金额单位：万元</span>
            <table class="table1 m_t">
                <tr>
            <th style="width:60px;">项目编号</th>
            <th style="width:50px;">项目名称</th>
            <th style="width:40px;">项目类型</th>
            <th style="width:40px;">是否中央投资项目</th>
            <th style="width:100px;">委托单位</th>
            <th style="width:40px;">委托金额 </th>
            <th style="width:40px;">中标金额 </th>
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
									if($("#orgStatusFlag").val()=="1"||$("#orgStatusFlag").val()=="2"||$("#orgStatusFlag").val()=="5"){
										var length=$("#applicationContainer").find(".flag").length;
										var dLength=$("#applicationContainer").find(".del").length;
										var btnLength=$("#applicationContainer").find(".btn").length;
										var selLength=$("#applicationContainer").find(".sel").length;
									for(var i=0;i<length;i++){
										$($("#applicationContainer").find(".flag")[i]).attr("disabled","disabled").css({"background-color":"#fff","border":"1px solid #fff","color":"gray","text-align":"center","cursor":"not-allowed"});
									}
									for(var i=0;i<dLength;i++){
										$($("#applicationContainer").find(".del")[i]).attr("disabled","disabled").css({"filter":"grayscale(100%)","color":"gray","pointer-events":"none","cursor":"not-allowed"});
									}
									for(var i=0;i<btnLength;i++){
										$($("#applicationContainer").find(".btn")[i]).attr("disabled","disabled").css({"background-color":"#eee","border":"1px solid #eee","color":"gray","pointer-events":"none","cursor":"not-allowed"});
									}
									for(var i=0;i<selLength;i++){
										$($("#applicationContainer").find(".sel")[i]).attr("disabled","disabled").css({"-webkit-appearance":"none","text-indent":"1em","background-color":"#fff","border":"1px solid #fff","color":"gray","pointer-events":"none","cursor":"not-allowed"});
									}
									 
									}
		            			}
		            		});
		            	});
		           	</script>
    			</div>
    			<a href="javascript:addAchieve();" class="del zjyhxadd1" ><img src="${ctxStatic}/images/daa.png" /> 增加一组</a>
    			
    <div id="pagenations" class="pagination" >
   		<input id="upPage" style="display:none;" type="button" class="zjyh" onclick="upPage()" value="上一页" />
		<input id="nextPage" style="display:none;" type="button" class="zjyh" onclick="nextPage()" value="下一页" />
   	</div>
   			<input type="hidden" id="addRowNum" value="1" />
          <div class="xieyi" style="margin-top:100px"><input required id="xieYi" type="checkbox" class="flag checkbox" />&nbsp;阅读并接受<a href="#">《中央投资项目服务与管理平台用户协议》</a><i id="xieYiLog"></i></div>  
            <div class="login_kuang_button" style="margin-top:90px;">
            
              <c:if test="${org.status==3 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}">
                 <input type="button" class="button" onclick="saveAchieve();" value="保存修改" />
                 <input id="bakAjax" type="button"  onclick="ajaxBak();" class="button1" value="重新提交"  />
                 </c:if>
                 <c:if test="${org.status==0||org.status==4||org.status==5||org.status==6||org.status==1||org.status==2||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
                   <input type="submit" class="btn button" onclick="ajaxSucessOrg()" value="提交发改委初审" />&nbsp;&nbsp;
                   <input type="button" onclick="saveAchieve()" class="btn button1" value="保&nbsp;&nbsp;存" />
                 </c:if>
                 
                 
            </div>
            <div class="clear"></div>
            <script>
              function fgwEntry(){
            	  window.location.href=ctxFront+"/org/org/fgwEntry";
              }
            </script>
        </div>
