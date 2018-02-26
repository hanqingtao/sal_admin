<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <script type="text/javascript" src="${ctxStatic}/js/staff.js"></script>
 	</head>
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
        <div class="jindubox">
            <p onclick="switchPage(1);" class="on1"><span>1</span>填写企业基本信息</p>
              <p  class="on1" onclick="switchPage(2);"><span>2</span>上传资质证明</p>
            <p onclick="switchPage(3);" id="staffFlag" class="on"><span>3</span>上传专职人员情况</p>
            <p  <c:if test="${org.achieveOn == 1 }">class="on1" onclick="switchPage(4);"</c:if>><span>4</span>上传招标业绩明细</p>
            <c:if test="${org.status==3 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}"><p  class="1on"><span>5</span>变更成功</p></c:if>
            <c:if test="${org.id==null||org.status==0||org.status==4||org.status==5||org.status==6||org.status==1||org.status==2||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
             <p  <c:if test="${org.status == 2 || org.status == 5 || org.status == 3 || org.status == 6 }">class="on1"  onclick="switchPage(5);"</c:if>><span>5</span>省发展改革委初审</p>
              <p <c:if test="${org.status == 3 || org.status == 6 }">class="on1"  onclick="switchPage(6);" </c:if>><span>6</span>招标中心终审</p>
              </c:if>
        </div>
        <input id="orgStatusFlag" type="hidden" value="${org.status}"/>
        <div class="login_kuang congxin" id="loginBox">
        <c:if test="${org.status==1 || org.status==2 || org.status==5}">
            <div class="promptBox">
                 <div class="promptContent" ><span>备案提交后的审核期间，禁止修改企业信息和相关资料！</span><span onclick="closePrompt()" class="promptClose">×</span></div>
            </div>
            </c:if>
         <!-- <input id="flag" value="${flag}"/>:标识
         <input id="qualificationOn" value="${org.qualificationOn}"/> 资质
         <input id="achieveOn" value="${org.achieveOn}"/>:业绩
         <input id="staffOn" value="${org.staffOn}"/>：专职 -->
            <div class="top">
                <div class="left">上传专职人员情况</div>
            </div>
            <div class="clear"></div>
            <div class="table_top">
                <div class="left">专职从业人员名单：</div>
                <div class="right">  <a href="${ctxStatic}/download/orgStaffImport.xlsx">模版下载</a> <input type="button" class="btn buttonright" onclick="uploadStaff()" value="导入" />  </div>
                <input type="file" onchange="ajaxImportOrgStaff();"  name="imporgStaff" class="uploadFile" id="orgStaffFile"  />
            </div>
            <table class="table1">
			              <tr>
			            <th style="width:60px;">姓名</th>
			            <th style="width:50px;">性别</th>
			            <th style="width:100px;">身份证号</th>
			            <th style="width:80px;">在本单位工<br />作起始时间</th>
			            <th style="width:100px;">毕业院校</th>
			            <th style="width:60px;">学历</th>
			            <th style="width:100px;">社会保险号</th>
			            <th style="width:50px;">职称</th>
			            <th style="width:60px;">所属部门</th>
			            <th style="width:60px;">劳动关系</th>
			            <th style="width:50px;">身份证</th>
			            <th style="width:50px;">职称证</th>
			            <th style="width:40px;">删除</th>
			        </tr>
			    </table>
			    <div id="staffContainer">
		        	<script>
		            	$(function(){
		            		
		            		
		            		$.ajax({
								async:true,
								url: "${ctxFront}/org/orgStaff/staffPage",
								type:"get", 
								success:function(data){
									$("#staffContainer").html(data);
									 
									if($("#orgStatusFlag").val()=="1"||$("#orgStatusFlag").val()=="2"||$("#orgStatusFlag").val()=="5"){
										var length=$("#applicationContainer").find(".flag").length;
										var fLength=$("#applicationContainer").find(".upload").length;
										var dLength=$("#applicationContainer").find(".del").length;
										var btnLength=$("#applicationContainer").find(".btn").length;
										var sLength=$("#applicationContainer").find(".sel").length;
										
									for(var i=0;i<length;i++){
										$($("#applicationContainer").find(".flag")[i]).attr("disabled","disabled").css({"-webkit-appearance":"none","text-align":"center","background-color":"#fff","border":"1px solid #fff","color":"gray","cursor":"not-allowed"});
									}
									for(var i=0;i<fLength;i++){
										$($("#applicationContainer").find(".upload")[i]).attr("disabled","disabled").css({"background-color":"#fff","color":"gray","cursor":"not-allowed"});
									}
									for(var i=0;i<dLength;i++){
										$($("#applicationContainer").find(".del")[i]).attr("disabled","disabled").css({"filter":"grayscale(100%)","color":"gray","cursor":"not-allowed","pointer-events":"none"});
									}
									for(var i=0;i<btnLength;i++){
										$($("#applicationContainer").find(".btn")[i]).attr("disabled","disabled").css({"background-color":"#eee","color":"gray","border":"1px solid #eee","cursor":"not-allowed","pointer-events":"none"});
									}
									for(var i=0;i<sLength;i++){
										$($("#applicationContainer").find(".sel")[i]).attr("disabled","disabled").css({"-webkit-appearance":"none","text-indent":"1em","text-align":"center","background-color":"#fff","border":"1px solid #fff","color":"gray","cursor":"not-allowed"});
									}
									 
									}
									
									 
									 
								
								}
							});
		            	});
		           	</script>
		           	
 			</div>
 			<a href="javascript:addStaff();"   class="del zjyhxadd1" ><img src="${ctxStatic}/images/daa.png" /> 增加一组</a>
		   	<!-- <div id="pagenations" class="pagination">
		   		<input id="upPage"  type="button" class="zjyh" onclick="upPage()" value="上一页" />
				<input id="nextPage"  type="button" class="zjyh" onclick="nextPage()" value="下一页" />		   		
		   	</div> -->
		   	  
            
           <form id="form3333"  action="${ctxFront}/org/org/add" id="selectFlag" method="post">
            <ul class="wenjian" style="margin-top:100px">
		        <li>
		        	<span>上传专职人员的社保证明图片（最少三个月）：</span>
		            <div id="dowebok" class="divpic">
		            	示例<br />
		            	<c:if test="${empty org.socialinsuPhoto }">
		                <img id="zgzsImg1" src="${ctxStatic}/images/pic4.jpg" width="144" height="191" /><br />
		            	</c:if>
		            	<c:if test="${!empty org.socialinsuPhoto }">
		                <img id="zgzsImg1" src="${imgServer }${org.socialinsuPhoto }" width="144" height="191" /><br />
		            	</c:if>
		                <a target="_blank" id="example1-1" href="javascript:show('PicAlert1','PicBoxShow1','#org_socialinsu_photo','#zgzsImg1');">查看大图</a>
		            </div>
		             <input type="file" name="file" onchange="ajaxUploadImage('uploadSocialSecurity','zgzsImg1','org_socialinsu_photo');" id="uploadSocialSecurity" class="uploadFile"/>
		            <input type="button" class="upload button1" onclick="uploadSocialSecurityPoints()" class="upload" value="点击上传" />  
		            <input type="hidden" value="${org.socialinsuPhoto }" name="org_socialinsu_photo" id="org_socialinsu_photo" /><!-- org社保图片入库字段 -->
		        </li>
		        <div class="clear"></div>
		    </ul>
            <div class="login_kuang_button">
                <c:if test="${org.status==3 and org.qualificationOn==1 and org.staffOn==1 and  org.achieveOn==1}">
                 <input type="submit" onclick="saveOrgStaff();" class="button" value="保存修改"  />&nbsp;<span id="mess33"></span> 
                 <input id="bakAjax" type="button"  onclick="ajaxBak();" class="button1" value="重新提交"  />
                 </c:if>
                 
               <c:if test="${org.status==0||org.status==4||org.status==5||org.status==6||org.status==1||org.status==2||org.qualificationOn==0||org.staffOn==0||org.achieveOn==0}">
                 <input type="submit" onclick="submitStaffNext(); " class="btn button" style="width:auto;padding:0 20px" value="下一步，填写招标业绩" />&nbsp;&nbsp;
                  <input type="button" onclick="saveOrgStaff();" class="btn button1" value="保&nbsp;&nbsp;存" />
                  
                   </c:if>
                 
                 
            </div>
            </form>
            <div class="clear"></div>
            <div class="PicAlert" id="PicAlert1" onclick="hide('PicAlert1','PicBoxShow1')"></div>
<div class="PicBoxShow" id="PicBoxShow1" onclick="hide('PicAlert1','PicBoxShow1')"><span><img id="imgContainer"  src="" /></span></div>
        </div>
