define([ 'base' ], function(base) {
	/**
	 * 私有成员定义区域
	 */
	var datatable;
	return {
		init : function(panel) {
			panel = panel || $('#indextab').tabs('getSelected');
			var self = this;
            $('#starttimePicker', panel).datetimepicker({
       			format : 'yyyy-mm-dd',
       			autoclose : true,
       			pickTime: true,
       			startView : 2,
       			minView : 2,
       			endDate : new Date()
       		});
            $("#startdate", panel).val((new Date((new Date()).getTime() - 1 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
            
            $('#endtimePicker', panel).datetimepicker({
       			format : 'yyyy-mm-dd',
       			autoclose : true,
       			pickTime: true,
       			startView : 2,
       			minView : 2,
       			endDate : new Date()
       		});
            
            $("#enddate", panel).val((new Date((new Date()).getTime() - 1 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
            base.collegeTree($('#selcollege', panel));
            
			datatable = base.datagrid({
				url : '/report/delivery/transport',
				queryParams : function(params) {
					return $.extend(params, {
						flag : $("#flag", panel).val(),
						college: base.getTreeValues($('#selcollege', panel)).join(','),
						selectStartDate : $("#startdate", panel).val(),
						selectEndDate : $("#enddate", panel).val()
					});
				},
				method : "post",
				contentType : "application/x-www-form-urlencoded",
				dataType : "json",
				cache : false,
				sidePagination : "client",
				onLoadSuccess : function(data) {
					// 表格控件不支持高度自适应
					var tableHeight = 105
							+ $("#transportReportTable", panel).find("thead")
									.height()
							+ $("#transportReportTable", panel).find("tbody")
									.height()
							+ $("#transportReportTable", panel).parent()
									.parent().parent().parent().find(
											".clearfix").height();
					if (!data || data.length == 0) {// 如果没有数据 给固定文字的高度
						tableHeight = 105;
					}

					$("#transportReportTable", panel).bootstrapTable(
							'resetView', {
								"height" : tableHeight
							});

				},
				columns : [
				           
				{
					field : 'day',
					title : '日期',
					sortable : true
				}, 
				
				{
					field : 'collegeName',
					title : '校区名称',
					sortable : true
				},
				
				{
					field : 'puserNum',
					title : '小派总数',
					sortable : true,
					width : 150
				} ,
				
				{
					field : 'orderStatusDone',
					title : '完成订单数',
					sortable : true
				}, 
				
				{
					field : 'bonusIncome',
					title : '打赏收入金额（元）',
					sortable : true
				},
				
				{
					field : 'avgOrder',
					title : '人均完成订单',
					sortable : true,
					width : 150
				},
				
				{
					field : 'avgOrderMoney',
					title : '人均打赏收入（元）',
					sortable : true
				}, 
				
				{
					field : 'shippingAbility',
					title : '运力',
					sortable : true
				},
				
				{
					field : 'growthPercent',
					title : '环比增长',
					formatter : function(value, row, index) {
						if(row.growthPercent==100)
						{
							 return value="-"  ; 
						}
						else if(row.growthPercent==0)
						{
							return value="0.00%"   ;
						}
						else
						{
							return value == "" ? "" : value + "%";
						}
					},
					align : 'center',
					sortable : true,
					width : 150
				}
				]
			}, '#transportReportTable', panel);

			$("#btn_query", panel).click(function() {
			
				$("#flag", panel).val('');
				datatable.bootstrapTable('refresh');
			});
			$("#clearSearch", panel).click(function() {
				base.reset(".main-box-header");
				 $("#selcollege", panel).combotree('clear');
				$('#selcollege', panel).val("").trigger("change");
				$('#startdate', panel).val("").trigger("change");
				$('#enddate', panel).val("").trigger("change");
				
			});

			
			
		}
	};
});
