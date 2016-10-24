define(
    ['base'],
    function (base) {
        /**
         * 私有成员定义区域
         */

        return {
            init: function (args) {
                // / <summary>
                // / 模块初始化方法
                // / </summary>
                // / <param name="args">初始化时传入的参数</param>
                var self = this;
                var date = new Date();
                // 开始时间
                $('#createStartDatePicker').datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
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
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
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
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
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
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
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
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliverySStartDatePicker').datetimepicker('setEndDate', startTime);
                });

                base.datagrid(
                    {
                        url: '/order/user/getPacketOrder',
                        queryParams: function (params) {
                            return $.extend(params, {
                                createStartDate: $('#createStartDate')
                                    .val(),
                                createEndDate: $('#createEndDate')
                                    .val(),
                                deliveryStartDate: $(
                                    '#deliveryStartDate')
                                    .val(),
                                deliveryEndDate: $(
                                    '#deliveryEndDate')
                                    .val(),
                                deliverySStartDate: $(
                                    '#deliverySStartDate')
                                    .val(),
                                deliverySEndDate: $(
                                    '#deliverySEndDate')
                                    .val(),
                                orderId: $.trim($("#orderId")
                                    .val()),
                                state: $.trim($("#state")
                                    .val()),
                                payType: $.trim($("#payType")
                                    .val()),
                                payId: $.trim($("#payId")
                                    .val()),
                                phone: $.trim($("#phone")
                                    .val()),
                                collegeId: $.trim($("#collegeId")
                                    .val()),
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
                                formatter: function (value,
                                                     row, index) {
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
                                formatter: function (value,
                                                     row, index) {
                                    if (value == "1") {
                                        return "<a class='label label-primary'>微信</a>";
                                    } else if (value == "2") {
                                        return "<a class='label label-success' >支付宝</a>";
                                    } else if (value == "3") {
                                        return "<a class='label label-info' >指端支付</a>";
                                    } else if (value == "4") {
                                        return "<a class='label label-warning' >现金</a>";
                                    } else {
                                        return "<a class='label label-default' >未知方式</a>";
                                    }
                                },
                                visible: true,
                                width: 400
                            }, {
                                field: 'finalMoney',
                                title: '最终金额',
                                formatter: function (value,
                                                     row, index) {
                                    return value == "" ? "" : value + "元";
                                },
                                align: 'center',
                                sortable: true,
                                width: 400
                            }, {
                                field: 'payId',
                                title: '交易流水',
                                formatter: function (value,
                                                     row, index) {
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
                            /*
                             * { field : 'beEnabled', title :
                             * '是否启用', formatter : function(value,
                             * row, index) { return value == "1" ? "<a
                             * class='label label-success'>启用</a>" : "<a
                             * class='label label-warning' >禁用</a>"; },
                             * sortable : true, width:100 }
                             */
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

                $("#btn_add").click(function () {
                    self.add();
                });
                $("#btn_edit").click(function () {
                    self.edit();
                });
                $("#btn_delete").click(function () {
                    self.remove();
                });
                $("#btn_initPwd").click(function () {
                    self.initPwd();
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

                $('#addModal').on(
                    'shown.bs.modal',
                    function () {
                        $('#addForm').data('bootstrapValidator')
                            .resetForm(true);
                    })
            },
            add: function () {
                var self = this;
                $('#addLoginName').val('');
                $('#addUserName').val('');
                $('#addUserCode').val('');
                $('#addPassword').val('');
                $('#addRePassword').val('');
                $('#addDutyId').val('');
                $('.addGender').val('');
                $('#addPhone').val('');
                $('#addAddress').val('');
                $('#addModal').modal({
                    keyboard: false,
                    backdrop: 'static'
                });
                base
                    .validator(
                        {
                            message: 'This value is not valid',
                            feedbackIcons: {
                                valid: 'glyphicon glyphicon-ok',
                                invalid: 'glyphicon glyphicon-remove',
                                validating: 'glyphicon glyphicon-refresh'
                            },
                            fields: {
                                addLoginName: {
                                    validators: {
                                        notEmpty: {
                                            message: '账号不能为空'
                                        },
                                        stringLength: {
                                            min: 0,
                                            max: 25,
                                            message: '账号长度不能超过25'
                                        }
                                    }
                                },
                                addUserName: {
                                    validators: {
                                        notEmpty: {
                                            message: '用户名不能为空'
                                        },
                                        stringLength: {
                                            min: 0,
                                            max: 50,
                                            message: '用户名长度不能超过25'
                                        }
                                    }
                                },
                                addPhone: {
                                    validators: {
                                        regexp: {
                                            regexp: /^[1]+[3,5,7,8]+\d{9}$/,
                                            message: '手机号格式不正确'
                                        }
                                    }
                                },
                                addUserCode: {
                                    validators: {
                                        notEmpty: {
                                            message: '工号不能为空'
                                        }
                                    }
                                },
                                addPassword: {
                                    validators: {
                                        notEmpty: {
                                            message: '密码不能为空'
                                        },
                                        stringLength: {
                                            min: 6,
                                            max: 12,
                                            message: '密码6~12位'
                                        }
                                    }
                                },
                                addRePassword: {
                                    validators: {
                                        notEmpty: {
                                            message: '请再次输入密码'
                                        },
                                        callback: {
                                            message: '两次输入密码不一致',
                                            callback: function (value,
                                                                validator) {
                                                var password = $(
                                                    '#addPassword')
                                                    .val();
                                                var rePassword = $(
                                                    '#addRePassword')
                                                    .val();
                                                return password == rePassword;
                                            }
                                        }
                                    }
                                }
                            }
                        }, "#addForm", self.create)
            },
            create: function () {
                $
                    .post(
                        "/user/managers/add",
                        {
                            "loginName": $("#addLoginName").val(),
                            "userName": $("#addUserName").val(),
                            "contactTel": $("#addPhone").val(),
                            "password": $("#addPassword").val(),
                            "address": $("#addAddress").val(),
                            "gender": $("#addMan").prop('checked') ? "p_gender_male"
                                : "p_gender_female",
                            "roleIds": $("#addRole").val() == null ? ""
                                : $("#addRole").val().join(","),
                            "userCode": $("#addUserCode").val(),
                            "dutyId": $("#addDutyId").val() == "请选择" ? ""
                                : $("#addDutyId").val(),
                            "deptId": $("#addDeptId").val() == "请选择" ? ""
                                : $("#addDeptId").val()
                        }, function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    base.success("增加成功");
                                    $("#userTable").bootstrapTable(
                                        'refresh');
                                    $('#addModal').modal('hide');
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                            }
                        });
            },
            edit: function () {
                var self = this;
                var arrselections = $("#userTable").bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                    return;
                }
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                $('#editUserManagerId').val(arrselections[0].userManagerId);
                $("#editLoginName").val(arrselections[0].loginName);
                $("#editUserName").val(arrselections[0].userName);
                $("#editAddress").val(arrselections[0].address);
                $("#editUserCode").val(arrselections[0].userCode);
                $("#editDeptId").val(arrselections[0].deptId).trigger(
                    "change");
                $("#editDutyId").val(arrselections[0].dutyId).trigger(
                    "change");
                $("#editPhone").val(arrselections[0].contactTel);
                $("#editBeEnabled").val(arrselections[0].beEnabled);
                $("#editRole").val(arrselections[0].roleIds).trigger(
                    "change");

                if (arrselections[0].gender == "男") {
                    $("#editMan").prop("checked", "checked");
                } else {
                    $("#editWoman").prop("checked", "checked");
                }
                $('#editModal').modal({
                    keyboard: false,
                    backdrop: 'static'
                });

                base.validator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        editUserName: {
                            validators: {
                                notEmpty: {
                                    message: '用户名不能为空'
                                }
                            }
                        },
                        editUserCode: {
                            validators: {
                                notEmpty: {
                                    message: '用户工号不能为空'
                                }
                            }
                        },
                        editPhone: {
                            validators: {
                                regexp: {
                                    regexp: /^[1]+[3,5,7,8]+\d{9}$/,
                                    message: '手机号格式不正确'
                                }
                            }
                        }
                    }
                }, "#editForm", self.update)
            },
            update: function () {
                $
                    .post(
                        "/user/managers/updateManager",
                        {
                            "userManagerId": $(
                                "#editUserManagerId").val(),
                            "userName": $("#editUserName").val(),
                            "userCode": $("#editUserCode").val(),
                            "address": $("#editAddress").val(),
                            "beEnabled": $("#editBeEnabled").val(),
                            "roleIds": $("#editRole").val() == null ? ""
                                : $("#editRole").val()
                                .join(","),
                            "contactTel": $("#editPhone").val(),
                            "dutyId": $("#editDutyId").val() == "请选择" ? ""
                                : $("#editDutyId").val(),
                            "deptId": $("#editDeptId").val() == "请选择" ? ""
                                : $("#editDeptId").val(),
                            "gender": $("#editMan")
                                .prop('checked') ? "p_gender_male"
                                : "p_gender_female"
                        },
                        function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    base.success("编辑成功");
                                    $("#userTable").bootstrapTable(
                                        'refresh');
                                    $('#editModal').modal('hide');
                                    $('#editForm').data(
                                        'bootstrapValidator')
                                        .resetForm(true);
                                    $('#addForm').data(
                                        'bootstrapValidator')
                                        .resetForm(true);
                                } else {
                                    base.error(data.message);
                                    $('#editForm').find(
                                        ".btn-primary")
                                        .removeAttr("disabled");
                                }
                            } else {
                                base.error("更新失败!");
                            }
                        });
            },
            remove: function () {
                var arrselections = $("#userTable").bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    base.error("只能选择一行进行编辑!");
                    return;
                }
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                var userManagerId = arrselections[0].userManagerId;

                base
                    .cancel(
                        {
                            title: "注销用户",
                            text: "您确定要注销此用户吗？"
                        },
                        function () {
                            $
                                .post(
                                    "/user/managers/cancelManagerUser",
                                    {
                                        "userManagerId": userManagerId
                                    },
                                    function (data, status) {
                                        if (status == "success") {
                                            var obj = JSON
                                                .parse(data);
                                            if (obj.success == 0) {
                                                base
                                                    .success("删除成功");
                                                $(
                                                    "#userTable")
                                                    .bootstrapTable(
                                                        'refresh');
                                            } else {
                                                base
                                                    .error(obj.message);
                                            }
                                        } else {
                                            base
                                                .error("注销失败");
                                        }
                                    });
                        });
            },
            initPwd: function () {
                var arrselections = $("#userTable").bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    base.error("只能选择一行进行编辑!");
                    return;
                }
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                var userManagerId = arrselections[0].userManagerId;
                base.cancel({
                    title: "初始化密码",
                    text: "您确定要初始化密码吗？"
                }, function () {
                    $.post("/user/managers/initManagerPwd", {
                        "userManagerId": userManagerId
                    }, function (data, status) {
                        if (status == "success") {
                            var obj = JSON.parse(data);
                            if (obj.success == 0) {
                                base.success("初始化密码为：111111，成功");
                                $("#userTable").bootstrapTable('refresh');
                            } else {
                                base.error(obj.message);
                            }
                        } else {
                            base.error("初始化失败!");
                        }
                    });
                });
            }
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

    $.post("/order/user/orderView", {
        "orderId": orderId
    }, function (data, status) {
        if (status == "success") {
            $('#detailMoblile').val(data.mobile);
            $('#detailOrderId').val(orderId);
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
    }).bootstrapTable('refresh', {query: {"orderId": orderId}});

    $('#detailModal').modal();
}