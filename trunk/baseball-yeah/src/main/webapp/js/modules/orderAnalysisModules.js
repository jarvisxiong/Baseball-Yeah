define(['base'], function (base) {
	
	return {
		init: function (args) {
			$.post("/order/analysis/getOrder",{},function(data){
				if(null != data && '' != data){
					$("#orderNum", args).html(data.orderNum);
					$("#orderNumQoq", args).html(data.orderNumQoq);
					$("#orderNumYoy", args).html(data.orderNumYoy);
					$("#winnerNum", args).html(data.winnerNum);
					$("#shippingAbilityNum", args).html(data.shippingAbilityNum);
					$("#orderSourceWeixin", args).html(data.orderSourceWeixin);
					$("#orderSourceSms", args).html(data.orderSourceSms);
					$("#orderSourceOther", args).html(data.orderSourceOther);
					$("#orderTypeTake", args).html(data.orderTypeTake);
					$("#orderTypeSend", args).html(data.orderTypeSend);
					$("#takeOrderMinsAvg", args).html(data.takeOrderMinsAvg);
					$("#takeOrderMins", args).html(data.takeOrderMins);
				}
			},"json");

			var option1 = {
				    title: {
				        text: '今日订单监控图表'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['下单量','接单量','支付金额','微信','支付宝','现金','指端支付']
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            magicType: {type: ['line', 'bar']},
				            restore: {},
				            saveAsImage: {}
				        }
				    },
				    xAxis:  {
				        type: 'category',
				        boundaryGap: false,
				        data: []
				    },
				    yAxis: [
				        {
				            type: 'value',
				            axisLabel: {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series: []
				};
			var chart1 = echarts.init(document.getElementById('chart1', args), 'macarons');
			chart1.setOption(option1);
			$.post("/order/analysis/getOrderByHours",{},function(data){
				var xAxis = [];
				var orderNums = [];
				var takeOrderNums = [];
				var payNums= [];
				var wechatOrderCounts = [];
				var alipayOrderCounts = [];
				var cashOrderCounts = [];
				var zhiduanOrderCounts = [];
				for(var i=0; i<data.length; i++){
					xAxis.push(data[i].hour);
					orderNums.push(data[i].orderNum);
					takeOrderNums.push(data[i].takeOrderNum);
					payNums.push(data[i].payNum);
					wechatOrderCounts.push(data[i].wechatOrderCount);
					alipayOrderCounts.push(data[i].alipayOrderCount);
					cashOrderCounts.push(data[i].cashOrderCount);
					zhiduanOrderCounts.push(data[i].zhiduanOrderCount);
				}
				
				chart1.setOption({
			        xAxis: {
			            data: xAxis
			        },
			        series: [
						{
						    name:'下单量',
						    type:'line',
						    data:orderNums
						},
						{
						    name:'接单量',
						    type:'line',
						    data:takeOrderNums
						},{
						    name:'支付金额',
						    type:'line',
						    data:payNums
						},{
						    name:'微信',
						    type:'line',
						    data:wechatOrderCounts
						},{
						    name:'支付宝',
						    type:'line',
						    data:alipayOrderCounts
						},{
						    name:'现金',
						    type:'line',
						    data:cashOrderCounts
						},{
							name:'指端支付',
							type:'line',
							data:zhiduanOrderCounts
						}
					]
			    });
			},"json");

			var option2 = {
				    title: {
				        text: '订单量监控图表'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['支付金额','运力','工作小派','订单量']
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            magicType: {type: ['line', 'bar']},
				            restore: {},
				            saveAsImage: {}
				        }
				    },
				    xAxis:  {
				        type: 'category',
				        boundaryGap: false,
				        data: []
				    },
				    yAxis: [
				        {
				            type: 'value',
				            axisLabel: {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series: []
				};
			var chart2 = echarts.init(document.getElementById('chart2', args), 'macarons');
			chart2.setOption(option2);
			$.post("/order/analysis/getOrderAmountByTenDay",{},function(data){
				var xAxis = [];
				var payNums= [];
				var shippingAbilityNums = [];
				var orderNums = [];
				var winnerNums = [];
				for(var i=0; i<data.length; i++){
					xAxis.push(data[i].day);
					payNums.push(data[i].payNum);
					shippingAbilityNums.push(data[i].shippingAbilityNum);
					orderNums.push(data[i].orderNum);
					winnerNums.push(data[i].winnerNum);
				}
				
				chart2.setOption({
			        xAxis: {
			            data: xAxis
			        },
			        series: [
						{
						    name:'支付金额',
						    type:'line',
						    data:payNums
						},
						{
						    name:'运力',
						    type:'line',
						    data:shippingAbilityNums
						},{
						    name:'工作小派',
						    type:'line',
						    data:winnerNums
						},{
						    name:'订单量',
						    type:'line',
						    data:orderNums
						}
					]
			    });
			},"json");

			var option3 = {
				    title: {
				        text: '订单量监控图表'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['代取件','代寄件']
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            magicType: {type: ['line', 'bar']},
				            restore: {},
				            saveAsImage: {}
				        }
				    },
				    xAxis:  {
				        type: 'category',
				        boundaryGap: false,
				        data: []
				    },
				    yAxis: [
				        {
				            type: 'value',
				            axisLabel: {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series: []
				};
			var chart3 = echarts.init(document.getElementById('chart3', args), 'macarons');
			chart3.setOption(option3);
			$.post("/order/analysis/getOrderTypeByTenDay",{},function(data){
				var xAxis = [];
				var orderTypeTakes= [];
				var orderTypeSends = [];
				for(var i=0; i<data.length; i++){
					xAxis.push(data[i].day);
					orderTypeTakes.push(data[i].orderTypeTake);
					orderTypeSends.push(data[i].orderTypeSend);
				}
				
				chart3.setOption({
			        xAxis: {
			            data: xAxis
			        },
			        series: [
						{
						    name:'代取件',
						    type:'line',
						    data:orderTypeTakes
						},
						{
						    name:'代寄件',
						    type:'line',
						    data:orderTypeSends
						}
					]
			    });
			},"json");

			var option4 = {
				    title: {
				        text: '地区订单对比图表'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['今日订单','历史平均订单']
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            magicType: {type: ['bar', 'line']},
				            restore: {},
				            saveAsImage: {}
				        }
				    },
				    xAxis:  {
				        type: 'category',
				        data: []
				    },
				    yAxis: [
				        {
				            type: 'value',
				            axisLabel: {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series: []
				};
			var chart4 = echarts.init(document.getElementById('chart4', args), 'macarons');
			chart4.setOption(option4);
			$.post("/order/analysis/getOrderByAreas",{},function(data){
				var xAxis = [];
				var orderNums= [];
				var orderNumAvgs = [];
				for(var i=0; i<data.length; i++){
					xAxis.push(data[i].regionName);
					orderNums.push(data[i].orderNum);
					orderNumAvgs.push(data[i].orderNumAvg);
				}
				
				chart4.setOption({
			        xAxis: {
			            data: xAxis
			        },
			        series: [
						{
						    name:'今日订单',
						    type:'bar',
						    data:orderNums
						},
						{
						    name:'历史平均订单',
						    type:'bar',
						    data:orderNumAvgs
						}
					]
			    });
			},"json");

			var option5 = {
				    title: {
				        text: '订单运力匹配监控'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['订单量','工作小派','运力','接单时效（分钟）']
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            magicType: {type: ['line', 'bar']},
				            restore: {},
				            saveAsImage: {}
				        }
				    },
				    xAxis:  {
				        type: 'category',
				        boundaryGap: false,
				        data: []
				    },
				    yAxis: [
				        {
				            type: 'value',
				            axisLabel: {
				                formatter: '{value} '
				            }
				        },
				        {
				            type: 'value',
				            axisLabel: {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series: []
				};
			var chart5 = echarts.init(document.getElementById('chart5', args), 'macarons');
			chart5.setOption(option5);
			$.post("/order/analysis/getOrderWinnerByTenDay",{},function(data){
				var xAxis = [];
				var orderNums = [];
				var winnerNums = [];
				var shippingAbilityNums = [];
				var takeOrderMinss= [];
				for(var i=0; i<data.length; i++){
					xAxis.push(data[i].day);
					orderNums.push(data[i].orderNum);
					winnerNums.push(data[i].winnerNum);
					shippingAbilityNums.push(data[i].shippingAbilityNum);
					takeOrderMinss.push(data[i].takeOrderMins);
				}
				
				chart5.setOption({
			        xAxis: {
			            data: xAxis
			        },
			        series: [
						{
						    name:'订单量',
						    type:'line',
						    yAxisIndex:'0',
						    data:orderNums
						},
						{
						    name:'工作小派',
						    type:'line',
						    yAxisIndex:'0',
						    data:winnerNums
						},{
						    name:'运力',
						    type:'line',
						    yAxisIndex:'0',
						    data:shippingAbilityNums
						},
						{
						    name:'接单时效（分钟）',
						    type:'line',
						    yAxisIndex:'1',
						    data:takeOrderMinss
						}
					]
			    });
			},"json");
		}
	};
});