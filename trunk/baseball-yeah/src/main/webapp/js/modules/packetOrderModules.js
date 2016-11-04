define(
    ['base','packetStuManagerModules'],
    function (base,packetStuManager) {
        /**
         * 私有成员定义区域
         */
        var packetStuinit=function (){

            packetStuManager.init();
        }

        return {
            init: function (args) {
                // / <summary>
                // / 模块初始化方法
                // / </summary>
                // / <param name="args">初始化时传入的参数</param>
                var self = this;
                var date = new Date();
                // 开始时间
                $('#createStartDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#createEndDatePicker',args).datetimepicker('setStartDate', startTime);
                });

                $('#createEndDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#createStartDatePicker',args).datetimepicker('setEndDate', startTime);
                });

                // 结束时间
                $('#deliveryStartDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliveryEndDatePicker',args).datetimepicker('setStartDate', startTime);
                });

                // 结束时间
                $('#deliveryEndDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliveryStartDatePicker',args).datetimepicker('setEndDate', startTime);
                });

                // 结束时间
                $('#deliverySStartDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliverySEndDatePicker',args).datetimepicker('setStartDate', startTime);
                });

                // 结束时间
                $('#deliverySEndDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#deliverySStartDatePicker',args).datetimepicker('setEndDate', startTime);
                });

                base.datagrid(
                    {
                        url: '/order/user/getPacketOrder',
                        queryParams: function (params) {
                            return $.extend(params, {
                                createStartDate: $('#createStartDate',args)
                                    .val(),
                                createEndDate: $('#createEndDate',args)
                                    .val(),
                                deliveryStartDate: $(
                                    '#deliveryStartDate',args)
                                    .val(),
                                deliveryEndDate: $(
                                    '#deliveryEndDate',args)
                                    .val(),
                                deliverySStartDate: $(
                                    '#deliverySStartDate',args)
                                    .val(),
                                deliverySEndDate: $(
                                    '#deliverySEndDate',args)
                                    .val(),
                                orderId: $.trim($("#orderId",args)
                                    .val()),
                                state: $.trim($("#state",args)
                                    .val()),
                                payType: $.trim($("#payType",args)
                                    .val()),
                                payId: $.trim($("#payId",args)
                                    .val()),
                                phone: $.trim($("#phone",args)
                                    .val()),
                                collegeId: $.trim($("#collegeId",args)
                                    .val()),
                                mobile: $("#mobile",args).val()
                            });
                        },
                        onLoadSuccess : function(data){
                            $('.zbUserPhoneManager',args).click(function(){
                                var phone = $.trim($(this).text());
                                var title = $(this).attr('title');
                                var href = '/order/gotoPacketStuManager?phone='+phone;
                                base.openTab(title,href,packetStuinit);
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
                                field: 'lockState',
                                title: '是否锁定',
                                formatter: function (value, row, index) {
                                    switch (value) {
                                        case 0:
                                            return "否";
                                        case 1:
                                            return "收件人分单锁定";
                                        case 2:
                                            return "货源分单锁定";
                                        case 3:
                                            return "众包分单锁定";
                                        case 4:
                                            return "系统分单锁定";
                                        case 5:
                                            return "预支付未付款锁定";
                                    }
                                },
                                width: 80
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
                                formatter : function(value,row, index) {
                                    return "<a title='众包用户管理' href='#' class='zbUserPhoneManager'>"+value+"</a>";
                                },
                                /*formatter: function (value, row, index) {
                                    return "<a title='管理此用户' href='/order/gotoPacketStuManager?phone=" + value + "'>" + value + "</a>";
                                },*/
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
                    }, '#userTable',args);

                $.ajax({
                    type: "POST",
                    url: "/order/user/selectAllPayType",
                    dataType: "json",
                    success: function (data) {
                        $("#payType",args).select2({
                            data: data
                        });
                        $('#collegeId',args).select2("val", "");
                    }
                });

                $.ajax({
                    type: "POST",
                    url: "/manage/college/getCollageForSel",
                    dataType: "json",
                    success: function (data) {
                        $("#collegeId",args).select2({
                            data: data.data
                        });
                    }
                });

                $("#btn_add",args).click(function () {
                    self.add(args);
                });
                $("#btn_edit",args).click(function () {
                    self.edit(args);
                });
                $("#btn_delete",args).click(function () {
                    self.remove(args);
                });
                $("#btn_initPwd",args).click(function () {
                    self.initPwd(args);
                });
                $("#btn_query",args).click(function () {
                    // $("#userTable",args).bootstrapTable('refresh');
                    $("#userTable", args).bootstrapTable('selectPage', 1);
                });

                $("#clearSearch",args).click(function () {
                    base.reset(".main-box-body");
                    $('#payType',args).select2("val", "");
                    $('#collegeId',args).select2("val", "");
                });

                $('#addRole',args).select2({
                    placeholder: '请选择角色'
                });

                $('#addModal',args).on(
                    'shown.bs.modal',
                    function () {
                        $('#addForm',args).data('bootstrapValidator')
                            .resetForm(true);
                    })
            },
            add: function (args) {
                var self = this;
                $('#addLoginName',args).val('');
                $('#addUserName',args).val('');
                $('#addUserCode',args).val('');
                $('#addPassword',args).val('');
                $('#addRePassword',args).val('');
                $('#addDutyId',args).val('');
                $('.addGender',args).val('');
                $('#addPhone',args).val('');
                $('#addAddress',args).val('');
                $('#addModal',args).modal({
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
                                                var password = $('#addPassword',args)
                                                    .val();
                                                var rePassword = $('#addRePassword',args)
                                                    .val();
                                                return password == rePassword;
                                            }
                                        }
                                    }
                                }
                            }
                        }, "#addForm", self.create,args)
            },
            create: function (args) {
                $
                    .post(
                        "/user/managers/add",
                        {
                            "loginName": $("#addLoginName",args).val(),
                            "userName": $("#addUserName",args).val(),
                            "contactTel": $("#addPhone",args).val(),
                            "password": $("#addPassword",args).val(),
                            "address": $("#addAddress",args).val(),
                            "gender": $("#addMan",args).prop('checked') ? "p_gender_male"
                                : "p_gender_female",
                            "roleIds": $("#addRole",args).val() == null ? ""
                                : $("#addRole",args).val().join(","),
                            "userCode": $("#addUserCode",args).val(),
                            "dutyId": $("#addDutyId",args).val() == "请选择" ? ""
                                : $("#addDutyId",args).val(),
                            "deptId": $("#addDeptId",args).val() == "请选择" ? ""
                                : $("#addDeptId",args).val()
                        }, function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    base.success("增加成功");
                                    // $("#userTable",args).bootstrapTable('refresh');
                                    $("#userTable", args).bootstrapTable('selectPage', 1);
                                    $('#addModal',args).modal('hide');
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                            }
                        });
            },
            edit: function (args) {
                var self = this;
                var arrselections = $("#userTable",args).bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                    return;
                }
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                $('#editUserManagerId',args).val(arrselections[0].userManagerId);
                $("#editLoginName",args).val(arrselections[0].loginName);
                $("#editUserName",args).val(arrselections[0].userName);
                $("#editAddress",args).val(arrselections[0].address);
                $("#editUserCode",args).val(arrselections[0].userCode);
                $("#editDeptId",args).val(arrselections[0].deptId).trigger(
                    "change");
                $("#editDutyId",args).val(arrselections[0].dutyId).trigger(
                    "change");
                $("#editPhone",args).val(arrselections[0].contactTel);
                $("#editBeEnabled",args).val(arrselections[0].beEnabled);
                $("#editRole",args).val(arrselections[0].roleIds).trigger(
                    "change");

                if (arrselections[0].gender == "男") {
                    $("#editMan",args).prop("checked", "checked");
                } else {
                    $("#editWoman",args).prop("checked", "checked");
                }
                $('#editModal',args).modal({
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
                }, "#editForm", self.update,args)
            },
            update: function (args) {
                $
                    .post(
                        "/user/managers/updateManager",
                        {
                            "userManagerId": $(
                                "#editUserManagerId",args).val(),
                            "userName": $("#editUserName",args).val(),
                            "userCode": $("#editUserCode",args).val(),
                            "address": $("#editAddress",args).val(),
                            "beEnabled": $("#editBeEnabled",args).val(),
                            "roleIds": $("#editRole",args).val() == null ? ""
                                : $("#editRole",args).val()
                                .join(","),
                            "contactTel": $("#editPhone",args).val(),
                            "dutyId": $("#editDutyId",args).val() == "请选择" ? ""
                                : $("#editDutyId",args).val(),
                            "deptId": $("#editDeptId",args).val() == "请选择" ? ""
                                : $("#editDeptId",args).val(),
                            "gender": $("#editMan",args)
                                .prop('checked') ? "p_gender_male"
                                : "p_gender_female"
                        },
                        function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    base.success("编辑成功");
                                    // $("#userTable",args).bootstrapTable('refresh');
                                    $("#userTable", args).bootstrapTable('selectPage', 1);
                                    $('#editModal',args).modal('hide');
                                    $('#editForm',args).data(
                                        'bootstrapValidator')
                                        .resetForm(true);
                                    $('#addForm',args).data(
                                        'bootstrapValidator')
                                        .resetForm(true);
                                } else {
                                    base.error(data.message);
                                    $('#editForm',args).find(
                                        ".btn-primary")
                                        .removeAttr("disabled");
                                }
                            } else {
                                base.error("更新失败!");
                            }
                        });
            },
            remove: function (args) {
                var arrselections = $("#userTable",args).bootstrapTable(
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
                                                // $("#userTable",args).bootstrapTable('refresh');
                                                $("#userTable", args).bootstrapTable('selectPage', 1);
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
            initPwd: function (args) {
                var arrselections = $("#userTable",args).bootstrapTable(
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
                                // $("#userTable",args).bootstrapTable('refresh');
                                $("#userTable", args).bootstrapTable('selectPage', 1);
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

function showDetail(orderId,args) {
    $('#detailMoblile',args).val("");
    $('#detailOrderId',args).val("");
    $('#detailRealName',args).val("");
    $('#detailWaybillNo',args).val("");
    $('#detailSex',args).val("");
    $('#detailState',args).val("");
    $('#detailCityName',args).val("");
    $('#detailAddress',args).val("");
    $('#detailCollegeName',args).val("");
    $('#detailStoreName',args).val("");
    $('#detailLocation',args).val("");
    $('#detailTotalMoney',args).val("");
    $('#detailRebateMoney',args).val("");
    $('#detailFinalMoney',args).val("");
    $('#detailPayMoney',args).val("");

    $.post("/order/user/orderView", {
        "orderId": orderId
    }, function (data, status) {
        if (status == "success") {
            $('#detailMoblile',args).val(data.mobile);
            $('#detailOrderId',args).val(orderId);
            $('#detailRealName',args).val(data.consignee);
            $('#detailWaybillNo',args).val(data.waybillNo);
            if (data.sex == "p_gender_male") {
                $('#detailSex',args).val("男");
            } else if (data.sex == "p_gender_female") {
                $('#detailSex',args).val("女");
            } else if (data.sex == "p_gender_secret") {
                $('#detailSex',args).val("保密");
            }
            $('#detailState',args).val(data.stateStr);
            $('#detailCityName',args).val(data.cityName);
            $('#detailAddress',args).val(data.address);
            $('#detailCollegeName',args).val(data.fullName);
            $('#detailStoreName',args).val(data.storeName);
            $('#detailLocation',args).val(data.location);
            $('#detailTotalMoney',args).val(data.totalMoney);
            $('#detailRebateMoney',args).val(data.rebateMoney);
            $('#detailFinalMoney',args).val(data.finalMoney);
            $('#detailPayMoney',args).val(data.payMoney);
        } else {
            base.error("初始化失败!");
        }
    });

    $('#detailTable',args).bootstrapTable({
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

    $('#detailModal',args).modal();
}