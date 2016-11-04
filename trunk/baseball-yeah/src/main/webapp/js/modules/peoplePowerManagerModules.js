define(['base'], function (base) {
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
                url: '/manage/role/selectAllRole',
                sScrollY: true,
                queryParams: function (params) {
                    return $.extend(params, {
                        roleName: $.trim($("#roleName", panel).val()),
                        roleType: $("#roleType", panel).val() == "请选择" ? "" : $("#roleType", panel).val(),
                        beSysPrivilege: $("#beSysPrivilege", panel).val() == "请选择" ? "" : $("#beSysPrivilege", panel).val()
                    });
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'roleId',
                    title: '角色Id',
                    sortable: true,
                    visible: false,
                    width: 800
                }, {
                    field: 'roleName',
                    title: '角色名称',
                    sortable: true,
                    width: 800
                }, {
                    field: 'roleType',
                    title: '角色类型',
                    formatter: function (value, row, index) {
                        return value == "1" ? "<a class='label label-success'>系统管理员</a>" : "<a class='label label-success' >超级系统管理员</a>";
                    },
                    sortable: true,
                    width: 800
                }, {
                    field: 'beSysPrivilege',
                    title: '是否系统权限',
                    formatter: function (value, row, index) {
                        return value == "1" ? "<a class='label label-success'>系统权限 </a>" : "<a class='label label-warning' >非系统权限</a>";
                    },
                    sortable: true,
                    width: 800
                }]
            }, '#userTable', panel);

            $("#btn_add", panel).click(function () {
                self.add(panel);
            });
            $("#btn_edit", panel).click(function () {
                self.edit(panel);
            });
            $("#btn_delete", panel).click(function () {
                self.remove(panel);
            });
            $("#btn_query", panel).click(function () {
                // $("#userTable", panel).bootstrapTable('refresh');
                $("#userTable", panel).bootstrapTable('selectPage', 1);
            });

            $("#clearSearch", panel).click(function () {
                $('#roleName', panel).val('');
                $('#roleType', panel).val("");
                $('#beSysPrivilege', panel).val("");
            });

            $('#addModal', panel).on('shown.bs.modal', function () {
                $('#addForm', panel).data('bootstrapValidator').resetForm(true);
            })
        },
        add: function (panel) {
            var self = this;
            $('#addRoleName', panel).val('');
            $('#addRoleType', panel).val('', panel).trigger("change");
            $('#addBeSysPrivilege', panel).val('', panel).trigger("change");
            $.fn.zTree.getZTreeObj("addTreeDemo", panel).checkAllNodes(false);
            $('#addModal', panel).modal({
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
                    addRoleName: {
                        validators: {
                            notEmpty: {
                                message: '角色名不能为空'
                            }
                        }
                    },
                    addRoleType: {
                        validators: {
                            notEmpty: {
                                message: '角色类型不能为空'
                            }
                        }
                    },
                    addBeSysPrivilege: {
                        validators: {
                            notEmpty: {
                                message: '是否系统权限不能为空'
                            }
                        }
                    }
                }
            }, '#addForm', self.create, panel)
        },
        create: function (panel) {
            var zTree = $.fn.zTree.getZTreeObj("addTreeDemo");
            var checkNodes = new Array();
            var nodes = zTree.getChangeCheckedNodes();
            for (var i = 0; i < nodes.length; i++) {
                checkNodes[i] = nodes[i].idStr;
            }
            $.post("/manage/role/addRole", {
                "roleName": $("#addRoleName", panel).val(),
                "roleType": $("#addRoleType", panel).val() == "请选择" ? "" : $("#addRoleType", panel).val(),
                "beSysPrivilege": $("#addBeSysPrivilege", panel).val() == "请选择" ? "" : $("#addBeSysPrivilege", panel).val(),
                "checkNodes": checkNodes.toString()
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        swal("增加成功");
                        $('#addModal', panel).modal('hide');
                        // $("#userTable", panel).bootstrapTable('refresh');
                        $("#userTable", panel).bootstrapTable('selectPage', 1);
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
            var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            $('#editRoleID', panel).val(arrselections[0].roleId);
            $('#editRoleName', panel).val(arrselections[0].roleName);
            $('#editRoleType', panel).val(arrselections[0].roleType).trigger("change");
            $('#editBeSysPrivilege', panel).val(arrselections[0].beSysPrivilege).trigger("change");

            var zTree = $.fn.zTree.getZTreeObj("editTreeDemo");
            zTree.checkAllNodes(false);
            $.get("/manage/menu/getRoleMenu", {
                "roleId": arrselections[0].roleId
            }, function (data, status) {
                if (status == "success") {
                    for (var i = 0; i < data.length; i++) {
                        var treeModel = zTree.getNodeByParam("idStr", data[i].menuIdStr);
                        if (treeModel) {
                            zTree.checkNode(treeModel, true, false);
                        }
                    }
                }
            });

            $('#editModal', panel).modal({
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
                    editRoleName: {
                        validators: {
                            notEmpty: {
                                message: '角色名不能为空'
                            }
                        }
                    },
                    editRoleType: {
                        validators: {
                            notEmpty: {
                                message: '角色类型不能为空'
                            }
                        }
                    },
                    editBeSysPrivilege: {
                        validators: {
                            notEmpty: {
                                message: '是否系统权限不能为空'
                            }
                        }
                    }
                }
            }, '#editForm', self.update, panel)
        },
        update: function (panel) {
            var zTree = $.fn.zTree.getZTreeObj("editTreeDemo");
            var checkNodes = new Array();
            var nodes = zTree.getChangeCheckedNodes();
            for (var i = 0; i < nodes.length; i++) {
                checkNodes[i] = nodes[i].idStr;
            }
            $.post("/manage/role/updateRoleAndPermission", {
                "roleId": $('#editRoleID', panel).val(),
                "roleName": $("#editRoleName", panel).val(),
                "roleType": $("#editRoleType", panel).val(),
                "beSysPrivilege": $("#editBeSysPrivilege", panel).val() == "请选择" ? "" : $("#editBeSysPrivilege", panel).val(),
                "checkNodes": checkNodes.toString()
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        $('#editModal', panel).modal('hide');
                        // $("#userTable", panel).bootstrapTable('refresh');
                        $('#editForm', panel).data('bootstrapValidator').resetForm(true);
                        base.success("编辑成功");
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
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var roleId = arrselections[0].roleId;

            base.cancel({
                title: "删除角色",
                text: "您确定要删除此角色吗？"
            }, function () {
                $.post("/manage/role/deleteRole", {
                    "roleId": roleId
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("删除成功");
                            // $("#userTable", panel).bootstrapTable('refresh');
                            $("#userTable", panel).bootstrapTable('selectPage', 1);
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("删除失败");
                    }
                });
            });
        },
    };
});
