
function indexStatic_com(type, param){
	 $.ajax({
		 type:"post",
		 async:true,
		 url:ctxFront + "/index/getOrgStaticForIndex",
		 data:{"type":type, "param":param},
		 dataType:"json",
		 success:function(dataJson){
			 var str="";
			 if(dataJson['code'] == 200){
				 if(dataJson['body'] == undefined || dataJson['body'] == 'undefined' || dataJson['body'] == null || dataJson['body'] == ''){
					 str = "<li>暂无统计信息...</li>";
				 }else{
					 for(var i = 0; i < dataJson['body'].length; i++){
						 str+="<li><p><a href='javascript:lookOrgDetails("+dataJson['body'][i].id+");' title='点击查看详细信息' >"+dataJson['body'][i].name+"</a></p><span>"+dataJson['body'][i].bid_money+"（万元）</span></li>";
					 }
				 }
			 }else{
				 str = "<li>信息加载失败...</li>";
			 }
             $("#div_ul_"+type).html(str); 
		 }
	 })
 }

$(function(){ 
   

        $('input, textarea').placeholder();

   
 
	//获取首页顶部轮播信息
	$.ajax({
        type : "post",
        async:true,
        url : ctxFront + "/cms/article/img",
        data : {  "posid":"1","category.id":""},
        dataType : "json",
        success : function(data) {
        	  
        	 var content="";
        	    
        	 for(var i=0;i<data.length;i++){
        		 
        		 
        		 content+="<li style='background:url("+imgServer+data[i].image+") no-repeat center top;'><a href='javascript:lookTuNewsDetails("+data[i].category.id+","+data[i].id+");'></a></li>";
        	 }
        	  
        	 
        	 $("#bannerContainer").html(content);
        	
        	$('#Flexslider').flexslider({

        			//directionNav:true,

        			//pauseOnAction:false

        		});
        	
        	
        }
    });
	
	$("#scrollbox1").on('click', 'p', function(e){  
		$('#scrollbox1 p').removeClass('on');
	    $(e.target).addClass('on'); 
	})
	
	$("#div_p_year").on('click', 'p', function(e){  
		$('#div_p_year p').removeClass('on');
	    $(e.target).addClass('on');
	})
	
	$("#natureTitleContainer").on('click', 'p', function(e){  
		$('#natureTitleContainer p').removeClass('on');
	    $(e.target).addClass('on'); 
	})
	

	
	
	$.ajax({
        type : "post",
        async:true,
        url : ctxFront + "/cms/article/img",
        data : {  "posid":"2","category.id":""},
        dataType : "json",
        success : function(dataJson) {
        	
            var str="";
         
            for(var i=0;i<dataJson.length;i++){
             
           	  str+="<li><a href='javascript:;'   onclick='lookTuNewsDetails("+dataJson[i].category.id+","+dataJson[i].id+")' ><img src="+imgServer+dataJson[i].image+" width='348' height='271' /></a><div class='boxbg'><span title="+dataJson[i].title+">"+dataJson[i].title+"</span> </div></li>";
           	  
            }
            
            $("#screenContainer").html(str);
            
            
            
            var box  = document.getElementById("all");  //   获得大盒子
        	var ul = box.children[0].children[0];  // 获取ul
        	var ulLis = ul.children;  //  ul 里面的所有  li
        	// 复习：  cloneNode()     克隆节点       追加a.appendChild(b)  把b 放到a 的最后面
        	//1.  ulLis[0].cloneNode(true)  克隆  节点
        	ul.appendChild(ulLis[0].cloneNode(true));   // ul 是 a   ulLis[0].cloneNode(true) 是b

        	//2. 插入 ol 里面的li
        	var ol = box.children[1];  // 获得ol
        	// 因为 我们要创建很多个 ol 里面的li 所以需要用到for 循环哦
        	for(var i=0;i<ulLis.length-1;i++) {   // ul 里面的li  长度 要减去 1  因为我们克隆一个
        		// 创建节点 li
        		var li = document.createElement("li");
        		li.innerHTML = "";   //  存在加1 的关系
        		// a.appendChild(b);
        		ol.appendChild(li);
        	}
        	var olLis = ol.children;  // 找到 ol 里面的li
        	olLis[0].className = 'current';
        	// 3. 动画部分  遍历小li ol
        	for(var i=0;i<olLis.length;i++) {
        		olLis[i].index = i;  // 赋予索引号
        		olLis[i].onmouseover = function() {
        			// 清除所有人
        			for(var j=0;j<olLis.length;j++) {
        				olLis[j].className = "";
        			}
        			this.className = 'current';
        			animate(ul,-this.index*ulLis[0].offsetWidth);
        			key = square = this.index; // 鼠标经过 key square 要等于 当前的索引号
        		}
        	}
           // 4. 定时器部分  难点
        	var timer = null;  // 定时器
        	var key = 0; // 用来控制图片的播放的
        	var square = 0;  // 用来控制小方块的
        	timer = setInterval(autoplay,3000);   // 每隔3s 调用一次 autoplay
        	function autoplay() {
        		key++;   // key == 1  先 ++
        		//console.log(key); //  不能超过5
        		if(key > ulLis.length - 1)
        		{
        		   // alert('停下');
        			ul.style.left = 0;
        			key = 1;  // 因为第6张就是第一张，我们已经播放完毕了， 下一次播放第2张
        			// 第2张的索引号是1
        		}
        		animate(ul,-key*ulLis[0].offsetWidth);
        		// 小方块的做法
        		square++;  // 小方块自加1
        		square = square>olLis.length-1 ? 0 : square;
        		/// 清除所有人
        		for(var i=0;i<olLis.length;i++) {
        			olLis[i].className = "";
        		}
        		olLis[square].className = "current";   // 留下当前自己的

        	}


        	// 鼠标经过和停止定时器
        	box.onmouseover = function() {
        		clearInterval(timer);
        	}

        	box.onmouseout = function() {
        		timer = setInterval(autoplay,3000);  // 一定这么开
        	}



        	//  基本封装
        	function animate(obj,target) {
        		clearInterval(obj.timer);  // 要开启定时器，先清除以前定时器
        		// 有2个参数 第一个 对象谁做动画  第二 距离 到哪里
        		// 如果 offsetLeft 大于了  target 目标位置就应该反方向
        		var speed = obj.offsetLeft < target ? 15 : -15;
        		obj.timer = setInterval(function() {
        			var result = target - obj.offsetLeft;  //  他们的值 等于 0 说明完全相等
        			// 动画的原理
        			obj.style.left = obj.offsetLeft + speed  + "px";
        			if(Math.abs(result) <= 15) {
        				obj.style.left = target + "px";   //抖动问题
        				clearInterval(obj.timer);
        			}
        		},10);
        	}
            
        }
    });
	
	 
	$.ajax({
        type : "post",
        async:true,
        url : ctxFront + "/cms/article/title",
        data : {  "inMenu":"1","site.id":"1","parent.id":"1"},
        dataType : "json",
        success : function(dataJson) {
        	console.log(dataJson);
            var str="";
            var ulStr="";
            var length=dataJson.length;
            for(var i=0;i<length;i++){
           	 str+="<p title="+dataJson[i].name+" data="+dataJson[i].id+">"+dataJson[i].name+"</p>";
           	 ulStr+="<ul></ul>";
            }
            $("#newsTitle").html(str);
            $("#newsContainer").html(ulStr);
            $("#newsTitle").find("p").first().attr("class","on");
            
            getNewsContent($("#newsTitle").find("p").first().attr("data"),"0")
            //新闻tab切换    
     	   $('#newsTitle  p').click(function() {
     	           var i = $(this).index();
     	           var n=$(this).attr("data");
	            	getNewsContent(n,i);
     	           $(this).addClass('on').siblings().removeClass('on');
     	           $('#newsContainer ul').eq(i).show().siblings().hide();
     	       });
        }
    });
	
	 
	
	
	
	 //首先加载全国名录
	$.ajax({
        type : "post",
        async:true,
        url : ctxFront + "/org/org/all",
        data : {  "area":""},
        dataType : "json",
        success : function(dataJson) {
            var str="";
            for(var i=0;i<dataJson.length;i++){
           	 str+="<li><p><a href='#'>"+dataJson[i].name+"</a></p><span>23092</span></li>";
            }
            $("#wholeCountry").html(str);
        }
    });
	
	 
	   
	
	   function getNewsContent(id,num){
			   $.ajax({
		        type : "post",
		        async:true,
		        url : ctxFront + "/cms/article/Seven",
		        data : {  "categoryId":id,"delFlag":"1"},
		        dataType : "json",
		        success : function(dataJson) {
		            var str="";
		            for(var i=0;i<dataJson.length;i++){
		           	 str+="<li><p><a  title="+dataJson[i].title+" href='javascript:lookTuNewsDetails("+dataJson[i].category.id+","+dataJson[i].id+");'>"+dataJson[i].title+"</a></p><span>"+dataJson[i].updateDate+"</span></li>";
		            }
		            $($("#newsContainer").find("ul")[num]).html(str);
		  
		        }
		    });
	   }
	   //提示登录
	   if($("#message").html().length>0){
		   alert($("#message").html());
	   }
	   
	
	   //招标业绩统计tab切换
	   $('#performanceStatisticsTtileContainer p').click(function() {
           var i = $(this).index();
           if(i=="0"){
        	   
           }else if(i=="1"){
        	   
           }else if(i=="2"){
        	   
        	   $.ajax({
                   type : "post",
                   async:true,
                   url : ctxFront + "/etc/term",
                   data : { "option" : "centralinvest_type" },
                   dataType : "json",
                   success : function(dataJson) {
                	   console.log(dataJson);
                       var str="";
                         for(var i=0;i<dataJson.length;i++){
                             str+="<p>"+dataJson[i].label+"</p>";
                         }
                         $("#scrollbox2").html(str); 
                         $("#scrollbox2").find("p").first().attr("class","on");
                         
                         //招标业绩统计里的企业性质tab切换
                         $('#scrollbox2 p').click(function() {
                             var i = $(this).index();
                             if(i=="0"){
                            	 
                             }else if(i=="1"){
                            	  
                             }else if(i=="2"){
                            	  
                             }else if(i=="3"){
                            	   
        	                  	
                             }
                             $(this).addClass('on').siblings().removeClass('on');
                             $('#sourceOfFundsContentContainer ul').eq(i).show().siblings().hide();
                         });
                         
                   }
               });
           }else if(i=="3"){
        	   natureCom(1,"StateOwnedContaienr");
        	   
           }
           $(this).addClass('on').siblings().removeClass('on');
           $('#performanceStatisticsContentContainer dl').eq(i).show().siblings().hide();
       });
	   
	   
	   
	   //加载地区
	 $.ajax({
           type : "post",
           async:true,
           url : ctxFront + "/etc/area",
           data : { "option" : "" },
           dataType : "json",
           success : function(dataJson) {
               	var str="";
                 for(var i=0;i<dataJson.length;i++){
                     str+="<p onclick=\"javascript:indexStatic_com('area','"+dataJson[i].id+"')\">"+dataJson[i].name+"</p>";
                 }
                 $("#scrollbox1").html(str); 
                 $("#scrollbox1").find("p").first().attr("class","on");
                 
                 $('#scrollbox1').find('p').first().click();
                 onloadScroll_Area();
                 /*$('#scrollbox1 p').click(function() {
                     var i = $(this).index();
                     if(i=="0"){
                    	 
                     }else if(i=="1"){
                    	  
                     }else if(i=="2"){
                    	  
                     }else if(i=="3"){
                    	   
	                  	
                     }
                     $(this).addClass('on').siblings().removeClass('on');
                     $('#areaOrgContentContainer ul').eq(i).show().siblings().hide();
                 });*/
           }
       }); 
	   
	 	function onloadScroll_Area(){
	 		var scrollPic_01 = new ScrollPic();
			scrollPic_01.scrollContId   = "scrollbox1"; //内容容器ID
			scrollPic_01.arrLeftId      = "arrLeft1";//左箭头ID
			scrollPic_01.arrRightId     = "arrRight1"; //右箭头ID
			scrollPic_01.frameWidth     = 690;//显示框宽度
			scrollPic_01.pageWidth      = 690; //翻页宽度
			scrollPic_01.speed          = 8; //移动速度(单位毫秒，越小越快)
			scrollPic_01.space          = 20; //每次移动像素(单位px，越大越快)
			scrollPic_01.autoPlay       = false; //自动播放
			scrollPic_01.autoPlayTime   = 3; //自动播放间隔时间(秒)
			scrollPic_01.initialize(); //初始化
	 		
	 	}
	 
	 
	   function natureCom(num,org){
		   $.ajax({
               type : "post",
               async:true,
               url : ctxFront + "/org/org/nature",
               data : {  "nature":num},
               dataType : "json",
               success : function(dataJson) {
                   var str="";
                   for(var i=0;i<dataJson.length;i++){
                  	 str+="<li><p><a href='#'>"+dataJson[i].name+"</a></p><span>23092</span></li>";
                   }
                   $("#"+org).html(str);
               }
           });
	   }
	   
	    //加载企业性质
	   $.ajax({
           type : "post",
           async:true,
           url : ctxFront + "/etc/term",
           data : { "option" : "org_nature" },
           dataType : "json",
           success : function(dataJson) {
               var str="";
                 for(var i=0;i<dataJson.length;i++){
                     str+="<p onclick=\"javascript:indexStatic_com('nature','"+dataJson[i].value+"')\">"+dataJson[i].label+"</p>";
                 }
                 $("#natureTitleContainer").append(str); 
                 $("#natureTitleContainer").find("p").first().attr("class","on");
               
               /*//招标业绩统计里的企业性质tab切换
                 $('#natureTitleContainer p').click(function() {
                     var i = $(this).index();
                     if(i=="0"){
                    	 natureCom(1,"StateOwnedContaienr");
                     }else if(i=="1"){
                    	 natureCom(2,"privateEnterpriseContaienr");
                     }else if(i=="2"){
                    	 natureCom(3,"publicSectorContaienr");
                     }else if(i=="3"){
                    	 natureCom(4,"foreignEnterpriseContaienr");
	                  	
                     }
                     $(this).addClass('on').siblings().removeClass('on');
                     $('#natureTitleContentContainer ul').eq(i).show().siblings().hide();
                 });*/
                   
           }
       });
	 
	   
	   
  })

  
  
  //招标业绩统计省份切换
  function findOrgInfoByArea(obj,arg){
	$(obj).siblings().removeClass('on');
	$(obj).attr("class","on");
    $('#'+arg).show().siblings().hide();
	
  } 

