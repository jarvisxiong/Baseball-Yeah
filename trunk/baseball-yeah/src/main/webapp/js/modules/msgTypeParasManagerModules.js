define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var _currentId = null;

    return {
        init: function (args) {
            var self = this;

            base.datagrid({
                url: '/message/typeparas/list',
                singleSelect: true,
                onPageChange: function (number, size) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#msgTypeParasTable').find("thead").height() + $('#msgTypeParasTable').find("tbody").height()
                        + 3 + $('#msgTypeParasTable').parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#msgTypeParasTable').bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#msgTypeParasTable').parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#msgTypeParasTable').bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1150);
                    }
                },
                queryParams: function (params) {
                    return $.extend(params, {msgType: ''});
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'parasCode',
                    title: '参数编码',
                    sortable: true
                }, {
                    field: 'parasName',
                    title: '参数名称',
                    sortable: true
                }, {
                    field: 'msgTypeName',
                    title: '消息类型',
                    sortable: true
                }, {
                    field: 'beNull',
                    title: '是否可空',
                    sortable: true,
                    formatter: function (value, row, index) {
                        return value.toString().trim() == "1" ? "是" : "否";
                    }
                }, {
                    field: 'insertTime',
                    title: '创建时间',
                    sortable: true
                }]
            }, '#msgTypeParasTable');

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
                $("#msgTypeParasTable").bootstrapTable('refresh');
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
                    add_parasCode: {
                        validators: {
                            notEmpty: {
                                message: '参数编码不能为空'
                            }
                        }
                    },
                    add_parasName: {
                        validators: {
                            notEmpty: {
                                message: '参数名称不能为空'
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
            $.post("/message/typeparas/add", {
                "parasCode": $("#add_parasCode").val(),
                "parasName": $("#add_parasName").val(),
                "msgType": $("#add_msg_type").val(),
                "beNull": $("#addIsNull").prop('checked') ? "1" : "0"
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("添加成功！");
                        $("#msgTypeParasTable").bootstrapTable('refresh');
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
            var arrselections = $("#msgTypeParasTable").bootstrapTable('getSelections');
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
            $("#edit_parasCode").val(arrselections[0].parasCode);
            $("#edit_parasName").val(arrselections[0].parasName);
            $("#edit_msgType").val(arrselections[0].msgType).trigger("change");
            if (arrselections[0].msgType == "1") {
                $("#editIsNull").prop("checked", "checked");
            } else {
                $("#editNotNull").prop("checked", "checked");
            }
            _currentId = arrselections[0].parasId;
            $('#editForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    edit_parasCode: {
                        notEmpty: {
                            message: '参数编码不能为空'
                        }
                    }
                },
                edit_parasName: {
                    validators: {
                        notEmpty: {
                            message: '参数名称不能为空'
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
            }).on('success.form.bv', function (e) {
                e.preventDefault();
                self.update();
            });
            $('#editModal').modal();
        },
        update: function () {
            $.post("/message/typeparas/update", {
                "parasId": _currentId,
                "parasCode": $("#edit_parasCode").val(),
                "parasName": $("#edit_parasName").val(),
                "msgType": $("#edit_msgType").val(),
                "beNull": $("#editIsNull").prop('checked') ? "1" : "0"
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("更新成功！");
                        $("#msgTypeParasTable").bootstrapTable('refresh');
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
            var arrselections = $("#msgTypeParasTable").bootstrapTable('getSelections');
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
                $.post("/message/typeparas/del", {
                    "parasId": arrselections[0].parasId
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("删除成功！");
                            $("#msgTypeParasTable").bootstrapTable('refresh');
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