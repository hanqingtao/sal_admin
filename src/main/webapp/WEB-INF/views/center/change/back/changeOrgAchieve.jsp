<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
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
            <p class="on"><span>4</span>上传招标业绩明细</p>
            <p ><span>5</span>省发展改革委初审</p>
              <p ><span>6</span>招标中心终审</p>
            
        </div>
        <div class="login_kuang congxin">
            <div class="top">
                <div class="left">上传招标业绩明细</div>
                <div class="right m_b"><input type="button" class="buttonright" value="导入" /> 支持导入，请下载模版   <a href="#">模版.xsl</a><br /></div>
        
            </div>
            <div class="clear"></div>
            <span style="float:right;">金额单位：万元</span>
            <table class="table1 m_t">
                <tr>
                   <th style="width:20px;">序号</th>
            <th style="width:50px;">项目编号</th>
            <th style="width:50px;">项目名称</th>
            <th style="width:40px;">项目类型</th>
            <th style="width:100px;">委托单位</th>
            <th style="width:40px;">委托金额</th>
            <th style="width:30px;">中标金额</th>
            <th style="width:50px;">开标时间</th>
            <th style="width:50px;">中标时间</th>
            <th style="width:50px;">公告媒体</th>
            <th style="width:50px;">公告时间</th>
            <th style="width:50px;">操作</th>
                </tr>
                </table>
                 <c:forEach items="${achieveList}" var="achieve">
			            <form  action="${ctxFront}/org/orgAchieve/change?id=${achieve.id}" class=""  method="post" enctype="multipart/form-data">
			            <table class="table1 m_t">
                <tr>
                  <td style="width:20px;">1</td>
            <td style="width:50px;"><input   value="${achieve.num}"    style="width:48px;" name="num"  maxlength="16"/></td>
            <td style="width:50px;"><input   value="${achieve.name}"    style="width:48px;" name="name"  maxlength="32"/></td>
            <td style="width:40px;"><select  value="${achieve.type}"    style="width:38px;"  type="text" name="type"> 
            <option value="1" <c:if test="${achieve.type==1}" >selected</c:if>>服务</option>
            <option value="2" <c:if test="${achieve.type==2}" >selected</c:if>>工程</option>
            <option value="3" <c:if test="${achieve.type==3}" >selected</c:if>>货物</option>
            </select></td>
            <td style="width:100px;"><input  value="${achieve.entrustUnit}"    style="width:98px;" name="entrustUnit"  maxlength="32"/></td>
            <td style="width:40px;"><input   value="${achieve.entrustMoney}"    style="width:38px;" name="entrustMoney"  maxlength="10"/></td>
            <td style="width:30px;"><input   value="${achieve.bidMoney}"    style="width:28px;" name="bidMoney"  maxlength="10"/></td>
            <td style="width:50px;"><input   value="<fmt:formatDate value='${achieve.tenderOpenTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"    style="width:48px;" name="tenderOpenTime"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></td>
            <td style="width:50px;"><input   value="<fmt:formatDate value='${achieve.bidTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"    style="width:48px;" name="bidTime"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></td>
            <td style="width:50px;"><input   value="${achieve.noticeMedia}"   maxlength="16"  style="width:48px;" name="noticeMedia"  /></td>
            <td style="width:50px;"><input   value="<fmt:formatDate value='${achieve.noticeDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"     style="width:48px;" name="noticeDate"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></td>
            <td style="width:50px;"><input     type="submit"  value="保存" />&nbsp;&nbsp;<input type="button" value="删除"/></td>
                </tr>
               
            </table>
            </form>
            </c:forEach>
            <!-- 
            <input type="button" class="zjyh" value="+增加一行" />  -->
            <div class="xieyi"><input type="checkbox" class="checkbox" />&nbsp;阅读并接受<a href="#">《中央投资项目服务与管理平台用户协议》</a></div>
            <div class="login_kuang_button">
                 <input type="button" class="button" onclick="fgwEntry()" value="保  存  修 改" />
            </div>
            <div class="clear"></div>
            <script>
              function fgwEntry(){
            	 
            	 /* $.ajax({
                      async:true,
                      url:ctxFront+"/org/org/fgwEntry",
                      type:"post",
                      success:function(data){
                          $("#pageContaienr").html(data);
                           
                      }
                  });*/
            	  window.location.href=ctxFront+"/org/org/fgwEntry";
              }
            </script>
        </div>
    </div>
    <div class="clear"></div>
</div>
</div>
<%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
</body>
</html>