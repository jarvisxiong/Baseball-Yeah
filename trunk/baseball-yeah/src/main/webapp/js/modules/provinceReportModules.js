define([ 'base','cityReportModules' ], function(base,cityReport) {
	/**
	 * 私有成员定义区域
	 */
	var datatable;
	return {
		init : function(panel) {
			panel = panel || $('#indextab').tabs('getSelected');
			var self = this;
			
			datatable = base.datagrid({
				url : '/report/delivery/getprovince',
				queryParams : function(params) {
					return $.extend(params, {
						firstArea : $("#firstArea", panel).val(),
						time : $("#time", panel).val(),
						provinceflag : $("#provinceflag", panel).val()=='true',
						province : $("#selprovince", panel).val(),
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
							+ $("#provinceReportTable", panel).find("thead")
									.height()
							+ $("#provinceReportTable", panel).find("tbody")
									.height()
							+ $("#provinceReportTable", panel).parent()
									.parent().parent().parent().find(
											".clearfix").height();
					if (!data || data.length == 0) {// 如果没有数据 给固定文字的高度
						tableHeight = 105;
					}

					$("#provinceReportTable", panel).bootstrapTable(
							'resetView', {
								"height" : tableHeight
							});

					$('.cityReportCls',panel).click(function(){
						var provinceId = $.trim($(this).attr('value'));
						var title = $(this).attr('title');
						var serchdate = $.trim($(this).attr('serchdate'));
						var href = '/report/delivery/gotoCityReport?id='+provinceId+'&time='+serchdate+'&cityflag=true';
						base.openTab(title,href,cityReport.init,cityReport);
					});
				},
				columns : [

				{
					field : 'province',
					title : '省份名称',
					sortable : true,
					formatter : function(value,row, index) {
						return "<a title='城市数量' href='#' class='cityReportCls' value='"+row.provinceId+"'cityflag=true'"+"' serchdate='"+row.day+"'>"+value+"</a>";
					}
				}, {
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
				},
				{
					field : 'provinceId',
					visible : false
				},
				{
					field : 'day',
					visible : false
				}
				]
			}, '#provinceReportTable', panel);

			$("#btn_query", panel).click(function() {
				self.searchDateVail(panel);
				$("#provinceflag", panel).val('');
				datatable.bootstrapTable('refresh');
			});
			$("#clearSearch", panel).click(function() {
				/*base.reset(".main-box-header");*/
				$('#selprovince', panel).val(" ").trigger("change");
			
			});

			
			  $.ajax({
	                type: "POST",
	                url: "/manage/province/getprovincesByAreaId?areaId= "+$("#firstArea", panel).val(),
	                dataType: "json",
	                success: function (data) {
	                    $("#selprovince", panel).select2({
	                        data: data,
	                        placeholder: '请选择',
	                        allowClear: true
	                    });
	                    $('#selprovince', panel).select2("val", null);
	                }
	            });

			
		},
		searchDateVail: function (panel) {
			
		
			var province = $("#selprovince", panel).val() || '';
			
			
			
	
		}
	};
});
