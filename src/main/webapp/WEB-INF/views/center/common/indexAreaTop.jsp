<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
	<script type="text/javascript" src='${ctxStatic}/js/static/echarts.js'></script>
	<script type="text/javascript" src='${ctxStatic}/js/static/jquery-1.9.1.min.js' ></script>
	<script type="text/javascript">
	var myChart;
	$(document).ready(function() {
   		myChart = echarts.init(document.getElementById('chart'));
		loadMap();		
		
	});
	
	/**
   	加载地图：根据地图所在省市的行政编号，
   	获取对应的json地图数据，然后向echarts注册该区域的地图
   	最后加载地图信息
   	@params {String} mapCode:地图行政编号,for example['中国':'100000', '湖南': '430000']
   	@params {String} mapName: 地图名称
	*/
	function loadMap(mapCode, mapName) {
   		//alert('${ctxStatic}/js/static/china.json');
   		//alert('开始加载地图！');
	    $.getJSON('${ctxStatic}/js/static/china.json', function (data) {
	         if (data) {
	             //向echarts插件注册地图
	             echarts.registerMap(mapName, data);
	             var option = {
	                tooltip: {
	                    trigger: 'item',
	                    formatter: '{b}'
	                },
	                series: [
	                    {
	                        name: '',
	                        type: 'map',
	                        mapType: mapName,
	                        selectedMode : 'single',//'multiple',
	                        label: {
	                            normal: {
	                                show: false
	                            },
	                            emphasis: {
	                                show: true
	                            }
	                        },
	                        data:[
	                        ]
	                    }
	                ]
	             };
	             myChart.setOption(option);
	             curMap = {
	                mapCode: mapCode,
	                mapName: mapName
	             };     
			   	myChart.on('click', function (params) {
			   		//var dataIndex = params.dataIndex;
			   		var dname = params.name;
			   		var dcode;
			   		var did;
			   		switch(dname){
	                    case '北京':
	                        //location.href = urlArr[0];
	                        dcode = '110000';
	                        did = 2; 
	                        break;
	                    case '天津':
	                    	dcode = '120000';
	                    	did = 3;
	                    	break;
	                    case '河北':
	                    	dcode = '130000';
	                    	did = 4;
	                    	break;
	                    case '山西':
	                    	dcode = '140000';
	                    	did = 5;
	                    	break;
	                    case '内蒙古':
	                    	dcode = '150000';
	                    	did = 6;
	                    	break;
	                    case '辽宁':
	                    	dcode = '210000';
	                    	did = 7;
	                    	break;
	                    case '沈阳':
	                    	dcode = '210100';
	                    	did = 34;
	                    	break;
	                    case '大连':
	                    	dcode = '210200';
	                    	did = 35;
	                    	break;
	                    case '吉林':
	                    	dcode = '220000';
	                    	did = 8;
	                    	break;
	                    case '长春':
	                    	dcode = '220100';
	                    	did = 36;
	                    	break;
	                    case '黑龙江':
	                    	dcode = '230000';
	                    	did = 50;
	                    	break;
	                    case '哈尔滨':
	                    	dcode = '230100';
	                    	did = 37;
	                    	break;
	                    case '上海':
	                    	dcode = '310000';
	                    	did = 10;
	                    	break;
	                    case '江苏':
	                    	dcode = '320000';
	                    	did = 11;
	                    	break;
	                    case '南京':
	                    	dcode = '320100';
	                    	did = 38;
	                    	break;
	                    case '浙江':
	                    	dcode = '330000';
	                    	did = 12;
	                    	break;
	                    case '杭州':
	                    	dcode = '330100';
	                    	did = 39;
	                    	break;
	                    case '宁波':
	                    	dcode = '330200';
	                    	did = 40;
	                    	break;
	                    case '安徽':
	                    	dcode = '340000';
	                    	did = 13;
	                    	break;
	                    case '福建':
	                    	dcode = '350000';
	                    	did = 14;
	                    	break;
	                    case '厦门':
	                    	dcode = '350200';
	                    	did = 41;
	                    	break;
	                    case '江西':
	                    	dcode = '360000';
	                    	did = 15;
	                    	break;
	                    case '山东':
	                    	dcode = '370000';
	                    	did = 16;
	                    	break;
	                    case '济南':
	                    	dcode = '370100';
	                    	did = 42;
	                    	break;
	                    case '青岛':
	                    	dcode = '370200';
	                    	did = 43;
	                    	break;
	                    case '河南':
	                    	dcode = '410000';
	                    	did = 17;
	                    	break;
	                    case '湖北':
	                    	dcode = '420000';
	                    	did = 18;
	                    	break;
	                    case '武汉':
	                    	dcode = '420100';
	                    	did = 44;
	                    	break;
	                    case '湖南':
	                    	dcode = '430000';
	                    	did = 19;
	                    	break;
	                    case '广东':
	                    	dcode = '440000';
	                    	did = 20;
	                    	break;
	                    case '广州':
	                    	dcode = '440100';
	                    	did = 45;
	                    	break;
	                    case '深圳':
	                    	dcode = '440300';
	                    	did = 46;
	                    	break;
	                    case '广西':
	                    	dcode = '450000';
	                    	did = 21;
	                    	break;
	                    case '海南':
	                    	dcode = '460000';
	                    	did = 22;
	                    	break;
	                    case '重庆':
	                    	dcode = '500000';
	                    	did = 23;
	                    	break;
	                    case '四川':
	                    	dcode = '510000';
	                    	did = 24;
	                    	break;
	                    case '成都':
	                    	dcode = '510100';
	                    	did = 47;
	                    	break;
	                    case '贵州':
	                    	dcode = '520000';
	                    	did = 25;
	                    	break;
	                    case '云南':
	                    	dcode = '530000';
	                    	did = 26;
	                    	break;
	                    case '西藏':
	                    	dcode = '540000';
	                    	did = 27;
	                    	break;
	                    case '陕西':
	                    	dcode = '610000';
	                    	did = 28;
	                    	break;
	                    case '西安':
	                    	dcode = '610100';
	                    	did = 48;
	                    	break;
	                    case '甘肃':
	                    	dcode = '620000';
	                    	did = 29;
	                    	break;
	                    case '青海':
	                    	dcode = '630000';
	                    	did = 30;
	                    	break;
	                    case '宁夏':
	                    	dcode = '640000';
	                    	did = 31;
	                    	break;
	                    case '新疆':
	                    	dcode = '650000';
	                    	did = 32;
	                    	break;
	                    case '新疆':
	                    	dcode = '650000';
	                    	did = 33;
	                    	break;
	                    default:
	                    	dcode = '000000';
	                    	did = -1;
	                        break;
                	}
			   		on_show(dname, dcode, did);
			    });
	         } else {
	             alert('无法加载该地图');
	         }       
	    });
	}
   	function on_show(s_name, area_code, area_id){
   		$('#s_name').html(s_name);
   		$.ajax({
    		type : "post",
            async:true,
            url: ctxFront+"index/getIndexAreaTop",
            dataType: "json",
            data: {"code":area_code, "id":area_id},
            success: function(data) {
            	//alert(data);
            	if(data['code'] == 200){
            		$("#s_org_count").html(data.body['org_count']);
            		$("#s_project_count").html(data.body['project_count']);
            		$("#s_project_bidMoney").html(data.body['bid_money']);
            	}
            	//on_show_down(area_id);
            	 loadIndexAreaDown(area_id);
            }
    	})
   	}
   	<%--
   	function on_show_down(area_id){
   		$.ajax({
    		type : "post",
            async: true,
            url: ctxFront+"index/getIndexAreaDown?areaId="+area_id,
            success: function(data) {
            	//alert(data);
            	$('#area_down').html(data);
            }
    	})
   	}
   	--%>
</script>
	<!-- 首页区域统计 -->    
	<div id="show_div" style="margin-left: 16px; line-height:1.3em;margin-top: 0px;" >
		<font style="color: #777;">中标金额: </font><b style="color: #ff8417;" id="s_project_bidMoney">0</b>亿元
		<font style="margin-left:20px;color: #777;">项目数量: </font><b style="color: #ff8417;" id="s_project_count">0</b>&nbsp;&nbsp;
		<br/><font style="color: #777;">所属区域: </font><b style="color: #ff8417;" id="s_name">全国</b><br/>
		
		
	</div> 
    <div id="chart" style="width:300px;height:236px; padding-top: 0px;"></div>
 
    <!-- 
    <div id="area_down">
    	<script>
    		$(function(){
    			on_show_down('0');
    		})
    	</script>
    </div>
     -->
    