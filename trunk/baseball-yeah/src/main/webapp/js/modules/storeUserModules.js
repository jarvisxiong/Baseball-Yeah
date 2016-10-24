define(['base', 'auditUserModules'], function (base, audit) {
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

            base.datagrid({
                url: '/user/GridData',
                method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            beSupervisor: $("#beSupervisor").val(),
                            realName: $("#realName").val(),
                            storeName: $("#storeName").val(),
                            idNo: $("#idNo").val(),
                            verifyStatus: $("#verifyStatus").val(),
                            cityId: $("#selcity").val(),
                            phone: $("#phone").val(),
                            storeId: $("#selstore").val() == "请选择" ? "" : $("#selstore").val(),
                            startDate: $('#startDate').val(),
                            endDate: $('#endDate').val() == "" ? "" : $('#endDate').val() + " 23:59:59",
                            expressId: $('#expressId').val()
                        });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'phone',
                        title: '手机号',
                        sortable: true,
                        width: 150
                    }
                    ,
                    {
                        field: 'realName',
                        title: '真实姓名',
                        sortable: true,
                        width: 150
                    }
                    , {
                        field: 'idNo',
                        title: '身份证号',
                        sortable: true,
                        width: 150
                    }
                    ,
                    {
                        field: 'signupTime',
                        title: '注册时间',
                        sortable: true,
                        width: 150
                    }
                    ,
                    {
                        field: 'cityName',
                        title: '城市',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'collegeFullName',
                        title: '校区',
                        sortable: true,
                        class: 'fixed-col-widths'
                    },
                    {
                        field: 'companyFullName',
                        title: '快递品牌',
                        sortable: true,
                        width: 150
                    },

                    {
                        field: 'storeCode',
                        title: '站点编号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'storeName',
                        title: '快递站点',
                        sortable: true,
                        width: 150
                    }, {
                        field: 'storeLocation',
                        title: '站点地址',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'verifyStatus',
                        title: '实名审核状态',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "未完善";
                                case 1:
                                    return "审核中";
                                case 2:
                                    return "审核通过";
                                case 3:
                                    return "审核失败";
                            }
                        },
                        align: 'center', sortable: true,
                        width: 150
                    },
                    {
                        field: 'auditDetail',
                        title: '审核详细',
                        align: 'center',
                        formatter: function () {
                            return '<a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span></a>';
                        },
                        width: 150
                    },
                    {
                        field: 'beSupervisor',
                        title: '角色',
                        formatter: function (value,
                                             row, index) {
                            return value == "1" ? "<a class='label label-success'>负责人 </a>"
                                : "<a class='label label-warning' >员工</a>";
                        }, align: 'center',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'sms',
                        title: '使用状态',
                        align: 'center',
                        formatter: function () {
                            return '<a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span></a>';
                        },
                        width: 150
                    }
                    ,
                    {
                        field: 'message',
                        title: '短信模板',
                        align: 'center',
                        formatter: function () {
                            return '<a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span></a>';
                        },
                        width: 150
                    },
                    {
                        field: 'userId',
                        title: 'userId',
                        visible: false,
                        width: 150
                    }
                ],
                onClickCell: function (field, value, row) {
                    switch (field) {
                        case "message":
                            self.messageTemplate(row);
                            break;
                        case "sms":
                            self.sms(row);
                            break;
                        case"auditDetail":

                            if (row.beSupervisor == 0) {
                                base.error("请选择负责人!");
                                return;
                            }
                            if (row.verifyStatus == 0) {
                                base.error("审核未提交!");
                                return;
                            }
                            $.post("/user/exp/getauditinfo", {
                                "userId": row.userId
                            }, function (data) {
                                if (data.success == 0) {
                                    audit.getUserInfo(data.data);
                                }
                                else {
                                    base.error(data.message);
                                }
                            });
                            break;
                    }
                }
            }, '#userTable');
            $('#auditcollage').bootstrapTable({
                data:[],
                height: 200
            });
            /**
             加载合作公司
             */
            $.ajax({
                url: "/manage/express/queryenabledforsel",
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    $("#expressId").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#expressId').select2("val", null);
                }
            });

            $.ajax({
                type: "POST",
                url: "/store/exp/storecodeinfo",
                dataType: "json",
                success: function (data) {
                    $("#selstore").select2({
                        data: data
                    });
                }
            });
            $.ajax({
                type: "POST",
                url: "/store/exp/expstoreinfo",
                dataType: "json",
                success: function (data) {

                    $("#add_store").select2({
                        data: data
                    });
                    $("#edit_store").select2({
                        data: data
                    });
                }
            });

            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    $("#selcity").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcity').select2("val", null);
                }
            });

            //开始时间
            $('#starttimePicker').datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2
            })

            //结束时间
            $('#endtimePicker').datetimepicker(
                {
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
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
            $("#btn_audit").click(function () {
                self.audit();
            });

            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
                $('#expressId').select2("val", null);
                $('#selcity').select2("val", null);
                $("#selstore").val(" ").trigger("change");
            });

            $('#addModal').on('shown.bs.modal', function () {
                $("#add_store").val(" ").trigger("change");
                $('#addForm').data('bootstrapValidator').resetForm(true);
            })

        },
        add: function () {
            var self = this;
            $('#addModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                    add_store: {
                        validators: {
                            notEmpty: {
                                message: '快递站点不能为空'
                            }
                        }
                    },
                    add_userName: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    add_phone: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^[1]+[3,4,5,7,8]+\d{9}$/,
                                message: '手机号格式不正确'
                            }
                        }
                    },
                    add_pwd: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            }
                        }
                    }
                }
            }, "#addForm", self.create)
        },
        create: function () {
            $.post("insertselective",
                {
                    "nickname": $(
                        "#add_nickname")
                        .val(),
                    "phone": $(
                        "#add_phone")
                        .val(),
                    "accountPwd": $(
                        "#add_pwd")
                        .val(),
                    "gender": $(
                        "#addMan")
                        .prop(
                            'checked') ? "p_gender_male"
                        : "p_gender_female",
                    "beSupervisor": $("#addYSupervisor").prop('checked') ? "1" : "0",
                    "storeId": $("#add_store").val() == "请选择" ? "" : $("#add_store").val()
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#userTable").bootstrapTable('refresh');
                            $('#addModal').modal('hide');
                            $("#add_store").val("").trigger("change");
                            base.success("添加成功！")
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
            var arrselections = $("#userTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $("#edit_nickname").val(arrselections[0].nickname);
            $("#edit_userName").val(arrselections[0].userName);
            $("#edit_phone").val(arrselections[0].phone);
            $("#userId").val(arrselections[0].userId);

            $("#edit_idNo").val(arrselections[0].idNo);
            $("#edit_realName").val(arrselections[0].realName);
            if (arrselections[0].verifyStatus != 2) {
                if (arrselections[0].idNo=="") {
                    $("#edit_idNo").removeAttr("readonly");
                }else {
                    $("#edit_idNo").attr("readonly","readonly");
                }
                if (arrselections[0].realName=="") {
                    $("#edit_realName").removeAttr("readonly");
                }else {
                    $("#edit_realName").attr("readonly","readonly");
                }
            }else {
                $("#edit_idNo").attr("readonly","readonly");
                $("#edit_realName").attr("readonly","readonly");
            }


            if (arrselections[0].gender) {
                $("#editMan").prop("checked", arrselections[0].gender == "p_gender_male" ? true : false);
                $("#editWoman").prop("checked", arrselections[0].gender == "p_gender_male" ? false : true);
            }

            $("#editYSupervisor").prop("checked", arrselections[0].beSupervisor == 1 ? true : false);
            $("#editNSupervisor").prop("checked", arrselections[0].beSupervisor == 1 ? false : true);

            $('#editModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            $("#edit_store").val(arrselections[0].storeId).trigger("change");
            base.validator({
                fields: {
                    edit_store: {
                        validators: {
                            notEmpty: {
                                message: '快递站点不能为空'
                            }
                        }
                    },
                    edit_userName: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    edit_phone: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^[1]+[3,4,5,7,8]+\d{9}$/,
                                message: '手机号格式不正确'
                            }
                        }
                    }
                }
            }, '#editForm', self.update)

        },
        update: function () {
            $.post(
                "updatebyprimarykeyselective",
                {
                    "nickname": $("#edit_nickname").val(),
                    "userName": $("#edit_userName").val(),
                    "phone": $("#edit_phone").val(),
                    "accountPwd": $("#edit_pwd").val(),
                    "gender": $("#editMan").prop('checked') ? "p_gender_male" : "p_gender_female",
                    "beSupervisor": $("#editYSupervisor").prop('checked') ? "1" : "0",
                    "storeId": $("#edit_store").val(),
                    "userId": $("#userId").val(),
                    "idNo": $("#edit_idNo").val(),
                    "realName": $("#edit_realName").val()
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#userTable").bootstrapTable('refresh');
                            $('#editModal').modal('hide');
                            $("#edit_store").val(" ").trigger("change");
                            $('#editForm').data('bootstrapValidator').resetForm(true);
                            base.success("更新成功！")
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
        },
        remove: function () {
            var arrselections = $("#userTable").bootstrapTable('getSelections');

            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var userInfos = [];
            for (var i = 0; i < arrselections.length; i++) {
                userInfos.push(arrselections[i].userId);
            }

            base.cancel({title: "注销用户", text: "您确定要注销此用户吗？"}, function () {
                $.post("cancelUser", {"userIds": userInfos.join(',')}
                    , function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                $("#userTable").bootstrapTable('refresh');
                                base.success("注销成功！")
                            } else {
                                base.error(data.message);
                            }
                        } else {
                            base.error("注销失败");
                        }
                    });
            });
        },
        initPwd: function () {
            var arrselections = $("#userTable")
                .bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var userName = arrselections[0].userName;
            base.cancel({title: "重置密码", text: "您确定要重置密码吗？"}, function () {
                $.post("initPwd", {
                    "userName": userName
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#userTable").bootstrapTable('refresh');
                            base.success("重置成功！")
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("重置失败!");
                    }
                });
            });
        },
        audit: function () {

        },
        messageTemplate: function (row) {
            BootstrapDialog.show({
                title: '短信模板',
                message: $("#divdialog").html(),
                cssClass: 'login-dialog',
                onshown: function () {

                    var dialogdom = $("#" + $(this)[0].id);
                    base.datagrid({
                        height: 300,
                        url: '/order/verify/getUserSmsModel',
                        method: 'post',
                        toolbar: $("#" + $(this)[0].id).find("#messageToolbar"),
                        queryParams: function (params) {
                            return $.extend(params,
                                {
                                    userId: row.userId,
                                    templateName: dialogdom.find("#templateName").val()
                                });
                        },
                        singleSelect: false,
                        columns: [
                            {
                                field: 'templateName',
                                title: '模板名称',
                                sortable: true,
                                width: 150
                            }
                            ,
                            {
                                field: 'modelContent',
                                title: '模板内容',
                                sortable: true,
                                width: 150
                            }
                        ]
                    }, dialogdom.find("#message"));


                    dialogdom.find("#mes_query").click(function () {
                        dialogdom.find("#message").bootstrapTable('refresh');
                    });
                },
                buttons: [{
                    label: '关闭!',
                    cssClass: 'btn-primary',
                    action: function (dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        sms: function (row) {
            BootstrapDialog.show({
                title: '使用状态',
                message: $("#divdialogsms").html(),
                cssClass: 'login-dialog',
                onshown: function () {
                    var dialogdom = $("#" + $(this)[0].id);
                    base.datagrid({
                        height: 300,
                        url: '/order/verify/getUserSms',
                        method: 'post',
                        toolbar: $("#" + $(this)[0].id).find("#smsToolbar"),
                        queryParams: function (params) {
                            return $.extend(params,
                                {
                                    userId: row.userId,
                                    startDate: dialogdom.find('#startdate').val(),
                                    endDate: dialogdom.find('#enddate').val() + " 23:59:59",
                                });
                        },
                        singleSelect: false,
                        columns: [
                            {
                                field: 'submitTime',
                                title: '日期',
                                sortable: true,
                                width: 150
                            },
                            {
                                field: 'smsCount',
                                title: '短信数量',
                                sortable: true,
                                width: 150
                            }
                        ]
                    }, dialogdom.find("#sms"));


                    dialogdom.find("#sms_query").click(function () {
                        dialogdom.find("#sms").bootstrapTable('refresh');
                    });
                    //开始时间
                    dialogdom.find('#starttimePicker').datetimepicker({
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    })

                    //结束时间
                    dialogdom.find('#endtimePicker').datetimepicker(
                        {
                            format: 'yyyy-mm-dd',
                            autoclose: true,
                            pickTime: false,
                            minView: 2
                        });

                },
                buttons: [{
                    label: '关闭!',
                    cssClass: 'btn-primary',
                    action: function (dialog) {
                        dialog.close();
                    }
                }]
            });
        }
    };
});