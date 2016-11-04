define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var validate = {
        fields: {
            deptCode: {
                validators: {
                    notEmpty: {
                        message: '部门编码不能为空'
                    },
                    stringLength : {
                        min : 0,
                        max : 10,
                        message : '部门编码不能超过10'
                    }
                }
            },
            deptName: {
                validators: {
                    notEmpty: {
                        message: '部门名称不能为空'
                    },
                    stringLength : {
                        min : 0,
                        max : 30,
                        message : '部门名称不能超过30'
                    }
                }
            }
        }
    };

    var datatable;

    return {
        init: function (panel) {
            var self = this;
            datatable = base.datagrid({
                url: '/manage/dept/list',
                singleSelect: true,//单选
                showFooter: false,
                search: true,
                showExport: true,// 显示导出按钮
                exportDataType: "all",// 导出类型
                pagination: true, // 是否显示分页（*）
                sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
                onPageChange: function (number, size) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#deptTable', panel).find("thead").height() + $('#deptTable', panel).find("tbody").height()
                        + 9 + $('#deptTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#deptTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#deptTable', panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#deptTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1150);
                    }
                    var allSelections =  $('#deptTable', panel).bootstrapTable("getAllSelections");
                    if (allSelections.length > 0) {
                        for (var shux in allSelections[0]) {
                            var removerCheck = [];
                            for (var i = 0; i < allSelections.length; i++) {
                                removerCheck.push(allSelections[i][shux]);
                            }
                            $('#deptTable', panel).bootstrapTable("uncheckBy", {field: shux, values: removerCheck});
                        }
                    }
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#deptTable', panel).find("thead").height() + $('#deptTable', panel).find("tbody").height()
                        + 9 + $('#deptTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        if ($('#deptTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                            tableHeight = 105;
                        }
                        tableHeight += ($('#deptTable', panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#deptTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if ((tableHeight + $(".panel-default").height() || 300) > 1350) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1500);
                    }
                },
                onSearch: function (text) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#deptTable', panel).find("thead").height() + $('#deptTable', panel).find("tbody").height()
                        + 9 + $('#deptTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#deptTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#deptTable', panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#deptTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if ((tableHeight + $(".panel-default").height() || 300) > 1350) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1500);
                    }
                },
                queryParams: function (params) {
                    return $.extend(params, {timeStamp: (new Date()).valueOf()});
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'deptId',
                    title: 'deptId',
                    visible: false,
                    searchable: false
                }, {
                    field: 'deptCode',
                    title: '部门编码',
                    sortable: true
                }, {
                    field: 'deptName',
                    title: '部门名称',
                    sortable: true
                }]
            }, $('#deptTable', panel), panel);

            $("#btn_add", panel).click(function () {
                self.add(panel);
            });
            $("#btn_edit", panel).click(function () {
                self.edit(panel);
            });
            $("#btn_delete", panel).click(function () {
                self.remove(panel);
            });
            $('#addModal', panel).on('hidden.bs.modal', function (e) {
                self.hiden(panel);
            });
        },
        add: function (panel) {
            var self = this;
            $('#myModalLabel', panel).html("新增");
            $('#addModal', panel).modal();
            base.validator(validate, "#addForm", self.create, panel);
        },
        create: function (panel) {
            $.post("/manage/dept/add", {
                "deptCode": $("#deptCode", panel).val(),
                "deptName": $("#deptName", panel).val()
            }, function (data, status) {
                if (status == "success") {
                    var obj = data;
                    if (obj.success == 0) {
                        $("#deptTable", panel).bootstrapTable('refresh');
                        $('#addModal', panel).modal('hide');
                        $('#addForm', panel).data('bootstrapValidator').resetForm(true);
                        base.success("添加成功！");
                    } else {
                        base.error(obj.message);
                    }
                } else {
                    base.error("数据加载失败!");
                }
            });
        },

        // 更新
        edit: function (panel) {
            var self = this;
            var arrselections = $("#deptTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $("#deptCode", panel).val(arrselections[0].deptCode);
            $("#deptName", panel).val(arrselections[0].deptName);
            $("#deptId", panel).val(arrselections[0].deptId);
            $('#myModalLabel', panel).html("编辑");
            $('#addModal', panel).modal();
            base.validator(validate, '#addForm', self.update, panel);
        },
        update: function (panel) {
            $.post("/manage/dept/update", {
                "deptCode": $("#deptCode", panel).val(),
                "deptName": $("#deptName", panel).val(),
                "deptId": $("#deptId", panel).val()
            }, function (data, status) {
                if (status == "success") {
                    var obj = data;
                    if (obj.success == 0) {
                        $("#deptTable", panel).bootstrapTable('refresh');
                        $('#addModal', panel).modal('hide');
                        // $('#addModal', panel).data('bootstrapValidator').resetForm(true);
                        base.success("更新成功！");
                    } else {
                        base.error(obj.message);
                    }
                } else {
                    base.error("更新失败!");
                }
            });
        },
        // 删除
        remove: function (panel) {
            var arrselections = $("#deptTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var deptId = arrselections[0].deptId;
            base.cancel({
                title: "删除部门信息",
                text: "您确定要删除此部门信息吗？"
            }, function () {
                $.post("/manage/dept/delete", {
                    "deptId": deptId
                }, function (data, status) {
                    if (status == "success") {
                        var obj = data;
                        if (obj.success == 0) {
                            $("#deptTable", panel).bootstrapTable('refresh');
                            base.success("删除成功！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("删除失败");
                    }
                });
            });
        },
        // model关闭
        hiden: function (panel) {
            $("#deptCode", panel).val('');
            $("#deptName", panel).val('');
            $("#deptId", panel).val('');
            $("#addForm", panel).data('bootstrapValidator').destroy();
        }
    };
});