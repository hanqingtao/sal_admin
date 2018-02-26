 $('#tabTotal  p').click(function() {
         var i = $(this).index();
         if(i=="2"){
        	 //企业性质
        	 $.ajax({
        	        type : "post",
        	        async:true,
        	        url : ctxFront + "/etc/term",
        	        data : { "option" : "org_nature" },
        	        dataType : "json",
        	        success : function(dataJson) {
        	        	console.log(dataJson);
        	            var str="";
        	              for(var i=0;i<dataJson.length;i++){
        	                  str+="<p data="+dataJson[i].value+" onclick='natureInjection(this)'>"+dataJson[i].label+"</p>";
        	              }
        	              $("#natureTitleContainer").html(str); 
        	              $("#natureTitleContainer").find("p").first().attr("class","on");
        	            
        	             $('#natureTitleContainer p').click(function() {
        	                  var i = $(this).index();
        	                  $(this).addClass('on').siblings().removeClass('on');
        	                   $('#natureTitleContentContainer ul').eq(i).show().siblings().hide();
        	              });
        	                
        	        }
        	    });
         }else if(i=="1"){
        	 //地区
        	 $.ajax({
        	        type : "post",
        	        async:true,
        	        url : ctxFront + "/etc/area",
        	        data : { "option" : "" },
        	        dataType : "json",
        	        success : function(dataJson) {
        	        	console.log(dataJson);
        	            var str="";
        	              for(var i=0;i<dataJson.length;i++){
        	                  str+="<p data="+dataJson[i].code+" onclick='areaInjection(this)'>"+dataJson[i].name+"</p>";
        	              }
        	              $("#areaTitleContainer").html(str); 
        	              $("#areaTitleContainer").find("p").first().attr("class","on");
        	              
        	              //招标业绩统计里的企业性质tab切换
        	             $('#areaTitleContainer p').click(function() {
        	                  var i = $(this).index();
        	                  $(this).addClass('on').siblings().removeClass('on');
        	                   $('#areaTitleContentContainer ul').eq(i).show().siblings().hide();
        	              }); 
        	              
        	        }
        	    });
         }
         $(this).addClass('on').siblings().removeClass('on');
        $('#tabContentContainer dl').eq(i).show().siblings().hide();
     });
 
 
 function natureInjection(obj){
	 $("#nature").val($(obj).attr("data"));
 }
 function areaInjection(obj){
	 $("#area").val($(obj).attr("data"));
 }