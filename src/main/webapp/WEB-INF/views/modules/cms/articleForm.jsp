<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	
	<!-- 配置文件 -->
		<!--  ueditor 引入 begin  -->
		<!-- 配置文件 -->
		<script type="text/javascript" src="${ctxStatic}/ueditor/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="${ctxStatic}/ueditor/ueditor.all.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${ctxStatic}/ueditor/lang/zh-cn/zh-cn.js"></script>
		<!--  ueditor 引入 end  -->
		<script>
			window.UEDITOR_HOME_URL = "/cip-bsmp/ueditor/";
		</script>
	<script type="text/javascript">
		$(document).ready(function() {
			
            if($("#link").val()){
                $('#linkBody').show();
                $('#url').attr("checked", true);
            }
			$("#title").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
                    if ($("#categoryId").val()==""){
                        $("#categoryName").focus();
                        top.$.jBox.tip('请选择归属栏目','warning');
                    }else if (ue.getContentTxt()=="" && $("#link").val().trim()==""){
                        top.$.jBox.tip('请填写正文','warning');
                        
                    }else if(ue.getContentTxt().length>10000){
                    	top.$.jBox.tip('正文字数不能超过1万字','warning');
					}else{
                        loading('正在提交，请稍等...');
                        form.submit();
                    }
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/article/?category.id=${article.category.id}">文章列表</a></li>
		<li class="active"><a href="<c:url value='${fns:getAdminPath()}/cms/article/form?id=${article.id}&category.id=${article.category.id}'><c:param name='category.name' value='${article.category.name}'/></c:url>">文章<shiro:hasPermission name="cms:article:edit">${not empty article.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:article:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="article" action="${ctx}/cms/article/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">归属栏目:</label>
			<div class="controls">
                <sys:treeselect id="category" name="category.id" value="${article.category.id}" labelName="category.name" labelValue="${article.category.name}"
					title="栏目" url="/cms/category/treeData" module="article" selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" cssClass="required"/>&nbsp;
                <!-- 
                <span>
                    <input id="url" type="checkbox" onclick="if(this.checked){$('#linkBody').show()}else{$('#linkBody').hide()}$('#link').val()"><label for="url">外部链接</label>
                </span>
                -->
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题:</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xxlarge measure-input required"/>
				<!-- 
				&nbsp;<label>颜色:</label>
				<form:select path="color" class="input-mini">
					<form:option value="" label="默认"/>
					<form:options items="${fns:getDictList('color')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				-->
			</div>
		</div>
		<!-- 
        <div id="linkBody" class="control-group" style="display:none">
            <label class="control-label">外部链接:</label>
            <div class="controls">
                <form:input path="link" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <span class="help-inline">绝对或相对地址。</span>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">关键字:</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<span class="help-inline">多个关键字，用空格分隔。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重:</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="200" class="input-mini required digits"/>&nbsp;
				<span>
					<input id="weightTop" type="checkbox" onclick="$('#weight').val(this.checked?'999':'0')"><label for="weightTop">置顶</label>
				</span>
				&nbsp;过期时间：
				<input id="weightDate" name="weightDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${article.weightDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				<span class="help-inline">数值越大排序越靠前，过期时间可为空，过期后取消置顶。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">摘要:</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		 -->
		<div class="control-group">
			<label class="control-label">文章内容:</label>
			<div class="controls">
			 <form:textarea id="container" htmlEscape="true" path="articleData.content" rows="4" maxlength="200" class="input-xxlarge"/> 
				 <!--<sys:ckeditor replace="content" uploadPath="/cms/article" />--> 
				  <input type="hidden" id="cmsArticle.content" name="cmsArticle.content" />		    
						<!-- 加载编辑器的容器 -->
						    <script id="container" name="content" type="text/plain"></script>
						    <!-- 实例化编辑器 -->
						    <script type="text/javascript">
								var ue = UE.getEditor('container', {
								toolbars: [[   
						           'fullscreen', 'source', '|', 'undo', 'redo', '|',   
						           'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',   
						           'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',   
						           'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',   
						           'directionalityltr', 'directionalityrtl', 'indent', '|',   
						           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',   
						           'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',   
						           'simpleupload',  'attachment','emotion', 'scrawl',  'map',  'insertframe', 'insertcode', 'pagebreak', 'template', 'background', '|',   
						           'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',   
						           'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',   
						           'searchreplace', 'help', 'drafts'   
						       ]],
						    autoFloatEnabled: false,
						    initialFrameWidth : 720,
						    initialFrameHeight: 400
						});
						
						    </script>
							
							<script>
								//对编辑器的操作最好在编辑器ready之后再做
								ue.ready(function() {
									var content = '<s:property value="cmsArticle.content" escapeHtml="false" />';
									//设置编辑器的内容
									var id= '<s:property value="cmsArticle.id" />';
									if(null != id && id>0){
										ue.setContent(content);
									}									
								});
							</script>
			</div>
		</div>
		<div class="control-group" style="margin-left:200px;">
			<!-- <label class="control-label">缩略图:</label>
			<div class="controls">
                <input type="hidden" id="image" name="image" value="${article.imageSrc}" />
				<sys:ckfinder input="image" type="thumb" uploadPath="/cms/article" selectMultiple="false"/> &nbsp;&nbsp;&nbsp;首页焦点图尺寸：1920*465 &nbsp;&nbsp;&nbsp;首页左侧焦点图尺寸：348*271
			</div> -->
			
			
			<div class="divpic">
            	<c:if test="${empty article.image}">
                 <img id="imgshow222" src="${ctxStatic}/images/qsimage.jpg" width="144" height="191" /><br />
                </c:if>
                <c:if test="${not empty article.image}">
                <img id="imgshow222" src="${imgServer}${article.image}" width="144" height="191" /><br />
                </c:if>
            </div>
            <input type="file" name="file" onchange="ajaxUploadImage('UploadID','imgshow222','legalrepPhotohidden');" id="UploadID"  /><span >首页顶部焦点图1920*465 首页左侧焦点图348*271</span>
            <input type="hidden" name="image" id="legalrepPhotohidden" />
            <!--  <input type="button"  id="UploadIDpoints" class="button1" value="点击上传" id="test" /> --> 
			
		</div>
		<div class="control-group">
			<label class="control-label">来源:</label>
			<div class="controls">
				<form:input path="articleData.copyfrom" htmlEscape="false" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<!--
		<div class="control-group">
			<label class="control-label">相关文章:</label>
			<div class="controls">
				<form:hidden id="articleDataRelation" path="articleData.relation" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<ol id="articleSelectList"></ol>
				<a id="relationButton" href="javascript:" class="btn">添加相关</a>
				<script type="text/javascript">
					var articleSelect = [];
					function articleSelectAddOrDel(id,title){
						var isExtents = false, index = 0;
						for (var i=0; i<articleSelect.length; i++){
							if (articleSelect[i][0]==id){
								isExtents = true;
								index = i;
							}
						}
						if(isExtents){
							articleSelect.splice(index,1);
						}else{
							articleSelect.push([id,title]);
						}
						articleSelectRefresh();
					}
					function articleSelectRefresh(){
						$("#articleDataRelation").val("");
						$("#articleSelectList").children().remove();
						for (var i=0; i<articleSelect.length; i++){
							$("#articleSelectList").append("<li>"+articleSelect[i][1]+"&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"articleSelectAddOrDel('"+articleSelect[i][0]+"','"+articleSelect[i][1]+"');\">×</a></li>");
							$("#articleDataRelation").val($("#articleDataRelation").val()+articleSelect[i][0]+",");
						}
					}
					$.getJSON("${ctx}/cms/article/findByIds",{ids:$("#articleDataRelation").val()},function(data){
						for (var i=0; i<data.length; i++){
							articleSelect.push([data[i][1],data[i][2]]);
						}
						articleSelectRefresh();
					});
					$("#relationButton").click(function(){
						top.$.jBox.open("iframe:${ctx}/cms/article/selectList?pageSize=8", "添加相关",$(top.document).width()-220,$(top.document).height()-180,{
							buttons:{"确定":true}, loaded:function(h){
								$(".jbox-content", top.document).css("overflow-y","hidden");
							}
						});
					});
				</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否允许评论:</label>
			<div class="controls">
				<form:radiobuttons path="articleData.allowComment" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		 -->
		<div class="control-group">
			<label class="control-label">推荐位:</label>
			<div class="controls">
				<!-- <form:checkboxes path="posidList" items="${fns:getDictList('cms_posid')}" itemLabel="label" itemValue="value" htmlEscape="false"/>-->
				  <form:radiobuttons path="posidList" items="${fns:getDictList('cms_posid')}" itemLabel="label" itemValue="value" htmlEscape="false"/> 
			</div>
		</div>
		 
		<div class="control-group">
			<label class="control-label">发布时间:</label>
			<div class="controls">
				<input id="createDate" name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<!--<shiro:hasPermission name="cms:article:audit">
			<div class="control-group">
				<label class="control-label">发布状态:</label>
				 <div class="controls">
					<form:radiobuttons path="delFlag" items="${fns:getDictList('cms_del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"></span>
				</div>  
			</div>
		</shiro:hasPermission>-->
		<!-- 
		<shiro:hasPermission name="cms:category:edit">
            <div class="control-group">
                <label class="control-label">自定义内容视图:</label>
                <div class="controls">
                      <form:select path="customContentView" class="input-medium">
                          <form:option value="" label="默认视图"/>
                          <form:options items="${contentViewList}" htmlEscape="false"/>
                      </form:select>
                      <span class="help-inline">自定义内容视图名称必须以"${article_DEFAULT_TEMPLATE}"开始</span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">自定义视图参数:</label>
                <div class="controls">
                      <form:input path="viewConfig" htmlEscape="true"/>
                      <span class="help-inline">视图参数例如: {count:2, title_show:"yes"}</span>
                </div>
            </div>
		</shiro:hasPermission>
		<c:if test="${not empty article.id}">
			<div class="control-group">
				<label class="control-label">查看评论:</label>
				<div class="controls">
					<input id="btnComment" class="btn" type="button" value="查看评论" onclick="viewComment('${ctx}/cms/comment/?module=article&contentId=${article.id}&status=0')"/>
					<script type="text/javascript">
						function viewComment(href){
							top.$.jBox.open('iframe:'+href,'查看评论',$(top.document).width()-220,$(top.document).height()-180,{
								buttons:{"关闭":true},
								loaded:function(h){
									$(".jbox-content", top.document).css("overflow-y","hidden");
									$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
									$("body", h.find("iframe").contents()).css("margin","10px");
								}
							});
							return false;
						}
					</script>
				</div>
			</div>
		</c:if>
		-->
		<div class="form-actions">
			<shiro:hasPermission name="cms:article:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">  
    var ue = UE.getEditor("container");  
    </script>  
    <script type="text/javascript" src="${ctxStatic}/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/js/cms.js"></script>
</body>
</html>