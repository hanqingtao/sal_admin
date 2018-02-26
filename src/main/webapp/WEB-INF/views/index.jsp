<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/center/common/meta.jsp"%> 
 <script type="text/javascript" src="${ctxStatic}/js/jquery.flexslider-min.js" ></script>
 <script type="text/javascript" src="${ctxStatic}/js/index.js"></script>
 <script type="text/javascript" src="${ctxStatic}/js/Designer.js"></script>
 <head>
    <meta name="renderer" content="webkit">
 </head>
<body style="background:#fff;">
<span id="message" class="uploadFile">${message }</span>
<div class="header">
	<%@ include file="/WEB-INF/views/center/common/top.jsp"%> 
    <%@ include file="/WEB-INF/views/center/common/nav.jsp"%> 
</div>
 <div id="pageContaienr">
    <div id="test"></div>
   <div id="Flexslider" class="flexslider">
             <ul class="slides" id="bannerContainer">
                 
                 
             </ul>
        </div>
 
<div class="main">
    <div class="all" id='all'>
        <div class="screen">
            <ul id="screenContainer"></ul>
        </div>
         <ol >
        </ol>
    </div>
    <div class="gonggao">
        <div class="top" id="newsTitleContainer">
            <div id="newsTitle"></div>
            <a target="_blank" href="javascript:moreNews();">更多>></a>
      </div>
       <div class="newsContainerContent" id="newsContainer">
        </div>
    </div>
	<div class="denglu">
        <div class="top">
            <p class="on">我要登录</p>
        </div>
        <input type="button" class="button1"  value="代理机构入口"  onclick="qualificationManagementEntrance()"/>
        <input type="button" class="button2"  value="管理员入口"  onclick="adminOpen()"/>
        <input type="button" class="button3"  value="新机构快速注册入口" onclick="register()"/>
        <input type="button" class="button4"  value="代理机构信息公示" onclick="agentInfoEnter()"/>
    </div>
    <div class="clear"></div>
    <div class="banner1"></div>
    <div class="indexxwdl left">
    	<div class="top">
            <p class="on">名录公示</p>
            <a  href="javascript:moreAgent();">更多>></a>
        </div>
        <dl>
         <c:forEach items="${orgList}" var="org">
        	<dt><a href="javascript:lookOrgDetails(${org.id});" >${org.name }</a></dt>
            <dd> 近三年业绩：<fmt:formatNumber value="${org.recentMoney}" pattern="#,#00.0#"/><c:if test="${not empty org.recentMoney}"> 万元</c:if>　 　变更时间：<fmt:formatDate value="${org.createDate}" pattern="yyyy-MM-dd"/>　　 　所在区域：${org.area.name} </dd>
		 </c:forEach> 
        </dl>
    </div> 
    <div class="indexxwdl right">
    	<div class="top">
            <p class="on">项目公示</p>
            <a href="javascript:moreProject();">更多>></a>
        </div>
        <dl>
              <c:forEach items="${projectList}" var="project">
        	<dt><a href="javascript:;" onclick="lookProjectDetails(${project.id})">${project.name}</a></dt>
            <dd> 中标金额：<fmt:formatNumber value="${project.projectFlow.bidMoney }" pattern="#,#00.0#"/><c:if test="${not empty project.projectFlow.bidMoney}"> 万元</c:if>　　　　中标单位：${project.projectFlow.bidUnit }　　  　　　中标时间：<fmt:formatDate value="${project.bidTime}" pattern="yyyy-MM-dd"/>　 　　　 </dd>
			 </c:forEach>
        </dl>
    </div>
    <div class="tongji_left">
    	<div class="top">
        	<p class="on">招标业绩统计</p><span>（统计数据为中央投资项目）</span>
        </div>
        <div class="left_nav" id="performanceStatisticsTtileContainer">
       		<p class="on" onclick="javascript: $('#scrollbox1').find('p').first().click();">地区</p>
        	<p onclick="javascript: $('#div_p_year').find('p').first().click();" >中标金额</p>
            <p onclick="javascript: $('#natureTitleContainer').find('p').first().click();" >企业性质</p>
        </div>
        
        <div id="performanceStatisticsContentContainer">
	          <dl >
	                <div class="jian_left"><img src="${ctxStatic}/images/leftjt1.png" id="arrLeft1" /></div>
			        <div class="right_nav" id="scrollbox1">
			        </div>
			        <div class="jian_right"><img src="${ctxStatic}/images/rightjt1.png" id="arrRight1"/></div>
			        <div id="areaOrgContentContainer"></div>
			        <div>
			        	<ul id="div_ul_area">
				        	<li>地区业绩统计数据加载中...</li>
				        </ul>
			        </div>
	         </dl> 
	          <dl style="display:none">
			        <div class="right_nav" id="div_p_year">
			        	<p onclick="javascript:indexStatic_com('year','2017')" >2017</p>
			        	<p onclick="javascript:indexStatic_com('year','2016')" >2016</p>
			        	<p onclick="javascript:indexStatic_com('year','2015')" >2015</p>
			        	<p  onclick="javascript:indexStatic_com('year','2014')">2014</p>
			        	 
			        </div>
			        <div>
				 		<ul id="div_ul_year">
							<li>中标金额统计数据加载中...</li>
				        </ul>
			        </div>
	         </dl>  
	           
	          <dl style="display:none">
			        <div class="right_nav" id="natureTitleContainer"></div>
			        <div>
				 		<ul id="div_ul_nature">
				        	<li>企业性质统计数据加载中...</li>
				        </ul>
			        </div>
	         </dl> 
         </div>
    </div>
    <div class="tongji_right">
    	<div class="top"><p class="on">区域统计</p></div>
    	<div id="div_area_top">
    	</div>
    	<div id="div_area_down">
    	</div>
    </div>
    <div class="clear"></div>
    <div class="youlian">
    	<div class="top">
            <p class="on">友情链接</p>
        </div>
        <a href="http://www.miit.gov.cn/" target="_blank">中华人民共和国工业和信息化部</a>
     	<a href="http://www.ndrc.gov.cn/" target="_blank">中华人民共和国国家发展和改革委员会</a>
     	<a href="http://www.mofcom.gov.cn/" target="_blank">中华人民共和国商务部</a>
     	<a href="http://www.mohurd.gov.cn/" target="_blank">中华人民共和国住房和城乡建设部</a>
     	<a href="http://www.mof.gov.cn/index.htm" target="_blank">中华人民共和国财政部</a>
     	<a href="http://www.365trade.com.cn/" target="_blank">中招联合信息股份有限公司</a>
     	<a href="http://www.cec.gov.cn/" target="_blank">中国招标投标网</a>
        <div class="clear"></div>
    </div>
</div>
 </div>
 
   <%@ include file="/WEB-INF/views/center/common/footer.jsp"%>
   
 </body>
</html>
