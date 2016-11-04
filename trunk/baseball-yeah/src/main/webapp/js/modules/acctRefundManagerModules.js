define(
    ['base'],
    function (base) {
        /**
         * 私有成员定义区域
         */
        var thdTypeData = [{'id': ' ', 'text': '请选择'}, {'id': '2', 'text': '支付宝'}, {'id': '1', 'text': '微信'}];
        var refundStateData = [{'id': ' ', 'text': '请选择'}, {'id': '0', 'text': '申请'}, {'id': '1', 'text': '处理中'}, {'id': '2', 'text': '到账'}, {
            'id': '3',
            'text': '驳回'
        }];
        return {
            init: function (panel) {
                var self = this;
                base.datagrid({
                    url: '/wallet/refund/list',
                    method: 'post', singleSelect: false,
                    onLoadSuccess: function (data) {
                        //表格控件不支持高度自适应
                        var tableHeight = $('#menuTable', panel).find("thead").height() + $('#menuTable', panel).find("tbody").height()
                            + 3 + $('#menuTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                        if (data.total == 0) {//如果没有数据 给固定文字的高度
                            tableHeight = 105;
                        }
                        if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                            if ($('#menuTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                                tableHeight = 105;
                            }
                            tableHeight += ($('#menuTable', panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                        }
                        $('#menuTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                        $(".seeCheck", panel).click();
                        $(".seeCheck", panel).click();
                    },
                    queryParams: function (params) {
                        return $.extend(params,
                            {
                                thdType: $(
                                    "#thdType", panel).val(),
                                startTime: $(
                                    "#startTime", panel).val(),
                                endTime: $(
                                    "#endTime", panel).val(),
                                refundState: $(
                                    "#refundState", panel).val(),
                                orderId: $(
                                    "#orderId", panel).val(),
                            });
                    },
                    columns: [{
                        checkbox: true
                    }, {
                        field: 'refundId',
                        title: 'ID',
                        sortable: true
                    }, {
                        field: 'orderId',
                        title: '订单ID',
                        sortable: true
                    }, {
                        field: 'randomCode',
                        title: '随机码',
                        sortable: true
                    }, {
                        field: 'flowId',
                        title: '支付流水ID',
                        sortable: true
                    }, {
                        field: 'thdType',
                        title: '支付类型',
                        formatter: function (value,
                                             row, index) {
                            var result = value;
                            if (value == "1") {
                                result = "微信";
                            } else if (value == "2") {
                                result = "支付宝";
                            }
                            return result;
                        }
                    }, {
                        field: 'payAmount',
                        title: '支付金额 /元',
                        sortable: true,
                        formatter: function (value,
                                             row, index) {
                            var result;
                            if (/^[0-9]*$/.test(value)) {
                                result = value / 100;
                            }
                            return result;
                        }
                    }, {
                        field: 'refundAmount',
                        title: '退款金额 /元',
                        sortable: true,
                        formatter: function (value,
                                             row, index) {
                            var result;
                            if (/^[0-9]*$/.test(value)) {
                                result = value / 100;
                            }
                            return result;
                        }
                    }, {
                        field: 'applicant',
                        title: '退款人姓名',
                    }, {
                        field: 'mobile',
                        title: '退款人手机号',
                    }, {
                        field: 'refundReason',
                        title: '退款原因',
                        formatter: function (value, row, index) {
                            var avalue = value;
                            if (avalue.length > 6) {
                                avalue = value.substring(0, 6) + "..";
                            }
                            return '<a href="#" class="seeCheck"><span class="label label-success">' + avalue + '</span></a>';
                        },
                        events: {
                            'click .seeCheck': function (e, value, row, index) {
                                $(this).popover({
                                    content: value,
                                    trigger: 'hover'
                                })

                            }
                        },
                    }, {
                        field: 'createTime',
                        title: '创建时间',
                        sortable: true
                    }, {
                        field: 'modifyTime',
                        title: '更新时间',
                        sortable: true
                    }, {
                        field: 'refundState',
                        title: '状态',
                        sortable: true,
                        formatter: function (value,
                                             row, index) {
                            var result;
                            if (value == "0") {
                                result = "申请";
                            } else if (value == "1") {
                                result = "处理中";
                            } else if (value == "2") {
                                result = "到账";
                            } else if (value == "3") {
                                result = "驳回";
                            }
                            return result;
                        }
                    }]
                }, '#menuTable', panel);
                $("#thdType", panel).select2({
                    data: thdTypeData
                });
                $("#refundState", panel).select2({
                    data: refundStateData
                });
                $("#btn_query", panel).click(function () {
                    $("#menuTable", panel).bootstrapTable('selectPage', 1);
                });
                $("#clearSearch", panel).click(function () {
                    base.reset(".main-box-header");
                    $('#thdType', panel).val(" ").trigger("change");
                    $('#refundState', panel).val(" ").trigger("change");
                    $('#startTime', panel).val("");
                    $('#endTime', panel).val("");
                    $('#orderId', panel).val("");
                });
                //开始时间
                $('#starttimePicker', panel).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    pickTime: false,
                    minView: 1,
                    todayHighlight: true
                });
                //结束时间
                $('#endtimePicker', panel).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    pickTime: false,
                    minView: 1,
                    todayHighlight: true
                });
                $("#btn_refund", panel).click(function () {
                    var arrselections = $("#menuTable", panel).bootstrapTable('getSelections');
                    if (arrselections.length <= 0) {
                        sweetAlert("Oops...", "请选择有效数据!", "error");
                        return;
                    }
                    var firstThdType = arrselections[0].thdType;//1:微信,2:支付宝
                    for (var i = 0; i < arrselections.length; i++) {
                        if (arrselections[i].thdType != firstThdType) {
                            //判断只能选择同种支付类型
                            sweetAlert("Oops...", "只能选择同种支付类型的进行批量退款!", "error");
                            return;
                        }
                        if (arrselections[i].refundState == 2) {//到账状态
                            sweetAlert("Oops...", "选中订单已到账!", "error");
                            return;
                        }
                        if (arrselections[i].thdType != 2 && arrselections[i].thdType != 1) {//1:微信,2:支付宝 这两种类型才能退款
                            sweetAlert("Oops...", "选中订单支付类型不支持退款!", "error");
                            return;
                        }
                    }
                    swal({
                            title: "确认退款",
                            text: "退款后对方将按照支付类型收到退款,请确认是否退款?",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            cancelButtonText: "取消",
                            confirmButtonText: "确认"
                        },
                        function () {
                            //拼接json请求参数
                            var jsonParam = "{";
                            for (var i = 0; i < arrselections.length; i++) {
                                if (i != 0) {
                                    jsonParam = jsonParam + ",";
                                }
                                jsonParam += "\"" + arrselections[i].flowId.substring(1) + "\":" + "\"" + arrselections[i].refundId + "\"";
                            }
                            jsonParam += "}";
                            if (firstThdType == 1) {//1:微信
                                $.ajax({
                                    type: "POST",
                                    url: "/wallet/refund/doRefund",
                                    dataType: "json",
                                    data: {"data": jsonParam},
                                    success: function (data, status) {
                                        if (status == "success") {
                                            var obj = data;
                                            if (obj.success >= 0) {
                                                base.success("退款成功！");
                                            } else {
                                                base.error(obj.message);
                                            }
                                        } else {
                                            base.error("退款失败");
                                        }
                                        $("#menuTable", panel).bootstrapTable('refresh');
                                    }
                                });
                            } else if (firstThdType == 2) {//2:支付宝
                                window.open("/wallet/refund/gotoAliPay?data=" + jsonParam);
                            }
                        });
                });
            },
        };
    });