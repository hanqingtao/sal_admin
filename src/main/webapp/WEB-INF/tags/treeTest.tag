<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="ur" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="url" type="java.lang.String"  description="隐藏域值（ID）"%>

<script type="text/javascript">
		var id='${id}';
		alert("id:"+id);
		var url1= "${url}";
		alert(url1);
		var url= '${ur}';
		
		alert(url);
		var v = '${value}';
		alert("v:"+v);
		// 正常打开
		
</script>