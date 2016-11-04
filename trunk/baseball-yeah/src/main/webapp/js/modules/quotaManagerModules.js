define(['base'], function (base) {
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
                url: '/manage/quota/selectall',

                singleSelect: false,
                columns: [{
                    checkbox: true
                }, {
                    field: 'quotaId',
                    title: '指标ID',
                    sortable: true
                }, {
                    field: 'quotaName',
                    title: '指标名称',
                    sortable: true
                }, {
                    field: 'fieldName',
                    title: '对应字段',
                    sortable: true
                }, {
                    field: 'state',
                    title: '状态',
                    sortable: true,
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return "启用"
                        }else if(value == 0){
                            return "禁用";
                        }
                    }
                }]
            }, '#quotaTable', args);

            $("#btn_add", args).click(function () {
                self.add(args);
            });
            $("#btn_edit", args).click(function () {
                self.edit(args);
            });
            $("#btn_delete", args).click(function () {
                self.delete(args);
            });
            $("#btn_activation", args).click(function () {
                self.activation(args);
            });
            $("#btn_query", args).click(function () {
                // $("#quotaTable",args).bootstrapTable('refresh');
                $("#quotaTable", args).bootstrapTable('selectPage', 1);
            });
            $('#addModal', args).on('shown.bs.modal', function () {
                $('#addForm', args).data('bootstrapValidator').resetForm(true);
            });
            $("#editModal", args).on('hidden.bs.modal', function() {
                $("#editForm", args).data('bootstrapValidator').destroy();
                $("#editForm", args).data('bootstrapValidator', null);
            });
        },
        add: function (args) {
            var self = this;
            $('#addModal', args).modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                    add_quotaid: {
                        validators: {
                            notEmpty: {
                                message: '指标ID不能为空'
                            }
                        }
                    },
                    add_quotaname: {
                        validators: {
                            notEmpty: {
                                message: '指标名称不能为空'
                            }
                        }
                    },
                    add_fieldname: {
                        validators: {
                            notEmpty: {
                                message: '对应字段不能为空'
                            }
                        }
                    }
                }
            }, "#addForm", self.create, args);
        },
        create: function (args) {
            $.post("/manage/quota/add", {
                "quotaId": $("#add_quotaid", args).val(),
                "quotaName": $("#add_quotaname", args).val(),
                "fieldName": $("#add_fieldname", args).val()
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("添加成功！");
                        // $("#quotaTable",args).bootstrapTable('refresh');
                        $("#quotaTable", args).bootstrapTable('selectPage', 1);
                        $("#addModal", args).modal('hide');
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
            var arrselections = $("#quotaTable", args).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            $('#editModal', args).modal({
                keyboard: false,
                backdrop: 'static'
            });
            $("#edit_quotaname", args).val(arrselections[0].quotaName);
            $("#edit_fieldname", args).val(arrselections[0].fieldName);
            $("#edit_quotaid", args).val(arrselections[0].quotaId);
            $("#edit_OldQuotaid", args).val(arrselections[0].quotaId);

            $('#editForm', args).bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    edit_quotaid: {
                        validators: {
                            notEmpty: {
                                message: '指标ID不能为空'
                            },
                            regexp: {
                                regexp: /^[0-9]*$/,
                                message: '必须为数字'
                            },
                            lessThan: {
                                value: 2147483647,
                                inclusive: true,
                                message: '指标ID值超出范围'
                            }
                        }
                    },
                    edit_quotaname: {
                        validators: {
                            notEmpty: {
                                message: '指标名称不能为空'
                            }
                        },
                        stringLength: {
                            min: 1,
                            max: 50,
                            message: '指标名称长度必须在1到50位之间'
                        }
                    },
                    edit_fieldname: {
                        validators: {
                            notEmpty: {
                                message: '对应字段不能为空'
                            }
                        },
                        stringLength: {
                            min: 1,
                            max: 50,
                            message: '对应字段长度必须在1到50位之间'
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                e.preventDefault();
                self.update(args);
            });
            $('#editModal', args).modal();
        },
        update: function (args) {
            $.post("/manage/quota/update", {
                "quotaId": $("#edit_quotaid", args).val(),
                "oldQuotaId": $("#edit_OldQuotaid", args).val(),
                "quotaName": $("#edit_quotaname", args).val(),
                "fieldName": $("#edit_fieldname", args).val(),
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("更新成功！");
                        // $("#quotaTable",args).bootstrapTable('refresh');
                        $("#quotaTable", args).bootstrapTable('selectPage', 1);
                        $("#editModal", args).modal('hide');
                        $("#editForm", args).data("bootstrapValidator").resetForm(true);
                    } else {
                        base.error(data.message);
                    }
                } else {
                    base.error("更新失败!");
                }
            });
        },
        delete: function (args) {
            var arrselections = $("#quotaTable", args).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var quotaIds = [];
            for (var i = 0; i < arrselections.length; i++) {
                quotaIds.push(arrselections[i].quotaId);
            }
            base.cancel({
                title: "禁用",
                text: "您确定要禁用吗？"
            }, function () {
                $.post("/manage/quota/delbyids", {
                    "quotaIds": quotaIds.join()
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("禁用成功！");
                            // $("#quotaTable",args).bootstrapTable('refresh');
                            $("#quotaTable", args).bootstrapTable('selectPage', 1);
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("禁用失败");
                    }
                }, 'json');
            });
        },
        activation: function (args) {
        var arrselections = $("#quotaTable", args).bootstrapTable('getSelections');
        if (arrselections.length <= 0) {
            base.error("请选择有效数据!");
            return;
        }
        var quotaIds = [];
        for (var i = 0; i < arrselections.length; i++) {
            quotaIds.push(arrselections[i].quotaId);
        }
        base.cancel({
            title: "启用",
            text: "您确定要启用吗？"
        }, function () {
            $.post("/manage/quota/active", {
                "quotaIds": quotaIds.join()
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("启用成功！");
                        // $("#quotaTable",args).bootstrapTable('refresh');
                        $("#quotaTable", args).bootstrapTable('selectPage', 1);
                    } else {
                        base.error(data.message);
                    }
                } else {
                    base.error("启用失败");
                }
            }, 'json');
        });
    }
    };
});