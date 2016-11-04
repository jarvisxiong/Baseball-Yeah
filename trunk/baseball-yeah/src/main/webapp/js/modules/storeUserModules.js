define(['base', 'auditUserModules'], function (base, audit) {
    /**
     * 私有成员定义区域
     */


    return {
        init: function (panel) {
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
                            beSupervisor: $("#beSupervisor",panel).val(),
                            realName: $("#realName",panel).val(),
                            storeName: $("#storeName",panel).val(),
                            idNo: $("#idNo",panel).val(),
                            verifyStatus: $("#verifyStatus",panel).val(),
                            cityId: $("#selcity",panel).val(),
                            phone: $("#phone",panel).val(),
                            storeId: $("#selstore",panel).val() == "请选择" ? "" : $("#selstore",panel).val(),
                            startDate: $('#startDate',panel).val(),
                            endDate: $('#endDate',panel).val() == "" ? "" : $('#endDate',panel).val() + " 23:59:59",
                            expressId: $('#expressId',panel).val()
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
                            self.messageTemplate(row, panel);
                            break;
                        case "sms":
                            self.sms(row, panel);
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
            }, '#userTable', panel);
            $('#auditcollage', panel).bootstrapTable({
                data: [],
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
                    $("#expressId", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#expressId', panel).select2("val", null);
                }
            });

            $.ajax({
                type: "POST",
                url: "/store/exp/storecodeinfo",
                dataType: "json",
                success: function (data) {
                    $("#selstore", panel).select2({
                        data: data
                    });
                }
            });
            $.ajax({
                type: "POST",
                url: "/store/exp/expstoreinfo",
                dataType: "json",
                success: function (data) {

                    $("#add_store", panel).select2({
                        data: data
                    });
                    $("#edit_store", panel).select2({
                        data: data
                    });
                }
            });

            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    $("#selcity", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcity', panel).select2("val", null);
                }
            });

            //开始时间
            $('#starttimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2
            })

            //结束时间
            $('#endtimePicker', panel).datetimepicker(
                {
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
                });

            $("#btn_add", panel).click(function () {
                self.add(panel);
            });
            $("#btn_edit", panel).click(function () {
                self.edit(panel);
            });
            $("#btn_delete", panel).click(function () {
                self.remove(panel);
            });
            $("#btn_initPwd", panel).click(function () {
                self.initPwd(panel);
            });
            $("#btn_query", panel).click(function () {
                //$("#userTable", panel).bootstrapTable('refresh',{"query": {"offset": 0}});
            	if ((new Date(Date.parse($('#endDate',panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#startDate',panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#userTable", panel).bootstrapTable('selectPage', 1);
            });
            $("#btn_audit", panel).click(function () {
                self.audit(panel);
            });

            $("#clearSearch", panel).click(function () {
                base.reset($(".main-box-header",panel));
                $('#expressId', panel).select2("val", null);
                $('#selcity', panel).select2("val", null);
                $("#selstore", panel).val(" ").trigger("change");
            });

            $('#addModal', panel).on('shown.bs.modal', function () {
                $("#add_store", panel).val(" ").trigger("change");
                $('#addForm', panel).data('bootstrapValidator').resetForm(true);
            })

        },
        add: function (panel) {
            var self = this;
            $('#addModal', panel).modal({
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
            }, "#addForm", self.create, panel)
        },
        create: function (panel) {
            $.post("/user/insertselective",
                {
                    "nickname": $("#add_nickname", panel).val(),
                    "phone": $("#add_phone", panel).val(),
                    "accountPwd": $("#add_pwd", panel).val(),
                    "gender": $("#addMan", panel).prop('checked') ? "p_gender_male" : "p_gender_female",
                    "beSupervisor": $("#addYSupervisor", panel).prop('checked') ? "1" : "0",
                    "storeId": $("#add_store", panel).val() == "请选择" ? "" : $("#add_store", panel).val()
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#userTable", panel).bootstrapTable('refresh');
                            $('#addModal', panel).modal('hide');
                            $("#add_store", panel).val("").trigger("change");
                            base.success("添加成功！")
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
        },
        edit: function (panel) {
            var self = this;
            var arrselections = $("#userTable",panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $("#edit_nickname", panel).val(arrselections[0].nickname);
            $("#edit_userName", panel).val(arrselections[0].userName);
            $("#edit_phone", panel).val(arrselections[0].phone);
            $("#userId", panel).val(arrselections[0].userId);

            $("#edit_idNo", panel).val(arrselections[0].idNo);
            $("#edit_realName", panel).val(arrselections[0].realName);
            if (arrselections[0].verifyStatus != 2) {
                if (arrselections[0].idNo == "") {
                    $("#edit_idNo", panel).removeAttr("readonly");
                } else {
                    $("#edit_idNo", panel).attr("readonly", "readonly");
                }
                if (arrselections[0].realName == "") {
                    $("#edit_realName", panel).removeAttr("readonly");
                } else {
                    $("#edit_realName", panel).attr("readonly", "readonly");
                }
            } else {
                $("#edit_idNo", panel).attr("readonly", "readonly");
                $("#edit_realName", panel).attr("readonly", "readonly");
            }


            if (arrselections[0].gender) {
                $("#editMan", panel).prop("checked", arrselections[0].gender == "p_gender_male" ? true : false);
                $("#editWoman", panel).prop("checked", arrselections[0].gender == "p_gender_male" ? false : true);
            }
            $("#edit_beSupervisor", panel).val( arrselections[0].beSupervisor == 1 ? "负责人" : "员工")

      

            $('#editModal', panel).modal({
                keyboard: false,
                backdrop: 'static'
            });
            $("#edit_store", panel).val(arrselections[0].storeId).trigger("change");
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
            }, '#editForm', self.update, panel)

        },
        update: function (panel) {
            $.post("/user/updatebyprimarykeyselective",
                {
                    "nickname": $("#edit_nickname", panel).val(),
                    "userName": $("#edit_userName", panel).val(),
                    "phone": $("#edit_phone", panel).val(),
                    "accountPwd": $("#edit_pwd", panel).val(),
                    "gender": $("#editMan", panel).prop('checked') ? "p_gender_male" : "p_gender_female",
                    // "beSupervisor": $("#editYSupervisor", panel).prop('checked') ? "1" : "0",
                    "storeId": $("#edit_store", panel).val(),
                    "userId": $("#userId", panel).val(),
                    "idNo": $("#edit_idNo", panel).val(),
                    "realName": $("#edit_realName", panel).val()
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#userTable", panel).bootstrapTable('refresh');
                            $('#editModal', panel).modal('hide');
                            $("#edit_store", panel).val(" ").trigger("change");
                            $('#editForm', panel).data('bootstrapValidator').resetForm(true);
                            base.success("更新成功！")
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
        },
        remove: function (panel) {
            var arrselections = $("#userTable", panel).bootstrapTable('getSelections');

            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var userInfos = [];
            for (var i = 0; i < arrselections.length; i++) {
                userInfos.push(arrselections[i].userId);
            }

            base.cancel({title: "注销用户", text: "您确定要注销此用户吗？"}, function () {
                $.post("/user/cancelUser", {"userIds": userInfos.join(',')}
                    , function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                $("#userTable",panel).bootstrapTable('refresh');
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
        initPwd: function (panel) {
            var arrselections = $("#userTable", panel)
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
                $.post("/user/initPwd", {
                    "userName": userName
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#userTable",panel).bootstrapTable('refresh');
                            base.success("重置成功，密码：" + data.data)
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
        messageTemplate: function (row, panel) {
            BootstrapDialog.show({
                title: '短信模板',
                message: $("#divdialog", panel).html(),
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
//                        dialogdom.find("#message").bootstrapTable('refresh');
                        dialogdom.find("#message").bootstrapTable('selectPage', 1);
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
        sms: function (row, panel) {
            BootstrapDialog.show({
                title: '使用状态',
                message: $("#divdialogsms", panel).html(),
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