/**
 * Created by wny on 2016-09-02.
 */
define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var _currentId = null;
    var datatable;
    return {
        init: function (args) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            function queryList() {

                datatable = base.datagrid({
                    url: '/message/msgType/queryMsgTypeGrid',
                    method: 'get',
                    sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                    singleSelect: true,
                    columns: [{
                        checkbox: true
                    }, {
                        field: 'msgType',
                        title: '类型编码',
                        sortable: true
                    }, {
                        field: 'id',
                        title: '类型id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'msgTypeName',
                        title: '类型名称',
                        sortable: true
                    }, {
                        field: 'typeTitle',
                        title: '类型显示标题',
                        sortable: true
                    }, {
                        field: 'typeLevel',
                        title: '类型级别',
                        sortable: true,
                        formatter: function (value,
                                             row, index) {
                            if (value == null || value == "") {
                                return "";
                            }
                            else {
                                return value.toString().trim() == "1" ? "一级类型" : (value.toString().trim() == "2" ? "二级类型" : value);

                            }
                        }
                    }, {
                        field: 'parentType',
                        title: '所属父级类型编码',
                        sortable: true
                    }, {
                        field: 'receiveRole',
                        title: '接收者角色',
                        sortable: true,
                        formatter: function (value,
                                             row, index) {
                            if (value == null || value == "") {
                                return "";
                            }
                            else {
                                return value.toString().trim() == "1" ? "axp所有类型用户" : (value.toString().trim() == "2" ? "非爱学派用户" : (value.toString().trim() == "3" ? "货源用户" : (value.toString().trim() == "4" ? "众包用户" : (value.toString().trim() == "5" ? "收件人用户" : value))));

                            }
                        }
                    }, {
                        field: 'headUrl',
                        title: '图标url',
                        sortable: true
                    }]
                }, '#quotaTable');
                datatable.bootstrapTable('refresh');
            }

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
                queryList();
               $("#quotaTable").bootstrapTable('refresh');
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
                    $("#add_selParentType").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#add_selParentType').select2("val", null);
                }
            });

            $('#addModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                    add_typeCode: {
                        validators: {
                            notEmpty: {
                                message: '类型编码不能为空'
                            },
                            integer: {
                                message: '类型编码只能为整数'
                            }
                        }
                    },
                    add_typeName: {
                        validators: {
                            notEmpty: {
                                message: '类型名称不能为空'
                            }
                        }
                    },
                    add_typeTitle: {
                        validators: {
                            notEmpty: {
                                message: '类型显示标题不能为空'
                            }
                        }
                    },
                    add_selRole: {
                        validators: {
                            notEmpty: {
                                message: '消息接收角色不能为空'
                            }
                        }
                    }
                }
            }, "#addForm", self.create);
        },
        create: function () {
            $.post("/message/msgType/addMsgType", {
                "msgType": $("#add_typeCode").val(),
                "msgTypeName": $("#add_typeName").val(),
                "typeTitle": $("#add_typeTitle").val(),
                "parentType":$("#edit_selParentType").val()==null?null:$("#edit_selParentType").val(),
                "receiveRole": $("#add_selRole").val(),
                "headUrl": $("#add_headUrl").val()
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("添加成功！");
                        queryList();
                        $("#quotaTable").bootstrapTable('refresh');
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
            var arrselections = $("#quotaTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            $.ajax({
                type: "GET",
                url: "/message/msgType/getMsgTypeSellList",
                dataType: "json",
                success: function (data) {
                    $("#edit_selParentType").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#edit_selParentType').select2("val", null);
                }
            });
            $('#editModal').modal({
                keyboard: false,
                backdrop: 'static'
            });

            $("#edit_typeCode").val(arrselections[0].msgType);
            $("#edit_typeName").val(arrselections[0].msgTypeName);
            $("#edit_typeTitle").val(arrselections[0].typeTitle);
            $("#edit_headUrl").val(arrselections[0].headUrl);
            $("#edit_selRole").val(arrselections[0].receiveRole);
            $("#edit_selParentType").val(arrselections[0].parentType);
            _currentId = arrselections[0].id;
            $('#editForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    edit_typeCode: {
                        notEmpty: {
                            message: '类型编码不能为空'
                        },
                        integer: {
                            message: '类型编码只能为整数'
                        },
                        stringLength: {
                            min: 2,
                            max: 4,
                            message: '类型编码必须在2到4位之间'
                        }

                },
                    edit_typeName: {
                    validators: {
                        notEmpty: {
                            message: '类型名称不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 20,
                            message: '类型名称必须在1到20位之间'
                        }
                    }
                },
                    edit_typeTitle: {
                    validators: {
                        notEmpty: {
                            message: '类型显示标题不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 10,
                            message: '类型显示标题必须在1到10位之间'
                        }

                    }
                },
                    edit_selRole: {
                    validators: {
                        notEmpty: {
                            message: '消息接收角色不能为空'
                        }
                    }
                }
                } }).on('success.form.bv', function (e) {
                e.preventDefault();
                self.update();
            });
            $('#editModal').modal();
        },
        update: function () {
            $.post("/message/msgType/updateMsgType", {
                "id": _currentId,
                "msgType": $("#edit_typeCode").val(),
                "msgTypeName": $("#edit_typeName").val(),
                "typeTitle": $("#edit_typeTitle").val(),
                "parentType":$("#edit_selParentType").val()==null?null:$("#edit_selParentType").val(),
                "receiveRole": $("#edit_selRole").val(),
                "headUrl": $("#edit_headUrl").val()
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("更新成功！");
                        queryList();
                        $("#quotaTable").bootstrapTable('refresh');
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
            var arrselections = $("#quotaTable").bootstrapTable('getSelections');
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
                $.post("/message/msgType/delMsgType", {
                    "info": arrselections[0].id
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("删除成功！");
                            queryList();
                            $("#quotaTable").bootstrapTable('refresh');
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