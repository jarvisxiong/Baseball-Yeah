define([ 'base' ], function(base) {
	var datatable;
	var detailtable;
	var status;
	var beVirtual;
	var storeId;
	
	// 对Date的扩展，将 Date 转化为指定格式的String
	// 例子： 
	// (new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423  
	Date.prototype.Format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"H+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
			"S" : this.getMilliseconds()
		// 毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	
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
	
	function calcTableHight(control,search){
		//表格控件不支持高度自适应
        var tableHeight = control.find("thead").height() + control.find("tbody").height() + 3 + control.parent().parent().parent().parent().find(".clearfix").height();
        var dataCount = control.bootstrapTable('getData').length;
        if(dataCount==0){//如果没有数据 给固定文字的高度
	        tableHeight=105;
	    }
        if (search) {//如果有自带的功能 把自带功能的元素高度加上
            tableHeight += (control.parent().parent().parent().parent().find(".pull-right").height() + 20);
        }
        return tableHeight;
	}

	return {
		init : function(args) {
			var self = this;
			datatable = base.datagrid({
				url : '/report/storesms/storesmstotal',
				method : 'get',
				pagination : true, // 是否显示分页（*）
				sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
				pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
				search : true,
				showRefresh : true,
				showExport: true,// 显示导出按钮
	            exportDataType: "all",// 导出类型
	            showFooter : true,
				queryParams : function(params) {
					return $.extend(params, {
						storeId : $('#storeid').val(),
						startDate : $('#startdate').val(),
						endDate : $('#enddate').val(),
					});
				},
				onLoadSuccess: function (data) {
					//表格控件不支持高度自适应
                    var tableHeight = $('#taotalContent').find("thead").height() + $('#taotalContent').find("tbody").height() + 3;
                    var dataCount = $('#taotalContent').bootstrapTable('getData').length;
                    if ($('#taotalContent').parent().parent().parent().parent().find(".clearfix").height()<
							$('#taotalContent').parent().parent().find(".fixed-table-footer").height()) {
                    	if(dataCount<10){
                    		tableHeight += 53;
                    	} else {
                    		tableHeight += 55;
                    	}
					} else {
						tableHeight += $('#taotalContent').parent().parent().parent().parent().find(".clearfix").height();
					}
                    var showFooter = true;
                    if(dataCount==0){//如果没有数据 给固定文字的高度
				        tableHeight=105;
				        showFooter = false;
				    }
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#taotalContent').parent().parent().parent().parent().find(".pull-right").height() + 20);
                    }
					tableHeight+=43;
					if(showFooter){
                    	$('#taotalContent').parent().parent().find('.fixed-table-footer').show();
                    } else {
                    	$('#taotalContent').parent().parent().find('.fixed-table-footer').hide();
                    }
                    $('#taotalContent').bootstrapTable('resetView', {"height": tableHeight});
                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }
				},
				/*field：点击列的 field 名称，
				value：点击列的 value 值，
				row：点击列的整行数据，
				$element：td 元素*/
				onDblClickCell : function(field, value, row, $element){
					beVirtual = null;
					status = null;
					storeId = row.storeId;
					if(field=='billCount'){
						beVirtual = '1';
					} else if(field=='sucessCount'){
						status = 2;
					} else if(field=='failCount'){
						status = -1;
					}
					detailtable.bootstrapTable('refresh');
				},
				onPageChange : function(number, size){
                	//表格控件不支持高度自适应
                    var tableHeight = $('#taotalContent').find("thead").height() + $('#taotalContent').find("tbody").height()
                        + 3 + $('#taotalContent').parent().parent().parent().parent().find(".clearfix").height();
					if ($('#taotalContent').parent().parent().parent().parent().find(".clearfix").height() == 0) {
						tableHeight += 56;
					}
                    var showFooter = null;
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#taotalContent').parent().parent().parent().parent().find(".pull-right").height() + 20);
                    }
                    if($('#taotalContent').bootstrapTable('getData').length==0){//如果没有数据 给固定文字的高度
				        tableHeight=105;
				        showFooter = false;
				    } else {
				    	showFooter = true;
				    }
					tableHeight+=43;
                    $('#taotalContent').bootstrapTable('resetView', {"height": tableHeight});
                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                    	$(parent.document).find("#mainFrame").height(1150);
                    }
                    if(showFooter){
                    	$('#taotalContent').parent().parent().find('.fixed-table-footer').show();
                    } else {
                    	$('#taotalContent').parent().parent().find('.fixed-table-footer').hide();
                    }
                },
                onSearch: function (text) {
                	//表格控件不支持高度自适应
                    var tableHeight = $('#taotalContent').find("thead").height() + $('#taotalContent').find("tbody").height()
                        + 3 + $('#taotalContent').parent().parent().parent().parent().find(".clearfix").height();
					if ($('#taotalContent').parent().parent().parent().parent().find(".clearfix").height() == 0) {
						tableHeight += 56;
					}
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#taotalContent').parent().parent().parent().parent().find(".pull-right").height() + 20);
                    }
                    if($('#taotalContent').bootstrapTable('getData').length==0){//如果没有数据 给固定文字的高度
				        tableHeight=105;
				    }
					tableHeight+=43;
                    $('#taotalContent').bootstrapTable('resetView', {"height": tableHeight});
                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }
                },
				columns : [ {
					field : 'storeName',
					title : '站点名称',
					sortable : true,
					footerFormatter: totalTextFormatter
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
				},
				{
					field : 'storeId',
					title : '站点编号',
					visible:false,
					searchable : false
				}]
			}, '#taotalContent');

			detailtable = $('#detailContent').bootstrapTable({
				url : '/report/storesms/storesmsdetail',
				method : 'get',
				striped : true, // 是否显示行间隔色
				singleSelect : true,// 单选
				pagination: true, // 是否显示分页（*）
				sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
				pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
				search : true,
				showExport : true,// 显示导出按钮
				exportDataType : "all",// 导出类型
				showRefresh : true,
				height : 800,
				exportTypes : [ 'excel' ],
				exportOptions : {
					fileName : (new Date())
							.Format("yyyy-MM-dd HH:mm:ss")
							+ $('h1').text() + "明细"
				},
				queryParams : function(params) {
					return $.extend(params, {
						storeId : storeId,
						startDate : $('#startdate').val(),
						endDate : $('#enddate').val(),
						status : status,
						beVirtual : beVirtual
					});
				},
				onLoadSuccess: function (data) {
					if (data.length > 0) { //如果有数据 切换到明细
						$('#myTab a:last').tab('show');
						var tableHeight = 45;
						var count = data.length > 10 ? 10 : data.length;
						tableHeight += count * 42;
						$('#detailContent').bootstrapTable('resetView', {"height": tableHeight});
				        if( tableHeight > 900){//当高度过高 刷新外面iframe高度
				            $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
				        }
					}
				},
				onPageChange : function(number, size){
					var tableHeight = calcTableHight($('#detailContent'),this.search);
                    $('#detailContent').bootstrapTable('resetView', {"height": tableHeight});
                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                    	$(parent.document).find("#mainFrame").height(1150);
                    }
				},
                onSearch: function (text) {
                	var tableHeight = calcTableHight($('#detailContent'),this.search);
                    $('#detailContent').bootstrapTable('resetView', {"height": tableHeight});
                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }
                },
				columns : [{
					field : 'storeName',
					title : '站点名称',
					sortable : true
				}, {
					field : 'billNo',
					title : '单号',
					sortable : true
				}, {
					field : 'phone',
					title : '手机号',
					sortable : true
				}, {
					field : 'status',
					title : '状态',
					sortable : true
				}, {
					field : 'sendTime',
					title : '发送时间',
					sortable : true
				}, {
					field : 'sendName',
					title : '发送人',
					sortable : true
				} ]
			});
			
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
			
			$("#btn_query").click(function() {
				datatable.bootstrapTable('refresh');
				$('#myTab a:first').tab('show');
			});
			
			$(window).resize(function() {
				$('#taotalContent').bootstrapTable('resetView');
				$('#detailContent').bootstrapTable('resetView');
			});
		},
		
	};
});