<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
   <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
		<script type="text/javascript">
		$(document).ready(function() {
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
	</script>
 
      </head>
<body>
<%@ include file="/WEB-INF/views/center/common/orgTop.jsp"%>
<div class="login_top">
	<p class="on"><span>1</span>帐号注册</p>
	<p class="on"><span>2</span>填写企业基本信息</p>
	<p class="on"><span>3</span>上传企业资质</p>
	<p class="on"><span>4</span>上传专职人员情况</p>
	<p class="on"><span>5</span>上传招标业绩明细</p>
	<p class="on" ><span>6</span>注册成功</p>
</div> 

<div class="login_kuang">
	<dl>
    	<dt><img src="${ctxStatic}/images/yuan.png" /> 恭喜您完成注册</dt>
        <dd>备案申请已提交到省发展改革委，审核一般需要15-20个工作日，请耐心等待</dd>
        <dd><input type="button" class="button" value="提交" onClick="rtn()" /></dd>
    </dl>
</div>
   <%@ include file="/WEB-INF/views/center/common/footer.jsp"%> 
</body>
<script > 
function rtn(){
	window.location.href = ctxFront+"?flag=1";
	/*
	alert("rtn"+ctxFront+"/org/org/enter.do");
	 $.ajax({
         async:true,
         url:ctxFront+"/org/org/enter.do",
         type:"post",
         success:function(data){
             $("#pageContaienr").html(data);
         }
     });
	 */
}
</script>
</html>