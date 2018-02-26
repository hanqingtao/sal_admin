<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 	</head>
<body style="background:#fff;">
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
<div id="pageContaienr">
 <div class="main">

	 <%@ include file="/WEB-INF/views/center/common/orgApplication.jsp"%>
    <div class="main_right">
    	<div class="tops"><div class="left">代理机构资质备案管理</div></div>
        <div class="jindubox">
            <p class="on"><span>1</span>填写企业基本信息</p>
            <p class="on"><span>2</span>上传资质证明</p>
            <p class="on"><span>3</span>上传专职人员情况</p>
            <p  ><span>4</span>上传招标业绩明细成功</p>
            <p ><span>5</span>省发展改革委初审</p>
              <p ><span>6</span>招标中心终审</p>
        </div>
        <div class="login_kuang congxin">
            <div class="top">
                <div class="left">上传专职人员情况</div>
            </div>
            <div class="clear"></div>
            <div class="table_top">
                <div class="left">专职从业人员名单：</div>
                <div class="right"><input type="button" class="buttonright" value="导入" /> 支持导入，请下载模版   <a href="#">模版.xsl</a></div>
            </div>
            <table class="table1">
			              <tr>
			        	<th style="width:20px;">序号</th>
			            <th style="width:50px;">姓名</th>
			            <th style="width:40px;">性别</th>
			            <th style="width:100px;">身份证号</th>
			            <th style="width:80px;">在本单位工<br />作起始时间</th>
			            <th style="width:100px;">毕业院校</th>
			            <th style="width:90px;">学历/学位</th>
			            <th style="width:100px;">社会保险号</th>
			            <th style="width:40px;">职称</th>
			            <th style="width:60px;">所属部门</th>
			            <th style="width:90px;">劳动关系</th>
			            <th style="width:50px;">身份证图片</th>
			            <th style="width:50px;">职称证图片</th>
			            <th style="width:100px;">操作</th>
			        </tr>
			    </table>
                <c:forEach items="${staffList}" var="staff">
			            <form  action="${ctxFront}/org/orgStaff/change?id=${staff.id}" class=""  method="post" enctype="multipart/form-data">
			      <table id="staffContainer" class="table1">
			       
			        <tr id="staffTbody">
			        	<td style="width:20px;">1</td>
			            <td style="width:50px;"><input style="width:45px;" type="text" name="name" value="${staff.name}" maxlength="16"></td>
			            <td style="width:40px;"><select style="width:40px;" type="text" name="sex" value="${staff.sex }" > 
			               <option value="1" <c:if test="${staff.sex==1}" >selected</c:if>>男</option>
			               <option value="2" <c:if test="${staff.sex==2}" >selected</c:if>>女</option>
			            </select></td>
			            <td style="width:100px;"><input style="width:90px;" type="text"  name="card" value="${staff.card}"  maxlength="32"/></td>
			            <td style="width:80px;"><input style="width:70px;" name="workStart" type="text" value="<fmt:formatDate value='${staff.workStart}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" ></td>
			            <td style="width:100px;"><input style="width:90px;"  type="text" name="university" value="${staff.university}"  maxlength="64"/></td>
			            <td style="width:90px;"> <select style="width:85px;"  name="degree" > 
			                  <option value="1" <c:if test="${staff.degree==1}" >selected</c:if>>专科</option>
			                  <option value="2" <c:if test="${staff.degree==2}" >selected</c:if>>本科</option>
			                  <option value="3" <c:if test="${staff.degree==3}" >selected</c:if>>研究生</option>
			             </select></td>
			            <td style="width:100px;"><input   style="width:90px;" type="text" name="ssn" value="${staff.ssn}"  maxlength="32"/></td>
			            <td style="width:40px;"><input  style="width:30px;" type="text" name="proTitle" value="${staff.proTitle}"  maxlength="23"/></td>
			            <td style="width:60px;"><input  style="width:55px;" type="text" name="dept" value="${staff.dept}"  maxlength="23"/></td>
			            <td style="width:90px;"><select  style="width:85px;" name="workType" > 
			            <option value="1" <c:if test="${staff.workType==1}" >selected</c:if>>在职</option>
			            <option value="2" <c:if test="${staff.workType==2}" >selected</c:if>>退休返聘</option>
			            </select></td>
			            <td style="width:50px;"><input style="width:50px;" type="file"   name="file1"/> </td>
			            <td style="width:50px;"><input style="width:50px;" type="file"  sname="file2"/> </td>
			            <td style="width:100px;"><input type="submit" value="保存" />&nbsp;&nbsp;<input type="button" value="删除"/></td>
			        </tr>
			       
			      </table>
       
            
       </c:forEach>
            <input type="button" class="zjyh" value="+增加一行" />
            <ul class="wenjian">
                <li>
                    <span>上传专职人员的社保证明图片（最少三个月）：</span>
                    <div class="divpic">
                        示例<br />
                        <img src="${ctxStatic}/images/tu1.png" width="144" height="191" /><br />
                        <a href="#">查看大图</a>
                    </div>
                    <input type="button" class="button1" value="点击上传" />
                </li>
                <div class="clear"></div>
            </ul> 
            <div class="login_kuang_button">
                 <input type="submit" class="button" value="保 存  下一步"  />&nbsp; 
                 <!-- 
                 <input type="button" onclick="changeOrgAchieveNext()" class="button1" value="下一步" />
                 -->
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="clear"></div>
</div>
</div>
</form>
<script>

/**
 *  变更机构上传完专职人员下一步,返回上传业绩页面
 */
function changeOrgAchieveNext(){
	/*$.ajax({
        async:true,
        url:ctxFront+"/org/orgAchieve/changeEntry",
        type:"post",
        success:function(data){
        	$("#pageContaienr").html(data);
        }
    });*/
	
	window.location.href=ctxFront+"/org/orgAchieve/changeEntry";
}
</script>
<%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
</body>
</html>