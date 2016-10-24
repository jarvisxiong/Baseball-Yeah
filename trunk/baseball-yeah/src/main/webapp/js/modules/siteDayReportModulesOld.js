define([ 'base' ], function(base) {
	var datatable;
	function sumFormatter(data) {
        field = this.field;
        var total_sum = data.reduce(function(sum, row) {
            return (sum) + (row[field] || 0);
        }, 0);
        return total_sum;
    }
	function totalTextFormatter(data) {
        return '合计';
    }
	
	return {
		init : function(args) {
			var self = this;
			datatable = base.datagrid({
				url : '/report/storesms/dayReport',
				method : 'get',
				sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
				pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
				search : true,
				showRefresh : true,
				showFooter : true,
//				toolbar : '#toolbar',
				showExport: true,//显示导出按钮  
	            exportDataType: "all",//导出类型
				height : 800,
				queryParams : function(params) {
					return $.extend(params, {
						storeid : $('#storeid').val(),
						phone : $('#phone').val(),
						querydate : $('#querydate').val()
					});
				},
                onLoadSuccess: function (data) {
					//表格控件不支持高度自适应
                    var tableHeight = $('#siteDayReportTable').find("thead").height() + $('#siteDayReportTable').find("tbody").height()
                        + 3; //+ $('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height();
                    var dataCount = $('#siteDayReportTable').bootstrapTable('getData').length;
                    if ($('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height()<
							$('#siteDayReportTable').parent().parent().find(".fixed-table-footer").height()) {
                    	if(dataCount<10){
                    		tableHeight += 53;
                    	} else {
                    		tableHeight += 55;
                    	}
					} else {
						tableHeight += $('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height();
					}
                    var showFooter = true;
                    if(dataCount==0){//如果没有数据 给固定文字的高度
				        tableHeight=105;
				        showFooter = false;
				    }
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#siteDayReportTable').parent().parent().parent().parent().find(".pull-right").height() + 20);
                    }
					tableHeight+=43;
					if(showFooter){
                    	$('#siteDayReportTable').parent().parent().find('.fixed-table-footer').show();
                    } else {
                    	$('#siteDayReportTable').parent().parent().find('.fixed-table-footer').hide();
                    }
                    $('#siteDayReportTable').bootstrapTable('resetView', {"height": tableHeight});
                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }
                },
                onPageChange : function(number, size){
                	//表格控件不支持高度自适应
                    var tableHeight = $('#siteDayReportTable').find("thead").height() + $('#siteDayReportTable').find("tbody").height()
                        + 3 + $('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height();
					if ($('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height() == 0) {
						tableHeight += 56;
					}
                    var showFooter = null;
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#siteDayReportTable').parent().parent().parent().parent().find(".pull-right").height() + 20);
                    }
                    if($('#siteDayReportTable').bootstrapTable('getData').length==0){//如果没有数据 给固定文字的高度
				        tableHeight=105;
				        showFooter = false;
				    } else {
				    	showFooter = true;
				    }
					tableHeight+=43;
                    $('#siteDayReportTable').bootstrapTable('resetView', {"height": tableHeight});
                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                    	$(parent.document).find("#mainFrame").height(1150);
                    }
                    if(showFooter){
                    	$('#siteDayReportTable').parent().parent().find('.fixed-table-footer').show();
                    } else {
                    	$('#siteDayReportTable').parent().parent().find('.fixed-table-footer').hide();
                    }
                },
                onSearch: function (text) {
                	//表格控件不支持高度自适应
                    var tableHeight = $('#siteDayReportTable').find("thead").height() + $('#siteDayReportTable').find("tbody").height()
                        + 3 + $('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height();
					if ($('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height() == 0) {
						tableHeight += 56;
					}
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#siteDayReportTable').parent().parent().parent().parent().find(".pull-right").height() + 20);
                    }
                    if($('#siteDayReportTable').bootstrapTable('getData').length==0){//如果没有数据 给固定文字的高度
				        tableHeight=105;
				    }
					tableHeight+=43;
                    $('#siteDayReportTable').bootstrapTable('resetView', {"height": tableHeight});
                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }
                },
				columns : [ {
					field : 'collegeName',
					title : '归属校区',
					sortable : true,
					width : 300,
					footerFormatter: totalTextFormatter
				}, {
					field : 'storeCode',
					title : '站点编号',
					sortable : true
				}, {
					field : 'storeName',
					title : '站点名称',
					sortable : true,
					width : 300
				}, {
					field : 'location',
					title : '站点地址',
					sortable : true,
					width : 300
				}, {
					field : 'supervisor',
					title : '负责人',
					sortable : true
				}, {
					field : 'phone',
					title : '手机号',
					sortable : true
				}, {
					field : 'expressCompanyName',
					title : '快递公司',
					sortable : true
				}, {
					field : 'totalCount',
					title : '短信总数',
					sortable : true,
					footerFormatter: sumFormatter
				}, {
					field : 'billCount',
					title : '带单号短信数',
					sortable : true,
					footerFormatter: sumFormatter
				}, {
					field : 'sucessCount',
					title : '成功数',
					sortable : true,
					footerFormatter: sumFormatter
				}, {
					field : 'failCount',
					title : '失败数',
					sortable : true,
					footerFormatter: sumFormatter
				} ]
			}, '#siteDayReportTable');
			
			$.ajax({
                type: "POST",
                url: "/store/exp/expstoreinfo",
                dataType: "json",
                success: function (data) {
                    $("#storeid").select2({
                        data: data
                    });
                }
            });
			
			$(window).resize(function() {
				$('#siteDayReportTable').bootstrapTable('resetView');
			});
			
			$("#btn_query").click(function() {
				datatable.bootstrapTable('refresh');
			});
		}
	};
});