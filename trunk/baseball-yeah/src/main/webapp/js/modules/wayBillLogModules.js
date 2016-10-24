define([ 'base' ], function(base) {
	var datatable;
	return {
		init : function(args) {
			var self = this;
		$("#btn_query").click(function() {
			 if ($("#wayBillNo").val()) {
				 datatable = base.datagrid({
						url : '/report/waybill/waybilllog',
						method : 'get',
						sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
						pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
						search : false,
						showRefresh : false,
						showExport: true,//显示导出按钮  
			            exportDataType: "all",//导出类型
						height : 800,
						queryParams : function(params) {
							return $.extend(params, {
									data:'{"operation":"'+$("#operation").val()+'","wayBillNo":"'+$("#wayBillNo").val()+'","expressCompanyId":"'+$("#expressId").val()+'"}'			
							});
						},
		                onLoadSuccess: function (data) {
							//表格控件不支持高度自适应
		                    var tableHeight = $('#wayBillLogTable').find("thead").height() + $('#wayBillLogTable').find("tbody").height()
		                        + 3; 
		                    var dataCount = $('#wayBillLogTable').bootstrapTable('getData').length;
		                    if ($('#wayBillLogTable').parent().parent().parent().parent().find(".clearfix").height()<
									$('#wayBillLogTable').parent().parent().find(".fixed-table-footer").height()) {
		                    	if(dataCount<10){
		                    		tableHeight += 53;
		                    	} else {
		                    		tableHeight += 55;
		                    	}
							} else {
								tableHeight += $('#wayBillLogTable').parent().parent().parent().parent().find(".clearfix").height();
							}
		                    var showFooter = true;
		                    if(dataCount==0){//如果没有数据 给固定文字的高度
						        tableHeight=105;
						        showFooter = false;
						    }
		                   //如果有自带的功能 把自带功能的元素高度加上
		                        tableHeight += ($('#wayBillLogTable').parent().parent().parent().parent().find(".pull-right").height() + 20);
		                   
							tableHeight+=43;
							if(showFooter){
		                    	$('#wayBillLogTable').parent().parent().find('.fixed-table-footer').show();
		                    } else {
		                    	$('#wayBillLogTable').parent().parent().find('.fixed-table-footer').hide();
		                    }
		                    $('#wayBillLogTable').bootstrapTable('resetView', {"height": tableHeight});
		                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
		                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
		                    }
		                    $("#toolbar").css("margin","0px");
		                },
								columns : [ {
									field : 'wayBillNo',
									title : '运单号',
									width : 120
								}, {
									field : 'storeName',
									title : '站点名称',
									width : 200
								}, {
									field : 'userName',
									title : '用户名称',
									width : 120
								}, {
									field : 'expressCompanyName',
									title : '快递公司',
									width : 200,
								}, {
									field : 'operation',
									title : '运单操作类型',
									formatter : function(value, row, index) {
										return base.property(row.operation);
									}
								},

								{
									field : 'content',
									title : '运单操作内容',
									width : 400,
								}, {
									field : 'logTime',
									title : '记录时间',
								} ]
					}, '#wayBillLogTable');
				datatable.bootstrapTable('refresh');
				$(window).resize(function() {
					$('#wayBillLogTable').bootstrapTable('resetView');
				});	
			 }
			 else
       	     {
       	      sweetAlert("", "快递公司或运单号不能为空", "info");
       	     }
			});
			 $("#clearSearch").click(function () {
	                base.reset(".main-box-header");
	                $('#expressId').select2("val", null);
	            });
		}
	};
});