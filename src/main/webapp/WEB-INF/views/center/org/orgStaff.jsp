<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 
<script type="text/javascript" src="${ctxStatic}/js/operation.js"></script>
 
<script type="text/javascript" src="${ctxStatic}/js/staff.js"></script>
 
 </head>
<body>
<%@ include file="/WEB-INF/views/center/common/orgTop.jsp"%> 
    
<div class="login_top">
	<p class="on"><span>1</span>帐号注册</p>
    <p class="on"><span>2</span>填写企业基本信息</p>
    <p class="on"><span>3</span>上传企业资质</p>
    <p class="on"><span>4</span>上传专职人员情况</p>
    <p><span>5</span>上传招标业绩明细</p>
    <p><span>6</span>注册成功</p>
</div> 

<div class="login_kuang">
	<div class="top">
    	<div class="left">上传专职人员情况</div>
    </div>
    <div class="clear"></div>
    <div class="table_top">
    	<div class="left">专职从业人员名单：</div>
    	<form id="staffExcelForm" action="${ctxFront}/org/orgStaff/imp"  method="post" enctype="multipart/form-data">
        <div class="right">
        <input type="file" onchange="ajaxImportOrgStaff();"  name="imporgStaff" class="uploadFile" id="orgStaffFile"  /><span class="info">${message }</span> 
        <input type="button" class="buttonright" onclick="uploadStaff()" value="导入" /> 
        <!-- <input type="submit" class="buttonright"  value="点击上传" /> --> 支持导入，请下载模版   <a href="${ctxStatic}/download/orgStaffImport.xlsx">模版.xsl</a></div>
        </form>
    </div>
	  <table class="table1">
    	<tr>
            <th style="width:60px;">姓名</th>
            <th style="width:40px;">性别</th>
            <th style="width:100px;">身份证号</th>
            <th style="width:80px;">在本单位工<br />作起始时间</th>
            <th style="width:100px;">毕业院校</th>
            <th style="width:60px;">学历/学位</th>
            <th style="width:100px;">社会保险号</th>
            <th style="width:50px;">职称</th>
            <th style="width:60px;">所属部门</th>
            <th style="width:60px;">劳动关系</th>
            <th style="width:50px;">身份证<br />图片</th>
            <th style="width:50px;">职称证<br />图片</th>
            <th style="width:60px;">操作</th>
        </tr>
       </table>
          <!-- </table> -->
         <div id="staffContainer">
        	<script>
            	$(function(){
            		$.ajax({
						async:true,
						url: "${ctxFront}/org/orgStaff/staffPage",
						data:{"orgId":${org.id}},
						type:"get", 
						success:function(data){
							$("#staffContainer").html(data);
						}
					});
            	});
           	</script>
           	
 		</div>
   	<div id="pagenations" class="pagination" >
   		<input id="upPage" style="display:none;" type="button" class="zjyh" onclick="upPage()" value="上一页" />
		<input id="nextPage" style="display:none;" type="button" class="zjyh" onclick="nextPage()" value="下一页" />
   	</div>
   	<input type="hidden" id="addRowNum" value="1" />
    <input type="button" class="zjyh" onclick="add()" value="+增加一行" />
    
    
    <form id="form3333"  action="${ctxFront}/org/org/add"  method="post" enctype="multipart/form-data">
  
    <ul class="wenjian">
        <li>
        	<span>上传专职人员的社保证明图片（最少三个月）：</span>
            <div id="dowebok" class="divpic">
            	示例<br />
                <img id="zgzsImg1" src="${ctxStatic}/images/tu1.png" width="144" height="191" /><br />
                <a target="_blank" id="example1-1" href="javascript:show('PicAlert1','PicBoxShow1','#org_socialinsu_photo','#zgzsImg1');">查看大图</a>
            </div>
             <input type="file" name="file" onchange="ajaxUploadImage('uploadSocialSecurity','zgzsImg1','org_socialinsu_photo');" id="uploadSocialSecurity" class="uploadFile"/>
            <input type="button" class="button1" onclick="uploadSocialSecurityPoints()" value="点击上传" />  
            <input type="hidden" name="org_socialinsu_photo" id="org_socialinsu_photo" /><!-- org社保图片入库字段 -->
        </li>
        <div class="clear"></div>
    </ul> 
    <div class="login_kuang_button">
    	 <input type="submit" onclick="staffAjaxSubmit('form3333','mess333');" class="button" value="下一步" />&nbsp; <span id="mess333"></span> 
    </div>
     </form>
    <div class="clear"></div>
    <div class="PicAlert" id="PicAlert1" onclick="hide('PicAlert1','PicBoxShow1')"></div>
<div class="PicBoxShow" id="PicBoxShow1" onclick="hide('PicAlert1','PicBoxShow1')"><span><img id="imgContainer"  src="" /></span></div>
</div>
 <%@ include file="/WEB-INF/views/center/common/footer.jsp"%> 
</body>

</html>

 