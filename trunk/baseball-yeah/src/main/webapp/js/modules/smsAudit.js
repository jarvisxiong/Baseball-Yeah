/**
 * Created by wny on 2016-06-17.
 */
/**
 * Created by wny on 2016-06-17.
 */
define(['base'], function (base) {

    return {
        init: function (panel) {
            var self = this;

            //开始时间
            $('#createStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            })

            //结束时间
            $('#createEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            });

            //开始时间
            $('#auditStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            })

            //结束时间
            $('#auditEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            });

            /*** 加载校区名称列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/college/getCollageForSel",
                dataType: "json",
                success: function (data) {
                    $("#collegeId", panel).select2({
                        data: data.data
                    });
                }
            });
            base.datagrid({
                url: '/message/usersmsmodel/querySmsAuditList',
                // method: 'post',
                showExport: true,// 显示导出按钮
                singleSelect: false,//允许多选
                exportDataType: "all",// 导出类型
                pagination: true, // 是否显示分页（*）
                // sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            createStartTime: $("#createStartTime", panel).val().trim() && ($("#createStartTime", panel).val().trim() + " 00:00:00"),
                            createEndTime: $("#createEndTime", panel).val().trim() && ($("#createEndTime", panel).val().trim() + " 23:59:59"),
                            stateNum: $("#stateNum", panel).val().trim(),
                            auditStartTime: $("#auditStartTime", panel).val().trim() && ($("#auditStartTime", panel).val().trim() + "  00:00:00"),
                            auditEndTime: $("#auditEndTime", panel).val().trim() && ($("#auditEndTime", panel).val().trim() + " 23:59:59"),
                            collegeId: $("#collegeId", panel).val() == null ? "" : $("#collegeId", panel).val().trim(),
                            modelContent: $("#modelContent", panel).val().trim(),
                            createUserPhone: $("#createUserPhone", panel).val().trim()
                        });
                },
                onLoadSuccess: function (data) {
                    $('.showAuditDetail', panel).click(function () {
                        var smsModelId = $(this).attr("id");
                        self.showDetail(smsModelId, panel);
                    });
                    /*                    $('.phoneUserManager', panel).click(function () {
                     var phone = $.trim($(this).text());
                     var title = $(this).attr('title');
                     var href = '/order/gotoPacketStuManager?phone=' + phone;
                     base.openTab(title, href, packetinit);
                     });*/
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'smsModelId',
                        title: '模板编号',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return "<a href='#' class='showAuditDetail' id=\'" + row.smsModelId + "\'>" + value + "</a>";
                            // return "<a href='#' onclick='showAuditDetail(\"" + row.smsModelId + "\");'>" + value + "</a>";
                        }
                    },
                    {
                        field: 'templateName',
                        title: '模板名称',
                        sortable: true
                    },
                    {
                        field: 'modelContent',
                        title: '模板内容',
                        sortable: true
                    },
                    {
                        field: 'stateNum',
                        title: '审核状态',
                        sortable: true,
                        formatter: function (value,
                                             row, index) {
                            if (value == "1") {
                                return "<a class='label label-success'>审核通过</a>";
                            } else if (value == "2") {
                                return "<a class='label label-info' >审核中</a>";
                            } else if (value == "3") {
                                return "<a class='label label-primary' >审核不通过</a>";
                            } else if (value == "0") {
                                return "<a class='label label-warning' >已删除</a>";
                            } else {
                                return "<a class='label label-default' >？</a>";
                            }
                        },
                    },
                    {
                        field: 'createUserName',
                        title: '创建人姓名',
                        sortable: true
                    },
                    {
                        field: 'createUserPhone',
                        title: '创建人手机号',
                        sortable: true
                    },
                    {
                        field: 'createTime',
                        title: '提交审核时间',
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != null && value.length > 2) {
                                return value.substr(0, value.length - 2)
                            }
                        }

                    },
                    {
                        field: 'auditTime',
                        title: '审核完成时间',
                        visible: true,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != null && value.length > 2) {
                                return value.substr(0, value.length - 2)
                            }
                        }
                    },

                    {
                        field: 'headUserPhone',
                        title: '负责人手机',
                        visible: true,
                        sortable: true
                    },

                    {
                        field: 'headUserName',
                        title: '负责人姓名',
                        visible: true,
                        sortable: true
                    }

                ]
            }, '#templetTable', panel);

            $("#btn_query", panel).click(function () {
                // $("#templetTable", panel).bootstrapTable('refresh', {"query": {"offset": 0}});
                $("#templetTable", panel).bootstrapTable('selectPage', 1);
            });

            /*            $("#btn_clear", panel).click(function () {
             });*/

            $("#clearSearch", panel).click(function () {
                $("#createStartTime", panel).val('');
                $("#createEndTime", panel).val('');
                $("#stateNum", panel).val('').trigger("change");
                $("#auditStartTime", panel).val('');
                $("#auditEndTime", panel).val('');
                $("#collegeId", panel).val('').trigger("change");
                $("#modelContent", panel).val('');
                $("#createUserPhone", panel).val('');
                $("#templetTable", panel).bootstrapTable('selectPage', 1);
            });
            $("#btn_pass", panel).click(function () {
                self.auditSms(1, panel);
            });
            $("#btn_Refuse", panel).click(function () {
                self.auditRefuseSms(3, panel);
            });
            $("#btn_del", panel).click(function () {
                self.smsDel(panel);
            });
        },
        showDetail: function (smsModelId, panel) {
            var self = this;
            $('#detailSmsModelId', panel).val("");
            $('#detailTemplateName', panel).val("");
            $('#detailModelContent', panel).val("");
            $('#detailModelEffect', panel).val("");
            $('#detailOptReason', panel).val("");
            $.post("/message/usersmsmodel/smsAuditView", {
                "smsModelId": smsModelId
            }, function (data, status) {
                if (status == "success") {
                    $('#deCreateUser', panel).val(data.userId);
                    $('#deCreateUserPhone', panel).val(data.createUserPhone);
                    $('#detailSmsModelId', panel).val(data.smsModelId);
                    $('#detailTemplateName', panel).val(data.templateName);
                    $('#detailModelContent', panel).val(data.modelContent);
                    $('#detailModelEffect', panel).val(data.modelContent);
                    $('#detailOptReason', panel).val(data.optReason);
                    if (data.stateNum != 2) {//非待审核状态
                        $("#btnRefuse", panel).hide();//审核拒绝按钮
                        $("#btnPass", panel).hide();//审核通过按钮
                        $("#detailOptReason", panel).attr("readonly", "readonly");
                    } else {
                        $("#btnRefuse", panel).show();//审核拒绝按钮
                        $("#btnPass", panel).show();//审核通过按钮
                        $("#detailOptReason", panel).attr("readonly", false);
                    }
                } else {
                    base.error("初始化失败!");
                }
            });
            $('#detailModal', panel).modal();
            base.validator(
                {
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    }
                }, "#detailForm", self.create, panel);

            $("#btnPass", panel).click(function () {
                self.create(1, panel);
            });

            $("#btnRefuse", panel).click(function () {
                self.create(3, panel);
            });
        },
        create: function (stateNum, panel) {
            var optReason = $("#detailOptReason", panel).val().trim();
            if((!(optReason!=null && optReason.length > 0)) && stateNum == 3){
                base.error("驳回原因不能为空！");return;
            }
            $.post("/message/usersmsmodel/audit",
                {
                    "smsModelId": $("#detailSmsModelId", panel).val(),
                    "optReason": $("#detailOptReason", panel).val(),
                    "userId": $("#deCreateUser", panel).val(),
                    "createUserPhone": $("#deCreateUserPhone", panel).val(),
                    "stateNum": stateNum
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("操作成功");
                            $("#templetTable", panel).bootstrapTable('selectPage', 1);
                            $('#detailModal', panel).modal('hide');

                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
        },
        auditSms: function (stateNum, panel) {
            var self = this;
            var arrselections = $("#templetTable", panel).bootstrapTable('getSelections');
            /*if (arrselections.length > 1) {
             base.error("只能选择一行进行编辑!");
             return;
             }*/
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var smsIdArr = {};var userIdPhoneArr = {};
            for (var i = 0; i < arrselections.length; i++) {
                if (arrselections[i].stateNum != 2) {
                    // base.error("第"+(i+1)+"行记录已审核，不能重复审核!");
                    base.error("有数据已审核，请重新选择审核记录！");
                    return;
                }
                smsIdArr[i] = arrselections[i].smsModelId;
                userIdPhoneArr[i] = arrselections[i].smsModelId + "~" + arrselections[i].userId + "~" + arrselections[i].createUserPhone;
            }
            $.post("/message/usersmsmodel/batchAudit", {
                    "smsModelIdArr": smsIdArr,
                    "userIdPhoneArr":userIdPhoneArr,
                    "stateNum": stateNum
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("操作成功");
                            $("#templetTable", panel).bootstrapTable('selectPage', 1);
                            $('#detailModal', panel).modal('hide');

                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                }
            );
        },
        auditRefuseSms: function (stateNum, panel) {
            var self = this;
            var arrselections = $("#templetTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var smsModelId = arrselections[0].smsModelId;
            self.showDetail(smsModelId, panel);
        },
        smsDel: function (panel) {
            var self = this;
            var arrselections = $("#templetTable", panel).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var arr = {};
            for (var i = 0; i < arrselections.length; i++) {
                if (arrselections[i].stateNum == 2) {
                    // base.error("第"+(i+1)+"行记录已审核，不能重复审核!");
                    base.error("有数据在审核中，请重新选择删除记录！");
                    return;
                }
                arr[i] = arrselections[i].smsModelId;
            }
            $.post("/message/usersmsmodel/del", {
                    "smsModelIdArr": arr
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("删除成功");
                            $("#templetTable", panel).bootstrapTable('selectPage', 1);
                            $('#detailModal', panel).modal('hide');

                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                }
            );
        }
    }
});
