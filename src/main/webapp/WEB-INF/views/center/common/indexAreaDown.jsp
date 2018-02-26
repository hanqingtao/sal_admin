<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<script type="text/javascript" src='${ctxStatic}/js/static/echarts.js'></script>
	<script type="text/javascript" src='${ctxStatic}/js/static/jquery-1.9.1.min.js' ></script>
	<script type="text/javascript">
		var myChart2 = echarts.init(document.getElementById('chartDown'));
		option2 = {
		    title : {
		        text: '${year}年${month}月地区统计',
		         subtext: '单位：亿元',
		         textStyle:{
			    	 fontSize: 15
			    	    
			    },
			    subtextStyle:{
			    	align:'center', 
			    	 color: '#000',
			    	 fontSize: 10,
			    	 
			    }      
		    },
		    
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		         //data:['项目数量','中标金额']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true}
		            //dataView : {show: true, readOnly: false},
		            //magicType : {show: true, type: ['line', 'bar']},
		            //restore : {show: true},
		            //saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            //boundaryGap: true,
		           /* splitArea : {
		                show: true,
		                areaStyle:{
		                    color:['rgba(144,238,144,0.3)','rgba(135,200,250,0.3)']
		                }
		            },*/
		            data : ['第一周','第二周','第三周','第四周']
		        
		        }
		    ],
		    yAxis : [
		        {
		        	 
		            type : 'value',
		            axisLabel : {
		                show:true,
		               
		              
		                textStyle: {
		                   
		                    fontSize: 10,
		                   
		                }
		            },
		          
		           /* splitArea : {
		                show: true,
		                areaStyle:{
		                    color:['rgba(144,238,144,0.3)','rgba(135,200,250,0.3)']
		                }
		            },*/
		        }
		    ],
		   
		    
		    series : [
		        {
		            name:'项目数量',
		            type:'bar',
					itemStyle: {normal: {
		                label : {show: true, position:'top'}
		            }},
		            data:[${projectCountStr }],
		            markPoint : {
		                data : [
		                    //{type : 'max', name: '最大值'},
		                    //{type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    //{type : 'average', name: '平均值'}
		                ]
		            }
		        },
		        {
		            name:'中标金额',
		            type:'bar',
		            itemStyle: {normal: {
		                label : {show: true, position:'top'}
		            }},
		            data:[${bidMoneyStr }],
		            markPoint : {
		                data : [
		                    //{name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
		                    //{name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
		                ]
		            },
		            markLine : {
		                data : [
		                    //{type : 'average', name : '平均值'}
		                ]
		            }
		        }
		    ]
		};
		myChart2.setOption(option2);
	</script>
    <div id="chartDown" style="margin-left:16px; width:250px;height:186px;   padding-top: 0px;line-height:2em; margin-top: -10px; "></div>
    
    