define(['base'], function (base) {
    var dataTable;

    return {
        init: function (panel) {
            var self = this;
            panel= panel || $('#indextab').tabs('getSelected');
            $('#startDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView : 2
            });

            $('#endDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView : 2
            });

            dataTable = base.datagrid({
                url: '/statistic/getAcctFlow',
                showExport: false,
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            startDate: $('#startDate', panel).val(),
                            endDate: $('#endDate', panel).val(),
                            packetUserId: $("#packetUserId", panel).val(),//小派手机号
                            tradeType: $("#tradeType", panel).val()//交易类型
                        });
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = 40 * data.total + 150;
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 120;
                    }else if(data.total >= 10){
                        tableHeight = 550;
                    }
                    $("#acctTable", panel).bootstrapTable('resetView', {"height": tableHeight});
                },
                columns: [
                    {
                        field: 'Number',
                        title: '序号',
                        formatter: function (value, row, index) {
                            return index + 1;
                        },
                        width: 10
                    },
                   /* {
                        field: 'acctId',
                        title: '账号',
                        sortable: false,
                        width: 80
                    },*/
                    {
                        field: 'bizType',
                        title: '交易类型',
                        sortable: false,
                        width: 80,
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 1:
                                    return "收款";
                                case 2:
                                    return "提现";
                                case 3:
                                    return "充值";
                            }
                        }
                    },
                    {
                        field: 'thdType',
                        title: '交易方账号类型',
                        sortable: false,
                        width: 50,
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 1:
                                    return "微信";
                                case 2:
                                    return "支付宝";
                                case 3:
                                    return "指端账户";
                                case 4:
                                    return "现金";
                            }
                        }
                    },
                    {
                        field: 'thdAcctNo',
                        title: '交易方账号',
                        sortable: true,
                        width: 50
                    },
                    {
                        field: 'state',
                        title: '交易状态',
                        sortable: false,
                        width: 50,
                        formatter: function (value, row, index) {
                            if(row["bizType"] == "1"){
                                switch (value) {
                                    case 0:
                                        return "失效";
                                    case 1:
                                        return "正常";
                                }
                            }
                            else if(row["bizType"] == "2"){
                                switch (value) {
                                    case 1:
                                        return "提交申请";
                                    case 2:
                                        return "处理中";
                                    case 3:
                                        return "到账";
                                    case 4:
                                        return "审核不通过";
                                }
                            }
                        }
                    },
                    {
                        field: 'flowId',
                        title: '流水号',
                        sortable: true,
                        width: 80
                    },
                    {
                        field: 'optAmount',
                        title: '操作金额',
                        sortable: true,
                        width: 80,
                        formatter: function (value, row, index) {
                            return value/100;
                        }
                    },
                    {
                        field: 'createTime',
                        title: '发生时间',
                        sortable: true,
                        width: 80
                    }]
            }, '#acctTable', panel);

            $("#btn_query", panel).click(function () {
                if ((new Date(Date.parse($('#endDate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#startDate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "发生时间-结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#acctTable", panel).bootstrapTable('selectPage', 1);

            });

            $("#clearSearch", panel).click(function () {
                base.reset($(".main-box-header", panel));
                $("#startDate", panel).val("");
                $("#endDate", panel).val("");
                $("#tradeType", panel).val("");
            });
        }
    };
});