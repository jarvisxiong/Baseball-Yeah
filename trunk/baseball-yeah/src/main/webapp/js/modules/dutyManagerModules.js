define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var validate = {
        fields: {
            rankNo: {
                validators: {
                    notEmpty: {
                        message: '序号不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9]\d*$/,
                        message: '序号格式不正确'
                    },
                    lessThan: {
                        value: 127,
                        inclusive: true,
                        message: '序号不能大于127'
                    }
                }
            },
            dutyName: {
                validators: {
                    notEmpty: {
                        message: '职务名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '职务名称长度必须在1到30位之间'
                    },
                }
            }
        }
    };

    var datatable;

    return {
        init: function (panel) {
            var self = this;
            datatable = base.datagrid({
                url: '/manage/duty/list',
                queryParams: function (params) {
                    return $.extend(params, {timeStamp: (new Date()).valueOf()});
                },
                singleSelect: true,// 单选
                showFooter: false,
                search: true,
                showExport: true,// 显示导出按钮
                exportDataType: "all",// 导出类型
                onPageChange: function (number, size) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#dutyTable', panel).find("thead").height() + $('#dutyTable', panel).find("tbody").height()
                        + 3 + $('#dutyTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#dutyTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#dutyTable').parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#dutyTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1150);
                    }
                    var allSelections =  $('#dutyTable', panel).bootstrapTable("getAllSelections");
                    if (allSelections.length > 0) {
                        for (var shux in allSelections[0]) {
                            var removerCheck = [];
                            for (var i = 0; i < allSelections.length; i++) {
                                removerCheck.push(allSelections[i][shux]);
                            }
                            $('#dutyTable', panel).bootstrapTable("uncheckBy", {field: shux, values: removerCheck});
                        }
                    }
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#dutyTable', panel).find("thead").height() + $('#dutyTable', panel).find("tbody").height()
                        + 9 + $('#dutyTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        if ($('#dutyTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                            tableHeight = 105;
                        }
                        tableHeight += ($('#dutyTable', panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#dutyTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if ((tableHeight + $(".panel-default").height() || 300) > 1350) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1500);
                    }

                },
                onSearch: function (text) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#dutyTable', panel).find("thead").height() + $('#dutyTable', panel).find("tbody").height()
                        + 9 + $('#dutyTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#dutyTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#dutyTable', panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#dutyTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if ((tableHeight + $(".panel-default").height() || 300) > 1350) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1500);
                    }
                },
                pagination: true, // 是否显示分页（*）
                sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
                columns: [{
                    checkbox: true
                }, {
                    field: 'dutyId',
                    title: 'dutyId',
                    visible: false,
                    searchable: false
                }, {
                    field: 'rankNo',
                    title: '序号',
                    sortable: true
                }, {
                    field: 'dutyName',
                    title: '职务名称',
                    sortable: true
                }]
            }, '#dutyTable', panel);

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
            $.post("/manage/duty/add", {
                "rankNo": $("#rankNo", panel).val(),
                "dutyName": $("#dutyName", panel).val()
            }, function (data, status) {
                if (status == "success") {
                    var obj = data;
                    if (obj.success == 0) {
                        $("#dutyTable", panel).bootstrapTable('refresh');
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
            var arrselections = $("#dutyTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑！");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据！");
                return;
            }
            $("#rankNo", panel).val(arrselections[0].rankNo);
            $("#dutyName", panel).val(arrselections[0].dutyName);
            $("#dutyId", panel).val(arrselections[0].dutyId);
            $('#myModalLabel', panel).html("编辑");
            $('#addModal', panel).modal();
            base.validator(validate, '#addForm', self.update, panel);
        },
        update: function (panel) {
            $.post("/manage/duty/update", {
                "rankNo": $("#rankNo", panel).val(),
                "dutyName": $("#dutyName", panel).val(),
                "dutyId": $("#dutyId", panel).val()
            }, function (data, status) {
                if (status == "success") {
                    var obj = data;
                    if (obj.success == 0) {
                        $("#dutyTable", panel).bootstrapTable('refresh');
                        $('#addModal', panel).modal('hide');
                        $('#addForm', panel).data('bootstrapValidator').resetForm(true);
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
            var arrselections = $("#dutyTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var dutyId = arrselections[0].dutyId;
            base.cancel({
                title: "删除职务信息",
                text: "您确定要删除此职务信息吗？"
            }, function () {
                $.post("/manage/duty/delete", {
                    "dutyId": dutyId
                }, function (data, status) {
                    if (status == "success") {
                        var obj = data;
                        if (obj.success == 0) {
                            $("#dutyTable", panel).bootstrapTable('refresh');
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
            $("#rankNo", panel).val('');
            $("#dutyName", panel).val('');
            $("#dutyId", panel).val('');
            $("#addForm", panel).data('bootstrapValidator').destroy();
        }
    };
});