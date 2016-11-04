define([ 'base', 'deliveryManReportModules', 'transportReportModules', 'activityRateManagerModules', 'commentRateManagerModules','receiveOrderRateModules' ],
		function(base, deliveryManReport, transportReport, activityRateManager, commentRateManager,receiveOrderRateManager) {
			/**
			 * 私有成员定义区域
			 */

			return {
				init : function(args) {
					// / <summary>
					// / 模块初始化方法
					// / </summary>
					// / <param name="args">初始化时传入的参数</param>
					
					var userTotalCount = echarts.init(document.getElementById('userTotalCount'));
					userTotalCount.showLoading({
					    text: '正在努力的读取数据中...',    //loading话术
					});

					//ajax callback
					
					$.post("report/packetoperation/getPuserTotalNum.htm",null , function (data) {
						userTotalCount.hideLoading();
					    if (data != null) {
					    	var  date = data.resultDate;
					    	var  total = data.resultNum;
					    	var  active = data.resultActiveNum;
					    	var option = {
					    			 title: {
					    	             text: '活跃度数据图表'
					    	         },
					    	        
					    	         tooltip : {
					    	             trigger: 'axis'
					    	         },
					    	         legend: {
					    	             data: ['总人数','活跃人数']
					    	           
					    	         },
					    	       
					    	         toolbox: {
					    	        	
					    	             feature: {
					    	            	 mark : {show: true},
					    	                 dataView: {show: true, readOnly: false}, 
					    	                 magicType: {show: true, type: ['line', 'bar']},
					    	                 restore: {show: true},
					    	                 saveAsImage: {show: true}
					    	             }
					    	         },
					    	         backgroundColor: '#aed4f9',
					    	         calculable : true,
					    	         grid: {
					    	             borderWidth: 0
					    	         },
					    	         xAxis: [
					    	              {
					    	            	  
					    	            	  axisTick:{show:false},
					    	            	  splitLine: {show: false},
					    	            	  type : 'category',
					    	                  data : date
					    	             }
					    	         ],
					    	         yAxis: [
					    	                 {
					    	                	 show:false,
					    	                	 axisTick:{show:false}
					    	                 }
					    	            
					    	         ],
					    	         series: [
					    	                  
					    	                 
					    	                  {
					    	                      name:'总人数',
					    	                      type:'bar',
					    	                      itemStyle: {
					    	                          normal: {
					    	                        	  color:'#3a52b2',
					    	                              label: {
					    	                                  show: true,
					    	                                  position: 'top'
					    	                                 
					    	                              }
					    	                          }
					    	                      },
					    	                      data:total,
					    	                      barGap:'0%',
					    	                      
					    	                  },
					    	                  {
					    	                      name:'活跃人数',
					    	                      type:'bar',
					    	                      itemStyle: {
					    	                          normal: {
					    	                        	  color:'#fb0663',
					    	                              label: {
					    	                                  show: true,
					    	                                  position: 'top',
					    	                                  
					    	                              }
					    	                          }
					    	                        
					    	                      },

					    	                      data:active
					    	                   	
					    	                  }
					    	         ]
					    	};
					    	
					    }else{
					    	
					    }
					    userTotalCount.setOption(option);
					}, 'json');




					//新增小派数
					var userIncr = echarts.init(document.getElementById('userIncrCount'));
					userIncr.showLoading({
					    text: '正在努力的读取数据中...',    //loading话术
					});

					$.post("report/packetoperation/getPuserIncrNum.htm",null , function (data) {
						
						userIncr.hideLoading();
						if(data != null){
							var date = data.date;
							var differenceNum = data.differenceNum;
							var option = {
									 title: {
							             text: '小派新增数据图表',
							           	 x: "center",
							           	
							         },
							         backgroundColor: '#F5F5F5',
							         tooltip: {
							             trigger: "axis"
							           
							         },
							         
							         toolbox: {
							             show: true,
							             feature: {
							                 mark: {
							                     show: true
							                 },
							                 dataView: {
							                     show: true,
							                     readOnly: true
							                 },
							                 restore: {
							                     show: true
							                 },
							                 saveAsImage: {
							                     show: true
							                 }
							             }
							         },
							         calculable : true,
							         
							         xAxis: [
							              {
							            	  axisTick:{show:false},
							            	  splitLine: {show: false},
							            	  type : 'category',
							                  data : date
							             }
							         ],
							         yAxis: [
							                 {
							                	 axisTick:{show:false}
							                	
							                 }
							            
							         ],
							         series: [
							                 
							                  {
							                      name:'小派新增数',
							                      type:'line',
							                      itemStyle: {
							                          normal: {
							                        	  color:'#3a52b2',
							                              label: {
							                                  show: false,
							                                  position: 'top'
							                                 
							                              }
							                          }
							                      },
							                      data:differenceNum
							                      
							                  },
							                 
							         ]
							   
							};
						}
						userIncr.setOption(option);
					},'json');
					//ajax callback


					//运力
					var capacityIncrCount = echarts.init(document.getElementById('capacityIncrCount'));
					capacityIncrCount.showLoading({
					    text: '正在努力的读取数据中...',    //loading话术
					});

					//ajax callback
					$.post("report/packetoperation/getCapacityIncrNum.htm",null , function (data) {
						capacityIncrCount.hideLoading();
						if(data != null){
							var date = data.date;
							var differenceNum = data.differenceNum;
							var option = {
									 title: {
							             text: '运力新增数据图表',
							           	 x: "center",
							           	
							         },
							         backgroundColor: '#F5F5F5',
							         tooltip: {
							             trigger: "axis"
							           
							         },
							         
							         toolbox: {
							             show: true,
							             feature: {
							                 mark: {
							                     show: true
							                 },
							                 dataView: {
							                     show: true,
							                     readOnly: true
							                 },
							                 restore: {
							                     show: true
							                 },
							                 saveAsImage: {
							                     show: true
							                 }
							             }
							         },
							         calculable : true,
							         
							         xAxis: [
							              {
							            	  axisTick:{show:false},
							            	  splitLine: {show: false},
							            	  type : 'category',
							                  data : date
							             }
							         ],
							         yAxis: [
							                 {
							                	
							                	 axisTick:{show:false}
							                	
							                 }
							            
							         ],
							         series: [
							                 
							                  {
							                      name:'运力新增',
							                      type:'line',
							                      itemStyle: {
							                          normal: {
							                        	  color:'#00CED1',
							                              label: {
							                                  show: false,
							                                  position: 'top'
							                                 
							                              }
							                          }
							                      },
							                      data:differenceNum
							                      
							                  },
							                 
							         ]
							   
							};
							capacityIncrCount.setOption(option);
						}
					},'json');




					//接单
					var receiveOrder = echarts.init(document.getElementById('receiveOrder'));
					receiveOrder.showLoading({
					    text: '正在努力的读取数据中...',    //loading话术
					});

					//ajax callback
					$.post("report/packetoperation/getReceiveOrderNum.htm",null , function (data) {
						receiveOrder.hideLoading();

						if(data != null){
							var date = data.date;
							var differenceNum = data.differenceNum;
							var option = {
									 title: {
							             text: '接单量图表',
							           	 x: "center",
							           	
							         },
							         backgroundColor: '#F5F5F5',
							         tooltip: {
							             trigger: "axis"
							           
							         },
							         
							         toolbox: {
							             show: true,
							             feature: {
							                 mark: {
							                     show: true
							                 },
							                 dataView: {
							                     show: true,
							                     readOnly: true
							                 },
							                 restore: {
							                     show: true
							                 },
							                 saveAsImage: {
							                     show: true
							                 }
							             }
							         },
							         calculable : true,
							         
							         xAxis: [
							              {
							            	  axisTick:{show:false},
							            	  splitLine: {show: false},
							            	  type : 'category',
							                  data : date
							             }
							         ],
							         yAxis: [
							                 {
							                	
							                	 axisTick:{show:false}
							                	
							                 }
							            
							         ],
							         series: [
							                 
							                  {
							                      name:'接单数',
							                      type:'line',
							                      itemStyle: {
							                          normal: {
							                        	  color:'#fb0663',
							                              label: {
							                                  show: false,
							                                  position: 'top'
							                                 
							                              }
							                          }
							                      },
							                      data:differenceNum
							                      
							                  },
							                 
							         ]
							   
								};
								receiveOrder.setOption(option);
						}
					},'json');

					$("#deliverCount").click(
							function() {
								base.openTab("小派数量",
										"/report/delivery/deliveryManReport?flag=true",
										deliveryManReport.init,deliveryManReport)
							})
					$("#transportCount").click(
							function() {
								base.openTab("新增运力",
										"/report/delivery/transportReport?flag=true",
										transportReport.init,transportReport)
							})

					$("#activityRateManager").click(
						function () {
							base.openTab("活跃度",
								"/report/packetoperation/activityRateManager",
								activityRateManager.init,activityRateManager);
						});

					$("#commentRateManager").click(
						function () {
							base.openTab("好评率",
								"/report/packetoperation/commentRateManager",
								commentRateManager.init,commentRateManager);
						});
					$("#receiveOrderRateManager").click(
							function () {
								base.openTab("接单率",
									"/report/packetoperation/gotoReceiveOrderRate",
									receiveOrderRateManager.init,receiveOrderRateManager);
							}
						);
			},
       };


		});