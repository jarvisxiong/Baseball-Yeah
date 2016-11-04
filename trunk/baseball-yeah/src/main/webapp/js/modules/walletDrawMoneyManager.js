define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var validate = {
        fields: {
            thdId: {
                validators: {
                    notEmpty: {
                        message: '交易流水号不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 32,
                        message: '交易流水号必须在1到32位之间'
                    }
                }
            }
        }
    };

    window.operateEvents = {
        'click .seeCheck': function (e, value, row, index) {
            switch (row.state) {
                //case 0:
                //$('#apply').attr("checked","checked");
                //	break;
                case 1:
                    //$('#checking').attr("checked","checked");
                    $("#state").html("审核通过");
                    break;
                case 0:
                case 3: //审核失败，已到帐则不可再操作
                case 2:
                    sweetAlert("", "审核通过才可编辑", "warning");
                    return;
            }
            $('#exchangeId').val(row.exchangeId);
            $('#flowId').val(row.flowId);
            $('#createTime').html(row.createTime);
            $('#userName').html(row.userName);
            $('#transType').html(row.transType);
            $('#drawAmount').html(row.drawAmount / 100);
            $('#addModal').modal();
        },
        'click .seeUserInfo': function (e, value, row, index) {
            $('#drawAcctNo').html(row.acctNo);
            $('#nickName').html(row.nickname);
            $('#idNo').html(row.idNo);
            $('#openId').html(row.openId);
            if (row.transType == "支付宝") {
                $('.myopenid').addClass("myvisible");
            } else {
                $('.myopenid').removeClass("myvisible");
            }

            $('#showDrawUseModal').modal();
        }
    };

    var datatable;

    return {
        init: function (panel) {
            var self = this;
            datatable = base.datagrid({
                singleSelect: false,
                url: '/wallet/draw/getdrawlist',
                queryParams: function (params) {
                    return $.extend(params, {
                        thdType: $("#transationid", panel).val() != "all" ? $("#transationid", panel).val() : null,
                        state: $("#orderstate", panel).val(),
                        userId: $('#applyerid', panel).val(),
                        checkerId: $('#checkerid', panel).val(),
                        thdFlowId: $('#thdFlowId', panel).val(),
                        applybgnTime: $('#apply_start_date', panel).val(),
                        applyendTime: $('#apply_end_date', panel).val(),
                        checkbgnTime: $('#check_start_date', panel).val(),
                        checkendTime: $('#check_end_date', panel).val(),
                        arrivebgnTime: $('#arrive_start_date', panel).val(),
                        arriveendTime: $('#arrive_end_date', panel).val(),
                        timeStamp: (new Date()).valueOf()
                    });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'createTime',
                        title: '提现日期',
                        sortable: true
                    },
                    {
                        field: 'state',
                        title: '提现状态',
                        sortable: true,
                        align: 'center',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "<a class='label label-info'>提交申请</a>";
                                case 1:
                                    return "<a class='label label-success'>处理中</a>";
                                case 2:
                                    return "<a class='label label-success'>已到帐</a>";
                                case 3:
                                    return "<a class='label label-warning'>审核失败</a>";
                            }
                        }
                    },
                    {
                        field: 'transType',
                        title: '账号类型',
                        sortable: true
                    },
                    {
                        field: 'thdTypeCode',
                        title: '提现帐户',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value == 1 ? row.openId : row.acctNo;
                        }
                    },
                    {
                        field: 'drawAmount',
                        title: '提现金额(元)',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value / 100;
                        }
                    },
                    {
                        field: 'acctNo',
                        title: '提现账号',
                        sortable: true
                    },
                    {
                        field: 'userName',
                        title: '申请人',
                        sortable: true,
                        align: 'center',
                        events: operateEvents,
                        formatter: function (value, row, index) {
                            return '<a href="#" class="seeUserInfo"><span class="label label-success">' + value + '</span></a>';
                        }
                    },
                    {
                        field: 'activeState',
                        title: '客户状态',
                        sortable: true,
                        align: 'center',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "<a class='label label-info'>封存</a>";
                                case 1:
                                    return "<a class='label label-success'>激活</a>";
                            }
                        }
                    },
                    {
                        field: 'checkEmp',
                        title: '审核人',
                        sortable: true
                    },
                    {
                        field: 'checkTime',
                        title: '审核时间',
                        sortable: true
                    },
                    {
                        field: 'thdFlowId',
                        title: '交易流水号',
                        sortable: true
                    },
                    {
                        field: 'arriTime',
                        title: '到账时间',
                        sortable: true
                    }]
            }, '#walletCheckTable', panel);

            $.ajax({
                type: "POST",
                url: "/wallet/draw/getthdpayment",
                dataType: "json",
                success: function (data) {
                    $("#transationid", panel).select2({
                        placeholder: '请选择交易类型',
                        allowClear: true,
                        data: data
                    });
                }
            });

            $("#orderstate", panel).select2({
                placeholder: '请选择订单状态',
                allowClear: true
            });

            $('#btn_query', panel).click(function () {
                if ($('#apply_start_date', panel).val() === '' || $('#apply_end_date', panel).val() === '') {
                    base.error("提现日期不能为空!");
                    return;
                }
                datatable.bootstrapTable('refresh', {"query": {"offset": 0}});
                // datatable.bootstrapTable('selectPage', 1);
                // datatable.bootstrapTable('selectPage', 1);
            });

            $(window).resize(function () {
                $('#walletCheckTable', panel).bootstrapTable('resetView');
            });

            $('#btnUpdate', panel).click(function () {
                var rows = $('#walletCheckTable', panel).bootstrapTable('getSelections');
                if (rows.length < 1) {
                    sweetAlert("", "请选择有效数据", "warning");
                    return;
                }
                var row = rows[0];
                switch (row.state) {
                    case 1:
                        $("#state", panel).html("审核通过");
                        break;
                    case 0:
                    case 3: //审核失败，已到帐则不可再操作
                    case 2:
                        sweetAlert("", "审核通过才可编辑", "warning");
                        return;
                }
                $('#exchangeId', panel).val(row.exchangeId);
                $('#flowId', panel).val(row.flowId);
                $('#createTime', panel).html(row.createTime);
                $('#userName', panel).html(row.userName);
                $('#transType', panel).html(row.transType);
                $('#drawAmount', panel).html(row.drawAmount / 100);
                $('#addModal', panel).modal();
            });

            $("#clearSearch", panel).click(function () {
                base.reset(".panel-body");
                $("#transationid", panel).val(" ").trigger("change");
                $("#orderstate", panel).val(" ").trigger("change");
            });

            $('#addModal', panel).on('show.bs.modal', function () {
                base.validator(validate, "#checkForm", self.checkok, panel);
            });

            $('#addModal', panel).on('hidden.bs.modal', function () {
                $('#checkForm', panel).data('bootstrapValidator').resetForm(true);
                $("#checkForm", panel).data('bootstrapValidator').destroy();
            });

            $('#btnAudit', panel).click(function () {
                var rows = $('#walletCheckTable', panel).bootstrapTable('getSelections');
                if (rows.length < 1) {
                    sweetAlert("", "请选择有效数据", "warning");
                    return;
                }

                var exchangeIds = [];
                var flowIds = [];
                var total = 0;
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].state == 2 || rows[i].state == 3) {
                        sweetAlert("", "审核失败，已到帐不可再审核", "warning");
                        return;
                    }
                    exchangeIds.push(rows[i].exchangeId);
                    flowIds.push(rows[i].flowId);
                    total += rows[i].drawAmount;
                }
                base.cancel({title: "提现申请", text: "总条数：" + rows.length + "；总金额：" + total / 100 + "元；确认审核通过该项申请？"}, function () {
                    $.post("/wallet/draw/check", {
                        "exchangeId": exchangeIds.join(","),
                        "isok": "1",
                        "flowId": flowIds.join(",")
                    }, function (data, status) {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            $("#walletCheckTable", panel).bootstrapTable('selectPage', 1);
                            base.success("审核通过！");
                        } else {
                            base.error(obj.message);
                        }
                    });
                });
            });

            $('#btnAuditFail', panel).click(function () {
                var rows = $('#walletCheckTable', panel).bootstrapTable('getSelections');
                if (rows.length < 1) {
                    sweetAlert("", "请选择有效数据", "warning");
                    return;
                }
                var exchangeIds = [];
                var flowIds = [];
                var total = 0;

                for (var i = 0; i < rows.length; i++) {

                    if (rows[i].state == 2 || rows[i].state == 3) {
                        sweetAlert("", "审核失败，已到帐不可再审核", "warning");
                        return;
                    }
                    exchangeIds.push(rows[i].exchangeId);
                    flowIds.push(rows[i].flowId);
                    total += rows[i].drawAmount;
                }
                base.cancel({title: "提现申请", text: "总条数：" + rows.length + "；总金额：" + total / 100 + "元；确认驳回该项申请？"}, function () {
                    $.post("/wallet/draw/check", {
                        "exchangeId": exchangeIds.join(","),
                        "isok": "0",
                        "flowId": flowIds.join(",")
                    }, function (data, status) {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            $("#walletCheckTable", panel).bootstrapTable('selectPage', 1);
                            base.success("审核通过！");
                        } else {
                            base.error(obj.message);
                        }
                    });
                });
            });

            $('#btnSave', panel).click(function () {
                self.drawcheck(panel);
            });

        },
        checkok: function (panel) {
            $.post("/wallet/draw/addthdid", {
                "exchangeId": $("#exchangeId", panel).val(),
                "thdId": $('#thdId', panel).val(),
                "flowId": $('#flowId', panel).val()
            }, function (data, status) {
                if (status == "success") {
                    var obj = json_parse(data);
                    if (obj.success == 0) {
                        // $("#walletCheckTable", panel).bootstrapTable('refresh');
                        $("#walletCheckTable", panel).bootstrapTable('selectPage', 1);
                        $('#addModal', panel).modal('hide');
                        $('#checkForm', panel).data('bootstrapValidator').resetForm(true);


                        swal({
                            title: "恭喜!", text: "保存成功！", timer: 3000,
                            showConfirmButton: false, type: "success"
                        });
                    } else {
                        base.error(obj.message);
                    }
                } else {
                    base.error("数据加载失败!");
                }
            });
        },
        drawcheck: function (panel) {
            var self = this;
            var rows = $('#walletCheckTable', panel).bootstrapTable('getSelections');
            if (rows.length < 1) {
                sweetAlert("", "请选择有效数据", "warning");
                return;
            }
            var row = null;
            if ($('#isApprove', panel).prop('checked')) {
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].state == 0) {
                        row = rows[i];
                    }
                }
                if (row == null) {
                    sweetAlert("", "只有申请状态数据才可以审核", "warning");
                    return;
                }
                self.drawcheckok(row);
            } else if ($('#isRefuse', panel).prop('checked')) {
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].state == 0 || rows[i].state == 1) {
                        row = rows[i];
                    }
                }
                if (row == null) {
                    sweetAlert("", "已审核失败或已到帐不可再审核", "warning");
                    return;
                }
                self.drawcheckno(row, panel);
            }
        },
        drawcheckok: function (row, panel) {
            sweetAlert({
                title: "提示信息",
                text: "确认审核通过该项申请?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            }, function () {
                $.post("/wallet/draw/check", {
                    "exchangeId": row.exchangeId,
                    "isok": "1",
                    "flowId": row.flowId
                }, function (data, status) {
                    if (status == "success") {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            // $("#walletCheckTable", panel).bootstrapTable('refresh');
                            $("#walletCheckTable", panel).bootstrapTable('selectPage', 1);
                            $('#checkDrawModal', panel).modal('hide');
                            base.success("审核通过！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });
        },
        drawcheckno: function (row, panel) {
            sweetAlert({
                title: "提示信息",
                text: "确认驳回该项申请?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            }, function () {
                $.post("/wallet/draw/check", {
                    "exchangeId": row.exchangeId,
                    "isok": "0",
                    "drawAmount": row.drawAmount,
                    "flowId": row.flowId
                }, function (data, status) {
                    if (status == "success") {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            // $("#walletCheckTable", panel).bootstrapTable('refresh');
                            $("#walletCheckTable", panel).bootstrapTable('selectPage', 1);
                            $('#checkDrawModal', panel).modal('hide');
                            base.success("审核不通过！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });
        },
        batchcheckok: function (panel) {
            var rows = $('#walletCheckTable', panel).bootstrapTable('getSelections');
            if (rows.length < 1) {
                sweetAlert("", "请选择有效数据", "warning");
                return;
            }
            var exchangeIds = [];
            var flowIds = [];
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].state == 0) {
                    exchangeIds.push(rows[i].exchangeId);
                    flowIds.push(rows[i].flowId);
                }
            }
            if (exchangeIds.length < 1) {
                sweetAlert("", "只有申请状态数据才可以审核", "warning");
                return;
            }
            sweetAlert({
                title: "提示信息",
                text: "确认审核通过该项申请?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            }, function () {
                $.post("/wallet/draw/check", {
                    "exchangeId": exchangeIds[0],
                    "isok": "1",
                    "flowId": flowIds[0]
                }, function (data, status) {
                    if (status == "success") {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            // $("#walletCheckTable", panel).bootstrapTable('refresh');
                            $("#walletCheckTable", panel).bootstrapTable('selectPage', 1);
                            $('#addModal', panel).modal('hide');
                            base.success("审核通过！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });
        },
        batchcheckno: function (panel) {
            var rows = $('#walletCheckTable', panel).bootstrapTable('getSelections');
            if (rows.length < 1) {
                sweetAlert("", "请选择有效数据", "warning");
                return;
            }
            var exchangeIds = [];
            var flowIds = [];
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].state == 0 || rows[i].state == 1) {
                    exchangeIds.push(rows[i].exchangeId);
                    flowIds.push(rows[i].flowId);
                }
            }
            if (exchangeIds.length < 1) {
                sweetAlert("", "已审核失败或已到帐不可再审核", "warning");
                return;
            }
            sweetAlert({
                title: "提示信息",
                text: "确认驳回该项申请?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            }, function () {
                $.post("/wallet/draw/check", {
                    "exchangeId": exchangeIds[0],
                    "isok": "0",
                    "flowId": flowIds[0]
                }, function (data, status) {
                    if (status == "success") {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            // $("#walletCheckTable", panel).bootstrapTable('refresh');
                            $("#walletCheckTable", panel).bootstrapTable('selectPage', 1);
                            $('#addModal', panel).modal('hide');
                            base.success("审核不通过！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });
        },
    };
});