//更多名录
function moreAgent(){
	$.ajax({
        async:true,
        url:ctxFront+"/agent",
        type:"post",
        success:function(data){
            $("#pageContaienr").html(data);
            footerCom('footer_agent');
            footerCom('nav_agent');
            $("#searchAgentInfn").click();
        }
    });
}
//更多中央投资项目
function moreProject(){
	$.ajax({
        async:true,
        url:ctxFront+"/pro",
        type:"post",
        success:function(data){
            $("#pageContaienr").html(data);
            footerCom('nav_project');
            footerCom('footer_project');
        }
    });
}
//首页进入代理机构
function agentInfoEnter(){
	  $.ajax({
          async:true,
          url:ctxFront+"/org/org/search",
          type:"post",
          success:function(data){
              $("#pageContaienr").html(data);
              footerCom('nav_agent');
              footerCom('footer_agent');
          }
      });
}

//查看机构详情
function lookOrgDetails(id){
	  $.ajax({
      async:true,
      url:ctxFront+"/org/org/details?id="+id,
      type:"post",
      success:function(data){
          $("#pageContaienr").html(data);
           
      }
  });
}
//查看项目详情
function lookProjectDetails(id){
	$.ajax({
      async:true,
      url:ctxFront+"/pro/project/details?id="+id,
      type:"post",
      success:function(data){
          $("#pageContaienr").html(data);
           
      }
  });
}


