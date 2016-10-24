define(
    ['base'],
    function (base) {
        /**
         * 私有成员定义区域
         */
        return {
            init: function () {
                var self = this;
                base.datagrid(
                    {
                        url: '/manage/goodstype/query',
                        method: 'get',
                        sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                        pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
                        search:true,
                        sortOrder:'desc',
                        sortName:'updateTime',
                        columns: [
                            {
                                checkbox: true
                            },
                            {
                                field: 'name',
                                title: '物品类型名称',
                                sortable: true,
                                width: 150
                            },
                            {
                                field: 'updateTime',
                                title: '更新时间',
                                sortable: true,
                                width: 150
                            },
                            {
                                field: 'description',
                                title: '描述',
                                sortable: true,
                                width: 150
                            }]
                    }, '#goodsTypeTable');
                $("#btn_add").click(function () {
                    self.add();
                });
                $("#btn_edit").click(function () {
                    self.edit();
                });
                $("#btn_delete").click(function () {
                    self.remove();
                });
                $("#btn_query").click(
                    function () {
                        $("#goodsTypeTable").bootstrapTable(
                            'refresh');
                    });
                $("#clearSearch").click(function () {
                    base.reset(".main-box-header");
                });

                $('#addModal').on(
                    'shown.bs.modal',
                    function () {
                        $('#addForm').data('bootstrapValidator')
                            .resetForm(true);
                    })

            },
            add: function () {
                base.reset("#addModal");
                var self = this;
                $('#addModal').modal({
                    keyboard: false,
                    backdrop: 'static'
                });
                base.validator({
                    fields: {
                        add_name: {
                            validators: {
                                notEmpty: {
                                    message: '物品类型名称不能为空'
                                }
                            }
                        }
                    }
                }, "#addForm", self.create)
            },
            create: function () {
                var json = {
                    name: $("#add_name").val(),
                    description: $("#add_description").val()
                }
                $.post("/manage/goodstype/insert", {
                    data: JSON.stringify(json)
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#goodsTypeTable").bootstrapTable(
                                'refresh');
                            $('#addModal').modal('hide');
                            base.success("添加成功！")
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
                var arrselections = $("#goodsTypeTable")
                    .bootstrapTable('getSelections');
                if (arrselections.length > 1) {
                    base.error("只能选择一行进行编辑!");
                    return;
                }
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                $("#edit_name").val(
                    arrselections[0].name);
                $("#edit_description").val(arrselections[0].description);
                $("#goodsTypeId").val(
                    arrselections[0].goodsTypeId);
                $('#editModal').modal({
                    keyboard: false,
                    backdrop: 'static'
                });
                base.validator({
                    fields: {
                        add_name: {
                            validators: {
                                notEmpty: {
                                    message: '物品类型名称不能为空'
                                }
                            }
                        }
                    }
                }, "#editForm", self.update)
            },
            update: function () {
                var json = {
                    name: $("#edit_name").val(),
                    description: $("#edit_description").val(),
                    goodsTypeId:$("#goodsTypeId").val()
                }

                $.post("/manage/goodstype/update", {
                    data: JSON.stringify(json)
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#goodsTypeTable").bootstrapTable(
                                'refresh');
                            $('#editModal').modal('hide');
                            $('#editForm').data('bootstrapValidator')
                                .resetForm(true);
                            base.success("更新成功！")
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
            },
            remove: function () {
                var arrselections = $("#goodsTypeTable")
                    .bootstrapTable('getSelections');

                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }

                base
                    .cancel(
                        {
                            title: "删除操作",
                            text: "您确定要删除该项吗？"
                        },
                        function () {
                            $
                                .post(
                                    "/manage/goodstype/delete",
                                    {
                                        "goodsTypeId": arrselections[0].goodsTypeId
                                    },
                                    function (data, status) {
                                        if (status == "success") {
                                            if (data.success == 0) {
                                                $(
                                                    "#goodsTypeTable")
                                                    .bootstrapTable(
                                                        'refresh');
                                                base
                                                    .success("删除成功！")
                                            } else {
                                                base
                                                    .error(data.message);
                                            }
                                        } else {
                                            base
                                                .error("删除失败");
                                        }
                                    });
                        });
            }
        };
    });