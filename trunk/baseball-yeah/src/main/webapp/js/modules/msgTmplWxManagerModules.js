define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var _currentId = null;

    return {
        init: function (args) {
            var self = this;

            base.datagrid({
                url: '/message/msgtmplwx/list',
                singleSelect: true,
                queryParams: function (params) {
                    return $.extend(params, {msgType: ''});
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'tmplCode',
                    title: '模板编码',
                    sortable: true,
                    width: 80
                }, {
                    field: 'tmplName',
                    title: '模板名称',
                    sortable: true,
                    width: 200
                }, {
                    field: 'tmplContent',
                    title: '模板内容',
                    sortable: true,
                    width: 500
                }, {
                    field: 'msgTypeName',
                    title: '消息类型',
                    sortable: true,
                    width: 200
                }, {
                    field: 'wxDetailUrl',
                    title: '微信详情url',
                    sortable: true,
                    width: 200
                }, {
                    field: 'wxTmplId',
                    title: '微信模板id',
                    sortable: true
                }]
            }, '#msgTmplWxTable');

            $("#btn_add").click(function () {
                self.add();
            });
            $("#btn_edit").click(function () {
                self.edit();
            });
            $("#btn_delete").click(function () {
                self.remove();
            });
            $("#btn_query").click(function () {
                $("#msgTmplWxTable").bootstrapTable('refresh');
            });
            $('#addModal').on('shown.bs.modal', function () {
                $('#addForm').data('bootstrapValidator').resetForm(true);
            });

        },
        add: function () {
            var self = this;
            $.ajax({
                type: "GET",
                url: "/message/msgType/getMsgTypeSellList",
                dataType: "json",
                success: function (data) {
                    $("#add_msg_type").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#add_msg_type').select2("val", null);
                }
            });
            $('#addModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                    add_tmplCode: {
                        validators: {
                            notEmpty: {
                                message: '模板编码不能为空'
                            }
                        }
                    },
                    add_tmplName: {
                        validators: {
                            notEmpty: {
                                message: '模板名称不能为空'
                            }
                        }
                    },
                    add_tmplContent: {
                        validators: {
                            notEmpty: {
                                message: '模板内容不能为空'
                            }
                        }
                    },
                    add_msgType: {
                        validators: {
                            notEmpty: {
                                message: '消息类型不能为空'
                            }
                        }
                    }
                }
            }, "#addForm", self.create);
        },
        create: function () {
            $.post("/message/msgtmplwx/add", {
                "tmplCode": $("#add_tmplCode").val(),
                "tmplName": $("#add_tmplName").val(),
                "tmplContent": $("#add_tmplContent").val(),
                "msgType": $("#add_msg_type").val(),
                "wxDetailUrl": $("#add_wxDetailUrl").val(),
                "wxTmplId": $("#add_wxTmplId").val()
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("添加成功！");
                        $("#msgTmplWxTable").bootstrapTable('refresh');
                        $("#addModal").modal('hide');
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
            var arrselections = $("#msgTmplWxTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            $('#editModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            $.ajax({
                type: "GET",
                url: "/message/msgType/getMsgTypeSellList",
                dataType: "json",
                success: function (data) {
                    $("#edit_msgType").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                }
            });
            $("#edit_tmplCode").val(arrselections[0].tmplCode);
            $("#edit_tmplName").val(arrselections[0].tmplName);
            $("#edit_tmplContent").val(arrselections[0].tmplContent);
            $("#edit_msgType").val(arrselections[0].msgType).trigger("change");
            $("#edit_wxDetailUrl").val(arrselections[0].wxDetailUrl);
            $("#edit_wxTmplId").val(arrselections[0].wxTmplId);

            _currentId = arrselections[0].id;
            base.validator({
                fields: {
                    edit_tmplCode: {
                        notEmpty: {
                            message: '模板编码不能为空'
                        }
                    },
                    edit_tmplName: {
                        validators: {
                            notEmpty: {
                                message: '模板名称不能为空'
                            }
                        }
                    },
                    edit_tmplContent: {
                        validators: {
                            notEmpty: {
                                message: '模板内容不能为空'
                            }
                        }
                    },
                    edit_msgType: {
                        validators: {
                            notEmpty: {
                                message: '消息类型不能为空'
                            }
                        }
                    }
                }
            }, '#editForm', self.update)
        },
        update: function () {
            $.post("/message/msgtmplwx/update", {
                "id": _currentId,
                "tmplCode": $("#edit_tmplCode").val(),
                "tmplName": $("#edit_tmplName").val(),
                "tmplContent": $("#edit_tmplContent").val(),
                "msgType": $("#edit_msgType").val(),
                "wxDetailUrl": $("#edit_wxDetailUrl").val(),
                "wxTmplId": $("#edit_wxTmplId").val()
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("更新成功！");
                        $("#msgTmplWxTable").bootstrapTable('refresh');
                        $("#editModal").modal('hide');
                        $("#editForm").data("bootstrapValidator").resetForm(true);
                    } else {
                        base.error(data.message);
                    }
                } else {
                    base.error("更新失败!");
                }
            });
        },
        remove: function () {
            var arrselections = $("#msgTmplWxTable").bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }

            base.cancel({
                title: "删除",
                text: "您确定要删除吗？"
            }, function () {
                $.post("/message/msgtmplwx/del", {
                    "id": arrselections[0].id
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("删除成功！");
                            $("#msgTmplWxTable").bootstrapTable('refresh');
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("删除失败");
                    }
                }, 'json');
            });
        },
    };
});