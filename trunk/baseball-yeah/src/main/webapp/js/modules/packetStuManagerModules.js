define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
    return {
        init: function (panel) {
        	panel = panel || $('#indextab').tabs('getSelected');
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;
            datatable = base.datagrid({
                url: '/order/verify/getManageUsers',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            userName: $("#userName",panel).val(),//登录账号
                            realName: $("#realName",panel).val(),//姓名
                            gender: $("#gender",panel).val(),//性别
                            phone: $("#phone",panel).val(),
                            grabOrderMode: $("#grabOrderMode",panel).val(),
                            collegeId: $("#selcollage",panel).val() == "请选择" ? "" : $("#selcollage",panel).val(),//校区
                            cityId: $("#selcity",panel).val() == "请选择" ? "" : $("#selcity",panel).val(),//城市
                            verifyStatus: $("#verifyStatus",panel).val(),
                            startDate: $("#startDate",panel).val(),
                            endDate: $('#endDate',panel).val() == "" ? "" : $('#endDate',panel).val() + " 23:59:59"

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
                    },
                    {
                        field: 'realName',
                        title: '真实姓名',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'gender',
                        title: '性别',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'cityName',
                        title: '城市',
                        sortable: true,
                        width: 150
                    },

                    {
                        field: 'collegeFullName',
                        title: '学校',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'collegeAddress',
                        title: '学校地址',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'dormitoryAddress',
                        title: '宿舍地址',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'signupTime',
                        title: '注册时间',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'verifyStatus',
                        title: '状态',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "未提交";
                                case 1:
                                    return "已提交";
                                case 2:
                                    return "审核通过";
                                case 3:
                                    return "审核失败";
                            }
                        },
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'grabOrderMode',
                        title: '账户状态',
                        formatter: function (value,
                                             row, index) {
                            return value == "1" ? "<a class='label label-success'>激活</a>"
                                : "<a class='label label-warning' >封存</a>";
                        }, align: 'center',
                        width: 150
                    },
                    {
                        field: 'userId',
                        title: 'userId',
                        visible: false
                    }]
            }, '#userTable',panel);

            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    $("#selcity",panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcity',panel).select2("val", null);
                }
            });

            //开始时间
            $('#starttimePicker',panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2
            })

            //结束时间
            $('#endtimePicker',panel).datetimepicker(
                {
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
                });

            $('#auditcollage',panel).bootstrapTable({
                data: [],
                height: 200
            });

            $.ajax({
                type: "POST",
                url: "/manage/college/getcollageforsel",
                dataType: "json",
                success: function (data) {

                    $("#selcollage",panel).select2({
                        data: data.data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcollage',panel).select2("val", null);
                }
            });

            $("#btn_query",panel).click(function () {
                //$("#userTable",panel).bootstrapTable('refresh',{"query": {"offset": 0}});
            	if ((new Date(Date.parse($('#endDate',panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#startDate',panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#userTable", panel).bootstrapTable('selectPage', 1);
            })
            $("#btn_sealed",panel).click(function () {
                self.grabMode(0,panel);
            });
            $("#btn_activation",panel).click(function () {
                self.grab(panel);
            });
            $("#btn_delete",panel).click(function () {
                self.remove(panel);
            });
            $("#clearSearch",panel).click(function () {
                base.reset($(".main-box-header",panel));
                $('#selcollage',panel).select2("val", null);
                $('#selcity',panel).select2("val", null);
            });

            $(".btn-primary",panel).click(function () {
                self.grabUpdate(panel);
            });
        },
        remove: function (panel) {
            var arrselections = $("#userTable",panel).bootstrapTable('getSelections');

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
        grabMode: function (mode,panel) {
            var arrselections = $("#userTable",panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var grabOrderMode = arrselections[0].grabOrderMode;
            var userId = arrselections[0].userId;
            var modetitle = ""
            var modetext = ""
            if (mode == 1) {
                modetitle = "账号激活";
                modetext = "您确定要激活吗";
                if (mode == grabOrderMode) {
                    base.error("已经是激活状态!");
                    return;
                }
            } else {
                modetitle = "账号封存";
                modetext = "您确定要封存吗";
                if (mode == grabOrderMode) {
                    base.error("已经是封存状态!");
                    return;
                }
            }
            base.cancel({title: modetitle, text: modetext}, function () {
                $.post("/order/verify/activation", {
                    "userId": userId,
                    "grabOrderMode": mode
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#userTable",panel).bootstrapTable('refresh');
                            base.success("操作成功！")
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("操作失败!");
                    }
                });
            });
        },
        grab: function (panel) {
            var self = this;
            var arrselections = $("#userTable",panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行操作!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $("#grab_phone",panel).val(arrselections[0].phone);
            $("#grab_realName",panel).val(arrselections[0].realName);
            $("#grab_cityName",panel).val(arrselections[0].cityName);
            $("#userId",panel).val(arrselections[0].userId);

            if (arrselections[0].grabOrderMode) {
                $("#grabY_packetStuManager",panel).prop("checked", arrselections[0].grabOrderMode == "1" ? true : false);
                $("#grabN_packetStuManager",panel).prop("checked", arrselections[0].grabOrderMode == "1" ? false : true);
            }
            $('#grabModal',panel).modal({
                keyboard: false,
                backdrop: 'static'
            });

        },
        grabUpdate: function (panel) {
            $.post("/order/verify/activation", {
                "userId": $("#userId",panel).val(),
                "grabOrderMode": $("#grabY_packetStuManager",panel).prop('checked') ? "1" : "0"
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        $("#userTable",panel).bootstrapTable('refresh');
                        $('#grabModal',panel).modal('hide');
                        base.success("操作成功！")
                    } else {
                        base.error(data.message);
                    }
                } else {
                    base.error("操作失败!");
                }
            });
        }
    };
});