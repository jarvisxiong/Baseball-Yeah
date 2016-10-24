define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
    return {
        init: function () {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            datatable = base.datagrid({
                url: '/order/doordertask/query',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            mainTaskNo: $("#mainTaskNo").val(),
                            taskNo: $("#taskNo").val(),
                            orderId: $("#orderId").val(),
                            state: $("#stateSelect").val(),
                            winnerName: $("#winner").val(),
                            phone: $("#phone").val(),
                            startReceiveTime: $("#startdate").val(),
                            endReceiveTime: $("#enddate").val(),
                            startOverTime: $("#startdate3").val(),
                            endOverTime: $("#enddate3").val(),
                            startAuditTime: $("#startdate2").val(),
                            endAuditTime: $("#enddate2").val()
                        });
                },
                onPageChange: function (number, size) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#doOrderTaskTable').find("thead").height() + $('#doOrderTaskTable').find("tbody").height()
                        + 3 + $('#doOrderTaskTable').parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#doOrderTaskTable').bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#doOrderTaskTable').parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#doOrderTaskTable').bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1150);
                    }
                },
                onSort: function () {
                    datatable.bootstrapTable('refresh')
                },
                columns: [
                    {checkbox: true},
                    {
                        field: 'taskNo',
                        title: '任务单号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'orderId',
                        title: '订单编号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'theme',
                        title: '任务名称',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'taskUnitPrice',
                        title: '任务赏金/元',
                        sortable: true,
                        formatter: function (value,
                                row, index) {
							var result = value;
							if(/^[0-9]*$/.test(value)){
								result = value/100;
							}
		                   return result;
						}
                    },
                    {
                        field: 'finalMoney',
                        title: '实际赏金/元',
                        sortable: true,
                        formatter: function (value,
                                row, index) {
							var result = value;
							if(/^[0-9]*$/.test(value)){
								result = value/100;
							}
		                   return result;
						}
                    },
                    {
                        field: 'collegeName',
                        title: '学校名称',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'winner',
                        title: '众包人',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'phone',
                        title: '手机号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'receiveTime',
                        title: '接单时间',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'state',
                        title: '状态',
                        sortable: true,
                        width: 150,
                        formatter: function (value, row, index) {
                            var stateName;
                            switch (row.state) {
                                case 10:
                                    stateName = "待审核";
                                    break;
                                case 6:
                                    stateName = "已取消";
                                    break;
                                case 5:
                                    stateName = "已完成";
                                    break;
                                default:
                                    stateName = "进行中";
                                    break;
                            }
                            return stateName;
                        }
                    },
                    {
                        field: 'auditTime',
                        title: '提交审核时间',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'endTime',
                        title: "结束时间",
                        sortable: true,
                        width: 200
                    },
                    // {
                    //     field: 'remark',
                    //     title: '备注',
                    //     width: 200
                    // },
                    {
                        title: '操作',
                        width: 200,
                        formatter: function (value, row, index) {
                            if (row.state == 10) {
                                return '<a href="/order/gotoDoOrderTaskAudit/' + row.orderId + '"><span class="label label-success">审核</span></a> ';
                            }
                        }
                    }
                ]
            }, '#doOrderTaskTable');
            $("#btn_query").click(function () {
                datatable.bootstrapTable('refresh', {"query": {"offset": 0}});
            });
            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
            });

        }
    };
});
