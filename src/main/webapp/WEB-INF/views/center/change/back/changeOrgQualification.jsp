<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <script type="text/javascript">
		$(document).ready(function() {
			 getOption();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		 function  getOption(){
			 $.ajax({
	                type:"post",
	                url:ctxFront+"/etc/term.do",
	                data:{"option":"qualification_grade"},
	                dataType : "json",
	                success:function(data){
	                    var str="";
	                    for(var i=0;i<data.length;i++){
	                        str+="<option value="+data[i].value+">"+data[i].label+"</option>";
	                    }
	                    $("#qualificationContent").find("select").last().html(str);;
	                }
	            });
		 }
		 /**
		  *  变更机构上传资质证明的下一步,返回上传专职页面
		  */
		 function changeOrgStaffNext(){
		 	/*$.ajax({
		         async:true,
		         url:ctxFront+"/org/orgStaff/changeEntry",
		         type:"post",
		         success:function(data){
		         	$("#pageContaienr").html(data);
		         }
		     });*/
			 window.location.href=ctxFront+"/org/orgStaff/changeEntry";
		 }

	</script>
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
            <p ><span>3</span>上传专职人员情况</p>
            <p ><span>4</span>上传招标业绩明细</p>
            <p ><span>5</span>省发展改革委初审</p>
              <p ><span>6</span>招标中心终审</p>
        </div>
        <div class="login_kuang congxin">
            <div class="top">
                <div class="left">更改</div>
                <div class="right"><span>*</span>为必填项</div>
            </div>
            <div class="clear"></div>
            <c:forEach items="${orgQualificationList}" var="orgQualification">
             <form id="inputForm"  action="${ctxFront}/org/orgQualification/change?id=${orgQualification.id}"   class="form-horizontal" method="post"  enctype="multipart/form-data" >
              
              
             <ul class="ulys" id="qualificationContent">
                <li><span>资格名称：</span><input type="text" class="text"  name="name" value="${orgQualification.name }" maxlength="32" /></li>
                <li><span>资格等级：</span><select name="grade" id="ziGe">
                <option value="1" <c:if test="${orgQualification.grade==1}" >selected</c:if>>二级</option>
               
                <option value="2" <c:if test="${orgQualification.grade==2}" >selected</c:if>>一级</option>
                
                </select> </li>
                <li>
                    <span>上传资格证书图片：</span>
                    <div class="divpic">
                        示例<br />
                        <img src="${ctxStatic}/images/tu1.png" width="144" height="191" /><br />
                        <a href="#">查看大图</a>
                    </div>
                    <!-- <input type="button" class="button1" value="点击上传" /> -->
                    <input name="file"  type="file">
                </li>
                <li>
                    <span>&nbsp;</span>
                    
                   
                </li>
                <div class="clear"></div>
            </ul>
            
            <div class="login_kuang_button">
                 &nbsp;  <input type="submit" class="button" value="保  存 下一步" />
            </div>
            </form>
             </c:forEach>
              <!-- 
              <div class="login_kuang_button">
                <input type="button" class="button" value="+增加一行" />&nbsp;&nbsp;<input type="button" onclick="changeOrgStaffNext()" class="button1" value="下一步" />
           	  </div>
              -->
            
            <div class="clear"></div>
        </div>
    </div>
    <div class="clear"></div>
</div>
</div>
  <%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
</body>
</html>