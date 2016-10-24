define(['base'],
    function (base) {
        /**
         * 私有成员定义区域
         */

        return {
            init: function (args) {
                var self = this;
                var date = new Date();
                // 开始时间
                $('#createStartDatePicker').datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#createEndDatePicker').datetimepicker('setStartDate', startTime);
                });

                $('#createEndDatePicker').datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#createStartDatePicker').datetimepicker('setEndDate', startTime);
                });

                // 结束时间
                $('#deliveryStartDatePicker').datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliveryEndDatePicker').datetimepicker('setStartDate', startTime);
                });

                // 结束时间
                $('#deliveryEndDatePicker').datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliveryStartDatePicker').datetimepicker('setEndDate', startTime);
                });

                // 结束时间
                $('#deliverySStartDatePicker').datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliverySEndDatePicker').datetimepicker('setStartDate', startTime);
                });

                // 结束时间
                $('#deliverySEndDatePicker').datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliverySStartDatePicker').datetimepicker('setEndDate', startTime);
                });

                base.datagrid(
                    {
                        url: '/order/user/getPickupOrder',
                        queryParams: function (params) {
                            return $.extend(params, {
                                createStartDate: $('#createStartDate').val(),
                                createEndDate: $('#createEndDate').val(),
                                deliveryStartDate: $('#deliveryStartDate').val(),
                                deliveryEndDate: $('#deliveryEndDate').val(),
                                deliverySStartDate: $('#deliverySStartDate').val(),
                                deliverySEndDate: $('#deliverySEndDate').val(),
                                orderId: $.trim($("#orderId").val()),
                                state: $.trim($("#state").val()),
                                payType: $.trim($("#payType").val()),
                                payId: $.trim($("#payId").val()),
                                phone: $.trim($("#phone").val()),
                                collegeId: $.trim($("#collegeId").val()),
                                mobile: $("#mobile").val()
                            });
                        },
                        columns: [
                            {
                                checkbox: true
                            },
                            {
                                field: 'orderId',
                                title: '订单号',
                                sortable: true,
                                formatter: function (value, row, index) {
                                    return "<a href='#' onclick='showDetail(\"" + value + "\");'>" + value + "</a>";
                                },
                                width: 400
                            },
                            {
                                field: 'state',
                                title: '订单状态',
                                formatter: function (value, row, index) {
                                    if (value == "1") {
                                        return "<a class='label label-primary'>创建</a>";
                                    } else if (value == "2") {
                                        return "<a class='label label-success'>已接单</a>";
                                    } else if (value == "3") {
                                        return "<a class='label label-info'>配送中</a>";
                                    } else if (value == "4") {
                                        return "<a class='label label-info'>处理中</a>";
                                    } else if (value == "5") {
                                        return "<a class='label label-success'>完成</a>";
                                    } else if (value == "6") {
                                        return "<a class='label label-default'>取消</a>";
                                    } else if (value == "7") {
                                        return "<a class='label label-danger'>异常</a>";
                                    }
                                },
                                sortable: true,
                                width: 400
                            },
                            {
                                field: 'payType',
                                title: '支付方式',
                                sortable: true,
                                formatter: function (value, row, index) {
                                    if (value == "1") {
                                        return "<a class='label label-primary'>微信</a>";
                                    } else if (value == "2") {
                                        return "<a class='label label-success' >支付宝</a>";
                                    } else if (value == "3") {
                                        return "<a class='label label-info' >指端支付</a>";
                                    } else {
                                        return "<a class='label label-default' >未知方式</a>";
                                    }
                                },
                                visible: true,
                                width: 400
                            }, {
                                field: 'finalMoney',
                                title: '最终金额',
                                formatter: function (value, row, index) {
                                    return value == "" ? "" : value + "元";
                                },
                                align: 'center',
                                sortable: true,
                                width: 400
                            }, {
                                field: 'payId',
                                title: '交易流水',
                                formatter: function (value, row, index) {
                                    return value == 0 ? '' : value;
                                },
                                align: 'center',
                                sortable: true,
                                width: 400
                            }, {
                                field: 'fullName',
                                title: '学校',
                                align: 'center',
                                sortable: true,
                                width: 400
                            }, {
                                field: 'phone',
                                title: '众包人手机号码',
                                formatter: function (value, row, index) {
                                    return "<a title='管理此用户' href='/order/gotoPacketStuManager?phone=" + value + "'>" + value + "</a>";
                                },
                                align: 'center',
                                sortable: true,
                                width: 400
                            }, {
                                field: 'realName',
                                title: '众包人员姓名',
                                align: 'center',
                                sortable: true,
                                width: 400
                            },
                            {
                                field: 'createDate',
                                title: '创建日期',
                                sortable: true,
                                align: 'center',
                                width: 400
                            }, {
                                field: 'deliveryStartDate',
                                title: '确认取件时间',
                                sortable: true,
                                align: 'center',
                                width: 400
                            }, {
                                field: 'deliveryEndDate',
                                title: '签收时间',
                                sortable: true,
                                align: 'center',
                                width: 400
                            }, {
                                field: 'mobile',
                                title: '收件人号码',
                                align: 'center',
                                sortable: true,
                                width: 400
                            }, {
                                field: 'consignee',
                                title: '收件人姓名',
                                align: 'center',
                                sortable: true,
                                width: 400
                            },{
                                field : 'commodityCode',
                                title : '代金券id',
                                align:'center',
                                formatter : function(value,row, index) {
                                    if (row.type !=null && row.type=="1008"){
                                        return value;
                                    }else {
                                        return "未使用代金券";
                                    }
                                },
                                sortable : true,
                                width : 400
                            },{
                                field : 'rebatePrice',
                                title : '代金券金额',
                                formatter : function(value,row, index) {
                                    if (row.type !=null && row.type=="1008"){
                                        return value+"元";
                                    }else {
                                        return "";
                                    }
                                },
                                align:'center',
                                sortable : true,
                                width : 400
                            }
                        ]
                    }, '#userTable');

                $.ajax({
                    type: "POST",
                    url: "/order/user/selectAllPayType",
                    dataType: "json",
                    success: function (data) {
                        $("#payType").select2({
                            data: data
                        });
                        $('#collegeId').select2("val", "");
                    }
                });

                $.ajax({
                    type: "POST",
                    url: "/manage/college/getCollageForSel",
                    dataType: "json",
                    success: function (data) {
                        $("#collegeId").select2({
                            data: data.data
                        });
                    }
                });

                $("#btn_query").click(function () {
                    $("#userTable").bootstrapTable('refresh');
                });

                $("#clearSearch").click(function () {
                    base.reset(".main-box-body");
                    $('#payType').select2("val", "");
                    $('#collegeId').select2("val", "");
                });

                $('#addRole').select2({
                    placeholder: '请选择角色'
                });

                $('#addModal').on('shown.bs.modal',
                    function () {
                        $('#addForm').data('bootstrapValidator').resetForm(true);
                    })
            },

        };
    });

