define([ 'base' ], function(base) {
	/**
	 * 私有成员定义区域
	 */
	var datatable;
	return {
		init : function(panel) {
			panel = panel || $('#indextab').tabs('getSelected');
			var self = this;
			
			datatable = base.datagrid({
				url : '/report/delivery/getcollege',
				queryParams : function(params) {
					return $.extend(params, {
						firstCity : $("#firstCity", panel).val(),
						time : $("#time", panel).val(),
						collegeflag : $("#collegeflag", panel).val()=='true',
						college : $("#selcollege", panel).val()
						
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
							+ $("#collegeReportTable", panel).find("thead")
									.height()
							+ $("#collegeReportTable", panel).find("tbody")
									.height()
							+ $("#collegeReportTable", panel).parent()
									.parent().parent().parent().find(
											".clearfix").height();
					if (!data || data.length == 0) {// 如果没有数据 给固定文字的高度
						tableHeight = 105;
					}

					$("#collegeReportTable", panel).bootstrapTable(
							'resetView', {
								"height" : tableHeight
							});

				},
				columns : [

				{
					field : 'college',
					title : '校区名称',
					sortable : true
				}, 
				
				{
					field : 'puserNum',
					title : '小派总数',
					sortable : true
				},
				
				{
					field : 'growthPercent',
					title : '环比增长',
					formatter : function(value, row, index) {
						if(row.growthPercent==100)
						{
							 return value="-"   ;
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
				} ]
			}, '#collegeReportTable', panel);

			$("#btn_query", panel).click(function() {
				self.searchDateVail(panel);
				$("#collegeflag", panel).val('');
				datatable.bootstrapTable('refresh');
			});
			$("#clearSearch", panel).click(function() {
				/*base.reset(".main-box-header");*/
				$('#selcollege', panel).val(" ").trigger("change");
			
			});

			

			 $.ajax({
	                type: "POST",
	                url: "/manage/college/getcollegesByCityId?cityId="+$("#firstCity", panel).val(),
	                dataType: "json",
	                success: function (data) {
	                  
	                    $("#selcollege",panel).select2({
	                        data: data,
	                        placeholder: '请选择',
	                        allowClear: true
	                    });
	                    $('#selcollege',panel).select2("val", null);
	                }
	            });
		},
		searchDateVail: function (panel) {
			
			
			var college = $("#selcollege", panel).val() || '';
			
			
			
		
		}
	};
});
