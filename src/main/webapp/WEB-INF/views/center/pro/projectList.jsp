<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  
    <script>
    function createProject(){
  	   $.ajax({
  	         async:true,
  	         url:ctxFront+"/pro/project/add",
  	         type:"post",
  	         success:function(data){
  	        	 $("#applicationContainer").html(data);
  	         }
  	 });
     }
    
    function deleteProject(proId,obj){
    	if(confirm("您确定要删除该项目吗？")){
   		 
    		$.ajax({
    			type:"post",
    			url:ctxFront+"/pro/project/delete",
    			data:{"id":proId},
    			dataType : "json",
    			success:function(data){
    				$(obj).parent().parent().remove();
    				$("#message").html(data).css("color","red");
    			} 
    		});
    		
    	}
    	
    }
    </script>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    	<div class="tops"><div class="left">项目列表</div><input onclick="createProject()" type="button" class="button" value="创建项目" /></div><span id="message"></span>
        <table id="contentTable" class="table1 m_t" >
            <thead>
            <tr>
                <th>项目名称</th>
                <th>招标编号</th>
                <th>地区</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
           <tbody>
             
            
            <c:forEach items="${page.list}" var="pro">
			<tr>
				<td>${pro.name}</td>
				<td> ${pro.sn}</td>
				<td>
					${pro.areaName}
				</td>
				
				<td>
					<fmt:formatDate value="${pro.opentenderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			 <td>
			 	<!-- 
			 	<a href="${ctxFront}/pro/project/uploadAnnouncement?projectId=${pro.id}" >上传相关资料</a>
			 	 -->
			 	<a href="javascript:toUploadFile_projectFlow('/pro/project/uploadAnnouncement?plist=1&projectId=${pro.id}');" >上传相关资料</a>
			 	&nbsp;&nbsp;<a href="javascript:modifyProject(${pro.id});" >修改项目信息</a>
			 	&nbsp;&nbsp;<a href="javascript:;" onclick="deleteProject(${pro.id},this)">删除</a>
			 </td>
				
			</tr>
		   </c:forEach>
		</tbody>
        </table>
     	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div class="clear"></div>
    <script>
       function modifyProject(proId){
    	   $.ajax({
  	         async:true,
  	         url:ctxFront+"/pro/project/add?id="+proId,
  	         type:"post",
  	         success:function(data){
  	        	 $("#applicationContainer").html(data);
  	         }
  	        });
       }
    </script>
   
    
     
   
 