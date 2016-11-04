define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;

    return {
        init: function (panel) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            datatable = base.datagrid({
                url: '/user/getaddresseeinfo',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            userName: $("#userName",panel).val(),//登录账号
                            realName: $("#realName",panel).val(),//姓名
                            gender: $("#gender",panel).val(),//性别
                            phone: $("#phone",panel).val(),
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
                        field: 'signupTime',
                        title: '注册时间',
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
            $("#btn_query",panel).click(function () {
                //$("#userTable",panel).bootstrapTable('refresh',{"query": {"offset": 0}});
            	if ((new Date(Date.parse($('#endDate',panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#startDate',panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#userTable", panel).bootstrapTable('selectPage', 1);
            })
            $("#btn_delete",panel).click(function () {
                self.remove(panel);
            });
            $("#btn_activation",panel).click(function () {
                self.grab(panel);
            });
            $("#clearSearch",panel).click(function () {
                base.reset($(".main-box-header",panel));
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
            $("#userId",panel).val(arrselections[0].userId);

            if (arrselections[0].grabOrderMode) {
                $("#grabY_addressee",panel).prop("checked", arrselections[0].grabOrderMode == "1");
                $("#grabN_addressee",panel).prop("checked", arrselections[0].grabOrderMode != "1");
            }
            $('#grabModal',panel).modal({
                keyboard: false,
                backdrop: 'static'
            });
            $(".btn-primary",panel).click(function () {
                self.grabUpdate(panel);
            });
        },
        grabUpdate: function (panel) {
            $.post("/user/activation", {
                "userId": $("#userId",panel).val(),
                "beEnabled": $("#grabY_addressee",panel).prop('checked') ? "1" : "0"
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
