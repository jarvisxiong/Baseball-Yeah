define([ 'base','provinceReportModules','cityReportModules','collegeReportModules' ], function(base,provinceReport,cityReport,collegeReport) {
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
//			$("#startdate", panel).val(
//					(new Date((new Date()).getTime())).Format("yyyy-MM-dd"));

			$('#endtimePicker', panel).datetimepicker({
				format : 'yyyy-mm-dd',
				autoclose : true,
				pickTime: true,
				startView : 2,
				minView : 2,
				endDate : new Date()
			});

			datatable = base.datagrid({
				url : '/report/delivery/getdelivery',
				queryParams : function(params) {
					return $.extend(params, {
						flag : $("#flag", panel).val() == 'true',
						selectMonth : $("#selectmonth", panel).val(),
						selectStartDate : $("#startdate", panel).val(),
						selectEndDate : $("#enddate", panel).val(),
						region : $("#selregion", panel).val(),
						province : $("#selprovince", panel).val(),
						city : $("#selcity", panel).val(),
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
							+ $("#deliveryManTable", panel).find("thead")
									.height()
							+ $("#deliveryManTable", panel).find("tbody")
									.height()
							+ $("#deliveryManTable", panel).parent().parent()
									.parent().parent().find(".clearfix")
									.height();
					if (!data || data.length == 0) {// 如果没有数据 给固定文字的高度
						tableHeight = 105;
					}

					$("#deliveryManTable", panel).bootstrapTable('resetView', {
						"height" : tableHeight
					});
					
					$('.provinceReportCls',panel).click(function(){
						var id = $.trim($(this).attr("value"));
						var flag = $.trim($(this).attr("flag"));
						var serchdate = $.trim($(this).attr("serchdate"));
						var href;
						if(id == '' || flag == '' || serchdate == ''){
							return false;
						}
						if(flag == 2){
							//校区
							href = '/report/delivery/gotoCollegeReport?id='+id + '&time=' + serchdate;
							base.openTab('校区数量',href,collegeReport.init,collegeReport);
						}else if(flag == 3){
							//城市
							href = '/report/delivery/gotoCityReport?id='+id + '&time=' + serchdate;
							base.openTab('城市数量',href,cityReport.init,cityReport);
						}else if(flag == 4){
							//省份
							href = '/report/delivery/gotoProvinceReport?id='+id + '&time=' + serchdate+'&provinceflag=true';
							base.openTab('省份数量',href,provinceReport.init,provinceReport);
						}
						
					});
				},
				columns : [ {
					field : 'time',
					title : '时间',
					sortable : true,
					width : 150,
					formatter : function(value,row, index) {
						var selectmonth = $("#selectmonth", panel).val();
						if(selectmonth == '' || selectmonth == '请选择'){
							return value;
						}
						return selectmonth;
					}
				},

				{
					field : 'puserNum',
					title : '小派总数',
					sortable : true
				}, {
					field : 'channel',
					title : '渠道来源',
					sortable : true
				}, {
					field : 'region',
					title : '区域名称',
					sortable : true,
					formatter : function(value,row, index) {
						var serchdate = $("#selectmonth", panel).val();
						if(serchdate == '' || serchdate == '请选择'){
							serchdate = row.time;
						}
						return "<a href='#' class='provinceReportCls' value='"+row.regionId+"' flag='"+row.flag+"'provinceflag=true'"+"' serchdate='"+serchdate+"'>"+value+"</a>";
					}
				}, {
					field : 'regionPercent',
					title : '区域百分比',
					formatter : function(value, row, index) {
						if(row.regionPercent==100)
						{
							 return value="-"   ;
						}
						else if(row.regionPercent==0)
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
					field : 'growthPercent',
					title : '环比增长',
					formatter : function(value, row, index) {
						if(row.growthPercent==100)
						{
							 return value ="-" ;
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
					field : 'regionId',
					visible : false
				},
				{
					field : 'flag',
					visible : false
				}

				]
			}, '#deliveryManTable', panel);

			$("#btn_query", panel).click(function() {
				self.searchDateVail(panel);
				$("#flag", panel).val('false');
				datatable.bootstrapTable('refresh');
			});
			$("#clearSearch", panel).click(function() {
				base.reset(".main-box-header");
				$('#selectmonth', panel).val("请选择").trigger("change");
				$('#selregion', panel).val(" ").trigger("change");
				$('#selprovince', panel).val(" ").trigger("change");
				$('#selcity', panel).val(" ").trigger("change");
				$('#selcollege', panel).val(" ").trigger("change");
				$('#startdate', panel).val("").trigger("change");
				$('#enddate', panel).val("").trigger("change");
			});

			$.ajax({
				type : "GET",
				url : "/manage/area/getarea",
				dataType : "json",
				success : function(data) {
					$("#selregion", panel).select2({
						data : data,
						placeholder : '请选择',
						allowClear : true
					});
					$('#selregion', panel).select2("val", null);

				}
			});

			$.ajax({
				type : "GET",
				url : "/manage/province/getprovince",
				dataType : "json",
				success : function(data) {
					$("#selprovince", panel).select2({
						data : data,
						placeholder : '请选择',
						allowClear : true
					});
					$('#selprovince', panel).select2("val", null);
				}
			});

			$.ajax({
				type : "POST",
				url : "/manage/province/getcity",
				dataType : "json",
				success : function(data) {
					$("#selcity", panel).select2({
						data : data,
						placeholder : '请选择',
						allowClear : true
					});
					$('#selcity', panel).select2("val", null);
				}
			});

			$.ajax({
				type : "POST",
				url : "/manage/college/getcollegeall",
				dataType : "json",
				success : function(data) {

					$("#selcollege", panel).select2({
						data : data,
						placeholder : '请选择',
						allowClear : true
					});
					$('#selcollege', panel).select2("val", null);
				}
			});
		},
		searchDateVail: function (panel) {
			var selectMonth = $("#selectmonth", panel).val();
			var selectStartDate = $("#startdate", panel).val();
			var selectEndDate = $("#enddate", panel).val();
			var region = $("#selregion", panel).val() || '';
			var province = $("#selprovince", panel).val() || '';
			var city = $("#selcity", panel).val() || '';
			var college = $("#selcollege", panel).val() || '';
			

			if(!(selectMonth == '' || selectMonth == '请选择') && !(selectStartDate == '' && selectEndDate == '')){
				sweetAlert("", "月份与日期段不能同时选择!", "info");
                return false;
			}
			
			
			
			if ((new Date(Date.parse($('#enddate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#startdate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                sweetAlert("", "查询结束时间不能小于开始时间!", "info");
                return false;
            }
		}
	};
});
