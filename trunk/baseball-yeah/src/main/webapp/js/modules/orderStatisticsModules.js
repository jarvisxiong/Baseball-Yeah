define(
    ['base','orderAppraiseManageModules'],
    function (base,orderAppraiseManager) {
        /**
         * 私有成员定义区域
         */
        var orderinit=function (){

        	orderAppraiseManager.init();
        }

        return {
            init: function (args) {
                // / <summary>
                // / 模块初始化方法
                // / </summary>
                // / <param name="args">初始化时传入的参数</param>
                var self = this;
                var date = new Date();
                // 开始时间
                $('#createStartDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    startView: 2,
                    minView: 2,
                    todayHighlight: true
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#createEndDatePicker',args).datetimepicker('setStartDate', startTime);
                });

                $('#createEndDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    startView: 2,
                    minView: 2,
                    todayHighlight: true,
                    endDate: new Date()
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#createStartDatePicker',args).datetimepicker('setEndDate', startTime);
                });
                
                base.collegeTree($('#selcollage', args));
                
                base.datagrid(
                    {
                        url: '/order/statistics/getOrderStatistics',
                        queryParams: function (params) {
                            return $.extend(params, {
                                createStartDate: $('#createStartDate',args)
                                    .val(),
                                createEndDate: $('#createEndDate',args)
                                    .val(),
                                collegeId: $("#selcollage", args).val() == "请选择" ? "" : $("#selcollage", args).val(),//校区
                                collegeList:base.getTreeValues($('#selcollage', args)).toString(),
                                collegeCEO: $('#collegeCEO',args).val()
                            });
                        },
                        onLoadSuccess : function(data){
                        },
                        columns:[
						  [
							{
							     title: '城市',
							     field: 'regionName',
							     rowspan: 2,
							     align: 'center',
							     valign: 'middle',
							     sortable: true
							 },{
							     title: '&nbsp;&nbsp;学校&nbsp;&nbsp;',
							     field: 'collegeName',
							     rowspan: 2,
							     align: 'center',
							     valign: 'middle',
							     sortable: true
							 }, {
							     title: '&nbsp;日期&nbsp;',
							     field: 'day',
							     rowspan: 2,
							     align: 'center',
							     valign: 'middle',
							     sortable: true
							 }, {
							     title: '订单总数',
							     field: 'totalOrderNum',
							     rowspan: 2,
							     align: 'center',
							     valign: 'middle',
							     sortable: true
							 }, {
							     title: '订单金额(元)',
							     field: 'totalOrderMoney',
							     rowspan: 2,
							     align: 'center',
							     valign: 'middle',
							     formatter: function (value) {
			                            return (value / 100)+".00";
			                     },
							     sortable: true
							 }, {
							     title: '订单均价(元)',
							     field: 'avgMoney',
							     rowspan: 2,
							     align: 'center',
							     valign: 'middle',
							     sortable: true
							 },  {
							     title: '订单状态',
							     colspan: 6,
							     align: 'center'
							 },  {
							     title: '状态分析',
							     colspan: 4,
							     align: 'center'
							 },  {
							     title: '订单平均时效分析',
							     colspan: 1,
							     align: 'center'
							 },
							 {
							     title: '运力匹配分析',
							     colspan: 3,
							     align: 'center'
							 },
							 {
							     title: '订单类型分析',
							     colspan: 3,
							     align: 'center'
							 },
							 {
							     title: '订单评价分析',
							     colspan: 2,
							     align: 'center'
							 },
							 {
							     title: '代金券使用情况',
							     colspan: 3,
							     align: 'center'
							 },
							 {
							     title: '订单来源分析',
							     colspan: 3,
							     align: 'center'
							 }
							],
							[
							  {
							     field: 'orderStatusWaittaking',
							     title: '待接单',
							     sortable: true,
							     align: 'center'
							 }, {
							     field: 'orderStatusToken',
							     title: '已接单',
							     align: 'center'
							 },{
							     field: 'orderStatusShipping',
							     title: '配送中',
							     sortable: true,
							     align: 'center'
							 }, {
							     field: 'orderStatusDone',
							     title: '已完成',
							     sortable: true,
							     align: 'center'
							 }, {
							     field: 'orderStatusCancel',
							     title: '已取消',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'orderStatusAbnormal',
							     title: '异常',
							     align: 'center',
							     sortable: true
							 },{
							     field: 'tokenPercent',
							     title: '接单率',
							     sortable: true,
							     align: 'center'
							 }, {
							     field: 'donePercent',
							     title: '完成率',
							     sortable: true,
							     align: 'center'
							 }, {
							     field: 'cancelPercent',
							     title: '取消率',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'abnormalPercent',
							     title: '异常率',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'takeOrderMins',
							     title: '接单时效(分钟)',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'activePuserNumDay',
							     title: '工作小派(人)',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'shippingIndex',
							     title: '运力指数(单)',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'shippingAbility',
							     title: '运力(人)',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'orderTypeCoTake',
							     title: '合作商户代取',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'orderTypeNoncoTake',
							     title: '非合作商户代取',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'orderTypeSend',
							     title: '代寄',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'avgGoogAppraise',
							     title: '好评率',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'avgScore',
							     title: '平均评分',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'voucherOrderNum',
							     title: '使用订单数',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'voucherOrderMoney',
							     title: '抵消金额(元)',
							     align: 'center',
							     formatter: function (value) {
			                            return (value / 100)+".00";
			                     },
							     sortable: true
							 }, {
							     field: 'voucherOrderActualpay',
							     title: '实付金额(元)',
							     align: 'center',
							     formatter: function (value) {
			                            return (value / 100)+".00";
			                     },
							     sortable: true
							 }, {
							     field: 'orderSourceService',
							     title: '服务号',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'orderSourceSms',
							     title: '商户短信',
							     align: 'center',
							     sortable: true
							 }, {
							     field: 'orderSourceColink',
							     title: '合作链接',
							     align: 'center',
							     sortable: true
							 }
							]  
                         ]
                       
                    }, '#userTable',args);

                $.ajax({
                    type: "POST",
                    url: "/order/statistics/selectCollegeCEO",
                    dataType: "json",
                    success: function (data) {
                        $("#collegeCEO",args).select2({
                            data: data
                        });
                    }
                });

                $("#btn_query",args).click(function () {
                    // $("#userTable",args).bootstrapTable('refresh');
                	 if($('#createEndDate', args).val()!="" && $('#createStartDate', args).val()==""){
                         sweetAlert("", "请选择开始日期!", "info");
                         return;
                     }
                	 if($('#createEndDate', args).val()=="" && $('#createStartDate', args).val()!=""){
                		 sweetAlert("", "请选择结束日期!", "info");
                         return;
                	 }
                	var createStartDate = $.trim($("#createStartDate",args).val());
					var createEndDate = $.trim($("#createEndDate",args).val());
					if(createEndDate == ""){
						createEndDate = new Date(new Date().getTime() - 24*60*60*1000).pattern("yyyy-MM-dd");
					}
					var start  = new Date(createStartDate.replace(/-/g,"/")).getTime();
					var end = new Date(createEndDate.replace(/-/g,"/")).getTime();
					if(end - start  > 30*24*60*60*1000 ){
						sweetAlert("", "开始日期与结束日期不能相差超过30天", "info");
						return false;
					}
					// updated by lixuxin
					//$("#userTable", args).bootstrapTable('selectPage', 1); 
                    $("#userTable", args).bootstrapTable('refresh', 1);
                });

                $("#clearSearch",args).click(function () {
                	base.reset(".main-box-body");
                    $("#createStartDate", args).val("");
                    $("#createEndDate", args).val("");
                    //校区控件清空
                    $('#selcollage').combotree('setValues', []);
                   // $("#selcollage",args).select2("val",null);
                    $("#collegeCEO", args).select2("val"," ");
                })
            }
        };
    });
    
	Date.prototype.pattern=function(fmt) { 
		var o = { 
		"M+" : this.getMonth()+1, //月份 
		"d+" : this.getDate(), //日 
		"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时 
		"H+" : this.getHours(), //小时 
		"m+" : this.getMinutes(), //分 
		"s+" : this.getSeconds(), //秒 
		"q+" : Math.floor((this.getMonth()+3)/3), //季度 
		"S" : this.getMilliseconds() //毫秒 
		}; 
		var week = { 
		"0" : "\u65e5", 
		"1" : "\u4e00", 
		"2" : "\u4e8c", 
		"3" : "\u4e09", 
		"4" : "\u56db", 
		"5" : "\u4e94", 
		"6" : "\u516d" 
		}; 
		if(/(y+)/.test(fmt)){ 
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		} 
		if(/(E+)/.test(fmt)){ 
		fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]); 
		} 
		for(var k in o){ 
		if(new RegExp("("+ k +")").test(fmt)){ 
		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
		} 
		} 
		return fmt; 
	} 

	function showDetail(orderId,args) {
	    $('#detailMoblile',args).val("");
	    $('#detailOrderId',args).val("");
	    $('#detailRealName',args).val("");
	    $('#detailWaybillNo',args).val("");
	    $('#detailSex',args).val("");
	    $('#detailState',args).val("");
	    $('#detailCityName',args).val("");
	    $('#detailAddress',args).val("");
	    $('#detailCollegeName',args).val("");
	    $('#detailStoreName',args).val("");
	    $('#detailLocation',args).val("");
	    $('#detailTotalMoney',args).val("");
	    $('#detailRebateMoney',args).val("");
	    $('#detailFinalMoney',args).val("");
	    $('#detailPayMoney',args).val("");
	
	    $.post("/order/user/orderView", {
	        "orderId": orderId
	    }, function (data, status) {
	        if (status == "success") {
	            $('#detailMoblile',args).val(data.mobile);
	            $('#detailOrderId',args).val(orderId);
	            $('#detailRealName',args).val(data.consignee);
	            $('#detailWaybillNo',args).val(data.waybillNo);
	            if (data.sex == "p_gender_male") {
	                $('#detailSex',args).val("男");
	            } else if (data.sex == "p_gender_female") {
	                $('#detailSex',args).val("女");
	            } else if (data.sex == "p_gender_secret") {
	                $('#detailSex',args).val("保密");
	            }
	            $('#detailState',args).val(data.stateStr);
	            $('#detailCityName',args).val(data.cityName);
	            $('#detailAddress',args).val(data.address);
	            $('#detailCollegeName',args).val(data.fullName);
	            $('#detailStoreName',args).val(data.storeName);
	            $('#detailLocation',args).val(data.location);
	            $('#detailTotalMoney',args).val(data.totalMoney);
	            $('#detailRebateMoney',args).val(data.rebateMoney);
	            $('#detailFinalMoney',args).val(data.finalMoney);
	            $('#detailPayMoney',args).val(data.payMoney);
	        } else {
	            base.error("初始化失败!");
	        }
	    });
	
	    $('#detailTable',args).bootstrapTable({
	        // 请求后台的URL（*）
	        url: '/order/user/orderDetail',
	        striped: true, // 是否显示行间隔色
	        singleSelect: true,
	        queryParams: function (pa) {
	            return {"orderId": orderId};
	        },
	        height: 320,
	        columns: [{
	            field: 'createDateStr',
	            title: '操作时间',
	            width: 100
	        }, {
	            field: 'content',
	            title: '内容',
	            width: 100
	        }
	        ]
	    }).bootstrapTable('refresh', {query: {"orderId": orderId}});
	
	    $('#detailModal',args).modal();
	}