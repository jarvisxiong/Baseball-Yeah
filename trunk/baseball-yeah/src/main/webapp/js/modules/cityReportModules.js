define([ 'base','collegeReportModules' ], function(base,collegeReport) {
	/**
	 * 私有成员定义区域
	 */
	var datatable;
	return {
		init : function(panel) {
			panel = panel || $('#indextab').tabs('getSelected');
			var self = this;
			
			datatable = base.datagrid({
				url : '/report/delivery/getcity',
				queryParams : function(params) {
					return $.extend(params, {
						firstProvince : $("#firstProvince", panel).val(),
						time : $("#time", panel).val(),
						cityflag : $("#cityflag", panel).val()=='true',
						city : $("#selcity", panel).val()
						
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
							+ $("#cityReportTable", panel).find("thead")
									.height()
							+ $("#cityReportTable", panel).find("tbody")
									.height()
							+ $("#cityReportTable", panel).parent()
									.parent().parent().parent().find(
											".clearfix").height();
					if (!data || data.length == 0) {// 如果没有数据 给固定文字的高度
						tableHeight = 105;
					}

					$("#cityReportTable", panel).bootstrapTable(
							'resetView', {
								"height" : tableHeight
							});
					
					$('.collegeReportCls',panel).click(function(){
						var id = $.trim($(this).attr('value'));
						var title = $(this).attr('title');
						var serchdate = $.trim($(this).attr("serchdate"));
						var href = '/report/delivery/gotoCollegeReport?id='+id + '&time='+serchdate+'&collegeflag=true';
						base.openTab(title,href,collegeReport.init,collegeReport);
					});

				},
				columns : [

				{
					field : 'city',
					title : '城市名称',
					sortable : true,
					formatter : function(value,row, index) {
						
						return "<a title='校区数量' href='#' class='collegeReportCls' value='"+row.cityId+"'collegeflag=true'"+"' serchdate='"+row.day+"'>"+value+"</a>";
					}
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
				},
				{
					field : 'cityId',
					visible : false
				},
				{
					field : 'day',
					visible : false
				}
				]
			}, '#cityReportTable', panel);

			$("#btn_query", panel).click(function() {
				self.searchDateVail(panel);
				$("#cityflag", panel).val(''),
				datatable.bootstrapTable('refresh');
			});
			$("#clearSearch", panel).click(function() {
				/*base.reset(".main-box-header");*/
				$('#selcity', panel).val(" ").trigger("change");
			
			});

			

			$.ajax({
				type : "POST",
				url : "/manage/province/getcitysByProvinceId?provinceId="+$("#firstProvince", panel).val(),
				dataType : "json",
				success : function(data) {
					$("#selcity",panel).select2({
						data : data,
						placeholder : '请选择',
						allowClear : true
					});
					$('#selcity', panel).select2("val", null);
				}
			});
			
		},
		searchDateVail: function (panel) {
			
			var city = $("#selcity", panel).val() || '';
			
			
			
			
		}
	};
});