//资质管理入口
function qualificationManagementEntrance(){
	 $("body").load(ctxFront +"/org/org/enter",{"qualificationManagement":"qualificationManagement"});
};

//更多新闻
function moreNews(){
	var categoryId=$("#newsTitle").find("p").first().attr("data");
	
	 window.open(ctxFront+"/news?categoryId="+categoryId+"&id="+0);
}
//查看新闻详情
function lookTuNewsDetails(categoryId,id){
	 window.open(ctxFront+"/news?categoryId="+categoryId+"&id="+id);
}
/**    
 *新代理机构注册入口 
 */
function register(){
	  
	  window.location.href=ctxFront+"/org/org/entry";
	  
  } 
    
    
 /**   
  * 管理员入口
  */
 function adminOpen(){
  	  window.location.href=ctx;
    }
 

    
/**
 * 首页轮播
 */
window.onload= function() {
	loadIndexAreaTop();
	
	loadIndexAreaDown('0');
	
	
	
	
}


function loadIndexAreaTop(){
	$.ajax({
		type : "post",
        async: true,
        url: ctxFront+"/index/toIndexAreaTop",
        success: function(data) {
        	$('#div_area_top').html(data);
        	on_show("全国",10000,0);
        }
	})
}

function loadIndexAreaDown(area_id){
	$.ajax({
		type : "post",
        async: true,
        url: ctxFront+"/index/getIndexAreaDown?areaId="+area_id,
        success: function(data) {
        	$('#div_area_down').html(data);
        }
	})
}
