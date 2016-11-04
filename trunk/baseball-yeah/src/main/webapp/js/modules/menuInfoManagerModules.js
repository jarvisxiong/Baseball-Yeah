define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var actionData = [{"id": " ", "text": "请选择"}, {"id": "ADD", "text": "增加"}, {"id": "DELETE", "text": "删除"}, {"id": "EDIT", "text": "编辑"}, {
        "id": "QUERY",
        "text": "查询"
    }, {"id": "PRINT", "text": "打印"}, {"id": "EXPORT", "text": "导出"}, {"id": "AUDIT", "text": "审核"}, {"id": "INITPWD", "text": "初始化密码"}];


    function add_validate(panel){
        return {fields: {
            add_caption: {
                validators: {
                    notEmpty: {
                        message: '菜单项标题 不能为空'
                    }
                }
            },
            add_hotKey: {
                validators: {}
            },
            add_action: {
                validators: {}
            },
            add_hint: {
                validators: {}
            },
            add_menuCtrlPath: {
                validators: {
                    callback: {
                        message: '路径不能为空',
                        callback: function (value, validator) {
                            if ($("#add_beLeaf",panel).prop('checked') && $("#add_menuCtrlPath",panel).val() && $("#add_menuCtrlPath",panel).val().trim() != "") {
                                //叶子节点才有路径
                                return true;
                            } else if ($("#add_beLeaf_no",panel).prop('checked')) {
                                return true;
                            }
                            return false;
                        }
                    }
                }
            },
            add_menuStatusCode: {
                validators: {
                    notEmpty: {
                        message: '菜单状态不能为空'
                    }
                }
            },
            add_menuDevStatusCode: {
                validators: {
                    notEmpty: {
                        message: '菜单开发状态不能为空'
                    }
                }
            },
            add_siblingSortNo: {
                validators: {
                    regexp: {
                        regexp: /^\d+$/,
                        message: '兄弟排序号格式不正确'
                    }
                }
            }
        }
    }};
    function edit_validate(panel){
        return {fields: {
            edit_caption: {
                validators: {
                    notEmpty: {
                        message: '菜单项标题 不能为空'
                    }
                }
            },
            edit_hint: {
                validators: {}
            },
            edit_hotKey: {
                validators: {}
            },
            edit_action: {
                validators: {}
            },
            edit_menuCtrlPath: {
                validators: {
                    callback: {
                        message: '路径不能为空',
                        callback: function (value, validator) {
                            if ($("#edit_beLeaf",panel).prop('checked') && $("#edit_menuCtrlPath",panel).val() && $("#edit_menuCtrlPath",panel).val().trim() != "") {
                                //叶子节点才有路径
                                return true;
                            } else if ($("#edit_beLeaf_no",panel).prop('checked')) {
                                return true;
                            }
                            return false;
                        }
                    }
                }
            },
            edit_menuStatusCode: {
                validators: {
                    notEmpty: {
                        message: '菜单状态不能为空'
                    }
                }
            },
            edit_menuDevStatusCode: {
                validators: {
                    notEmpty: {
                        message: '菜单开发状态不能为空'
                    }
                }
            },
            edit_siblingSortNo: {
                validators: {
                    regexp: {
                        regexp: /^\d+$/,
                        message: '兄弟排序号格式不正确'
                    }
                }
            }
        }
    }};
    return {
        init: function (panel) {
            var self = this;
            var pIds = null;
            var dIds = null;
            //请求菜单parentId
            $.ajax({
                type: "GET",
                url: "/manage/menu/getParent",
                dataType: "json",
                success: function (data) {
                    pIds = data;
                }
            });
            //请求所属部门信息
            $.ajax({
                type: "GET",
                url: "/manage/menu/getDept",
                dataType: "json",
                success: function (data) {
                    dIds = data;
                }
            });
            base.datagrid({
                url: '/manage/menu/getall',
                method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            caption: $("#caption", panel).val(),
                        });
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'menuId',
                    title: 'Id',
                    sortable: true
                }, {
                    field: 'caption',
                    title: '标题',
                    sortable: true
                }, {
                    field: 'menuCtrlPath',
                    title: '路径',
                    sortable: true
                }, {
                    field: 'menuStatusCode',
                    title: '状态',
                    formatter: function (value,
                                         row, index) {
                        var result;
                        if (value == "p_menu_disable") {
                            result = "未启用";
                        } else if (value == "p_menu_maintain") {
                            result = "维护";
                        } else if (value == "p_menu_normal") {
                            result = "正常";
                        }
                        return result;
                    },
                    sortable: true
                }, {
                    field: 'menuDevStatusCode',
                    title: '开发状态',
                    formatter: function (value,
                                         row, index) {
                        var result;
                        if (value == "p_devstatus_done") {
                            result = "完成";
                        } else if (value == "p_devstatus_no") {
                            result = "未开发";
                        } else if (value == "p_menu_test") {
                            result = "测试中";
                        } else if (value == "p_menu_dev") {
                            result = "开发中";
                        }
                        return result;
                    },
                    sortable: true
                }, {
                    field: 'beLeaf',
                    title: '是否叶子节点',
                    formatter: function (value,
                                         row, index) {
                        return value == "1" ? "是" : "否";
                    },
                    sortable: true,
                    width: 150
                }, {
                    field: 'parentId',
                    title: '上级菜单',
                    sortable: true,
                    formatter: function (value,
                                         row, index) {
                    	if(value == 0){
                    		return "无";
                    	}
                         for (var i = 0; i < pIds.length; i++) {
                             var parentInfo = pIds[i];
                             if (parentInfo.id != " " && parentInfo.id == value) {
                                 return parentInfo.text;
                             }
                         }
                        return value;
                    }
                }, {
                    field: 'siblingSortNo',
                    title: '兄弟排序号',
                    sortable: true
                }, {
                    field: 'belongSiteKind',
                    title: '所属部门',
                    sortable: true,
                    formatter: function (value,
                                         row, index) {
                         for (var i = 0; i < dIds.length; i++) {
                             var deptInfo = dIds[i];
                             if (deptInfo.id != " " && deptInfo.id == value) {
                                 return deptInfo.text;
                             }
                         }
                        return value;
                    }
                }, {
                    field: 'action',
                    title: '动作',
                    sortable: true
                }]
            }, '#menuTable',panel);

            $.ajax({
                type: "POST",
                url: "/manage/menu/getEnumPermission",
                dataType: "json",
                success: function (data) {
                    $("#add_action", panel).select2({
                        data: data
                    });
                    $("#edit_action", panel).select2({
                        data: data
                    });
                }
            });

            $('#add_beLeaf',panel)
                .on('click', function (e) {
                    $('#addForm', panel)
                        .data('bootstrapValidator')
                        .updateStatus('add_menuCtrlPath', 'NOT_VALIDATED', null)
                        .validateField('add_menuCtrlPath');
                });
            $('#edit_beLeaf', panel)
                .on('click', function (e) {
                    $('#editForm',panel)
                        .data('bootstrapValidator')
                        .updateStatus('edit_menuCtrlPath', 'NOT_VALIDATED', null)
                        .validateField('edit_menuCtrlPath');
                });
            $('#add_beLeaf_no', panel)
                .on('click', function (e) {
                    $('#addForm',panel)
                        .data('bootstrapValidator')
                        .updateStatus('add_menuCtrlPath', 'NOT_VALIDATED', null);
                });
            $('#edit_beLeaf_no', panel)
                .on('click', function (e) {
                    $('#editForm',panel)
                        .data('bootstrapValidator')
                        .updateStatus('edit_menuCtrlPath', 'NOT_VALIDATED', null);
                });
            //请求菜单状态
            $.ajax({
                type: "GET",
                url: "/manage/menu/getMenuStatus",
                dataType: "json",
                success: function (data) {

                    $("#add_menuStatusCode", panel).select2({
                        data: data
                    });
                    $("#edit_menuStatusCode", panel).select2({
                        data: data
                    });
                }
            });

            //请求菜单开发状态
            $.ajax({
                type: "GET",
                url: "/manage/menu/getMenuDevStatus",
                dataType: "json",
                success: function (data) {

                    $("#add_menuDevStatusCode", panel).select2({
                        data: data
                    });
                    $("#edit_menuDevStatusCode", panel).select2({
                        data: data
                    });
                }
            });

            //请求菜单类型
            $.ajax({
                type: "GET",
                url: "/manage/menu/getMenuType",
                dataType: "json",
                success: function (data) {

                    $("#add_menuTypeId", panel).select2({
                        data: data
                    });
                    $("#edit_menuTypeId", panel).select2({
                        data: data
                    });
                }
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
            $("#btn_query", panel).click(function () {
                $("#menuTable", panel).bootstrapTable('selectPage', 1);
            });
            $('#addModal', panel).on('shown.bs.modal', function () {
                $("#add_menuStatusCode", panel).val(" ").trigger("change");
                $("#add_menuDevStatusCode", panel).val(" ").trigger("change");
                $("#add_menuTypeId", panel).val(" ").trigger("change");
                $("#add_parentId", panel).val(" ").trigger("change");
                $("#add_belongSiteKind", panel).val(" ").trigger("change");
                $("#add_action", panel).val("").trigger("change");
                $('#addForm', panel).data('bootstrapValidator').resetForm(true);
            });
            $("#clearSearch", panel).click(function () {
                base.reset(".main-box-header");
            });
            $("#editModal", panel).on('hidden.bs.modal', function() {
                $("#editForm", panel).data('bootstrapValidator').destroy();
                $("#editForm", panel).data('bootstrapValidator', null);
            });
        },
        add: function (panel) {
            var self = this;
            //请求菜单parentId
            $.ajax({
                type: "GET",
                url: "/manage/menu/getParent",
                dataType: "json",
                success: function (data) {
                    $("#add_parentId", panel).select2({
                        data: data
                    });
                }
            });
            //请求所属部门信息
            $.ajax({
                type: "GET",
                url: "/manage/menu/getDept",
                dataType: "json",
                success: function (data) {
                    $("#add_belongSiteKind", panel).select2({
                        data: data
                    });
                }
            });
            $('#addModal', panel).modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator(add_validate(panel), "#addForm", self.create,panel)
        },
        create: function (panel) {
            $.post("/manage/menu/add",
                {
                    "menuCtrlPath": $("#add_beLeaf", panel).prop('checked') ? $("#add_menuCtrlPath",panel).val() : "",
                    "menuStatusCode": $("#add_menuStatusCode", panel).val(),
                    "menuDevStatusCode": $("#add_menuDevStatusCode", panel).val(),
                    "caption": $("#add_caption", panel).val(),
                    "beLeaf": $("#add_beLeaf", panel).prop('checked') ? "1" : "0",
                    "hint": $("#add_hint", panel).val(),
                    "parentId": ($("#add_parentId", panel).val()&&$("#add_parentId", panel).val()!=" ")?$("#add_parentId", panel).val():"0",
                    "menuTypeId": $("#add_menuTypeId", panel).val(),
                    "siblingSortNo": $("#add_siblingSortNo", panel).val(),
                    "dllFile": $("#add_dllFile", panel).val(),
                    "picFile": $("#add_picFile", panel).val(),
                    "hotKey": $("#add_hotKey", panel).val(),
                    "beMandatory": $("#add_beMandatory", panel).prop('checked') ? "1" : "0",
                    "belongSiteKind": $("#add_belongSiteKind", panel).val(),
                    "action": (function () {
                        var result = "";
                        var arr = $("#add_action", panel).val();
                        if (arr && arr != "") {
                            for (var i = 0; i < arr.length; i++) {
                                if (i == arr.length - 1) {
                                    result = result + arr[i];
                                } else {
                                    result = result + arr[i] + ",";
                                }
                            }
                        }
                        return result;
                    })()
                }, function (data, status) {
                    if (status == "success") {
                        var obj = data;
                        if (obj.success == 0) {
                            base.success("添加成功！");
                            $("#menuTable",panel).bootstrapTable('refresh');
                            $('#addModal',panel).modal('hide');
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
        },

        //更新
        edit: function (panel) {
            var self = this;
            var arrselections = $("#menuTable", panel).bootstrapTable(
                'getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            //请求菜单parentId
            $.ajax({
                type: "GET",
                url: "/manage/menu/getParent",
                dataType: "json",
                data: {"menuId": arrselections[0].menuId},
                success: function (data) {
                    $("#edit_parentId", panel).select2({
                        data: data
                    });
                    $("#edit_parentId", panel).val((arrselections[0].parentId && arrselections[0].parentId!="0") ? arrselections[0].parentId : " ").trigger("change");
                }
            });
            //请求所属部门信息
            $.ajax({
                type: "GET",
                url: "/manage/menu/getDept",
                dataType: "json",
                success: function (data) {
                    $("#edit_belongSiteKind", panel).select2({
                        data: data
                    });
                    $("#edit_belongSiteKind", panel).val(arrselections[0].belongSiteKind ? arrselections[0].belongSiteKind : " ").trigger("change");
                }
            });
            $('#editModal',panel).modal({
                keyboard: false,
                backdrop: 'static'
            });
            $("#edit_menuCtrlPath", panel).val(arrselections[0].menuCtrlPath);
            $("#edit_menuStatusCode", panel).val(arrselections[0].menuStatusCode).trigger("change");
            $("#edit_menuDevStatusCode", panel).val(arrselections[0].menuDevStatusCode).trigger("change");
            $("#edit_caption", panel).val(arrselections[0].caption);
            $("#edit_beLeaf", panel).prop("checked", arrselections[0].beLeaf == "1" ? true : false);
            $("#edit_beLeaf_no", panel).prop("checked", arrselections[0].beLeaf == "0" ? true : false);
            $("#edit_hint", panel).val(arrselections[0].hint);
            $("#edit_menuTypeId", panel).val(arrselections[0].menuTypeId ? arrselections[0].menuTypeId : " ").trigger("change");
            $("#edit_siblingSortNo", panel).val(arrselections[0].siblingSortNo);
            $("#edit_dllFile", panel).val(arrselections[0].dllFile);
            $("#edit_picFile", panel).val(arrselections[0].picFile);
            $("#edit_hotKey", panel).val(arrselections[0].hotKey);
            $("#edit_dllFile", panel).val(arrselections[0].dllFile);
            $("#edit_action", panel).val((function () {
                var arr = arrselections[0].action;
                if (arr && arr != "") {
                    return arr.split(",");
                }
                return "";
            })()).trigger("change");
            $("#edit_beMandatory", panel).prop("checked", arrselections[0].beMandatory == "1" ? true : false);
            $("#edit_beMandatory_no", panel).prop("checked", arrselections[0].beMandatory == "0" ? true : false);
            $("#menuId", panel).val(arrselections[0].menuId);

            base.validator(edit_validate(panel), '#editForm', self.update,panel)
        },
        update: function (panel) {
            $.post("/manage/menu/update",
                {
                    "menuCtrlPath": $("#edit_beLeaf", panel).prop('checked') ? $("#edit_menuCtrlPath",panel).val() : "",
                    "menuStatusCode": $("#edit_menuStatusCode", panel).val(),
                    "menuDevStatusCode": $("#edit_menuDevStatusCode", panel).val(),
                    "caption": $("#edit_caption", panel).val(),
                    "beLeaf": $("#edit_beLeaf", panel).prop('checked') ? "1" : "0",
                    "hint": $("#edit_hint", panel).val(),
                    "parentId": ($("#edit_parentId", panel).val()&&$("#edit_parentId", panel).val()!=" ")?$("#edit_parentId", panel).val():"0",
                    "menuTypeId": $("#edit_menuTypeId", panel).val(),
                    "siblingSortNo": $("#edit_siblingSortNo", panel).val(),
                    "dllFile": $("#edit_dllFile", panel).val(),
                    "picFile": $("#edit_picFile", panel).val(),
                    "hotKey": $("#edit_hotKey", panel).val(),
                    "beMandatory": $("#edit_beMandatory", panel).prop('checked') ? "1" : "0",
                    "belongSiteKind": $("#edit_belongSiteKind", panel).val(),
                    "action": (function () {
                        var result = "";
                        var arr = $("#edit_action", panel).val();
                        if (arr && arr != "") {
                            for (var i = 0; i < arr.length; i++) {
                                if (i == arr.length - 1) {
                                    result = result + arr[i];
                                } else {
                                    result = result + arr[i] + ",";
                                }
                            }
                        }
                        return result;
                    })(),
                    "menuId": $("#menuId", panel).val()
                }, function (data, status) {
                    if (status == "success") {
                        var obj = data;
                        if (obj.success == 0) {
                            base.success("更新成功！");
                            $("#menuTable", panel).bootstrapTable('refresh');
                            $('#editModal', panel).modal('hide');
                            $('#editForm', panel).data('bootstrapValidator').resetForm(true);
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
        },
        //删除
        remove: function (panel) {
            var arrselections = $("#menuTable", panel).bootstrapTable(
                'getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var menuId = arrselections[0].menuId;

            base.cancel({
                title: "删除",
                text: "您确定要删除此菜单吗？"
            }, function () {
                $.post("/manage/menu/delete", {
                    "menuId": menuId
                }, function (data, status) {
                    if (status == "success") {
                        var obj = data;
                        if (obj.success == 0) {
                            base.success("删除成功！");
                            $("#menuTable", panel).bootstrapTable('refresh');
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("删除失败");
                    }
                });
            });
        }
    };
});