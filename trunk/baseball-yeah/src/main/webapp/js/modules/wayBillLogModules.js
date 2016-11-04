define([ 'base' ], function(base) {
	var datatable;
	return {
		init : function(panel) {
			var self = this;
		    $("#btn_query",panel).click(function() {
		    	 if ($("#wayBillNo",panel).val()) {
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
									data:'{"operation":"'+$("#operation",panel).val()+'","wayBillNo":"'+$("#wayBillNo",panel).val()+'","expressCompanyId":"'+$("#expressId",panel).val()+'"}'			
							});
						},
		                onLoadSuccess: function (data) {
							//表格控件不支持高度自适应
		                    var tableHeight = $('#wayBillLogTable',panel).find("thead").height() + $('#wayBillLogTable',panel).find("tbody").height()
		                        + 3; 
		                    var dataCount = $('#wayBillLogTable',panel).bootstrapTable('getData').length;
		                    if ($('#wayBillLogTable',panel).parent().parent().parent().parent().find(".clearfix").height()<
									$('#wayBillLogTable',panel).parent().parent().find(".fixed-table-footer").height()) {
		                    	if(dataCount<10){
		                    		tableHeight += 53;
		                    	} else {
		                    		tableHeight += 55;
		                    	}
							} else {
								tableHeight += $('#wayBillLogTable',panel).parent().parent().parent().parent().find(".clearfix").height();
							}
		                    var showFooter = true;
		                    if(dataCount==0){//如果没有数据 给固定文字的高度
						        tableHeight=105;
						        showFooter = false;
						    }
		                   //如果有自带的功能 把自带功能的元素高度加上
		                        tableHeight += ($('#wayBillLogTable',panel).parent().parent().parent().parent().find(".pull-right").height() + 20);
		                   
							tableHeight+=43;
							if(showFooter){
		                    	$('#wayBillLogTable',panel).parent().parent().find('.fixed-table-footer').show();
		                    } else {
		                    	$('#wayBillLogTable',panel).parent().parent().find('.fixed-table-footer').hide();
		                    }
		                    $('#wayBillLogTable',panel).bootstrapTable('resetView', {"height": tableHeight});
		                    $("#toolbar",panel).css("margin","0px");
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
					}, '#wayBillLogTable',panel);
				//datatable.bootstrapTable('refresh');
				datatable.bootstrapTable('selectPage', 1);
				$(window).resize(function() {
					$('#wayBillLogTable',panel).bootstrapTable('resetView');
				});	
			 }
			 else
       	     {
       	      sweetAlert("", "运单号不能为空", "info");
       	     }
			});
			$("#clearSearch",panel).click(function () {
                base.reset($(".main-box-header",panel));
                $('#expressId',panel).select2("val", null);
            });
			/**
		    加载合作公司
		    */
		    $.ajax({
	          url: "/manage/express/queryenabledforsel",
	          dataType: 'json',
	          type: 'post',
	          success: function (data) {
	              $("#expressId",panel).select2({
	                  data: data,
	                  placeholder: '请选择',
	                  allowClear: true
	              });
	              $('#expressId',panel).select2("val", null);
	          }
	      });
		}
	};
});