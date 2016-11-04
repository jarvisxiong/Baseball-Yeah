define(['base'], function (base) {
    var datatable;

    var oneday = 24 * 3600 * 1000;
    return {
        init: function (panel) {
        	panel = panel || $('#indextab').tabs('getSelected');
            var self = this;
            $('#startDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2,
                endDate: new Date(new Date().getTime()-oneday)
            })
            
            $('#endDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2,
                endDate: new Date(new Date().getTime()-oneday)
            });

            //$("#startDate", panel).val((new Date((new Date()).getTime()-oneday)).Format("yyyy-MM-dd"));
            //$("#endDate", panel).val((new Date((new Date()).getTime()-oneday)).Format("yyyy-MM-dd"));
            base.collegeTree($('#selcollage', panel));
            
            $(".textbox-text",panel).bind("input propertychange", function() {

            	$('#selcollage',panel).combotree('tree').tree("search",$(this).val());

            	if($(this).val()=="" || null==$(this).val()){

            		$('#selcollage',panel).combotree('tree').tree("collapseAll");

            	}

            });
            
            base.datagrid({
                url: '/report/packetoperation/queryReceiveOrderRateList',
                method: 'GET',
                exportAllExcel: function () {
                    return "/report/packetoperation/exportReceiveOrderRateExcel" + self.searchVal(panel,self);
                },
                singleSelect: false,
                showExport: true,// 显示导出按钮
                exportDataType: "all",// 导出类型
                pagination: true, // 是否显示分页（*）
                pageList: [10, 20, 50, 100, 500], // 可供选择的每页的行数（*）
                queryParams: function (params) {
                	self.searchSort.sort = params.sort;
                	self.searchSort.order = params.order;
                    return $.extend(params, {
                    	collegeIds: base.getTreeValues($('#selcollage', panel)).join(','),
                    	startDate: $("#startDate", panel).val().trim(),
                        endDate: $("#endDate", panel).val().trim()
                    });
                },
                onLoadSuccess : function(data) {
					// 表格控件不支持高度自适应
					var tableHeight = 105
							+ $("#receiveOrderRateTable", panel).find("thead")
									.height()
							+ $("#receiveOrderRateTable", panel).find("tbody")
									.height()
							+ $("#receiveOrderRateTable", panel).parent()
									.parent().parent().parent().find(
											".clearfix").height();
					if (!data || data.length == 0) {// 如果没有数据 给固定文字的高度
						tableHeight = 105;
					}
					$("#receiveOrderRateTable", panel).bootstrapTable('resetView', {
						"height" : tableHeight
					});
				},
                columns: [
                    {
                     checkbox: true
                    },
                    {
                        field: 'day',
                        title: '日期',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'collegeName',
                        title: '校区名称',
                        sortable: true,
                        width: 250
                    },
                    {
                        field: 'totalOrderNum',
                        title: '有效订单数',
                        align: 'center',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'orderStatusWaittaking',
                        title: '未接单数',
                        align: 'center',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'recOrderRate',
                        title: '接单率',
                        align: 'center',
                        width: 150
                    },
                    {
                        field: 'recOrderQoq',
                        title: '环比增长',
                        align: 'center',
                        width: 150
                    }],
            }, '#receiveOrderRateTable', panel);

            $("#btn_query", panel).click(function () {
                self.searchDateVail(panel);
            });
            $("#btn_reset", panel).click(function () {
                base.reset(".main-box-header");
                $("#selcollage", panel).combotree('clear');
                //$("#startDate", panel).val((new Date((new Date()).getTime()-oneday)).Format("yyyy-MM-dd"));
                //$("#endDate", panel).val((new Date((new Date()).getTime()-oneday)).Format("yyyy-MM-dd"));
                self.searchDateVail(panel);
            });
        },
        searchDateVail: function (panel) {
        	var startDate = $('#startDate', panel).val();
        	var endDate = $('#endDate', panel).val();
        	if ((new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime() - new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime()) < 0) {
                sweetAlert("", "查询结束时间不能小于开始时间!", "info");
                return false;
            }
            $("#receiveOrderRateTable", panel).bootstrapTable('selectPage', 1);
            $("#receiveOrderRateTable", panel).bootstrapTable('refresh');
        },
        searchVal: function (panel,self) {
        	var str = "?collegeIds=";
        	if($("#collegeId", panel).val()){
        		str += $("#collegeId", panel).val().join(',');
        	}
        	str += "&startDate=" + ($("#startDate", panel).val().trim())
            + "&endDate=" + ($("#endDate", panel).val().trim());
        	if(self.searchSort.sort && self.searchSort.order){
        		str += "&sort=" + (self.searchSort.sort)
        		+ "&order=" + (self.searchSort.order);
        	}
            return str;
        },
        searchSort: {
        	sort : '',
        	order : ''
        }
    }
});
