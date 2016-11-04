define(['base'], function(base){
	var datatable;
	
	return {
		init: function(panel) {
			var self = this;
			
			 //开始时间
            $('#starttimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2
            })

            //结束时间
            $('#endtimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2
            });
          
			/*** 加载城市列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    $("#selcity", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcity', panel).select2("val", null);
                }
            });
            /*** 加载快递公司列表 ***/
            $.ajax({
            	type: 'POST',
            	url: '/manage/express/getexpress',
            	dataType: 'json',
            	success: function(data) {
            		$("#selexpress", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selexpress', panel).select2("val", null);
            	}
            });
            /*** 加载门店列表 ***/
            $.ajax({
                type: "POST",
                url: "/store/exp/expstoreinfo",
                dataType: "json",
                success: function (data) {
                    $("#selstore", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selstore', panel).select2("val", null);
                }
            });
            
            $("#startdate", panel).val((new Date((new Date()).getTime() - 30 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
            $("#enddate", panel).val((new Date()).Format("yyyy-MM-dd"));
            
            datatablse = base.datagrid({
            	url: '/report/offline/query',
            	method: 'GET',
                // exportAllExcel:"/report/offline/exportExcel",
            	queryParams: function(params) {
            		return $.extend(params, {
            			cityName: $("#selcity", panel).val(),
            			storeName: $("#selstore", panel).val(),
            			startDate: $("#startdate", panel).val(),
            			endDate: $("#enddate", panel).val(),
            			expressName: $("#selexpress", panel).val()
            		});
            	},
            	columns:[
            	     {
            	    	 checkbox: true
            	     },
            	    
            	     {
            	    	 field: 'cityName',
            	    	 title: '城市',
            	    	 sortable: true
            	     },
            	     {
            	    	 field: 'date',
            	    	 title: '日期',
            	    	 sortable: true
            	     },
            	     {
            	    	 field: 'storeName',
            	    	 title: '门店名称',
            	    	 sortable: true
            	     },
            	     {
            	    	 field: 'expressName',
            	    	 title: '货源品牌',
            	    	 sortable: true
            	     },
            	     {
            	    	 field: 'arrivTotal',
            	    	 title: '到件数',
            	    	 sortable: true
            	     },
            	     {
            	    	 field: 'collectTotal',
            	    	 title: '签收数',
            	    	 sortable: true
            	     }, {
            	    	 field: 'storeId',
            	    	 title: '门店编号',
            	    	 visible: false
            	     }],
            	     onClickCell: function (field, value, row) {
                         switch (field) {
                             case "arrivTotal":
                                 self.detail(row);
                                 break;
                         }
                     }
            },'#userTable', panel);
            
            $("#btn_query", panel).click(function () {
//                $("#userTable", panel).bootstrapTable('refresh');
                $("#userTable", panel).bootstrapTable('selectPage', 1);
            });
            $("#btn_Excel", panel).click(function () {
                location.href="/report/offline/exportExcel";
            });
            $("#clearSearch", panel).click(function () {
            	base.reset(".main-box-header");
            	$("#startdate", panel).val((new Date((new Date()).getTime() - 30 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
                $("#enddate", panel).val((new Date()).Format("yyyy-MM-dd"));
                $("#selcity", panel).select2("val",null);
                $("#selexpress", panel).select2("val",null);
                $("#selstore", panel).select2("val",null);
            });
		},
		detail: function(row) {
			BootstrapDialog.show({
                title: '直营门店明细',
                message: $("#divdialog", panel).html(),
                cssClass: 'login-dialog',
                onshown: function () {
                	  
                  
                    var dialogdom= $("#" + $(this)[0].id);
                    
                    var date = new Date(row.date);
                    date.setHours(0);
                    date.setMinutes(0);
                    date.setSeconds(0);
                    dialogdom.find("#arrive_startdate", panel).val(date.Format("yyyy-MM-dd HH:mm:ss"));
					date.setHours(23);
		            date.setMinutes(59);
		            date.setSeconds(59);
                    dialogdom.find("#arrive_enddate", panel).val(date.Format("yyyy-MM-dd HH:mm:ss"));
                    
                  //开始时间
                    dialogdom.find('#starttimePicker', panel).datetimepicker({
            			format : 'yyyy-mm-dd hh:ii:ss',
            			autoclose : true,
            			startView : 2,
            			minView : 0,
            			todayHighlight : true,
            			endDate : new Date()
            		}).on('changeDate', function(e) {
            			var startTime = e.date;
            			$('#endtimePicker', panel).datetimepicker('setStartDate', startTime);
            		});

            		//结束时间
                    dialogdom.find('#endtimePicker', panel).datetimepicker({
            			format : 'yyyy-mm-dd hh:ii:ss',
            			autoclose : true,
            			startView : 2,
            			minView : 1,
            			todayHighlight : true,
            			endDate : new Date()
            		}).on('changeDate', function(e) {
            			var endTime = e.date;
            			$('#starttimePicker', panel).datetimepicker('setEndDate', endTime);
            		});
                    
                    
                    base.datagrid({
                        url: '/report/offline/detail',
                        method: 'post',
                        toolbar: $("#" + $(this)[0].id, panel).find("#messageToolbar", panel),
                        queryParams: function (params) {
                            return $.extend(params,
                                {
                            		storeId: row.storeId,
                                    waybillNo: dialogdom.find("#waybillNo", panel).val(),
                                    arriveStartDate: dialogdom.find("#arrive_startdate", panel).val(),
                                    arriveEndDate: dialogdom.find("#arrive_enddate", panel).val(),
                                    signStartDate: dialogdom.find("#sign_startdate", panel).val(),
                                    signEndDate: dialogdom.find("#sign_enddate", panel).val()
                                });
                        },
                        singleSelect: false,
                        height:300,
                        columns: [
                            {
                                field: 'waybillNo',
                                title: '运单号',
                                sortable: true
                            }
                            ,
                            {
                                field: 'arriveDate',
                                title: '到件时间',
                                sortable: true,
                                formatter: function(value, row, field) {
                                	return (new Date(value)).Format("yyyy-MM-dd HH:mm:ss")
                                }
                            }
                            ,
                            {
                                field: 'signDate',
                                title: '签收时间',
                                sortable: true,
                                formatter: function(value, row, field) {
                                	if(value != null){
                                		return (new Date(value)).Format("yyyy-MM-dd HH:mm:ss")
                                	}else{
                                		return value;
                                	}
                                }
                            }
                        ]
                    }, dialogdom.find("#message", panel));
                    
                    dialogdom.find("#mes_query", panel).click(function () {
                        dialogdom.find("#message", panel).bootstrapTable('refresh');
                    });
                    
                    dialogdom.find('#mes_reset', panel).click(function () {
                    	base.reset(".table-responsive");
                    	var date = new Date(row.date);
                        date.setHours(0);
                        date.setMinutes(0);
                        date.setSeconds(0);
                        dialogdom.find("#arrive_startdate", panel).val(date.Format("yyyy-MM-dd HH:mm:ss"));
    					date.setHours(23);
    		            date.setMinutes(59);
    		            date.setSeconds(59);
                        dialogdom.find("#arrive_enddate", panel).val(date.Format("yyyy-MM-dd HH:mm:ss"));
                        
                    });
                }
                    
			})
		}
	}
});