function showDetail(orderId) {
    $('#detailMoblile').val("");
    $('#detailOrderId').val("");
    $('#detailRealName').val("");
    $('#detailWaybillNo').val("");
    $('#detailSex').val("");
    $('#detailState').val("");
    $('#detailCityName').val("");
    $('#detailAddress').val("");
    $('#detailCollegeName').val("");
    $('#detailStoreName').val("");
    $('#detailLocation').val("");
    $('#detailTotalMoney').val("");
    $('#detailRebateMoney').val("");
    $('#detailFinalMoney').val("");
    $('#detailPayMoney').val("");

    $.post("/order/user/orderView1", {
        "orderId": orderId
    }, function (data, status) {
        if (status == "success") {
            data = json_parse(data);
            $('#detailMoblile').val(data.mobile);
            $('#detailOrderId').val(data.orderId);
            $('#detailRealName').val(data.consignee);
            $('#detailWaybillNo').val(data.waybillNo);
            if (data.sex == "p_gender_male") {
                $('#detailSex').val("男");
            } else if (data.sex == "p_gender_female") {
                $('#detailSex').val("女");
            } else if (data.sex == "p_gender_secret") {
                $('#detailSex').val("保密");
            }
            $('#detailState').val(data.stateStr);
            $('#detailCityName').val(data.cityName);
            $('#detailAddress').val(data.address);
            $('#detailCollegeName').val(data.fullName);
            $('#detailStoreName').val(data.storeName);
            $('#detailLocation').val(data.location);
            $('#detailTotalMoney').val(data.totalMoney);
            $('#detailRebateMoney').val(data.rebateMoney);
            $('#detailFinalMoney').val(data.finalMoney);
            $('#detailPayMoney').val(data.payMoney);
        } else {
            base.error("初始化失败!");
        }
    });

    $('#detailTable').bootstrapTable({
        // 请求后台的URL（*）
        url: '/order/user/orderDetail',
        striped: true, // 是否显示行间隔色
        singleSelect: true,
        queryParams: function (pa) {
            return {"orderId": orderId};
        },
        height: 320,
        columns: [{
            field: 'createDateStr',
            title: '操作时间',
            width: 100
        }, {
            field: 'content',
            title: '内容',
            width: 100
        }
        ]
    });//.bootstrapTable('refresh', {query: {"orderId": orderId}});

    $('#detailModal').modal();
}