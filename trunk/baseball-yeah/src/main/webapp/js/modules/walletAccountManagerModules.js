define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
    var cashShowTable = function (acctId) {
        var dataObject = $("#cashShowTable").bootstrapTable({
            url: '/wallet/account/postcashinfolist',
            queryParams: function (params) {
                return $.extend(params,
                    {
                        acctId: acctId
                    });
            },
            method: "post",
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            cache: false,
            height: 400,
            checkbox: false,
            sidePagination: "client",
            columns: [

                {
                    field: 'typeName',
                    title: '帐号类型',
                    sortable: true,
                    width: 150
                },
                {
                    field: 'acctNo',
                    title: '帐号',
                    sortable: true,
                    width: 150
                },
            ]
        });
        dataObject.bootstrapTable('refresh', {query: {acctId: acctId}});
        $("#showModal").modal();
    }
    var capitalChangeTable = function (acctId) {
        var dataObject2 = $("#capitalChangeTable").bootstrapTable({
            url: '/wallet/account/capitalchangelist',
            queryParams: function (params) {
                return $.extend(params,
                    {
                        data: '{"acctId":"' + acctId + '","startTime":"' + $("#startdate2").val() + '","endTime":"' + $("#enddate2").val() + '","payType":"' + $("#payType").val() + '"}'
                    });
            },
            method: "post",
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            cache: false,
            pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
            height: 500,
            search: false,
            showRefresh: false,
            checkbox: false,
            striped: true, // 是否显示行间隔色
            pagination: true, // 是否显示分页（*）
            sidePagination: "client",
            columns: [

                {
                    field: 'userName',
                    title: '帐号',
                    sortable: true,
                    width: 150
                },
                {
                    field: 'paymentName',
                    title: '交易类型',
                    sortable: true,
                    width: 150
                },
                {
                    field: 'typeName',
                    title: '交易方帐号类型',
                    sortable: true,
                    width: 80
                },
                {
                    field: 'acctNo',
                    title: '交易方帐号',
                    sortable: true,
                    width: 150
                },
                {
                    field: 'state',
                    title: '交易状态',
                    sortable: true,
                    width: 80
                },
                {
                    field: 'flowId',
                    title: '流水号',
                    sortable: true,
                    width: 150
                },
                {
                    field: 'optAmount',
                    title: '操作金额',
                    sortable: true,
                    width: 100,
                    formatter: function (value, row, index) {
                        return value / 100;
                    }
                },
                {
                    field: 'createTime',
                    title: '发生时间',
                    sortable: true,
                    width: 200
                },
            ]
        });
        dataObject2.bootstrapTable('refresh', {query: {data: '{"acctId":"' + acctId + '","startTime":"' + $("#startdate2").val() + '","endTime":"' + $("#enddate2").val() + '","payType":"' + $("#payType").val() + '"}'}});
        $("#btn_query_capital").click(function () {
            dataObject2.bootstrapTable('refresh');
        });

    }
    return {

        init: function (args) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            datatable = base.datagrid({
                url: '/wallet/account/postwalletaccountlist',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            userName: $("#userName").val(),
                            activeState: $("#activeState").val(),
                            state: $("#state").val(),
                            activeStartTime: $("#startdate").val(),
                            activeEndTime: $("#enddate").val()
                        });
                },
                showFooter: true,
                method: "post",
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                cache: false,
                sidePagination: "client",
                onPageChange: function () {
                    datatable.bootstrapTable('refresh');
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = 105 + $("#walletAccountTable").find("thead").height() + $("#walletAccountTable").find("tbody").height()
                        + $("#walletAccountTable").parent().parent().parent().parent().find(".clearfix").height();
                    if (!data || data.length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }

                    $("#walletAccountTable").bootstrapTable('resetView', {"height": tableHeight});
                    if ((tableHeight + $(".panel-default").height() || 300) > 1350) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1500);
                    }
                    $(".cashShow").on("click", function () {
                        var acctId = $(this).attr("data-acctId");
                        cashShowTable(acctId);
                    });
                    $(".capitalShow").on("click", function () {
                        var acctId = $(this).attr("data-acctId");
                        capitalChangeTable(acctId);
                        $("#showCapitalModal").modal();
                    });
                },
                onSort: function () {
                    datatable.bootstrapTable('refresh')
                },
                columns: [
                    {checkbox: true},
                    {
                        field: 'userName',
                        title: '钱包帐号',
                        sortable: true,
                        width: 150,
                        footerFormatter: function () {
                            return "合计";
                        }
                    },
                    {
                        field: 'nickName',
                        title: '真实姓名',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'activeStateName',
                        title: '帐号状态',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'stateName',
                        title: '锁定状态',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'balance',
                        title: '账户余额(元)',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return "￥" + (row.balance) / 100
                        },
                        footerFormatter: function (data) {
                            field = this.field;
                            var total_sum = data.reduce(function (sum, row) {
                                return (sum) + (row[field] || 0);
                            }, 0);
                            return "￥" + total_sum / 100;
                        }
                    },
                    {
                        field: 'frozenBalance',
                        title: '冻结金额(元)',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return "￥" + (row.frozenBalance) / 100
                        },
                        footerFormatter: function (data) {
                            field = this.field;
                            var total_sum = data.reduce(function (sum, row) {
                                return (sum) + (row[field] || 0);
                            }, 0);
                            return "￥" + total_sum / 100;
                        }
                    },
                    {
                        field: 'activeTime',
                        title: '激活时间',
                        sortable: true
                    },
                    {
                        title: '提现帐号',
                        width: 250,
                        formatter: function (value, row, index) {
                            return '<a href="#" class="cashShow" data-acctId="' + row.acctId + '"><span class="label label-success">查看详细</span></a> '
                                + '<a href="#" class="capitalShow" data-acctId="' + row.acctId + '"><span class="label label-info">资金变动记录</span></a>';
                        }
                    }
                ]
            }, '#walletAccountTable');
            $("#btn_query").click(function () {
                datatable.bootstrapTable('refresh');
            });

            $("#btn_lock").click(function () {
                self.lock();
            });
            $("#btn_unlock").click(function () {
                self.unlock();
            });
            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
            });


        },
        lock: function () {
            var arrselections = $("#walletAccountTable")
                .bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            base.cancel({text: "确认冻结该帐号？"}, function () {
                $.post("/wallet/account/lock", {acctId: arrselections[0].acctId}, function (data) {
                    if (data.success == 0) {
                        base.success("冻结成功");
                        $("#walletAccountTable")
                            .bootstrapTable('refresh');
                    }
                    else {
                        base.error(data.message);
                    }
                });
            });
        },
        unlock: function () {
            var arrselections = $("#walletAccountTable")
                .bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            base.cancel({text: "确认解锁该帐号？"}, function () {
                $.post("/wallet/account/unlock", {acctId: arrselections[0].acctId}, function (data) {
                    if (data.success == 0) {
                        base.success("解锁成功");
                        $("#walletAccountTable")
                            .bootstrapTable('refresh');
                    }
                    else {
                        base.error(data.message);
                    }
                });
            });

        }
    };
});
